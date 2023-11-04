package org.example.service;

import org.example.entity.Student;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Interface for StudentService
 */
@Service
public interface StudentService {
     public List<Student> fetchStudentList();
     public Student getStudentById(int id);
     public void saveStudents(String name,String emailid);
     public void deleteStudent(Integer id);
     public void registerStudent(String name,String emailId,String password,String role);
     public boolean isValidUser(String emailid, String password);
     public boolean isStudent(String emailid, String password);
     public void updateStudent(int id,String name,String emailid);
     public String checkDueDate(String emailid,String title);

}