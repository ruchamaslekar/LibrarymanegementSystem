package org.example.controller;

import org.example.entity.Book;
import org.example.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * This is a Spring controller class.
 */
@Controller
public class BookController {

    /** Calling BookService */
    @Autowired
    private BookService bookService;

    private final Logger LOGGER =
            LoggerFactory.getLogger(BookController.class);

    /** Method to get Book list from service
     * and navigate to book-details html page
     */
    @PostMapping("/book-details")
    public String getBooks(Model model) {
        //LOGGER.info("Inside getBooks of BookController");
        List<Book> books = bookService.fetchBookList();
        model.addAttribute("book",books);
        return "book-details";
    }

    /** Method to get result of search Book from service
     * and navigate to search-books html page
     */
    @GetMapping("/search-books")
    public String searchBook(@RequestParam String title,Model model) {
        List<Book> bookList = bookService.searchBooksByTitle(title);
        model.addAttribute("list", bookList);
        return "search-books";
    }

    /** Method to get result of borrow Book from service
     * and navigate to borrow-books html page
     */
    @GetMapping("/borrow-books")
    public String borrowBook(@RequestParam String title,Model model) {
        String bookDetails = bookService.borrowBookDetails(title);
        model.addAttribute("bookString",bookDetails);
        return "borrow-books";
    }

    /** Method to get result of return Book from service
     * and navigate to return-books html page
     */
    @GetMapping("/return-books")
    public String returnBook(String title) {
        return bookService.returnBookDetails(title);
    }

}
