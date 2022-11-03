package ua.denk.resttest.repositories;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import ua.denk.resttest.model.Person;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
class PersonRepositoryUnitTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private PersonRepository repo;

    @Test
    public void whenFindById_thenReturnPerson() {

        Person danyl = new Person("Danyl","Denk", LocalDate.parse("2001-06-20"));
        entityManager.persist(danyl);
        entityManager.flush();

        Person found = repo.findById(danyl.getId()).get();

        assertThat(found.getName()).isEqualTo(danyl.getName());
    }

}