package com.meri.fraudcheck.service;

import com.meri.fraudcheck.FraudHistoryRepository;
import com.meri.fraudcheck.entity.FraudCheck;
import com.meri.fraudcheck.message.MessageSender;
import com.meri.fraudcheck.message.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckService {

    private final FraudHistoryRepository fraudHistoryRepository;
    private final MessageService messageService;

    public boolean isFraudulentPerson(Long personId) {
        fraudHistoryRepository.save(
                FraudCheck.builder()
                        .personId(Math.toIntExact(personId))
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        messageService.sendFraudCheckNotification("Fraud check has been processed for the person with id: " + personId);
        return false;
    }
}
