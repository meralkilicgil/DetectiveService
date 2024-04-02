package com.meri.murdermysterygame.dao;

import com.meri.murdermysterygame.entity.Person;
import com.meri.murdermysterygame.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonDao {

    private final PersonRepository personRepository;

    public PersonDao(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAll() {
        return personRepository.findAllByOrderByName();
    }

    public Optional<Person> getById(Long id) {
        return personRepository.findById(id);
    }

    public Person create(Person person) {
        return personRepository.save(person);
    }

    public Person update(Person updatedPerson) {
        return personRepository.save(updatedPerson);
    }

    public void delete(Long id) {
        personRepository.deleteById(id);
    }

    public Optional<Person> getByLicenseId(Long licenseId){
        return personRepository.findPersonByLicenseId(licenseId);
    }
}
