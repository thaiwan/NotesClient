package ru.levelp.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.levelp.model.entities.AuthPayload;
import ru.levelp.model.entities.RegisterPayload;
import ru.levelp.presenter.Presenter;

/**
 * Created by Tanya on 16.12.2016.
 */
@Service("api")
public class ClientApi implements Api {
    private Presenter presenter;

    @Autowired
    public ClientApi(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void authorize(String email, String pwdHash) {
        AuthPayload payload = new AuthPayload(email, pwdHash);
        wsClient.sendRequest(payload);
    }

    @Override
    public void registration(String name, String email, String pwdHash) {
        RegisterPayload payload = new RegisterPayload(name, email, pwdHash);
        wsClient.sendRequest(payload);
    }

    @Override
    public void getUsers() {

    }
}
