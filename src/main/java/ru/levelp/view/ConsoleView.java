package ru.levelp.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Tanya on 16.12.2016.
 */
@Component("view")
public class ConsoleView implements View {
    private BufferedReader console;

    @Autowired
    public ConsoleView(BufferedReader concole) {
        this.console = concole;
    }

    @Override
    public void showMessage(String text) {
        System.out.println(text);
    }

    @Override
    public void showLoading() {
        System.out.println("Please, wait... ");
    }

    @Override
    public void showLoadingDone() {
        System.out.println("Done");
    }

    @Override
    public void showLoadingError(String reason) {
        System.out.println("Error " + reason);
    }

    @Override
    public String read(String message) {
        System.out.println(message);
        String input = null;
        try {
            input = console.readLine();
        } catch (IOException ignored){
        }

        return input;
    }
}
