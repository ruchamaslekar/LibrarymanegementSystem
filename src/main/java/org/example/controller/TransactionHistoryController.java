package org.example.controller;


import org.example.entity.TransactionHistory;
import org.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class TransactionHistoryController {

    @Autowired
    private BookService bookService;

    /** Method to get transaction history from service
     * and navigate it to transaction-history html page
     */
    @GetMapping("/transaction-history")
    public String showTransactionHistory(String title, Model model) {
        List<TransactionHistory> historyList = bookService.showTransactionHistory(title);
        model.addAttribute("history",historyList);
        return "transaction-history";
    }

}
