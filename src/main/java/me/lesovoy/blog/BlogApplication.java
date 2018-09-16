package me.lesovoy.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



@SpringBootApplication
public class BlogApplication {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:~/posts";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";


    public static void main(String[] args) {

		SpringApplication.run(BlogApplication.class, args);


	}

}




