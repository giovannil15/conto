package it.logon.conto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.logon.conto.ContoServiceImpl;
import it.logon.conto.dao.StoricoRepository;
import it.logon.conto.exception.ApiCustomError;
import it.logon.conto.exception.ApiGenericError;
import it.logon.conto.exception.ApiHttpStatusError;
import it.logon.conto.models.bonifico.BonificoRequestDTO;
import it.logon.conto.models.saldo.SaldoDTO;
import it.logon.conto.models.transazioni.TransazioneDTO;
import it.logon.conto.models.transazioni.TransazioniRequestDTO;

@RestController
public class AccountController {
	
	private StoricoRepository storicoRepository;
	private ContoServiceImpl serviceImpl;
	
	@Autowired
	public AccountController(StoricoRepository repository, ContoServiceImpl service){
		storicoRepository = repository;
		serviceImpl = service;
	}
	
	public AccountController() {
	}
	
	@GetMapping("/saldo/{accountId}")
	public SaldoDTO getSaldo(@PathVariable Long accountId) {
		try {
			return serviceImpl.saldo(accountId.toString());
		} catch(ApiGenericError e) {
			throw new ApiGenericError(e.getMessage());
		} catch(ApiHttpStatusError e) {
			throw new ApiHttpStatusError(e.getMessage());
		} catch (Exception e) {
			throw e;
		}
	}
	
	@PostMapping("/bonifico")
	public String bonifico(@RequestBody BonificoRequestDTO request) {
		try {
			return serviceImpl.bonifico(request);
		} catch(ApiCustomError e) {
			throw new ApiCustomError(request.getAccountId().toString());
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	@PostMapping("/transazioni/{accountId}") // GET with body
	public List<TransazioneDTO> transazioni(
			@PathVariable Long accountId,
			@RequestBody TransazioniRequestDTO request) {
		try {
			return serviceImpl.transazioni(accountId.toString(), request.getDataInizio(), request.getDataFine());
		} catch(ApiGenericError e) {
			throw new ApiGenericError(e.getMessage());
		} catch(ApiHttpStatusError e) {
			throw new ApiHttpStatusError(e.getMessage());
		} catch (Exception e) {
			throw e;
		}
	}
	
}
