package org.example.util;

import lombok.RequiredArgsConstructor;
import org.example.dao.PersonDao;
import org.example.models.Person;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class PersonValidator implements Validator {
    private final PersonDao personDao;

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (personDao.findByName(person.getName()).isPresent()) {
            errors.rejectValue("name", "", "Человек с таким ФИО уже существует!");
        }
    }
}
