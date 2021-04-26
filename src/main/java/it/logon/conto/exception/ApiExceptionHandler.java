package it.logon.conto.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = {ApiCustomError.class})
    public ResponseEntity<Object> handleApiCustomError(
    		ApiCustomError ex, WebRequest request) {
		
		String code = "API000";
		String description = String.format("Errore tecnico  La condizione BP049 non e' prevista per il conto id %s", ex.getMessage());
		ErrorMessage errorMessage = new ErrorMessage(code, description);
			
    	return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(value = {ApiGenericError.class})
    public ResponseEntity<Object> handleApiGenericError(
    		ApiGenericError ex, WebRequest request) {
		
		String code = "API999";
		String description = String.format("Errore generico durante la chiamata al servizio esterno: %s", ex.getMessage());
		ErrorMessage errorMessage = new ErrorMessage(code, description);
			
    	return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(value = {ApiHttpStatusError.class})
    public ResponseEntity<Object> handleHttpStatusError(
    		ApiHttpStatusError ex, WebRequest request) {
		
		String code = "APISTATUS";
		String description = String.format("Errore durante la chiamata al servizio esterno %s", ex.getMessage());
		ErrorMessage errorMessage = new ErrorMessage(code, description);
			
    	return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
