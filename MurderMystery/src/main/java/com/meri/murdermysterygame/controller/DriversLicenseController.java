package com.meri.murdermysterygame.controller;

import com.meri.murdermysterygame.dto.DriversLicenseDto;
import com.meri.murdermysterygame.exception.ObjectNotFoundException;
import com.meri.murdermysterygame.service.DriversLicenseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/driversLicense")
public class DriversLicenseController {

    private final DriversLicenseService driversLicenseService;

    public DriversLicenseController(DriversLicenseService driversLicenseService) {
        this.driversLicenseService = driversLicenseService;
    }

    @GetMapping("/getAll")
    public List<DriversLicenseDto> getAllDriversLicense(){
        return driversLicenseService.getDriversLicenseDtoList();
    }

    @GetMapping("/get")
    public DriversLicenseDto getDriversLicense(@RequestParam(name = "id") Long id){
        try {
            return driversLicenseService.getDriversLicenseById(id);
        } catch (ObjectNotFoundException e) {
            System.out.println("Drivers License cannot be found.");
        }
        return null;
    }

    @PostMapping("/add")
    public DriversLicenseDto addDriversLicense(@RequestBody DriversLicenseDto driversLicenseDto) throws ObjectNotFoundException {
        return driversLicenseService.createDriversLicense(driversLicenseDto);
    }

    @PutMapping("/update")
    public DriversLicenseDto updateDriversLicense(@RequestBody DriversLicenseDto driversLicenseDto, @RequestParam(name = "id") Long id) throws ObjectNotFoundException {
        return driversLicenseService.updateDriversLicense(driversLicenseDto, id);
    }

    @DeleteMapping("/delete")
    public void deleteDriversLicense(@RequestParam(name = "id") Long id){
        try {
            DriversLicenseDto driversLicenseDto = driversLicenseService.getDriversLicenseById(id);
            driversLicenseService.deleteDriversLicense(driversLicenseDto);
        } catch (ObjectNotFoundException e) {
            System.out.println("Drivers License cannot be found to delete.");
        }

    }
}
