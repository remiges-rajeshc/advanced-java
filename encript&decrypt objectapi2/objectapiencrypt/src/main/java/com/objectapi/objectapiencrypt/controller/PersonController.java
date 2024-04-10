package com.objectapi.objectapiencrypt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.objectapi.objectapiencrypt.dto.PersonDto;
import com.objectapi.objectapiencrypt.entity.Person;
import com.objectapi.objectapiencrypt.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @PostMapping("/add")
    public String savePersonData(@RequestBody Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setName(person.getName());
        personDto.setAge(person.getAge());
        personDto.setCardDetails(person.getCardDetails());
        try {
            personService.savePersonData(personDto);
            return "Person data saved successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to save person data. Please try again later.";
        }
    }

    @GetMapping("/{id}")
    public Person GetPersonData(@PathVariable Long id) {
        try {
           return personService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
