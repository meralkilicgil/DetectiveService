package com.meri.murdermysterygame.utils;

import com.meri.murdermysterygame.dto.DriversLicenseDto;
import com.meri.murdermysterygame.dto.InterviewDto;
import com.meri.murdermysterygame.dto.PersonDto;
import com.meri.murdermysterygame.entity.DriversLicense;
import com.meri.murdermysterygame.entity.Interview;
import com.meri.murdermysterygame.entity.Person;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class DtoUtils {

    public static PersonDto convertPersonEntityToPersonDto(Person entity) {
        PersonDto personDto = new PersonDto();
        BeanUtils.copyProperties(entity, personDto);
        if (entity.getDriversLicense() != null) {
            DriversLicenseDto driversLicenseDto = new DriversLicenseDto();
            BeanUtils.copyProperties(entity.getDriversLicense(), driversLicenseDto);
            personDto.setDriversLicense(driversLicenseDto);
            personDto.setLicenseId(driversLicenseDto.getId());
        }
        if (entity.getInterviews() != null && !entity.getInterviews().isEmpty()){
            List<Interview> interviewList = entity.getInterviews();
            List<InterviewDto> interviewDtoList = interviewList.stream().map(DtoUtils::convertInterviewEntityToInterviewDto).toList();
            personDto.setInterviewDtoList(interviewDtoList);
        }
        return personDto;
    }

    public static Person convertPersonDtoToPersonEntity(PersonDto dto) {
        Person personEntity = new Person();
        BeanUtils.copyProperties(dto, personEntity);
        if(dto.getDriversLicense() != null){
            DriversLicense driversLicense = convertDriversLicenseDtoToDriversLicenseEntity(dto.getDriversLicense());
            personEntity.setDriversLicense(driversLicense);
        }
        if(dto.getInterviewDtoList() != null && !dto.getInterviewDtoList().isEmpty()){
            List<InterviewDto> interviewDtoList = dto.getInterviewDtoList();
            List<Interview> interviewList = interviewDtoList.stream().map(DtoUtils::convertInterviewDtoToInterviewEntity).toList();
            interviewList = interviewList.stream().map(i ->{
               i.setPerson(personEntity);
               return i;
            }).toList();
            personEntity.setInterviews(interviewList);
        }
        return personEntity;
    }

    public static DriversLicenseDto convertDriverLicenseEntityToDriversLicenseDto(DriversLicense entity) {
        DriversLicenseDto driversLicenseDto = new DriversLicenseDto();
        BeanUtils.copyProperties(entity, driversLicenseDto);
        return driversLicenseDto;
    }

    public static DriversLicense convertDriversLicenseDtoToDriversLicenseEntity(DriversLicenseDto dto) {
        DriversLicense driversLicense = new DriversLicense();
        BeanUtils.copyProperties(dto, driversLicense);
        return driversLicense;
    }

    public static InterviewDto convertInterviewEntityToInterviewDto(Interview entity) {
        InterviewDto interviewDto = new InterviewDto();
        BeanUtils.copyProperties(entity, interviewDto);
        if(entity.getPerson() != null) {
            PersonDto personDto = new PersonDto();
            BeanUtils.copyProperties(entity.getPerson(), personDto);
            interviewDto.setPerson(personDto);
            interviewDto.setPersonId(personDto.getId());
        }
        return interviewDto;
    }

    public static Interview convertInterviewDtoToInterviewEntity(InterviewDto dto) {
        Interview interview = new Interview();
        BeanUtils.copyProperties(dto, interview);
        if(dto.getPersonId() != null && dto.getPerson() != null){
            Person person = convertPersonDtoToPersonEntity(dto.getPerson());
            interview.setPerson(person);
        }
        return interview;
    }
}
