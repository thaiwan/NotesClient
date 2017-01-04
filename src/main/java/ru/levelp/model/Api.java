package ru.levelp.model;

import ru.levelp.model.entities.User;

import java.util.List;

/**
 * Created by Tanya on 16.12.2016.
 */
public interface Api {
    //TODO: add all methods
    void authorize(String email, String pwdHash, Callback<String> onSuccess, Callback<String> onError);
    void registration(String name, String email, String pwdHash,  Callback<String> onSuccess, Callback<String> onError);
    void getUsers(Callback<List<User>> onSuccess, Callback<String> onError);
}
