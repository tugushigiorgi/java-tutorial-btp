package com.example.java_tutorial;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.sap.cloud.security.xsuaa.token.Token;

@RestController
@RequestMapping(path = "")
@Slf4j
public class MainController {

  @GetMapping(path = "")
  public ResponseEntity<String> readAll(@AuthenticationPrincipal Token token) {
    if (!token.getAuthorities().contains(new SimpleGrantedAuthority("Display"))) {
      log.error("This operation requires \"Display\" scope");



      throw new NotAuthorizedException("This operation requires \"Display\" scope");
    }
    log.info("hello world   ");

    return new ResponseEntity<String>("Hello World!", HttpStatus.OK);
  }
}