package org.example.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.example.entity.Book;
import org.example.repository.BookRepository;
import org.example.repository.StudentRepository;
import org.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

/**
 * This is a Spring controller class.
 */
@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;

    /** Method to get result of search Book from service
     * student will search for a book
     */
    @GetMapping("/search-books")
    public String searchBook(@RequestParam String title,Model model) {
        List<Book> bookList = bookService.searchBooksByTitle(title);
        String error = " No book with this name is available";
        if(bookList.isEmpty()) {
            model.addAttribute("error",error);
        } else {
            model.addAttribute("list", bookList);
        }
        return "/search-books";
    }

    /** Method to get result of borrow Book from service
     * student will borrow a book
     */
    @GetMapping("/borrow-books")
    public String borrowBook(@RequestParam String title,Model model,HttpSession session) {
        Object username = session.getAttribute("username");
        String bookDetails = bookService.borrowBookDetails(title,username.toString());
        model.addAttribute("bookString",bookDetails);
        return "/borrow-books";
    }

    /** Method to get result of return Book from service
     * student will return a book
     */
    @GetMapping("/return-books")
    public String returnBook(String title,Model model,HttpSession session) {
        Object username = session.getAttribute("username");
        String book =  bookService.returnBookDetails(title,username.toString());
        model.addAttribute("book",book);
        return "/return-books";
    }

    /** Method to get Book list from service
     * and navigate to book-details html page
     */
    @PostMapping("/book-list")
    public String getBooks(Model model) {
        List<Book> books = bookService.fetchBookList();
        model.addAttribute("book",books);
        return "/book-list";
    }

    @GetMapping("/book-list")
    public String getBooks1(Model model) {
        //LOGGER.info("Inside getBooks of BookController");
        List<Book> books = bookService.fetchBookList();
        model.addAttribute("book",books);
        return "/book-list";
    }

    /** Method to get result of return Book from service
     * and navigate to return-books html page
     */
    @PostMapping("/add-books")
    public String addBooks(String title,String author,int quantity,Model model) {
        String result = bookService.addBookDetails(title,author,quantity);
        model.addAttribute("bookString",result);
        return "redirect:/book-list";
    }

    @PostMapping("/update-books")
    public String updateBooks(Model model,int quantity,int id) {
        bookRepository.updateBooks(quantity,id);
        model.addAttribute("book","book updated");
        return "redirect:/book-list";
    }

    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") int id,Model model) {
        Book book =  bookRepository.findById(id);
        model.addAttribute("book",book);
        return "/save-book";
    }

    @RequestMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id")int id,Model model) {
        String result = bookService.deleteById(id);
        model.addAttribute("result",result);
        return "redirect:/book-list";
    }

}
