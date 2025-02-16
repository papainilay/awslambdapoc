package com.poc.loan_management.controller;

import com.poc.loan_management.entity.LoanApplication;
import com.poc.loan_management.service.LoanService;
import com.poc.loan_management.service.SnsService;
import com.poc.loan_management.service.SqsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    /*public LoanApplication saveLoan(@RequestBody LoanApplication loan) {
        return loanService.saveLoan(loan);
    }*/

    @PostMapping(consumes = "application/json", produces = "application/json")
    public LoanApplication saveLoan(@RequestBody LoanApplication loan) {
        return loanService.saveLoan(loan);
    }

    @GetMapping(value = "/{loanId}", produces = "application/json")
    public LoanApplication getLoan(@PathVariable String loanId) {
        return loanService.getLoan(loanId);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong - Hello, World!");
    }


}
