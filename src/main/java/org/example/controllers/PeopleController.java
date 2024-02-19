package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dao.PersonDao;
import org.example.models.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {
    private final PersonDao personDao;

    @GetMapping
    public String peopleList(Model model) {
        model.addAttribute("people", personDao.getAll());

        return "/people/list";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") Long id, Model model) {
        model.addAttribute("person", personDao.findById(id));

        return "/people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "/people/new";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("person") Person person) {
        personDao.save(person);

        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") Long id, Model model) {
        model.addAttribute("person", personDao.findById(id));

        return "/people/edit";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute("person") Person person) {
        personDao.update(id, person);

        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        personDao.delete(id);

        return "redirect:/people";
    }
}
