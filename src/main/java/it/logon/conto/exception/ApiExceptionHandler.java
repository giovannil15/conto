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

}
