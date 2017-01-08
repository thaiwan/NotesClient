package ru.levelp;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.*;

/**
 * Created by Tanya on 16.12.2016.
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(BeanConfig.class);
    }
}

