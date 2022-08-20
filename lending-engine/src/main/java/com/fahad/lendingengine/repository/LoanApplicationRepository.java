package com.fahad.lendingengine.repository;

import com.fahad.lendingengine.domain.entity.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
}
