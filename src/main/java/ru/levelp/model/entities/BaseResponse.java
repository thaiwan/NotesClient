package ru.levelp.model.entities;

/**
 * Created by Tanya on 16.12.2016.
 */
public class BaseResponse {
    private String requestId;
    private int code;

    public String getRequestId() {
        return requestId;
    }

    public int getCode() {
        return code;
    }
}
