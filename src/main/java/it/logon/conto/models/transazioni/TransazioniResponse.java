package it.logon.conto.models.transazioni;

import java.util.List;

public class TransazioniResponse {

	private ListaTransazioni payload;
	private List<Object> error;
	private String status;
	
	public TransazioniResponse() {
		
	}
	
	public ListaTransazioni getPayload() {
		return payload;
	}
	public void setPayload(ListaTransazioni payload) {
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
