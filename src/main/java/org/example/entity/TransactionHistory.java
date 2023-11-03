package org.example.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name ="transaction_history")
public class TransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int student_id;
    private int bookId;
    public enum Action{borrowed,returned};
    @Enumerated(EnumType.STRING)
    private Action action;

    @Column(name = "last_updated")
    private Timestamp date;
    private String title;
    private String emailid;

    public TransactionHistory(int id, int bookId, Action action,Timestamp date,String title,int student_id,String emailid) {
        this.id = id;
        this.bookId = bookId;
        this.action = action;
        this.date = date;
        this.title = title;
        this.student_id = student_id;
        this.emailid= emailid;
    }

    public int getId() {
        return id;
    }

    public int getBookId() {
        return bookId;
    }

    public Timestamp getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public Action getAction() {
        return action;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
