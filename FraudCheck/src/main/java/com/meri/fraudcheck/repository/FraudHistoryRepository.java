package com.meri.fraudcheck.repository;

import com.meri.fraudcheck.entity.FraudCheck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FraudHistoryRepository extends JpaRepository<FraudCheck, Integer> {
}
