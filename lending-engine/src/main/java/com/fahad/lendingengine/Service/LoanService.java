package com.fahad.lendingengine.Service;

import com.fahad.lendingengine.domain.entity.Loan;
import com.fahad.lendingengine.domain.entity.LoanApplication;
import com.fahad.lendingengine.domain.entity.User;
import com.fahad.lendingengine.exception.LoanApplicationNotFoundException;
import com.fahad.lendingengine.exception.UserNotFoundException;
import com.fahad.lendingengine.repository.LoanApplicationRepository;
import com.fahad.lendingengine.repository.LoanRepository;
import com.fahad.lendingengine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class LoanService {
    @Autowired
    private LoanApplicationRepository loanApplicationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoanRepository loanRepository;

    public void acceptLoan(final Long loanApplicationId, final Long lenderId){
        User lender = userRepository.findById(lenderId).
                orElseThrow(()-> new UserNotFoundException(lenderId));
        LoanApplication loanApplication = loanApplicationRepository.findById(loanApplicationId).
                orElseThrow(()-> new LoanApplicationNotFoundException(loanApplicationId));
        loanRepository.save(new Loan(lender,loanApplication));
    }

    public List<Loan> getLoans(){
        return loanRepository.findAll();
    }

}
