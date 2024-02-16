package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dao.PersonDao;
import org.example.models.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {
    private final PersonDao personDao;

    @GetMapping
    public String peopleList() {
        return "/people/people-list";
    }

    @GetMapping("/create")
    public String create(@ModelAttribute("person") Person person) {
        return "/people/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("person") Person person) {
        personDao.save(person);

        return "redirect:/people";
    }
}
