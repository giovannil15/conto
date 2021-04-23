package it.logon.conto.models.transazioni;

import java.time.LocalDate;

public class TransazioniRequestDTO {

	private LocalDate dataInizio;
	private LocalDate dataFine;
	
	public LocalDate getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}
	public LocalDate getDataFine() {
		return dataFine;
	}
	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}
	
	
}
