package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


/*Book class implementation*/
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
}
