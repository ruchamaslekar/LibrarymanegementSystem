package org.example.controller;

import org.example.entity.Book;
import org.example.entity.Student;
import org.example.service.BookService;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * This is a Spring controller class.
 */
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("student", new Student());
        return "/student-registration";
    }

    @PostMapping("/register-done")
    public String registerStudent(String name,String emailId,String password, String role,Model model) {
        studentService.registerStudent(name,emailId,password,role);
        return "/registration-success";
    }

}
