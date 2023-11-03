package org.example.service;

import org.example.entity.Student;
import org.example.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a Spring service class.
 * It provides methods related to students
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    private final Logger LOGGER =
            LoggerFactory.getLogger(StudentService.class);

    @Override
    public List<Student> fetchStudentList() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public void saveStudents(String name,String emailid) {
        studentRepository.saveStudent(name,emailid);
    }

    @Override
    public void updateStudent(int id,String name,String emailid) {
        studentRepository.updateStudent(id,name,emailid);
    }

    @Override
    public void deleteStudent(Integer id) {
        studentRepository.deleteStudentByID(id);
    }

    public void registerStudent(String name,String emailId,String password,String role) {
    studentRepository.registerStudent(name,emailId,password,role);
    }
//    @Override
//    public UserDetails loadUserByUsername(String emailid) throws UsernameNotFoundException {
//        Student student = studentRepository.findByUsername(emailid);
//        if (student == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//        return new org.springframework.security.core.userdetails.User(student.getEmailId(), student.getPassword(), new ArrayList<>());
//    }

    @Override
    public boolean isValidUser(String emailid, String password) {
        Student user = studentRepository.findByUsername(emailid);

        if (user != null && user.getPassword().equals(password)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isStudent(String emailid, String password) {
        Student user = studentRepository.findUserRole(emailid);

        if (user != null && user.getRole().equals("student")) {
            return true;
        }

        return false;
    }

}