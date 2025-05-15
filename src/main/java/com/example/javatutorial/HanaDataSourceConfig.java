package com.example.javatutorial;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("prod")
@Configuration
public class HanaDataSourceConfig {


  @Bean
  @Primary
  public DataSource hanaDataSource() throws IOException, SQLException {
    var vcapServices = System.getenv("VCAP_SERVICES");
    if (vcapServices == null) {
      throw new IllegalStateException("VCAP_SERVICES not found.");
    }
    var mapper = new ObjectMapper();
    var root = mapper.readTree(vcapServices);
    var hanaNode = root.path("hana").get(0).path("credentials");
    var url = hanaNode.get("url").asText();
    var username = hanaNode.get("hdi_user").asText();
    var password = hanaNode.get("hdi_password").asText();
    HikariDataSource dataSource = new HikariDataSource();
    dataSource.setJdbcUrl(url);
    dataSource.setUsername(username);
    dataSource.setPassword(password);
    dataSource.setDriverClassName("com.sap.db.jdbc.Driver");


    return dataSource;
  }

}
