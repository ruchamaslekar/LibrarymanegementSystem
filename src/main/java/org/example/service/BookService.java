package org.example.service;

import org.example.entity.Book;
import org.example.entity.TransactionHistory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interface for BookServiceImpl
 */
@Service
public interface BookService {

    public List<Book> fetchBookList();

    public List<Book> searchBooksByTitle(String title);

    public String borrowBookDetails(String title);
    public String returnBookDetails(String title);

    public List<TransactionHistory> showTransactionHistory(String title);


}
