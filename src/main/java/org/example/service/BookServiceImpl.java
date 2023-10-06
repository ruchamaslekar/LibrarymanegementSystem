package org.example.service;

import org.example.entity.Book;
import org.example.entity.Student;
import org.example.repository.BookRepository;
import org.example.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * This is a Spring service class.
 * It provides methods related to books
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StudentRepository studentRepository;
    private final Logger LOGGER =
            LoggerFactory.getLogger(BookService.class);
    @Override
    public List<Book> fetchBookList() {
        return bookRepository.findAll();
    }
    @Override
    public List<Book> searchBooksByTitle(String title) {
            return bookRepository.findByTitleContaining(title);
    }

    @Override
    public String borrowsBook(String title){
        Book book = bookRepository.getBookDetails(title);
        if(book == null) {
            return "Book not available or Incorrect book name entered";
        }
        StringBuilder result = new StringBuilder();
        result.append("Book borrowed \n").append("Name: ").append(book.getTitle()).append("\n").append("Author: ").append(book.getAuthor());
        bookRepository.updateTable(title);
        return result.toString();
    }

}
