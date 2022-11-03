package ua.denk.resttest.services;

import org.junit.jupiter.api.Test;
import ua.denk.resttest.model.Person;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PersonServiceUnitTest {

    @Test
    void testCalculatePersonAge() {
        PersonService service = new PersonService();
        int expected = 21;
        int actual = service.calculatePersonAge(new Person(1L,"danyl","denk", LocalDate.parse("2001-06-20")));
        assertEquals(expected,actual);
    }
}