package com.meri.murdermysterygame.service;

import com.meri.murdermysterygame.dao.InterviewDao;
import com.meri.murdermysterygame.dto.InterviewDto;
import com.meri.murdermysterygame.dto.PersonDto;
import com.meri.murdermysterygame.entity.Interview;
import com.meri.murdermysterygame.exception.ObjectNotFoundException;
import com.meri.murdermysterygame.utils.DtoUtils;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class InterviewService {

    private final InterviewDao interviewDao;
    private final PersonService personService;

    public InterviewService(InterviewDao interviewDao, PersonService personService) {
        this.interviewDao = interviewDao;
        this.personService = personService;
    }

    public List<InterviewDto> getAllInterviewDtoList(){
        List<Interview> result = interviewDao.getAll();
        return result.stream().map(DtoUtils::convertInterviewEntityToInterviewDto).toList();
    }

    public InterviewDto getInterviewById(Long id) throws ObjectNotFoundException {
        Optional<Interview> result = interviewDao.getById(id);
        if(result.isPresent()) {
            return DtoUtils.convertInterviewEntityToInterviewDto(result.get());
        }
        throw new ObjectNotFoundException("Interview cannot be found with Id: " + id, HttpStatusCode.valueOf(404));
    }

    public InterviewDto createInterview(InterviewDto interviewDto) throws ObjectNotFoundException {
        if(interviewDto.getPersonId() != null){
            try{
                PersonDto personDto = personService.getPersonById(interviewDto.getPersonId());
                personDto.setId(interviewDto.getPersonId());
                interviewDto.setPerson(personDto);
            }
            catch (ObjectNotFoundException ex){
                System.out.println("Person is not created, skipping...");
            }
        }
        Interview interview = DtoUtils.convertInterviewDtoToInterviewEntity(interviewDto);
        interviewDao.create(interview);
        return getInterviewById(interview.getId());
    }

    public InterviewDto updateInterview(InterviewDto interviewDto, Long id) throws ObjectNotFoundException {
        Interview interview = DtoUtils.convertInterviewDtoToInterviewEntity(interviewDto);
        interview.setId(id);
        interview = interviewDao.update(interview);
        if(interviewDto.getPersonId() != null){
            updatePerson(interviewDto.getPersonId(), interview);
        }
        return getInterviewById(id);
    }

    public void deleteInterview(InterviewDto interviewDto){
        Long id = interviewDto.getId();
        interviewDao.delete(id);
    }

    public void updatePerson(Long personId, Interview interview) {
        try {
            PersonDto personDto = personService.getPersonById(personId);
            List<InterviewDto> interviewDtoList = personDto.getInterviewDtoList();
            interviewDtoList = interviewDtoList.stream().map(i -> {
                if (Objects.equals(i.getId(), interview.getId())) {
                    i = DtoUtils.convertInterviewEntityToInterviewDto(interview);
                }
                return i;
            }).toList();
            personDto.setInterviewDtoList(interviewDtoList);
            personService.updatePerson(personDto, personDto.getId());
        }
        catch (ObjectNotFoundException ex){
            System.out.println("Person record not found, skipping...");
        }
    }
}
