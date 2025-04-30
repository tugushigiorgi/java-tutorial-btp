package com.example.javatutorial;

import static com.example.javatutorial.ConstData.DEST_NAME;
import static com.example.javatutorial.ConstData.REL_URL;

import com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpClientAccessor;
import com.sap.cloud.security.xsuaa.token.Token;
import io.micrometer.core.instrument.util.IOUtils;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpGet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MainController {

  @GetMapping
  public ResponseEntity<String> readAll(@AuthenticationPrincipal Token token) {
    if (!token.getAuthorities().contains(new SimpleGrantedAuthority("Display"))) {
      log.error("This operation requires \"Display\" scope");
      throw new NotAuthorizedException("This operation requires \"Display\" scope");
    }

    log.info("hello world");
    return new ResponseEntity<String>("Hello World!", HttpStatus.OK);
  }

  @GetMapping("/call-second")
  public String callSecondApp() throws Exception {
    log.info("Entered in call-second route");
    var destination = DestinationAccessor.getLoader()
        .tryGetDestination(DEST_NAME)
        .get()
        .asHttp();

    var client = HttpClientAccessor.getHttpClient(destination);
    var httpGet = new HttpGet(REL_URL);
    var httpResponse = client.execute(httpGet);
    var responseString = IOUtils.toString(httpResponse.getEntity().getContent(), StandardCharsets.UTF_8);

    log.info(responseString);
    return responseString;
  }
}