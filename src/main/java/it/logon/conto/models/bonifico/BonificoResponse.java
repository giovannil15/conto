package it.logon.conto.models.bonifico;

import java.util.List;

public class BonificoResponse {

	private Object payload;
	private List<Object> error;
	private String status;
	
	
	public Object getPayload() {
		return payload;
	}
	public void setPayload(Object payload) {
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
