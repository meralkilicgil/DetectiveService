package com.meri.murdermysterygame.service;

import com.meri.murdermysterygame.aop.LogAnnotation;
import com.meri.murdermysterygame.dao.PersonDao;
import com.meri.murdermysterygame.dto.DriversLicenseDto;
import com.meri.murdermysterygame.dto.PersonDto;
import com.meri.murdermysterygame.entity.Person;
import com.meri.murdermysterygame.exception.ObjectNotFoundException;
import com.meri.murdermysterygame.utils.DtoUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class PersonService {

    private final PersonDao personDao;
    private final RestTemplate restTemplate;

    @LogAnnotation
    public List<PersonDto> getPersonDtoList(){
        List<Person> personList = personDao.getAll();
        return personList.stream().map(DtoUtils::convertPersonEntityToPersonDto).toList();
    }

    @LogAnnotation
    public PersonDto getPersonById(Long id) throws ObjectNotFoundException {
        Optional<Person> result = personDao.getById(id);
        if(result.isPresent()) {
            return DtoUtils.convertPersonEntityToPersonDto(result.get());
        }
        throw new ObjectNotFoundException("Person cannot be found with Id: " + id, HttpStatusCode.valueOf(404));
    }

    @LogAnnotation
    public PersonDto createPerson(PersonDto personDto) throws ObjectNotFoundException {
        Long licenseId = personDto.getLicenseId();
        if( licenseId != null){
            DriversLicenseDto driversLicenseDto= new DriversLicenseDto();
            driversLicenseDto.setId(personDto.getLicenseId());
            personDto.setDriversLicense(driversLicenseDto);
        }
        Person person = DtoUtils.convertPersonDtoToPersonEntity(personDto);
        person = personDao.create(person);
        return getPersonById(person.getId());
    }

    @LogAnnotation
    public PersonDto updatePerson(PersonDto personDto, Long id) throws ObjectNotFoundException {
        Long licenseId = personDto.getLicenseId();
        PersonDto oldPerson = getPersonById(id);
        if( licenseId != null){
            DriversLicenseDto driversLicenseDto = oldPerson.getDriversLicense();
            personDto.setDriversLicense(driversLicenseDto);
        }
        Person person = DtoUtils.convertPersonDtoToPersonEntity(personDto);
        person.setId(id);
        personDao.update(person);
        return getPersonById(id);
    }

    @LogAnnotation
    public void deletePerson(PersonDto personDto){
        personDao.delete(personDto.getId());
    }

    @LogAnnotation
    public PersonDto getPersonByLicenseId(Long id) throws ObjectNotFoundException {
        Optional<Person> result = personDao.getByLicenseId(id);
        if(result.isPresent())
            return DtoUtils.convertPersonEntityToPersonDto(result.get());

        throw new ObjectNotFoundException("person not found with license id: " + id, HttpStatusCode.valueOf(404));
    }

    @LogAnnotation
    public Boolean checkFraud(Long personId){
        return restTemplate.getForObject("http://localhost:8081/api/fraud-check/{personId}",
                Boolean.class,
                personId);
    }
}
