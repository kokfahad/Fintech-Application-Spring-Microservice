package com.fahad.lendingengine.controller;

import com.fahad.lendingengine.Service.LoanAplicationAdapter;
import com.fahad.lendingengine.Service.LoanService;
import com.fahad.lendingengine.Service.TokenValidationService;
import com.fahad.lendingengine.applicatioon.model.LoanRequest;
import com.fahad.lendingengine.domain.entity.Loan;
import com.fahad.lendingengine.domain.entity.LoanApplication;
import com.fahad.lendingengine.domain.entity.User;
import com.fahad.lendingengine.repository.LoanApplicationRepository;
import com.fahad.lendingengine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private TokenValidationService tokenValidationService;

//    @PostMapping(value = "/loan/request")
//    public void requestLoan(@RequestBody LoanRequest loanRequest, HttpServletRequest request){
//        tokenValidationService.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
//        LoanApplication loanApplication = loanAplicationAdapter.transform(loanRequest);
//        loanApplicationRepository.save(loanApplication);
//    }
    @PostMapping(value = "/loan/request")
    public void requestLoan(@RequestBody LoanRequest loanRequest, HttpServletRequest request){
        User borrower= tokenValidationService.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
        LoanApplication loanApplication = loanAplicationAdapter.transform(loanRequest,borrower);
        loanApplicationRepository.save(loanApplication);
    }
    @GetMapping(value = "/loan/requests")
    public List<LoanApplication> findAllLoanApplications(HttpServletRequest request){
        tokenValidationService.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
        return loanApplicationRepository.findAll();
    }

    @GetMapping(value = "/users")
    public List<User> findUsers(HttpServletRequest request){
        tokenValidationService.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
        return userRepository.findAll();
    }

//    @PostMapping(value = "/loan/accept/{lenderId}/{loanApplicationId}")
//    public void acceptLoan(@PathVariable final String lenderId,
//                           @PathVariable final String loanApplicationId,
//                           HttpServletRequest request){
//        tokenValidationService.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
//        loanService.acceptLoan(Long.parseLong(lenderId),Long.parseLong(loanApplicationId));
//    }

    @PostMapping(value = "/loan/accept/{loanApplicationId}")
    public void acceptLoan(@PathVariable final String loanApplicationId,
                           HttpServletRequest request){
        User lender = tokenValidationService.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
        loanService.acceptLoan(Long.parseLong(loanApplicationId),lender.getUsername());
    }


    @GetMapping(value = "/loans")
    public List<Loan> getLoans(HttpServletRequest request){
        tokenValidationService.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
        return loanService.getLoans();
    }
}
