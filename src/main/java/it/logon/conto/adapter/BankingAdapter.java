package it.logon.conto.adapter;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import it.logon.conto.exception.ApiCustomError;
import it.logon.conto.exception.ApiGenericError;
import it.logon.conto.exception.ApiHttpStatusError;
import it.logon.conto.models.bonifico.BonificoRequestDTO;
import it.logon.conto.models.bonifico.BonificoResponse;
import it.logon.conto.models.saldo.SaldoDTO;
import it.logon.conto.models.saldo.SaldoResponse;
import it.logon.conto.models.transazioni.TransazioneDTO;
import it.logon.conto.models.transazioni.TransazioniResponse;

@Component
public class BankingAdapter {
	
	@Autowired
	public BankingAdapter() {	
	}
	
	private final String domain = "https://sandbox.platfr.io";
	
	public SaldoDTO saldo(String accountId) {
		try {
	    	String url = String.format("%s/api/gbs/banking/v4.0/accounts/%s/balance",domain,accountId.toString());
	    	HttpEntity<String> entity = new HttpEntity<>("parameters", getHeaders());
	    	RestTemplate restTemplate = new RestTemplate();
	    	ResponseEntity<SaldoResponse> respEntity = restTemplate.exchange(url, HttpMethod.GET, entity, SaldoResponse.class);
			if (respEntity.getStatusCode().equals(HttpStatus.OK) && respEntity.getBody().getStatus().equals("OK")) {
				return respEntity.getBody().getPayload();
			}
			else
				throw new ApiGenericError("lettura saldo");
		} catch(HttpClientErrorException e) {
			throw new ApiHttpStatusError(String.format("lettura saldo con http status %s ", e.getStatusCode().toString()));
		} catch(Exception e) {
			throw e;
		}
	}
	
	public String bonifico(BonificoRequestDTO request) {
		try {
			String url = String.format("%s/api/gbs/banking/v4.0/accounts/%s/payments/money-transfers", domain,
					request.getAccountId().toString());
			HttpEntity<BonificoRequestDTO> entity = new HttpEntity<>(request, getHeaders());
	    	RestTemplate restTemplate = new RestTemplate();
	    	ResponseEntity<BonificoResponse> respEntity = restTemplate.exchange(url, HttpMethod.POST, entity, BonificoResponse.class);
			if (respEntity.getStatusCode().equals(HttpStatus.OK) && respEntity.getBody().getStatus().equals("OK")) {
				return respEntity.getBody().getStatus();
			}
			else 
				throw new ApiCustomError(request.getAccountId().toString());
		} catch(RestClientException e) {
			throw new ApiCustomError(request.getAccountId().toString());
		} catch(Exception e) {
			throw e;
		}
	}
	
	public List<TransazioneDTO> transazioni(String accountId, LocalDate dataInizio, LocalDate dataFine) {
		try {
	    	String url = String.format("%s/api/gbs/banking/v4.0/accounts/%s/transactions?fromAccountingDate=%s&toAccountingDate=%s",
	    			domain,accountId.toString(),dataInizio.toString(), dataFine.toString());
	    	HttpEntity<String> entity = new HttpEntity<>("parameters", getHeaders());
	    	RestTemplate restTemplate = new RestTemplate();
	    	ResponseEntity<TransazioniResponse> respEntity = restTemplate.exchange(url, HttpMethod.GET, entity, TransazioniResponse.class);
			if (respEntity.getStatusCode().equals(HttpStatus.OK) && respEntity.getBody().getStatus().equals("OK")) {
				return respEntity.getBody().getPayload().getList();
			}
			else
				throw new ApiGenericError("lettura transazioni");
		} catch(HttpClientErrorException e) {
			throw new ApiHttpStatusError(String.format("lettura transazioni con http status %s ", e.getStatusCode().toString()));
		} catch (Exception e) {
			throw e;
		}
	}	
	
	
	private HttpHeaders getHeaders() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.set("Auth-Schema", "S2S");
    	headers.set("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
    	return headers;
	}
}
