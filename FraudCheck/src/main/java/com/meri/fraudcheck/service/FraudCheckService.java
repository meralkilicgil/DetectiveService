package com.meri.fraudcheck.service;

import com.meri.fraudcheck.FraudHistoryRepository;
import com.meri.fraudcheck.entity.FraudCheck;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckService {

    private final FraudHistoryRepository fraudHistoryRepository;

    public boolean isFraudulentPerson(Long personId) {
        fraudHistoryRepository.save(
                FraudCheck.builder()
                        .personId(Math.toIntExact(personId))
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }
}
