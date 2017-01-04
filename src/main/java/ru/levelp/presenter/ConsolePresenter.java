package ru.levelp.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levelp.model.Api;
import ru.levelp.model.Callback;
import ru.levelp.view.View;

/**
 * Created by Tanya on 16.12.2016.
 */
@Component("presenter")
public class ConsolePresenter implements Presenter {
    private View view;
    private Api api;

    @Autowired
    @Override
    public void setView(View view) {
        this.view = view;
        authorize();
        //вступительное меню автор регистр вых
    }

    @Autowired
    public void setApi(Api api) {
        this.api = api;
    }

    private void authorize(){
        //TODO: спрашиваем авторизацию или регистрацию. С промощью метода read() просим у view данные пользователя
        String answer = view.read("1 - Autorization\n" +
                 "2 - Registration\n" +
                "3 - Quit");
        // if (answer...) {} получение email pwd, проверка что такие существуют

        String email = "... ";
        String pwd = "... ";//сделать хэширование pwd = sha5(pwd)

        api.authorize(email, pwd,
                new Callback<>());
    }
}
