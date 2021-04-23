package it.logon.conto.exception;

import org.springframework.http.HttpStatus;

public class ApiCustomError extends RuntimeException {
	private HttpStatus status;
    private String code;
    private String description;
    
    public ApiCustomError(Throwable t) {
        super(t);
    }

    public ApiCustomError(String prova) {
    	super(prova);
    }
    
    public ApiCustomError(HttpStatus status, String code, String description) {
        this.status = status;
        this.code = code;
        this.description = description;
    }
    
    public ApiCustomError(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
