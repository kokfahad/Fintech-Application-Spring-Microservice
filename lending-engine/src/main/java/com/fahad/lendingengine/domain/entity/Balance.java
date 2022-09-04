package com.fahad.lendingengine.domain.entity;

import com.fahad.lendingengine.Enum.Currency;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Balance {
    @Id
    @GeneratedValue
    private Long id;
    @ElementCollection
    @MapKeyClass(Currency.class)
    @OneToMany(targetEntity = Money.class, cascade = CascadeType.ALL)
    private Map<Currency, Money> moneyMap = new HashMap<>();

   public void topUp(final Money money){
       if (moneyMap.get(money.getCurrency()) == null)
           moneyMap.put(money.getCurrency() , money);
       else {
           moneyMap.put(money.getCurrency(), moneyMap.get(money.getCurrency())
                   .add(money));
       }
   }

   public void withdraw(final Money money){
       final Money moneyBalance = moneyMap.get(money.getCurrency());

       if (moneyBalance == null)
           throw new IllegalStateException();
       else {
           moneyMap.put(money.getCurrency(),
                   moneyMap.get(money.getCurrency()).minus(money));
       }
   }

    public Map<Currency, Money> getMoneyMap() {
        return moneyMap;
    }
}
