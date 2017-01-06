package ru.levelp.model;

import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.levelp.model.entities.User;
import ru.levelp.model.entities.AuthPayload;
import ru.levelp.model.entities.RegisterPayload;
import ru.levelp.model.entities.ResponseContainer;

import java.util.List;

/**
 * Created by Tanya on 16.12.2016.
 */
@Service("api")
public class ClientApi implements Api {
    private WSClient wsClient;
    private String token;

    @Autowired
    public ClientApi(WSClient wsClient) {
        this.wsClient = wsClient;
    }

    public void authorize(String email, String pwdHash, Callback<String> onSuccess, Callback<String> onError) {
        AuthPayload payload = new AuthPayload(email, pwdHash);
        wsClient.sendRequest("authorize", payload, null,
                new TypeToken<ResponseContainer<String>>() {
                }.getType(), new Callback<String>() {
                    @Override
                    public void call(String result) {
                        token = result;
                        onSuccess.call(result);
                    }
                }, onError);
    }

    public void registration(String name, String email, String pwdHash, Callback<String> onSuccess, Callback<String> onError) {
        RegisterPayload payload = new RegisterPayload(email, pwdHash, name);
        wsClient.sendRequest("registration", payload, null,
                new TypeToken<ResponseContainer<String>>() {
                }.getType(), onSuccess, onError);
    }

    public void getUsers(Callback<List<User>> onSuccess, Callback<String> onError) {
        wsClient.sendRequest("getUsers", null, token,
                new TypeToken<ResponseContainer<String>>() {
                }.getType(), onSuccess, onError);
    }
}
