package ru.levelp.model;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.URI;

/**
 * Created by Tanya on 16.12.2016.
 */
@Component("wsClient")
public class WSClient extends WebSocketClient {

    @Autowired
    public WSClient(URI wsURI) {
        //ws://localhost
        super(wsURI);
    }
    @PostConstruct
    public void onCreaten() {
        connect();
    }

    @PreDestroy
    public void onDestroyed() {
        close();
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {

    }

    @Override
    public void onMessage(String s) {

    }

    @Override
    public void onClose(int i, String s, boolean b) {

    }

    @Override
    public void onError(Exception e) {

    }
}
