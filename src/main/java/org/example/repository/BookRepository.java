package org.example.repository;

import org.example.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * This is a Spring repository interface.
 * It provides methods and SQL queries related to books
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query(value = "SELECT * FROM books WHERE title like %?1%", nativeQuery = true)
    List<Book> findByTitleContaining(String title);

    @Query(value = "SELECT * FROM books WHERE id=?1", nativeQuery = true)
    Book findById(int bookID);

    @Query(value = "SELECT * FROM books WHERE title=?1 and quantity_available >= 0", nativeQuery = true)
    Book getBookDetails(String bookTitle);

    @Transactional
    @Modifying
    @Query(value = "UPDATE books SET quantity_available = quantity_available - 1 WHERE title=?1", nativeQuery = true)
    void updateTable(String bookTitle);

    @Transactional
    @Modifying
    @Query(value = "UPDATE books SET quantity_available = ?1 WHERE id=?2", nativeQuery = true)
    void updateBooks(int quantity,int id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE books SET quantity_available = quantity_available + 1 WHERE title=?1", nativeQuery = true)
    void modifyTable(String bookTitle);

    @Transactional
    @Modifying
    @Query(value = "Insert into books(title,author,quantity_available) values(?1,?2,?3)", nativeQuery = true)
    void addToTable(String bookTitle,String bookAuthor,int quantity);

    @Transactional
    @Modifying
    @Query(value = "Delete from books where id=?1", nativeQuery = true)
    void deleteBookFromTable(int bookID);
}