package ru.levelp.model.entities;

/**
 * Created by Tanya on 09.12.2016.
 */
public class RequestContainer<T> extends BaseRequest {
    private T payload;

    public T getPayload() {
        return payload;
    }
}
