package org.example.service;

import org.example.entity.Book;
import org.example.entity.Student;
import org.example.repository.BookRepository;
import org.example.repository.StudentRepository;
import org.example.repository.TransactionHistoryRepository;
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
    private StudentService studentService;

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    @Override
    public List<Book> fetchBookList() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooksByTitle(String title) {

        return bookRepository.findByTitleContaining(title);
    }

    @Override
    public String borrowBookDetails(String title,int student_id) {
        Book book = bookRepository.getBookDetails(title);
        if (book == null) {
               return "Book not found or not available.Please try typing correct name of book or contact admin to check book availability";
        }
        String result = "Book borrowed \n" + "Name: " + book.getTitle() + "\n" + "Author: " + book.getAuthor();
        bookRepository.updateTable(title);
        Student student = studentService.getStudentById(student_id);
        transactionHistoryRepository.updateTransactionHistory(book.getId(),student_id, "borrowed",book.getTitle(),student.getEmailId());
        return result;
    }

    @Override
    public String returnBookDetails(String title, int student_id) {
        Book book = bookRepository.getBookDetails(title);
        StringBuilder result = new StringBuilder();
        bookRepository.modifyTable(title);
        Student student = studentService.getStudentById(student_id);
        transactionHistoryRepository.updateTransactionHistory(book.getId(),student_id,"returned",book.getTitle(),student.getEmailId());
        return "Book returned";
    }

    @Override
    public String addBookDetails(String title,String bookAuthor,int quantity) {
        List<Book> books= fetchBookList();
        for(Book b:books) {
            if(b.getTitle().equals(title)){
                return "Book already exists";
            }
        }
        bookRepository.addToTable(title, bookAuthor, quantity);
        return "Book added to table";
    }

    @Override
    public String getBookById(int bookID) {
        Book b = bookRepository.findById(bookID);
        bookRepository.updateTable(b.getTitle());
        return "Book updated";
    }

    @Override
    public String deleteById(int bookID) {
        StringBuilder result = new StringBuilder();
        Book book = bookRepository.findById(bookID);
        transactionHistoryRepository.deleteBookById(bookID);
        bookRepository.deleteBookFromTable(bookID);
        if(book.getQuantity_available() <=0){
            return "No book available to delete";
        }
        return "Book successfully deleted";
    }

}
