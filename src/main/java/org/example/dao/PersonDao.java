package org.example.dao;

import lombok.RequiredArgsConstructor;
import org.example.models.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PersonDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Person> getAll() {
        return jdbcTemplate.query("select * from person", new PersonMapper());
    }

    public Person findById(Long id) {
        return jdbcTemplate.queryForStream("select * from person where person_id=?", new PersonMapper(), id)
                .findAny().orElse(new Person());
    }

    public void save(Person person) {
        jdbcTemplate.update("insert into person(name, year_of_birth) values (?, ?)",
                person.getName(), person.getYearOfBirth());
    }

    public void update(Long id, Person person) {
        jdbcTemplate.update("update person set name=?, year_of_birth=? where person_id=?",
                person.getName(), person.getYearOfBirth(), id);
    }

    public void delete(Long id) {
        jdbcTemplate.update("delete from person where person_id=?", id);
    }

    public Optional<Person> findByName(String name) {
        return jdbcTemplate.queryForStream("select * from person where name=?", new PersonMapper(), name)
                .findAny();
    }
}
