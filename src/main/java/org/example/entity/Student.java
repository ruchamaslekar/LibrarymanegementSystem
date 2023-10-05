package org.example.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/* Student class implementation */
@Entity
@Data
@NoArgsConstructor
@Builder
@Table(name ="users")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String emailid;
    private String password;
    private String name;
    public Student(Integer id, String emailid, String password, String name) {
        this.id = id;
        this.emailid = emailid;
        this.password = password;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", emailid='" + emailid + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }



    }

