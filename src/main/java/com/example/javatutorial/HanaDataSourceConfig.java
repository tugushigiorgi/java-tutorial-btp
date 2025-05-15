package com.example.javatutorial;
import static com.example.javatutorial.ConstControllerMessages.VCAP_NOT_FOUND;
import static com.example.javatutorial.ConstData.CREDENTIALS;
import static com.example.javatutorial.ConstData.HANA;
import static com.example.javatutorial.ConstData.HANA_DB_URL;
import static com.example.javatutorial.ConstData.HANA_HDI_USER;
import static com.example.javatutorial.ConstData.HANA_HDI_USER_PASSWORD;
import static com.example.javatutorial.ConstData.SAP_JDBC_DRIVER_NAME;
import static com.example.javatutorial.ConstData.VCAP_SERVICES;
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
    var vcapServices = System.getenv(VCAP_SERVICES);
    if (vcapServices == null) {
      throw new IllegalStateException(VCAP_NOT_FOUND);
    }
    var mapper = new ObjectMapper();
    var root = mapper.readTree(vcapServices);
    var hanaNode = root.path(HANA).get(0).path(CREDENTIALS);
    var url = hanaNode.get(HANA_DB_URL).asText();
    var username = hanaNode.get(HANA_HDI_USER).asText();
    var password = hanaNode.get(HANA_HDI_USER_PASSWORD).asText();
    HikariDataSource dataSource = new HikariDataSource();
    dataSource.setSchema(username);
    dataSource.setJdbcUrl(url);
    dataSource.setUsername(username);
    dataSource.setPassword(password);
    dataSource.setDriverClassName(SAP_JDBC_DRIVER_NAME);
    return dataSource;
  }
}
