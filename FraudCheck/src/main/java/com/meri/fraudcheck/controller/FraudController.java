package com.meri.fraudcheck.controller;

import com.meri.fraudcheck.service.FraudCheckService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fraud-check")
@AllArgsConstructor
@Slf4j
public class FraudController {

    private final FraudCheckService fraudCheckService;

    @GetMapping(path = "{personId}")
    public Boolean isFraudster(@PathVariable("personId") Long personId){
        log.info("fraud check request for person {}", personId);
        return fraudCheckService.isFraudulentPerson(personId);
    }
}
