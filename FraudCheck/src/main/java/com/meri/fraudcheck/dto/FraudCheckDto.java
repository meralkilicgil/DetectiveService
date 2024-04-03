package com.meri.fraudcheck.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record FraudCheckDto(Integer id, Long personId, Boolean isFraudster, LocalDateTime createdAt) {
}
