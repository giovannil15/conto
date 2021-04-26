package it.logon.conto.models.transazioni;

import java.time.LocalDate;

public class TransazioneDTO {
	
	public TransazioneDTO() {
		
	}
	
	private String transactionId;
	private String operationId;
	private LocalDate accountingDate;
	private LocalDate valueDate;
	private double amount;
	private String currency;
	private Enumeration type;
	private String description;
	
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getOperationId() {
		return operationId;
	}
	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}
	public LocalDate getAccountingDate() {
		return accountingDate;
	}
	public void setAccountingDate(LocalDate accountingDate) {
		this.accountingDate = accountingDate;
	}
	public LocalDate getValueDate() {
		return valueDate;
	}
	public void setValueDate(LocalDate valueDate) {
		this.valueDate = valueDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Enumeration getType() {
		return type;
	}
	public void setType(Enumeration type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "TransazioneDTO [transactionId=" + transactionId + ", operationId=" + operationId + ", accountingDate="
				+ accountingDate + ", valueDate=" + valueDate + ", amount=" + amount + ", currency=" + currency
				+ ", type=" + type + ", description=" + description + "]";
	}
	
	
	
}
