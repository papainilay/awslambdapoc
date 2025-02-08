package com.poc.loan_management.repository;

import com.poc.loan_management.entity.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<LoanApplication, String> {
}
