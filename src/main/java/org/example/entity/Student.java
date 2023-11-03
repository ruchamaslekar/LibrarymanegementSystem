package org.example.entity;

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
@Builder
@Table(name ="students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String emailid;
    private String password;
    private String name;
    private String role;
    public Student(Integer id, String emailid, String password, String name, String role) {
        this.id = id;
        this.emailid = emailid;
        this.password = password;
        this.name = name;
        this.role = "ROLE_USER";
    }

    public String getRole() {
        return role;
    }

    public int getId() {
        return id;
    }

    public String getEmailId() {
        return emailid;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + id +
                ", emailId='" + emailid + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
        }
    }

