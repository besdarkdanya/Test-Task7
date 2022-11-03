package ua.denk.resttest.services;

import org.springframework.stereotype.Service;
import ua.denk.resttest.model.Person;

import java.time.LocalDate;
import java.time.Period;

@Service
public class PersonService {
    public int calculatePersonAge(Person person) {
        return Period.between(person.getDateOfBirth(), LocalDate.now()).getYears();
    }
}
