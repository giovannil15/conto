package it.logon.conto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.logon.conto.adapter.BankingAdapter;
import it.logon.conto.dao.StoricoRepository;
import it.logon.conto.dao.TransazioneEntity;
import it.logon.conto.exception.ApiCustomError;
import it.logon.conto.exception.ApiGenericError;
import it.logon.conto.exception.ApiHttpStatusError;
import it.logon.conto.models.bonifico.BonificoRequestDTO;
import it.logon.conto.models.saldo.SaldoDTO;
import it.logon.conto.models.transazioni.TransazioneDTO;

@Service
public class ContoServiceImpl implements ContoService {
	
	@Autowired
	private BankingAdapter bankingAdapter;
	
	@Autowired
	private StoricoRepository storicoRepository;
	
	@Autowired
	public ContoServiceImpl(StoricoRepository repository, BankingAdapter adapter) {
		storicoRepository = repository;
		bankingAdapter = adapter;
	}
	
	public ContoServiceImpl() {
		
	}
	
	@Override
	public SaldoDTO saldo(String accountId) {
		try {
			return bankingAdapter.saldo(accountId);
		} catch(ApiGenericError e) {
			throw new ApiGenericError(e.getMessage());
		} catch(ApiHttpStatusError e) {
			throw new ApiHttpStatusError(e.getMessage());
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public String bonifico(BonificoRequestDTO request) {
		try {
			return bankingAdapter.bonifico(request);
		}catch (ApiCustomError e) {
			throw new ApiCustomError(request.getAccountId().toString());
		} catch(ApiHttpStatusError e) {
			throw new ApiHttpStatusError(e.getMessage());
		}catch (Exception e) {
			throw e;
		}		
	}

	@Override
	public List<TransazioneDTO> transazioni(String accountId, LocalDate dataInizio, LocalDate dataFine) {
		try {
			List<TransazioneDTO> transazioni = bankingAdapter.transazioni(accountId, dataInizio, dataFine);

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
		} catch(ApiGenericError e) {
			throw new ApiGenericError(e.getMessage());
		} catch(ApiHttpStatusError e) {
			throw new ApiHttpStatusError(e.getMessage());
		} catch (Exception e) {
			throw e;
		}
	}
	
	
}
