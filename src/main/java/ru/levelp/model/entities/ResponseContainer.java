package ru.levelp.model.entities;

import com.google.gson.annotations.Expose;

/**
 * Created by Tanya on 09.12.2016.
 */
public class ResponseContainer<T> extends BaseResponse {

    private T payload;

    public T getPayload() {
        return payload;
    }
}
