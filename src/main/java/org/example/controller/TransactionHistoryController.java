package org.example.controller;


import org.example.entity.TransactionHistory;
import org.example.repository.BookRepository;
import org.example.repository.StudentRepository;
import org.example.repository.TransactionHistoryRepository;
import org.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TransactionHistoryController {

    @Autowired
    private TransactionHistoryRepository transactionRepository;
    @Autowired
    private BookService bookService;
    @Autowired
    private StudentRepository studentRepository;


    /** Method to get transaction history from service
     * and navigate it to transaction-history html page
     */
    @GetMapping("/user-transaction")
    public String showTransactionHistory(int student_id, Model model) {
        List<TransactionHistory> history = transactionRepository.getTransactionHistory(student_id);
        model.addAttribute("history",history);
        return "/user-transaction";
    }

    @PostMapping("/transaction-history")
    public String viewTransactionHistory(String emailid,Model model) {
        // Fetch the transactions for the specified student
//        List<TransactionHistory> transactions = transactionRepository.findAll();
//        model.addAttribute("history", transactions);
        return "/transaction-history";
    }


}
