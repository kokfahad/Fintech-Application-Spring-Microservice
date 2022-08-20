package com.fahad.lendingengine.Service;

import com.fahad.lendingengine.applicatioon.model.LoanRequest;
import com.fahad.lendingengine.domain.entity.LoanApplication;
import com.fahad.lendingengine.domain.entity.User;
import com.fahad.lendingengine.exception.UserNotFoundException;
import com.fahad.lendingengine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
public class LoanAplicationAdapter {

    @Autowired
    private UserRepository userRepository;

    public LoanApplication transform(LoanRequest request){
        Optional<User>userOptional = userRepository.findById(request.getBorrowerId());
        if (userOptional.isPresent()){
            return new LoanApplication(request.getAmount(),userOptional.get(),
                    request.getDaysToRepay(), request.getInterestRate());
        }else {
               throw new UserNotFoundException(request.getBorrowerId());
        }
    }


}
