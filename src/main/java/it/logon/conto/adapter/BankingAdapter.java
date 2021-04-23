package it.logon.conto.adapter;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import it.logon.conto.models.BonificoRequestDTO;
import it.logon.conto.models.BonificoResponse;
import it.logon.conto.models.saldo.SaldoResponse;
import it.logon.conto.models.transazioni.TransazioneDTO;
import it.logon.conto.models.transazioni.TransazioniResponse;

public class BankingAdapter {
	
	private final String domain = "https://sandbox.platfr.io";
	
	public double saldo(String accountId) {
    	String url = String.format("%s/api/gbs/banking/v4.0/accounts/%s/balance",domain,accountId.toString());
    	HttpEntity<String> entity = getHeaders();
    	RestTemplate restTemplate = new RestTemplate();
    	ResponseEntity<SaldoResponse> respEntity = restTemplate.exchange(url, HttpMethod.GET, entity, SaldoResponse.class);
		return respEntity.getBody().getPayload().getAvailableBalance();
	}
	
	public String bonifico(BonificoRequestDTO request) {
    	//String url = String.format("%s/api/gbs/banking/v4.0/accounts/%s/payments/money-transfers",domain,accountId.toString());
    	String url = "provabhociaonebei123421";
		HttpEntity<String> entity = getHeaders();
    	RestTemplate restTemplate = new RestTemplate();
    	ResponseEntity<BonificoResponse> respEntity = restTemplate.exchange(url, HttpMethod.POST, entity, BonificoResponse.class);
		return respEntity.getBody().getEsito();
	}
	
	public List<TransazioneDTO> transazioni(String accountId, LocalDate dataInizio, LocalDate dataFine) {
    	String url = String.format("%s/api/gbs/banking/v4.0/accounts/%s/transactions?fromAccountingDate=%s&toAccountingDate=%s",
    			domain,accountId.toString(),dataInizio.toString(), dataFine.toString());
    	HttpEntity<String> entity = getHeaders();
    	RestTemplate restTemplate = new RestTemplate();
    	ResponseEntity<TransazioniResponse> respEntity = restTemplate.exchange(url, HttpMethod.GET, entity, TransazioniResponse.class);
		return respEntity.getBody().getPayload().getList();
	}	
	
	private HttpEntity<String> getHeaders() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.set("Auth-Schema", "S2S");
    	headers.set("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
    	return new HttpEntity<String>("parameters", headers);
	}
	
	
}
