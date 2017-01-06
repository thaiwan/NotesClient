package ru.levelp;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.*;

/**
 * Created by Tanya on 16.12.2016.
 */
public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            long mills = (long) (Math.random() * 1000 + 1000);
            Future<String> task = executor.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    try {
                        Thread.sleep(mills);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "Some operation " + finalI + " time: " + mills + " in thread: " + Thread.currentThread().getName();
                }
            });
            try {
                String result = task.get(1500, TimeUnit.MILLISECONDS);
                System.out.println(result);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                System.out.println("Operation " + finalI + " timeout " + mills);
            }
        }
//        List<Runnable> list = executor.shutdownNow();
//        System.out.println("Not executed: " + list.size());
        executor.shutdown();
    }
}

