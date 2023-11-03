package org.example.repository;

import org.example.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is a Spring repository interface.
 * It provides methods and SQL queries related to students
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Transactional
    @Modifying
    @Query(value = "Insert into students(name,emailid,password,role) values(?1,?2,?3,?4)", nativeQuery = true)
    void registerStudent(String name,String emailid,String password,String role);

    @Transactional
    @Modifying
    @Query(value = "Insert into students(name,emailid) values(?1,?2)", nativeQuery = true)
    void saveStudent(String name,String emailid);

    @Transactional
    @Modifying
    @Query(value = "update students set name=?2,emailid=?3 where id=?1", nativeQuery = true)
    void updateStudent(int id,String name,String emailid);

    @Transactional
    @Modifying
    @Query(value = "Delete from students where id=?1", nativeQuery = true)
    void deleteStudentByID(int id);

    @Query(value = "Select * from students where emailid=?1", nativeQuery = true)
    Student findByUsername(String emailid);

    @Query(value = "Select * from students where emailid=?1", nativeQuery = true)
    Student findUserRole(String emailid);

//    @Query(nativeQuery = true, value = "select * from students where emailId=?1")
//    Student findByEmailId(String emailId);
//
//    @Query(nativeQuery = true, value = "select * from students where id=?1")
//    Student getStudentById(int id);

}