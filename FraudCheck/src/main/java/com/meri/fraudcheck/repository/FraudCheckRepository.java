package com.meri.fraudcheck.repository;

import com.meri.fraudcheck.entity.FraudCheck;
import com.meri.murdermysterygame.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface FraudCheckRepository extends JpaRepository<FraudCheck, Integer> {

    List<Optional<FraudCheck>> findByPersonId(Long personId);
}
