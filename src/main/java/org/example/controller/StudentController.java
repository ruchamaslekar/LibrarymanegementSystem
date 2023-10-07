package org.example.controller;

import org.example.entity.Student;
import org.example.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger LOGGER =
            LoggerFactory.getLogger(StudentController.class);

    /** Method to get Student list from service
     * and navigate it to home html page
     */
    @PostMapping("/home")
    public String displayHomePage(Model model) {
        List<Student> studentList = studentService.fetchStudentList();
        model.addAttribute("list",studentList);
//        LOGGER.info("Inside Home: ");
        return "home";
    }

    /** Method to navigate to login page
     */
    @GetMapping("/login")
    public String displayLoginPage()
    {
        return "login";
    }

}
