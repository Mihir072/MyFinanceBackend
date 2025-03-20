package com.mihir.MyFinace.controllers;

import com.mihir.MyFinace.services.FinancialSummary;
import com.mihir.MyFinace.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @GetMapping("/summary/{userId}")
    public FinancialSummary getFinancialSummary(@PathVariable String userId) {
        return reportService.generateFinancialSummary(userId); // ✅ Correct method name
    }
}
