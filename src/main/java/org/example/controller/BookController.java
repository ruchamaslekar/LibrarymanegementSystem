package org.example.controller;

import org.example.entity.Book;
import org.example.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/* Controller class for Book */
@RestController
public class BookController {

    /* Calling BookService */
    @Autowired
    private BookService bookService;

    private final Logger LOGGER =
            LoggerFactory.getLogger(BookController.class);

    /* getBooks method to get the list of all books present in database */
    @GetMapping("/books")
    public List<Book> getBooks()
    {
        LOGGER.info("Inside getBooks of BookController");
        return bookService.fetchBookList();
    }

    /* searchBook method to search book by its title */
    @GetMapping("/search-books")
    public List<Book> searchBook(@RequestParam String title) {
        List<Book> foundBooks = bookService.searchBooksByTitle(title);
        return foundBooks;
    }
}
