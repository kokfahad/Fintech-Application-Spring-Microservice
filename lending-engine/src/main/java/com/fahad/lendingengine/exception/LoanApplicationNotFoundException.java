package com.fahad.lendingengine.exception;

public class LoanApplicationNotFoundException extends RuntimeException{

    public LoanApplicationNotFoundException(Long loanApplicationId) {
        super("Loan application with id: "+ loanApplicationId + " was not found");
    }
}
