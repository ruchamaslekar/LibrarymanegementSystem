package org.example.controller;
import java.util.List;
import org.example.entity.Student;
import org.example.repository.TransactionHistoryRepository;
import org.example.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TransactionHistoryRepository transactionRepository;


    private final Logger LOGGER =
            LoggerFactory.getLogger(StudentController.class);

    /** Method to get Student list from service
     * and navigate it to home html page
     */
    @PostMapping("/userHome")
    public String postUserHome(Model model) {
        return "/userHome";
    }
    @GetMapping("/userHome")
    public String getUserHome(Model model,HttpSession session) {
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
    public String displayLoginPage(@RequestParam String username, @RequestParam String password, Model model, HttpServletRequest request) {
        HttpSession hs = request.getSession();
        hs.setAttribute("username",username);
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