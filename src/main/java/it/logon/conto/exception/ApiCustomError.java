package it.logon.conto.exception;

@SuppressWarnings("serial")
public class ApiCustomError extends RuntimeException {
    
    public ApiCustomError(Throwable t) {
        super(t);
    }

    public ApiCustomError(String error) {
    	super(error);
    }
    
}
