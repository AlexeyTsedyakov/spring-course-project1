package org.example.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dao.BookDao;
import org.example.dao.PersonDao;
import org.example.models.Book;
import org.example.models.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BooksController {
    private final BookDao bookDao;
    private final PersonDao personDao;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("books", bookDao.getAll());
        return "/books/list";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Book book = bookDao.findById(id);
        model.addAttribute("book", book);
        model.addAttribute("people", personDao.getAll());
        model.addAttribute("reader", personDao.findById(book.getPersonId()));
        return "/books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "/books/new";
    }

    @PostMapping
    public String save(@ModelAttribute("book") @Valid Book book,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "/books/new";

        bookDao.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", bookDao.findById(id));
        return "/books/edit";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "/books/edit";

        bookDao.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        bookDao.delete(id);
        return "redirect:/books";
    }

    @PutMapping("/{id}/set-reader")
    public String setReader(@PathVariable("id") Long id,
                            @ModelAttribute("reader") Person reader) {
        bookDao.setReader(id, reader.getId());
        return "redirect:/books/" + id;
    }

    @PutMapping("/{id}/remove-reader")
    public String removeReader(@PathVariable("id") Long id) {
        bookDao.removeReader(id);
        return "redirect:/books/" + id;
    }
}
