package com.fahad.lendingengine.controller;

import com.fahad.lendingengine.Service.BalanceService;
import com.fahad.lendingengine.domain.entity.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/balance")
public class BalanceController {
    @Autowired
    private BalanceService balanceService;

    @PostMapping("/topUp")
    public void topUp(final @RequestBody Money money,
                      @RequestHeader("Authorization") String authorization){
        balanceService.topUpBalance(money, authorization);
    }

    @PostMapping("/withdraw")
    public void withdraw(final @RequestBody Money money,
                         @RequestHeader("Authorization") String authorization){
        balanceService.withdrawFromBalance(money, authorization);

    }


}
