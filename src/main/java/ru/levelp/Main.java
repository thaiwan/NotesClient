package ru.levelp;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Tanya on 16.12.2016.
 */
public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {

            try {

                int finalI = i;

                WebSocketClient client = new WebSocketClient(new  URI("ws://localhost:8080")) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                System.out.println("connected");

                for (int j = 0; j < 10 ; j++) {
                    send("client " + finalI +" message " + j);
                }
            }

            @Override
            public void onMessage(String s) {
                System.out.println("server " + s);
            }

            @Override
            public void onClose(int i, String s, boolean b) {

            }

            @Override
            public void onError(Exception e) {

            }
        };
        client.connect();
    } catch (URISyntaxException e) {
        e.printStackTrace();
        }
        }
    }
}

