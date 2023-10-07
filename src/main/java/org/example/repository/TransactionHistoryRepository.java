package org.example.repository;

import org.example.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Integer> {

    @Query(value = "select * from transaction_history where title=?1",nativeQuery = true)
    public List<TransactionHistory> getTransactionHistory(String title);

    @Modifying
    @Transactional
    @Query(value = "insert into transaction_history (book_id,title,action) values (?1,?2,?3)" ,nativeQuery = true)
    void  updateTransactionHistory(int bookId, String title, String action);
}
