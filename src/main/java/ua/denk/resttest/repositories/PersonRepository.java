package ua.denk.resttest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.denk.resttest.model.Person;
@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {

}
