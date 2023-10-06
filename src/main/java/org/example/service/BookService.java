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

    public String borrowsBook(String title);


}
