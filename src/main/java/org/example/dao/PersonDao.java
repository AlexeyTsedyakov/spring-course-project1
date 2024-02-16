package org.example.dao;

import lombok.RequiredArgsConstructor;
import org.example.models.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Person> getAll() {
        return jdbcTemplate.query("select * from person",
                new BeanPropertyRowMapper<>(Person.class));
    }

    public void save(Person person) {
        jdbcTemplate.update("insert into person(name, year_of_birth) values (?, ?)",
                person.getName(), person.getYearOfBirth());
    }
}
