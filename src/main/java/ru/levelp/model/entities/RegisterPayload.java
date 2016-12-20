package ru.levelp.model.entities;

/**
 * Created by Tanya on 15.12.2016.
 */
public class RegisterPayload {

    private String email;
    private String pwdHash;
    private String name;

    public RegisterPayload(String email, String pwdHash, String name) {
        this.email = email;
        this.pwdHash = pwdHash;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getPwdHash() {
        return pwdHash;
    }

    public String getName() {
        return name;
    }
}
