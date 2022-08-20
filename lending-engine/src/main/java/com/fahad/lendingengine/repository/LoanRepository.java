package com.fahad.lendingengine.repository;

import com.fahad.lendingengine.domain.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan,Long> {
}
