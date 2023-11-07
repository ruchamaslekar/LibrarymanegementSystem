package org.example.controller;

import org.example.entity.TransactionHistory;
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

    /** Method to get transaction history from service
     * and navigate it to user-transaction html page
     */
    @GetMapping("/user-transaction")
    public String showTransactionHistory(Model model,HttpSession session) {
        Object username = session.getAttribute("username");
        List<TransactionHistory> history = transactionRepository.getTransactionHistory(username.toString());
        model.addAttribute("history",history);
        return "/user-transaction";
    }

    /** Method to navigate it to transaction-history html page */
    @PostMapping("/transaction-history")
    public String viewTransactionHistory(Model model) {
        return "/transaction-history";
    }


}
