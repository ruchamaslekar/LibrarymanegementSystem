package org.example.service;

import org.example.entity.Student;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Interface for StudentService
 */
@Service
public interface StudentService {
     public List<Student> fetchStudentList();

}