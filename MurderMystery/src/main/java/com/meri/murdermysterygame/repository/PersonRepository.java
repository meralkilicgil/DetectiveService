package com.meri.murdermysterygame.repository;

import com.meri.murdermysterygame.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findAllByOrderByName();

    @Transactional(readOnly = true)
    @Query("SELECT p FROM Person p JOIN p.driversLicense dl WHERE dl.id = :licenseId")
    Optional<Person> findPersonByLicenseId(@Param("licenseId") Long licenseId);
}
