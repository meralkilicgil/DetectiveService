package com.meri.murdermysterygame.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value="fraudChecker", url = "http://localhost:8081/api/fraud-check")
public interface FraudCheckFein {

    @GetMapping(path = "{personId}")
    Boolean isFraudster(@PathVariable Long personId);
}
