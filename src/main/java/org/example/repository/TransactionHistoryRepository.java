package org.example.repository;

import org.example.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Integer> {
    @Modifying
    @Transactional
    @Query(value = "insert into transaction_history (book_id,student_id,action,title,emailid) values (?1,?2,?3,?4,?5)" ,nativeQuery = true)
    void  updateTransactionHistory(int bookId,int student_id,String action, String title,String emailid);

//    @Query(value = "select * from transaction_history",nativeQuery = true)
//    List<TransactionHistory> findStudentById();
//
    @Query(value = "select * from transaction_history where student_id=?1",nativeQuery = true)
    List<TransactionHistory> getTransactionHistory(int student_id);

    @Modifying
    @Transactional
    @Query(value = "Delete from transaction_history where book_id=?1",nativeQuery = true)
    void deleteBookById(int bookID);
}
