package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;


/**
 * This is a Spring entity class.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name ="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String author;
    private int quantity_available;
    private String action;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id + System.lineSeparator()+
                ", title='" + title + '\''+ System.lineSeparator() +
                ", author='" + author + '\'' + System.lineSeparator()+
                ", quantity_available=" + quantity_available +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getQuantity_available() {
        return quantity_available;
    }


}
