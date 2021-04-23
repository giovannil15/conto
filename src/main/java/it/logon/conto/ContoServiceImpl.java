package it.logon.conto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;

import it.logon.conto.adapter.BankingAdapter;
import it.logon.conto.dao.StoricoRepository;
import it.logon.conto.dao.TransazioneEntity;
import it.logon.conto.exception.ApiCustomError;
import it.logon.conto.models.BonificoRequestDTO;
import it.logon.conto.models.transazioni.TransazioneDTO;

public class ContoServiceImpl implements ContoService {
	
	private final BankingAdapter adapter = new BankingAdapter();
	private final StoricoRepository storicoRepository;
	
	public ContoServiceImpl(StoricoRepository repository) {
		storicoRepository = repository;
	}
	
	@Override
	public double saldo(String accountId) {
		try {
			return adapter.saldo(accountId);
		}
		catch (Exception e) {
			throw e;
		}
	}

	@Override
	public String bonifico(BonificoRequestDTO request) throws ApiCustomError {
		try {
			return adapter.bonifico(request);
		}
		catch (Exception e) {
			throw new ApiCustomError(request.getAccountId().toString());
		}
	}

	@Override
	public List<TransazioneDTO> transazioni(String accountId, LocalDate dataInizio, LocalDate dataFine) {
		try {
			List<TransazioneDTO> transazioni = adapter.transazioni(accountId, dataInizio, dataFine);
			
			transazioni.stream().forEach(t -> 
			{
				Long id = Long.valueOf(t.getTransactionId());
				Optional<TransazioneEntity> optTrans = storicoRepository.findById(id);
				if (optTrans.isEmpty()) {
					TransazioneEntity transazione = new TransazioneEntity(id, 
														t.getOperationId(), 
														t.getAccountingDate().toString(), 
														t.getValueDate().toString(), 
														t.getAmount(), 
														t.getCurrency());
					storicoRepository.save(transazione);
				}
							
			});
						
			return transazioni;
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	
}
