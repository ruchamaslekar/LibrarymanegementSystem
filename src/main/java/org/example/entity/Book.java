package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id + System.lineSeparator()+
                ", title='" + title + '\''+ System.lineSeparator() +
                ", author='" + author + '\'' + System.lineSeparator()+
                ", quantity_available=" + quantity_available +
                '}';
    }

    private String author;
    private int quantity_available;
}
