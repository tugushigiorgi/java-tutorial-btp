package com.example.javatutorial;

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
