package it.logon.conto.exception;

@SuppressWarnings("serial")
public class ApiGenericError extends RuntimeException {
    
    public ApiGenericError(Throwable t) {
        super(t);
    }

    public ApiGenericError(String error) {
    	super(error);
    }

}
