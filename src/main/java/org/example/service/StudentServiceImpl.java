package org.example.service;

import org.example.entity.Student;
import org.example.entity.TransactionHistory;
import org.example.repository.StudentRepository;
import org.example.repository.TransactionHistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

/**
 * This is a Spring service class.
 * It provides methods related to students
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    /** A method to fetch student list from repository */
    @Override
    public List<Student> fetchStudentList() {
        return studentRepository.findAll();
    }

    /** A method to get student by id from repository */
    @Override
    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    /** A method to save students to database using repository */
    @Override
    public void saveStudents(String name,String emailid) {
        studentRepository.saveStudent(name,emailid);
    }

    /** A method to update student details in database by repository */
    @Override
    public void updateStudent(int id,String name,String emailid) {
        studentRepository.updateStudent(id,name,emailid);
    }

    /** A method to delete student from database by repository */
    @Override
    public void deleteStudent(Integer id) {
        studentRepository.deleteStudentByID(id);
    }

    /** A method to register student in database by repository */
    public void registerStudent(String name,String emailId,String password,String role) {
    studentRepository.registerStudent(name,emailId,password,role);
    }

    /** A method to validate user  */
    @Override
    public boolean isValidUser(String emailid, String password) {
        Student user = studentRepository.findByUsername(emailid);

        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    /** A method to check if the user is student or not  */
    @Override
    public boolean isStudent(String emailid, String password) {
        Student user = studentRepository.findUserRole(emailid);
        if (user != null && user.getRole().equals("student")) {
            return true;
        }
        return false;
    }

    /** A method to check due date of the books for returning  */
    public String checkDueDate(String emailid,String title){
        TransactionHistory borrowHistory = transactionHistoryRepository.getLastRowHistory(emailid,title,"borrowed");
        TransactionHistory returnHistory = transactionHistoryRepository.getLastRowHistory(emailid,title,"returned");
        LocalDate date1 = borrowHistory.getDue_date();
        LocalDate date2 = returnHistory.getDate().toLocalDateTime().toLocalDate();
        if(date1.isBefore(date2)){
            transactionHistoryRepository.addFine(5,emailid);
            return "Book returned Successfully! But you have returned the book after the due date.So need to pay $5 fine";
        }
        return "Book returned Successfully! No fine!";
    }
}