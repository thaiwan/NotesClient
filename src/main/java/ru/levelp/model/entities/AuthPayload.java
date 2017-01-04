package ru.levelp.model.entities;

/**
 * Created by Tanya on 09.12.2016.
 */
public class AuthPayload {
    private String email;
    private String pwdHash;

    public AuthPayload(String email, String pwdHash) {
        this.email = email;
        this.pwdHash = pwdHash;
    }

}
