package com.meri.fraudcheck.dto;

import com.meri.fraudcheck.entity.FraudCheck;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record FraudCheckDto(Integer id, Long personId, Boolean isFraudster, LocalDateTime createdAt) {
    public static FraudCheckDto fromEntity(FraudCheck fraudCheck) {
        return FraudCheckDto.builder()
                .id(fraudCheck.getId())
                .personId(fraudCheck.getPersonId())
                .isFraudster(fraudCheck.getIsFraudster())
                .createdAt(fraudCheck.getCreatedAt())
                .build();
    }
}
