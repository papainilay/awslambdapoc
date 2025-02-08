package com.poc.loan_management.service;

import com.poc.loan_management.entity.LoanApplication;
import com.poc.loan_management.repository.LoanRepository;
import com.poc.loan_management.utility.loanIdAutoGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Data
@Service
@NoArgsConstructor
@AllArgsConstructor
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;

    public LoanApplication saveLoan(LoanApplication loan){
        String loanId;
        do{
            loanId= loanIdAutoGenerator.getLoanId();
        }
        while
        (loanRepository.existsById(loanId));
        loan.setLoanId(loanId);
        return loanRepository.save(loan);
    }

    public LoanApplication getLoan(String loanId)
    {
        return loanRepository.findById(loanId).orElse(null);
    }
}
