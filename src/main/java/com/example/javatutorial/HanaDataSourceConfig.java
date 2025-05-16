package com.example.javatutorial;

import static com.example.javatutorial.ConstData.SAP_JDBC_DRIVER_NAME;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("prod")
@Configuration
public class HanaDataSourceConfig {
  @Value("${hdi-username}")
  private String userName;

  @Value("${hdi-password}")
  private String password;

  @Value("${hdi-url}")
  private String url;

  @Bean
  @Primary
  public DataSource hanaDataSource() {
    HikariDataSource dataSource = new HikariDataSource();
    dataSource.setSchema(userName);
    dataSource.setJdbcUrl(url);
    dataSource.setUsername(userName);
    dataSource.setPassword(password);
    dataSource.setDriverClassName(SAP_JDBC_DRIVER_NAME);
    return dataSource;
  }
}
