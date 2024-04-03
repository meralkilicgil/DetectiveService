package com.meri.fraudcheck.service;

import com.meri.fraudcheck.repository.FraudHistoryRepository;
import com.meri.fraudcheck.dto.FraudCheckDto;
import com.meri.fraudcheck.entity.FraudCheck;
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
        LocalDateTime checkDate = LocalDateTime.now();
        FraudCheck fraudCheck = fraudHistoryRepository.save(
                FraudCheck.builder()
                        .personId(personId)
                        .isFraudster(false)
                        .createdAt(checkDate)
                        .build()
        );
        FraudCheckDto fraudCheckDto = FraudCheckDto.builder()
                                                    .id(fraudCheck.getId())
                                                    .personId(fraudCheck.getPersonId())
                                                    .isFraudster(fraudCheck.getIsFraudster())
                                                    .createdAt(fraudCheck.getCreatedAt())
                                                    .build();
        messageService.sendFraudCheckNotification("Fraud check has been processed for the person with id: " + personId + " and result is: " + fraudCheckDto);
        return false;
    }
}
