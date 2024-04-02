package com.meri.murdermysterygame.service;

import com.meri.murdermysterygame.dao.DriversLicenseDao;
import com.meri.murdermysterygame.dto.DriversLicenseDto;
import com.meri.murdermysterygame.dto.PersonDto;
import com.meri.murdermysterygame.entity.DriversLicense;
import com.meri.murdermysterygame.exception.ObjectNotFoundException;
import com.meri.murdermysterygame.utils.DtoUtils;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriversLicenseService {

    private final DriversLicenseDao driversLicenseDao;
    private final PersonService personService;

    public DriversLicenseService(DriversLicenseDao driversLicenseDao, PersonService personService) {
        this.driversLicenseDao = driversLicenseDao;
        this.personService = personService;
    }

    public List<DriversLicenseDto> getDriversLicenseDtoList(){
        List<DriversLicense> driversLicenseList = driversLicenseDao.getAll();
        return driversLicenseList.stream().map(DtoUtils::convertDriverLicenseEntityToDriversLicenseDto).toList();
    }

    public DriversLicenseDto getDriversLicenseById(Long id) throws ObjectNotFoundException {
        Optional<DriversLicense> result = driversLicenseDao.getById(id);
        if(result.isPresent()){
            return DtoUtils.convertDriverLicenseEntityToDriversLicenseDto(result.get());
        }
        throw new ObjectNotFoundException("Drivers License cannot be found with Id: " + id, HttpStatusCode.valueOf(404));
    }

    public DriversLicenseDto createDriversLicense(DriversLicenseDto driversLicenseDto) throws ObjectNotFoundException {
        DriversLicense driversLicense = DtoUtils.convertDriversLicenseDtoToDriversLicenseEntity(driversLicenseDto);
        driversLicenseDao.create(driversLicense);
        return getDriversLicenseById(driversLicense.getId());
    }

    public DriversLicenseDto updateDriversLicense(DriversLicenseDto driversLicenseDto, Long id) throws ObjectNotFoundException {
        DriversLicense driversLicense = DtoUtils.convertDriversLicenseDtoToDriversLicenseEntity(driversLicenseDto);
        driversLicense.setId(id);
        driversLicenseDao.update(driversLicense);
        return getDriversLicenseById(id);
    }

    public void deleteDriversLicense(DriversLicenseDto driversLicenseDto){
        //check person that have license id
        Long id = driversLicenseDto.getId();
        try {
            PersonDto personDto = personService.getPersonByLicenseId(id);
            if(personDto != null){
                personDto.setDriversLicense(null);
                personDto.setLicenseId(null);
                try {
                    personService.updatePerson(personDto, personDto.getId());
                } catch (ObjectNotFoundException e) {
                    System.out.println("Person is not found to update drivers license field.");
                }
            }
        } catch (ObjectNotFoundException e) {
            System.out.println("Any person related to drivers license is found");
        }
        driversLicenseDao.delete(id);
    }
}
