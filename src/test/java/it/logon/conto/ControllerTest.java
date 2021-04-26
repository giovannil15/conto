package it.logon.conto;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.logon.conto.controller.AccountController;
import it.logon.conto.dao.StoricoRepository;
import it.logon.conto.models.bonifico.BonificoRequestDTO;
import it.logon.conto.models.saldo.SaldoDTO;
import it.logon.conto.models.transazioni.TransazioneDTO;
import it.logon.conto.models.transazioni.TransazioniRequestDTO;


@WebMvcTest(controllers = AccountController.class)
public class ControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private ContoServiceImpl service;
	
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

	    when(service.saldo(accountId)).thenReturn(saldo);

	    MvcResult mvcResult = mockMvc.perform(get(String.format("/saldo/%s/", accountId))
	            .contentType("application/json")).andReturn();
	
	    System.out.println(String.format("response saldo: %s",mvcResult.getResponse().getContentAsString()));
	    Assert.assertEquals(200, mvcResult.getResponse().getStatus());
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

	    when(service.bonifico(any(BonificoRequestDTO.class))).thenReturn("OK");

	    MvcResult mvcResult = mockMvc.perform(post("/bonifico")
	    	    .content(objectMapper.writeValueAsString(request))
	            .contentType("application/json")).andReturn();
	    
	    System.out.println(String.format("request bonifico: %s", mvcResult.getRequest().getContentAsString()));
	    System.out.println(String.format("response bonifico: %s",mvcResult.getResponse().getContentAsString()));
	    Assert.assertEquals(200, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void transazioni() throws Exception {
		// stub di request
		TransazioniRequestDTO request = new TransazioniRequestDTO();
		request.setDataInizio(LocalDate.now());
		request.setDataFine(LocalDate.now());
		
		// stub di response
		List<TransazioneDTO> transazioni = new ArrayList<>();
		TransazioneDTO transazione = new TransazioneDTO();
		transazione.setAccountingDate(LocalDate.now());
		transazione.setAmount(2000);
		transazione.setCurrency("EUR");
		transazione.setDescription("spese varie");
		transazioni.add(transazione);

	    when(service.transazioni(any(String.class), any(LocalDate.class), any(LocalDate.class))).thenReturn(transazioni);

	    MvcResult mvcResult = mockMvc.perform(post("/transazioni/14537780")
	    	    .content(objectMapper.writeValueAsString(request))
	            .contentType("application/json")).andReturn();
	    
	    System.out.println(String.format("request transazioni: %s", mvcResult.getRequest().getContentAsString()));
	    System.out.println(String.format("response transazioni: %s", mvcResult.getResponse().getContentAsString()));
	    Assert.assertEquals(200, mvcResult.getResponse().getStatus());
	}	
	
}