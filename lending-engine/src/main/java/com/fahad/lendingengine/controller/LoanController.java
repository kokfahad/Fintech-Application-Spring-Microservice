package com.fahad.lendingengine.controller;

import com.fahad.lendingengine.Service.LoanAplicationAdapter;
import com.fahad.lendingengine.Service.LoanService;
import com.fahad.lendingengine.applicatioon.model.LoanRequest;
import com.fahad.lendingengine.domain.entity.Loan;
import com.fahad.lendingengine.domain.entity.LoanApplication;
import com.fahad.lendingengine.domain.entity.User;
import com.fahad.lendingengine.repository.LoanApplicationRepository;
import com.fahad.lendingengine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoanController {
    @Autowired
    private LoanApplicationRepository loanApplicationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoanAplicationAdapter loanAplicationAdapter;
    @Autowired
    private LoanService loanService;

    @PostMapping(value = "/loan/request")
    public void requestLoan(@RequestBody LoanRequest loanRequest){
        LoanApplication loanApplication = loanAplicationAdapter.transform(loanRequest);
        loanApplicationRepository.save(loanApplication);
    }
    @GetMapping(value = "/loan/requests")
    public List<LoanApplication> findAllLoanApplications(){
        return loanApplicationRepository.findAll();
    }

    @GetMapping(value = "/users")
    public List<User> findUsers(){
        return userRepository.findAll();
    }

    @PostMapping(value = "/loan/accept/{lenderId}/{loanApplicationId}")
    public void acceptLoan(@PathVariable final String lenderId,
                           @PathVariable final String loanApplicationId){
        loanService.acceptLoan(Long.parseLong(lenderId),Long.parseLong(loanApplicationId));
    }

    @GetMapping(value = "/loans")
    public List<Loan> getLoans(){
        return loanService.getLoans();
    }
}
