package com.example.javatutorial;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
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
  public DataSource hanaDataSource() throws IOException {
    String vcapServices = System.getenv("VCAP_SERVICES");
    if (vcapServices == null) {
      throw new IllegalStateException("VCAP_SERVICES not found.");
    }
    ObjectMapper mapper = new ObjectMapper();
    JsonNode root = mapper.readTree(vcapServices);
    JsonNode hanaNode = root.path("hana").get(0).path("credentials");
    String url = hanaNode.get("url").asText();
    String username = hanaNode.get("user").asText();
    String password = hanaNode.get("password").asText();
    HikariDataSource dataSource = new HikariDataSource();
    dataSource.setJdbcUrl(url);
    dataSource.setUsername(username);
    dataSource.setPassword(password);
    dataSource.setDriverClassName("com.sap.db.jdbc.Driver");
    return dataSource;
  }
}
