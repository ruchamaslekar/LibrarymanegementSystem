package org.example.service;

import org.example.entity.Book;
import org.example.entity.TransactionHistory;
import org.example.repository.BookRepository;
import org.example.repository.StudentRepository;
import org.example.repository.TransactionHistoryRepository;
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

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;


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
    public String borrowBookDetails(String title) {
        Book book = bookRepository.getBookDetails(title);
        if (book == null) {
               return "Book not found or not available.Please try typing correct name of book or contact admin to check book availability";
        }
        StringBuilder result = new StringBuilder();
        result.append("Book borrowed \n").append("Name: ").append(book.getTitle()).append("\n").append("Author: ").append(book.getAuthor());
        bookRepository.updateTable(title);
        transactionHistoryRepository.updateTransactionHistory(book.getId(), book.getTitle(),"borrowed");
        return result.toString();
    }

    @Override
    public String returnBookDetails(String title) {
        Book book = bookRepository.getBookDetails(title);
        StringBuilder result = new StringBuilder();
        //result.append("Return book \n").append("Name: ").append(book.getTitle()).append("\n").append("Author: ").append(book.getAuthor());
        bookRepository.modifyTable(title);
        transactionHistoryRepository.updateTransactionHistory(book.getId(),book.getTitle(), "returned");
        return "Book returned";
    }

    @Override
    public List<TransactionHistory> showTransactionHistory(String title) {
       // Book book = bookRepository.getBookDetails(title);
        return transactionHistoryRepository.getTransactionHistory(title);
    }

}
