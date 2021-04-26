package it.logon.conto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import it.logon.conto.adapter.BankingAdapter;
import it.logon.conto.dao.StoricoRepository;
import it.logon.conto.dao.TransazioneEntity;
import it.logon.conto.models.bonifico.BonificoRequestDTO;
import it.logon.conto.models.saldo.SaldoDTO;
import it.logon.conto.models.transazioni.TransazioneDTO;

@SpringBootTest()
public class ServiceTest {
	
	@Autowired
	private ContoServiceImpl service;
	
	@MockBean
	private BankingAdapter adapter;
	
	@MockBean
	private StoricoRepository repository;
	
	private String accountId = "14537780";
	
	@Test
	public void saldo() throws Exception {
	    SaldoDTO saldo = new SaldoDTO();
        saldo.setAvailableBalance(2000);
        saldo.setBalance(2000);
        saldo.setCurrency("EUR");
        saldo.setDate(LocalDate.now());
        
        Mockito.when(adapter.saldo(accountId)).thenReturn(saldo);
        	
	    SaldoDTO saldoService = service.saldo(accountId);
	    
	    Assert.assertEquals(saldo, saldoService);
	}
	
	@Test
	public void bonifico() throws Exception {
		// stub di request
		BonificoRequestDTO request = new BonificoRequestDTO();
	    request.setAccountId(Long.valueOf(accountId));
	    request.setAmount("2000");
	    request.setCurrency("EUR");
	    request.setDescription("spese condominiali");
	    request.setExecutionDate(LocalDate.now().toString());
        request.setReceiverName("Mario Rossi");
        
        Mockito.when(adapter.bonifico(Mockito.any(BonificoRequestDTO.class))).thenReturn("OK");
        	
	    String esitoService = service.bonifico(request);
	    Assert.assertEquals("OK", esitoService);
	}
	
	@Test
	public void transazioni() throws Exception {
	    TransazioneEntity entity = new TransazioneEntity(123L, "123", "2000-01-01", "2000-01-01", 2000.0, "EUR");
        
	    List<TransazioneDTO> transazioni = new ArrayList<>();
		TransazioneDTO trans = new TransazioneDTO();
		trans.setTransactionId("123");
		transazioni.add(trans);
	    
	    Mockito.when(adapter.transazioni(Mockito.anyString(), Mockito.any(LocalDate.class), Mockito.any(LocalDate.class))).thenReturn(transazioni);
	    Mockito.when(repository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(entity));
        Mockito.when(repository.save(Mockito.any(TransazioneEntity.class))).thenReturn(entity);
        
		List<TransazioneDTO> transazioniService = service.transazioni("123", LocalDate.now(), LocalDate.now());

	    Assert.assertEquals(transazioni, transazioniService);
	}

}
