package br.com.noeleduk.noelproject.controllers.healthcheck;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healthcheck")
public class HealthcheckController {
  @GetMapping("/")
  public String healthcheck(){
    return "OK";
  }
}
