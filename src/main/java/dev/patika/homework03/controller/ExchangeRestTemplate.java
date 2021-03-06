package dev.patika.homework03.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class ExchangeRestTemplate {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/exchange")
    public ResponseEntity<Double> getExchange() {

        ResponseEntity<Double> dollarEquivalent = restTemplate.getForEntity("http://localhost:8081/api/exchange/2000", Double.class);

        return dollarEquivalent;
    }


}
