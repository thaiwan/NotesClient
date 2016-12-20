package ru.levelp.view;

/**
 * Created by Tanya on 16.12.2016.
 */
public interface View {

    void showMessage(String text);
    void showLoading();
    void showLoadingDone();
    void showLoadingError(String reason);

    String read(String message);
}
