package ua.denk.resttest.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ua.denk.resttest.model.Person;
import ua.denk.resttest.exceptions.PersonNotFoundException;
import ua.denk.resttest.repositories.PersonRepository;
import ua.denk.resttest.services.PersonService;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest
class PersonControllerUnitTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    PersonService service;
    @MockBean
    PersonRepository repository;

    @Test
    void whenPersonReturn_thenAssertionSucceeds() throws Exception {
        when(service.calculatePersonAge(
                new Person(1L,"danyl","denk", LocalDate.parse("2001-06-20"))))
                .thenReturn(21);

        when(repository.findById(1L))
                .thenReturn(
                        Optional.of(new Person(1L, "danyl", "denk", LocalDate.parse("2001-06-20"))));

        mvc.perform(get("/api/person?id=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("danyl"));
    }

    @Test
    void whenExceptionThrown_thenAssertionSucceeds() throws Exception {
        mvc.perform(get("/api/person?id=1000")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof PersonNotFoundException))
                .andExpect(result -> assertEquals("Person with this Id doesn't exists",
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));

    }

}