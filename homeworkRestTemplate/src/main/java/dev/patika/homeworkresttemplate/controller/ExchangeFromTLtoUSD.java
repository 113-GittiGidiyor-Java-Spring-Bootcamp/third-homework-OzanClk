package dev.patika.homeworkresttemplate.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ExchangeFromTLtoUSD {

    @GetMapping("/exchange/{TL}")
    public ResponseEntity<Double> getUSDAmount(@PathVariable long TL) {

        Double result = (TL / 8.5);

        return new ResponseEntity<Double>(result, HttpStatus.OK);
    }


}
