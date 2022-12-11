package com.news;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.news.commons.DBInfo;
import com.news.commons.InitializationDB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApplication {
    public static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting().create();

    public static void main(String[] args) {
        DBInfo CREDS = new DBInfo();

        InitializationDB initializer = new InitializationDB(CREDS);
        initializer.initDB();

        SpringApplication.run(WebApplication.class, args);
    }
}
