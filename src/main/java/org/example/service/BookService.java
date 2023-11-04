package org.example.service;

import org.example.entity.Book;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Interface for BookServiceImpl
 */
@Service
public interface BookService {
    public List<Book> fetchBookList();

    public List<Book> searchBooksByTitle(String title);

    public String borrowBookDetails(String title,String emailid);

    public String returnBookDetails(String title,String emailid);

    public String addBookDetails(String title,String author,int quantity);

    public String deleteById(int bookID);

    public String getBookById(int bookID);

//    public List<TransactionHistory> getTransactionHistoryForId();
//    public List<TransactionHistory> showTransactionHistory(String title);
}
