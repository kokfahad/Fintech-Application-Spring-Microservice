package com.fahad.lendingengine.Service;

import com.fahad.lendingengine.domain.entity.Money;
import com.fahad.lendingengine.domain.entity.User;
import com.fahad.lendingengine.exception.UserNotFoundException;
import com.fahad.lendingengine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BalanceService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void topUpBalance(final Money money, String authToken){
        User user = findUser(authToken);
        user.topUp(money);
    }

    @Transactional
    public void withdrawFromBalance(final Money money, String authToken){
        User user = findUser(authToken);
        user.withDraw(money);
    }

    private User findUser(String authToken) {
        return userRepository.findById(authToken).orElseThrow(() ->
                new UserNotFoundException(authToken));
    }
}
