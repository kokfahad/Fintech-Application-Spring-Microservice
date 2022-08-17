package com.fahad.lendingengine.controller;

import com.fahad.lendingengine.applicatioon.model.LoanRequest;
import com.fahad.lendingengine.domain.entity.LoanApplication;
import com.fahad.lendingengine.domain.entity.User;
import com.fahad.lendingengine.repository.LoanRequestRepository;
import com.fahad.lendingengine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoanController {
    @Autowired
    private LoanRequestRepository loanRequestRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/loan/request")
    public void requestLoan(@RequestBody final LoanRequest loanApplication){
        System.out.println(loanApplication);
    }

    @GetMapping(value = "/users")
    public List<User> findUsers(){
        return userRepository.findAll();
    }
}
