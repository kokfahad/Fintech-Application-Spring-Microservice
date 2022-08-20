package com.fahad.lendingengine.domain.entity;

import javax.persistence.*;
import java.time.Duration;
import java.util.Objects;

@Entity
public final class LoanApplication {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private int amount;
    @ManyToOne
    private User borrower;
    private int repaymentTermInDays;
    private double interestRate;

    public  LoanApplication() {
    }

    public Long getId() {
        return id;
    }

    public LoanApplication(int amount, User borrower, int repaymentTermInDays,
                           double interestRate) {
        this.amount = amount;
        this.borrower = borrower;
        this.repaymentTermInDays = repaymentTermInDays;
        this.interestRate = interestRate;
    }

    public int getAmount() {
        return amount;
    }

    public User getBorrower() {
        return borrower;
    }

    public int getRepaymentTerm() {
        return repaymentTermInDays;
    }

    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanApplication that = (LoanApplication) o;
        return amount == that.amount && Double.compare(that.interestRate, interestRate) == 0 && Objects.equals(borrower, that.borrower) && Objects.equals(repaymentTermInDays, that.repaymentTermInDays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, borrower, repaymentTermInDays, interestRate);
    }

    @Override
    public String toString() {
        return "LoanRequest{" +
                "amount=" + amount +
                ", borrower=" + borrower +
                ", repaymentTerm=" + repaymentTermInDays +
                ", interestRate=" + interestRate +
                '}';
    }
}
