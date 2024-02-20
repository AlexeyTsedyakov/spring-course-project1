package org.example.dao;

import lombok.RequiredArgsConstructor;
import org.example.models.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Book> getAll() {
        return jdbcTemplate.query("select * from book", new BookMapper());
    }

    public Book findById(Long id) {
        return jdbcTemplate.queryForStream("select * from book where book_id=?", new BookMapper(), id)
                .findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("insert into book(person_id, name, author, year) values(?, ?, ?, ?)",
                book.getPersonId(), book.getName(), book.getAuthor(), book.getYear());
    }

    public void update(Long id, Book book) {
        jdbcTemplate.update("update book set person_id=? ,name=?, author=?, author=? where book_id=?",
                book.getPersonId(), book.getName(), book.getAuthor(), book.getYear(), id);
    }

    public void delete(Long id) {
        jdbcTemplate.update("delete from book where book_id=?", id);
    }

    public void removeTheBookOwner(Long id) {
        jdbcTemplate.update("update book set person_id=null where book_id=?", id);
    }
}
