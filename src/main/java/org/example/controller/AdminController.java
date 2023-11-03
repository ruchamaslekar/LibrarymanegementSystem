package org.example.controller;

import org.example.entity.Student;
import org.example.entity.TransactionHistory;
import org.example.repository.TransactionHistoryRepository;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private TransactionHistoryRepository transactionRepository;

    /** Method to add student */
    @PostMapping("/add")
    public String addStudent(String name,String emailId) {
        studentService.saveStudents(name,emailId);
        return "redirect:/student-list";
    }

    /** Method to delete student */
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id")int id) {
        studentService.deleteStudent(id);
        return "redirect:/student-list";
    }

    /** Method to edit student form */
    @GetMapping("/edit/{id}")
    public String getEditForm(@PathVariable("id")int id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student",student);
        return "/save-student";
    }

    /** Method to save student */
    @PostMapping("/save/{id}")
    public String getSaveForm(@PathVariable("id")int id,Model model,String name,String emailId) {
        studentService.updateStudent(id,name,emailId);
        return "redirect:/student-list";
    }

    /** Method to get student list */
    @PostMapping("/student-list")
    public String getStudentList(Model model) {
        List<Student> students = studentService.fetchStudentList();
        model.addAttribute("students", students);
        return "/student-list";
    }

    /** Method to get student form */
    @GetMapping("/student-list")
    public String getStudentListForm(Model model) {
        List<Student> students = studentService.fetchStudentList();
        model.addAttribute("students", students);
        return "/student-list";
    }

    /** Method to get add form */
    @GetMapping("/add")
    public String getAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "/add-student";
    }

    @PostMapping("/transaction-details")
    public String getTransactionDetails(Model model) {
        List<TransactionHistory> history = transactionRepository.findAll();
        model.addAttribute("history", history);
        return "/transaction-history";
    }

}
