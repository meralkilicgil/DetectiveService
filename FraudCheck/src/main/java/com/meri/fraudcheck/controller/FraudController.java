package com.meri.fraudcheck.controller;

import com.meri.fraudcheck.dto.FraudCheckDto;
import com.meri.fraudcheck.service.FraudCheckService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping(path = "{personId}")
    public void reportFraudster(@PathVariable("personId") Long personId){
        log.info("fraud check request for person {}", personId);
        fraudCheckService.reportFraudulent(personId);
    }

    @DeleteMapping(path = "{id}")
    public void deleteFraudster(@PathVariable("id") Integer fraudId){
        fraudCheckService.deleteFraudulent(fraudId);
    }

    @GetMapping("/")
    public List<FraudCheckDto> getAllFrauds(){
        return fraudCheckService.getAllFrauds();
    }

    @DeleteMapping("/")
    public void deleteAllFrauds(){
        fraudCheckService.deleteAllFrauds();
    }
}
