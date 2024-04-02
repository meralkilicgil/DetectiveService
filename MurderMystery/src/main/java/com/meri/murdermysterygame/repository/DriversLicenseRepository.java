package com.meri.murdermysterygame.repository;

import com.meri.murdermysterygame.entity.DriversLicense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriversLicenseRepository extends JpaRepository<DriversLicense, Long> {

    List<DriversLicense> findAllByOrderById();
}
