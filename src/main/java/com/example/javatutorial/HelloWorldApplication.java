package com.example.javatutorial;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class HelloWorldApplication {
  static DataSource dataSource;

  public static void main(String[] args) {
    SpringApplication.run(HelloWorldApplication.class, args);

  }
}
