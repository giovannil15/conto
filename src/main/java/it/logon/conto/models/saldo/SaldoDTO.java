package it.logon.conto.models.saldo;

import java.time.LocalDate;

public class SaldoDTO {
	
	public SaldoDTO() {
		
	}
	
	private LocalDate date;
	private double balance;
	private double availableBalance;
	private String currency;
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(double availableBalance) {
		this.availableBalance = availableBalance;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	
}
