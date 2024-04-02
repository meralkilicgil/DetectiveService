package com.meri.murdermysterygame.controller;


import com.meri.murdermysterygame.dto.PersonDto;
import com.meri.murdermysterygame.exception.ObjectNotFoundException;
import com.meri.murdermysterygame.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/")
    public String index(){
        return "Welcome Murder Mystery Game!";
    }

    @GetMapping("/getAll")
    public List<PersonDto> getAllPeople(){
        return personService.getPersonDtoList();
    }

    @GetMapping("/get")
    public PersonDto getPerson(@RequestParam("id") Long id){
        PersonDto personDto = null;
        try {
            personDto = personService.getPersonById(id);
        } catch (ObjectNotFoundException e) {
            System.out.println("not found");
        }
        return personDto;
    }

    @PostMapping("/add")
    public PersonDto addPerson(@RequestBody PersonDto personDto) throws ObjectNotFoundException {
        return personService.createPerson(personDto);
    }

    @PutMapping("/update")
    public PersonDto updatePerson(@RequestBody PersonDto personDto, @RequestParam("id") Long id) throws ObjectNotFoundException {
        return personService.updatePerson(personDto, id);
    }

    @DeleteMapping("/delete")
    public void deletePerson(@RequestParam("id") Long id){
        try {
            PersonDto personDto = personService.getPersonById(id);
            personService.deletePerson(personDto);
        } catch (ObjectNotFoundException e) {
            System.out.println("Person cannot be found to delete.");
        }
    }

    @GetMapping("/checkFraud")
    public Boolean checkFraud(@RequestParam("id") Long id){
        try{
            return personService.checkFraud(id);
        } catch (Exception e) {
            System.out.println("Person cannot be found to delete.");
        }
        return true;
    }
}
