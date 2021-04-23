package it.logon.conto.models.saldo;

import java.util.List;

public class SaldoResponse {

	private SaldoDTO payload;
	private List<Object> error;
	private String status;
	
	public SaldoResponse() {
		
	}
	
	public SaldoDTO getPayload() {
		return payload;
	}
	public void setPayload(SaldoDTO payload) {
		this.payload = payload;
	}
	public List<Object> getError() {
		return error;
	}
	public void setError(List<Object> error) {
		this.error = error;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
