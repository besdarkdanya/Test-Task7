package ua.denk.resttest.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.denk.resttest.model.Person;
import ua.denk.resttest.model.Views;
import ua.denk.resttest.exceptions.PersonNotFoundException;
import ua.denk.resttest.repositories.PersonRepository;
import ua.denk.resttest.services.PersonService;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class PersonController {

    @Autowired
    private  PersonRepository personRepository;
    @Autowired
    private  PersonService personService;


    @GetMapping("/get-person")
    @JsonView(Views.IdName.class)
    public Person getPersonById(@RequestParam Long id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        person.setAge(personService.calculatePersonAge(person));
        return person;
    }
}
