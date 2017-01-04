package ru.levelp;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * Created by Tanya on 16.12.2016.
 */
@Configuration
@ComponentScan(basePackages = {"ru.levelp"})
public class BeanConfig {

    @Bean
    public BufferedReader console() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }

    @Bean
    public URI wsUri() {
        try {
            return new URI("ws:/")
        }
    }
}
