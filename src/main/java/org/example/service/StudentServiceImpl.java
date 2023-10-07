package org.example.service;

import org.example.entity.Student;
import org.example.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

}