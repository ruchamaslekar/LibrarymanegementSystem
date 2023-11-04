package org.example.repository;

import org.example.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Integer> {
    @Modifying
    @Transactional
    @Query(value = "insert into transaction_history (book_id,student_id,action,title,emailid,due_date) values (?1,?2,?3,?4,?5,?6)" ,nativeQuery = true)
    void  updateTransactionHistory(int bookId, int student_id, String action, String title, String emailid, LocalDate due_date);

    @Query(value = "select * from transaction_history where emailid=?1",nativeQuery = true)
    List<TransactionHistory> getTransactionHistory(String emailid);

    @Modifying
    @Transactional
    @Query(value = "Delete from transaction_history where book_id=?1",nativeQuery = true)
    void deleteBookById(int bookID);

    @Query(value = "select * from transaction_history where emailid=?1 and title=?2 and action=?3",nativeQuery = true)
    List<TransactionHistory> getBorrowHistory(String emailid, String title,String action);

    @Query(value = "select * from transaction_history where emailid=?1 and title=?2 and action=?3 order by last_updated desc limit 1",nativeQuery = true)
    TransactionHistory getLastRowHistory(String emailid,String title,String action);
}
