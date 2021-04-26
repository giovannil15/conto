package it.logon.conto.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoricoRepository extends CrudRepository<TransazioneEntity,Long>{

}
