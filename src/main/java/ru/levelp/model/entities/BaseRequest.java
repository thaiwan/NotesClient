package ru.levelp.model.entities;

/**
 * Created by Tanya on 09.12.2016.
 */
public class BaseRequest {
    protected String requestId;
    protected String method;
    protected String token;

    public String getRequestId() {
        return requestId;
    }

    public String getMethod() {
        return method;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
