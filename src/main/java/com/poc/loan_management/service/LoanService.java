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

    @Autowired
    private SqsService sqsService;

    @Autowired
    private SnsService snsService;

    public LoanApplication saveLoan(LoanApplication loan){
        String loanId;
        do{
            loanId= loanIdAutoGenerator.getLoanId();
        }
        while
        (loanRepository.existsById(loanId));

        loan.setLoanId(loanId);
        LoanApplication savedLoan = loanRepository.save(loan);
        //send message to sqs
        sqsService.sendMessage("https://sqs.us-east-1.amazonaws.com/825765403933/LoanApplicationQueue", "Loan Application saved with ID: "+loan.getLoanId());

        //send message to sns
        snsService.publishMessage("arn:aws:sns:us-east-1:825765403933:LoanApplicationTopic", "Loan Application saved with ID: "+loan.getLoanId());

        return savedLoan;
    }

    public LoanApplication getLoan(String loanId)
    {
        return loanRepository.findById(loanId).orElse(null);
    }
}
