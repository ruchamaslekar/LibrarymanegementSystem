package org.example.service;

import org.example.entity.Book;
import org.example.entity.Student;
import org.example.entity.TransactionHistory;
import org.example.repository.BookRepository;
import org.example.repository.StudentRepository;
import org.example.repository.TransactionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
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
    public String borrowBookDetails(String title,String emailid) {
        Book book = bookRepository.getBookDetails(title);
        if (book == null) {
               return "Book not found or not available.Please try typing correct name of book or contact admin to check book availability";
        }
        String result = "Book borrowed \n" + "Name: " + book.getTitle() + "\n" + "Author: " + book.getAuthor();
        bookRepository.updateTable(title);
        Student student = studentRepository.findByUsername(emailid);
        LocalDate date = LocalDate.now().plusDays(30);
        int borrowCount =0;
        int returnCount =0;
        List<TransactionHistory> borrowHistory = transactionHistoryRepository.getBorrowHistory(emailid,title,"borrowed");
        for(TransactionHistory hist: borrowHistory) {
            borrowCount++;
        }
        List<TransactionHistory> returnHistory = transactionHistoryRepository.getBorrowHistory(emailid,title,"returned");
        for(TransactionHistory hist: returnHistory) {
            returnCount++;
        }
        if(borrowCount == returnCount) {
            transactionHistoryRepository.updateTransactionHistory(book.getId(),student.getId(),"borrowed",book.getTitle(),emailid,date);
        } else {
            return "This book is already borrowed.Please return the borrowed book first";
        }
        return result;
    }

    @Override
    public String returnBookDetails(String title,String emailid) {
        Book book = bookRepository.getBookDetails(title);
        StringBuilder result = new StringBuilder();
        bookRepository.modifyTable(title);
        Student student = studentRepository.findByUsername(emailid);
        LocalDate date = null;
        int borrowCount =0;
        int returnCount =0;
        List<TransactionHistory> borrowHistory = transactionHistoryRepository.getBorrowHistory(emailid,title,"borrowed");
        for(TransactionHistory hist: borrowHistory) {
            borrowCount++;
        }
        List<TransactionHistory> returnHistory = transactionHistoryRepository.getBorrowHistory(emailid,title,"returned");
        for(TransactionHistory hist: returnHistory) {
            returnCount++;
        }
        if(borrowCount != returnCount) {
            transactionHistoryRepository.updateTransactionHistory(book.getId(),student.getId(),"returned",book.getTitle(),emailid,date);
        }
        else {
            return "Please borrow the book to return";
        }
         return studentService.checkDueDate(emailid,title);
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
