package org.example.controller;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.example.entity.Student;
import org.example.repository.StudentRepository;
import org.example.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private StudentService studentService;

    private StudentRepository studentRepository;

    private final Logger LOGGER =
            LoggerFactory.getLogger(StudentController.class);

    /** Method to get Student list from service
     * and navigate it to home html page
     */
    @PostMapping("/userHome")
    public String postUserHome(Model model) {
////        List<Student> studentList = studentService.fetchStudentList();
////        model.addAttribute("list",studentList);
////        if(studentList == null)
////        {
////            model.addAttribute(" No student available");
////        }
//        Student student = studentRepository.findByUsername(username);
//        model.addAttribute("student",student);
        return "/userHome";
    }
    @GetMapping("/userHome")
    public String getUserHome(Model model) {
        return "/userHome";
    }

    @PostMapping("/adminHome")
    public String postAdminHome(Model model) {
        List<Student> studentList = studentService.fetchStudentList();
        model.addAttribute("list",studentList);
        if(studentList == null)
        {
            model.addAttribute(" No student available");
        }
        return "/adminHome";
    }
    @GetMapping("/adminHome")
    public String getAdminHome(Model model) {
        List<Student> studentList = studentService.fetchStudentList();
        model.addAttribute("list",studentList);
        if(studentList == null)
        {
            model.addAttribute(" No student available");
        }
        return "/adminHome";
    }

    @GetMapping("/login")
    public String displayLoginPage1()
    {
        return "/login";
    }

    /** Method to navigate to login page
     */
    @PostMapping("/login")
    public String displayLoginPage(@RequestParam String username, @RequestParam String password, Model model) {
        if (studentService.isValidUser(username, password)) {
            if(studentService.isStudent(username,password)) {
                return "redirect:/userHome";
            }
            else{
                return "redirect:/adminHome";
            }
        } else{
            model.addAttribute("error", "Invalid credentials. Please try again.");
            return "/login";
        }
    }

//    @GetMapping("/login")
//    public String getLoginDetails(@RequestParam String username, @RequestParam String password, Model model) {
//        if (studentService.isValidUser(username, password)) {
//            if(studentService.isStudent(username,password)) {
//                return "redirect:/userHome";
//            }
//            else{
//                return "redirect:/adminHome";
//            }
//        } else{
//            model.addAttribute("error", "Invalid credentials. Please try again.");
//            return "/login";
//        }
//    }

}