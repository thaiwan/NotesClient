package ru.levelp.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levelp.model.entities.BaseResponse;
import ru.levelp.model.entities.RequestContainer;
import ru.levelp.model.entities.ResponseContainer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component("wsClient")
public class WSClient extends WebSocketClient {
    private Gson gson;
    private HashMap<String, ResponseProcessingData> requests = new HashMap<>();
    private ExecutorService executor;
    private Integer poolSize;

    @Autowired
    public WSClient(URI wsUri, Gson gson, Integer poolSize) {
        super(wsUri);
        this.gson = gson;
        this.poolSize = poolSize;
    }

    @PostConstruct
    public void onCreated() {
        executor = Executors.newFixedThreadPool(poolSize);
        connect();
    }

    @PreDestroy
    public void onDestroyed() {
        close();
        executor.shutdown();
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Connected");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("server: " + message);
        BaseResponse baseResponse = gson.fromJson(message, BaseResponse.class);
        synchronized (requests) {
            if (requests.containsKey(baseResponse.getRequestId())) {
                ResponseProcessingData processingData = requests.get(baseResponse.getRequestId());
                if (baseResponse.getCode() == 200) {
                    //success
                    ResponseContainer response = gson.fromJson(message, processingData.getResultType());
                    processingData.getOnSuccess().call(response.getPayload());
                } else {
                    //error
                    ResponseContainer<String> response = gson.fromJson(message,
                            new TypeToken<ResponseContainer<String>>() {
                            }.getType());
                    processingData.getOnError().call(response.getPayload());
                }
                requests.remove(baseResponse.getRequestId());
            }
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Closed: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    public void sendRequest(String method, Object payload, String token, Type type,
                            Callback onSuccess, Callback onError) {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                RequestContainer request = new RequestContainer(method, payload);
                request.setToken(token);
                requests.put(request.getRequestId(), new ResponseProcessingData(type, onSuccess, onError));
                send(gson.toJson(request));
                Timer timer = new Timer();
                ScheduledTask waiter = new ScheduledTask(requests, request.getRequestId(), 50, onError);
                timer.schedule(waiter, 0, 100);
            }
        });
    }

    private static class ScheduledTask extends TimerTask {
        HashMap<String, ResponseProcessingData> requests;
        String requestId;
        int repeatCount;
        Callback onError;

        public ScheduledTask(HashMap<String, ResponseProcessingData> requests,
                             String requestId,
                             int repeatCount,
                             Callback onError) {
            this.requests = requests;
            this.requestId = requestId;
            this.repeatCount = repeatCount;
            this.onError = onError;
        }

        @Override
        public void run() {
            if (!requests.containsKey(requestId)) {
                cancel();
                return;
            }
            repeatCount--;
            if (repeatCount == 0) {
                synchronized (requests) {
                    if (requests.containsKey(requestId)) {
                        requests.remove(requestId);
                        onError.call("TIMEOUT_ERROR");
                    }
                }
                cancel();
            }
        }
    }
}