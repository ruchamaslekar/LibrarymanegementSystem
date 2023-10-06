package org.example.repository;

import org.example.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

/**
 * This is a Spring repository interface.
 * It provides methods and SQL queries related to students
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query(nativeQuery = true, value = "select * from users where emailid=?1")
    Student findByName(String emailid);


}