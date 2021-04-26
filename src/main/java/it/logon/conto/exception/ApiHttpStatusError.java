package it.logon.conto.exception;

@SuppressWarnings("serial")
public class ApiHttpStatusError extends RuntimeException {
		
    public ApiHttpStatusError(Throwable t) {
        super(t);
    }

    public ApiHttpStatusError(String error) {
    	super(error);
    }

}
