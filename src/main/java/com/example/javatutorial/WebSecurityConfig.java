package com.example.javatutorial;

import com.sap.cloud.security.xsuaa.XsuaaServiceConfiguration;
import com.sap.cloud.security.xsuaa.token.TokenAuthenticationConverter;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.SecurityFilterChain;

@Configuration

@AllArgsConstructor
@Slf4j
public class WebSecurityConfig {


  private final XsuaaServiceConfiguration xsuaaServiceConfiguration;


  @SuppressWarnings({"removal", "deprecation"})
  @Bean
  @Profile("prod")
  public SecurityFilterChain filterChain(HttpSecurity http, Environment env) throws Exception {
    http
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .requestMatchers("/**").authenticated()
        .anyRequest().denyAll()
        .and()
        .oauth2ResourceServer().jwt()
        .jwtAuthenticationConverter(getJwtAuthoritiesConverter());
    return http.build();

  }

  @Bean
  @Profile("local")
  @SuppressWarnings({"removal", "deprecation"})
  public SecurityFilterChain localFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeRequests()
        .anyRequest().permitAll();
    return http.build();
  }

  /**
   * Customizes how GrantedAuthority are derived from a Jwt
   *
   * @returns jwt converter
   */
  Converter<Jwt, AbstractAuthenticationToken> getJwtAuthoritiesConverter() {
    TokenAuthenticationConverter converter = new TokenAuthenticationConverter(xsuaaServiceConfiguration);
    converter.setLocalScopeAsAuthorities(true);
    return converter;
  }
}