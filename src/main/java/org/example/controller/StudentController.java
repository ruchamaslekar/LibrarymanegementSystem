package org.example.controller;

import org.example.entity.Student;
import org.example.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    private final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);
    @GetMapping("/students")
    public List<Student> getStudents() {
        LOGGER.info("Inside getUsers of UserController");
    return studentService.fetchUserList();
    }
    @GetMapping("/user_login")
    public String studentLogin(String username, String password) {
        if(username.equals("xyz") && password.equals("abc")) {
            return "login success";
        }
    return "login not success";
    }

}
