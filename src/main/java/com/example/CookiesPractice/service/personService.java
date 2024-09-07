package com.example.CookiesPractice.service;

import com.example.CookiesPractice.model.Person;
import com.example.CookiesPractice.repository.personRepository;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class personService {
    private personRepository operations;

    @Autowired
    public void setOperations(personRepository operations) {
        this.operations = operations;
    }
    public List<Person>getAllPeople(){
        return operations.findAll();
    }
    public Person getConcretePerson(int id){
        return operations.findById(id).orElse(null);
    }
    public void savePerson(Person person){
        operations.save(person);
    }
    public void updatePerson(int id,Person person){
        person.setId(id);
        operations.save(person);
    }
    public void deletePerson(int id){
        operations.deleteById(id);
    }
}