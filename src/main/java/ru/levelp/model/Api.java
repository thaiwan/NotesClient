package ru.levelp.model;

/**
 * Created by Tanya on 16.12.2016.
 */
public interface Api {
    void authorize(String email, String pwdHash);//перечисляем все методы, которые можно вызвать у сервера
    void registration(String name, String email, String pwdHash);
    void getUsers();//не возвращается, потому что выполнение переходит в фоновый поток
}
