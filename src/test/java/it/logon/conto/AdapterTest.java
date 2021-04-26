package it.logon.conto;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;

import it.logon.conto.adapter.BankingAdapter;
import it.logon.conto.exception.ApiCustomError;
import it.logon.conto.models.bonifico.BonificoRequestDTO;
import it.logon.conto.models.saldo.SaldoDTO;
import it.logon.conto.models.transazioni.TransazioneDTO;

@SpringBootTest()
public class AdapterTest {

	    private BankingAdapter adapter = new BankingAdapter();
	    private BonificoRequestDTO bonificoRequest = new BonificoRequestDTO();
	    
		private String accountId = "14537780";

	    
	    @Test                                                                                          
	    public void saldo() throws JsonProcessingException, URISyntaxException {   
	    	
	    	SaldoDTO saldo = adapter.saldo(accountId);
	    	
	    	Assert.assertNotNull(saldo);
	    }
	    
	    @Test
	    public void transazioni() { 
			
	    	List<TransazioneDTO> transazioni =	adapter.transazioni(accountId, LocalDate.parse("2019-01-01", DateTimeFormatter.ISO_DATE), 
						LocalDate.parse("2019-12-01", DateTimeFormatter.ISO_DATE));
			
			Assert.assertNotNull(transazioni);
	    }
	    
	    @Test
	    public void bonifico() { 
			
	    	bonificoRequest.setAccountId(Long.valueOf(accountId));
			
	    	Assertions.assertThrows(ApiCustomError.class, () -> {
				adapter.bonifico(bonificoRequest);
			});
	    }
	    
	    
	
}
