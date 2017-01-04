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

    public void showMessage(String text) {
        System.out.println(text);
    }

    public void showLoading() {
        System.out.println("Please, wait... ");
    }

    public void showLoadingDone() {
        System.out.println("Done");
    }

    public void showLoadingError(String reason) {
        System.out.println("Error " + reason);
    }

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
