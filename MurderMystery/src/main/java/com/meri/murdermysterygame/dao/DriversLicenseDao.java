package com.meri.murdermysterygame.dao;

import com.meri.murdermysterygame.entity.DriversLicense;
import com.meri.murdermysterygame.repository.DriversLicenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriversLicenseDao {

    private final DriversLicenseRepository driversLicenseRepository;

    public DriversLicenseDao(DriversLicenseRepository driversLicenseRepository) {
        this.driversLicenseRepository = driversLicenseRepository;
    }

    public List<DriversLicense> getAll(){
        return driversLicenseRepository.findAllByOrderById();
    }

    public Optional<DriversLicense> getById(Long id){
        return driversLicenseRepository.findById(id);
    }

    public void create(DriversLicense driversLicense){
        driversLicenseRepository.save(driversLicense);
    }

    public void update(DriversLicense driversLicense){
        driversLicenseRepository.save(driversLicense);
    }

    public void delete(Long id){
        driversLicenseRepository.deleteById(id);
    }
}
