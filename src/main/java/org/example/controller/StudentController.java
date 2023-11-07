package org.example.controller;

import org.example.entity.Student;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * This is a Spring controller class.
 */
@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    /** Method to register a new student */
    @PostMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("student", new Student());
        return "/student-registration";
    }

    /** Method to call registration-success html page */
    @PostMapping("/register-done")
    public String registerStudent(String name,String emailId,String password, String role,Model model) {
        studentService.registerStudent(name,emailId,password,role);
        return "/registration-success";
    }

}
