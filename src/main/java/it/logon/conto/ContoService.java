package it.logon.conto;

import java.time.LocalDate;
import java.util.List;

import it.logon.conto.exception.ApiCustomError;
import it.logon.conto.models.bonifico.BonificoRequestDTO;
import it.logon.conto.models.saldo.SaldoDTO;
import it.logon.conto.models.transazioni.TransazioneDTO;

public interface ContoService {
	
	SaldoDTO saldo(String accountId);
	String bonifico(BonificoRequestDTO request) throws ApiCustomError;
	List<TransazioneDTO> transazioni(String accountId, LocalDate dataInizio, LocalDate dataFine);
}
