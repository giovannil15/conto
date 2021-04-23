package it.logon.conto.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.logon.conto.ContoServiceImpl;
import it.logon.conto.dao.StoricoRepository;
import it.logon.conto.exception.ApiCustomError;
import it.logon.conto.models.BonificoRequestDTO;
import it.logon.conto.models.transazioni.TransazioneDTO;
import it.logon.conto.models.transazioni.TransazioniRequestDTO;

@RestController
public class AccountController {

	//private final NoteRepository noteRepository;
	private final StoricoRepository storicoRepository;
	
	//AccountController(NoteRepository repository) {
		//noteRepository = repository;
	//}
	
	AccountController(StoricoRepository repository){
		storicoRepository = repository;
	}
	
	@GetMapping("/saldo/{accountId}")
	double getSaldo(@PathVariable Long accountId) {
		
		ContoServiceImpl serviceImpl = new ContoServiceImpl(storicoRepository);
		return serviceImpl.saldo(accountId.toString());
	}
	
	@PostMapping("/bonifico")
	String bonifico(@RequestBody BonificoRequestDTO request) throws ApiCustomError {
		ContoServiceImpl serviceImpl = new ContoServiceImpl(storicoRepository);
		//String code = "API000";
	    //throw new ApiCustomError(code);
		return serviceImpl.bonifico(request);
	}
	
	@PostMapping("/transazioni/{accountId}") // GET with body
	List<TransazioneDTO> transazioni(
			@PathVariable Long accountId,
			@RequestBody TransazioniRequestDTO request) {
		
		ContoServiceImpl serviceImpl = new ContoServiceImpl(storicoRepository);
		return serviceImpl.transazioni(accountId.toString(), request.getDataInizio(), request.getDataFine());
	}
	
}
