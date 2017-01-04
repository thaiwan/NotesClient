package ru.levelp.model.entities;

import java.util.UUID;

/**
 * Created by Tanya on 09.12.2016.
 */
public class RequestContainer<T> extends BaseRequest {
    private T payload;


    public RequestContainer(String method, T payload) {
        this.requestId = UUID.randomUUID().toString();
        this.method = method;
        this.payload = payload;
    }

}
