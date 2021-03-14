package br.com.cooperativismo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.cooperativismo.domain.Pauta;

@Repository
public interface PautaRepository  extends CrudRepository<Pauta, Long>{

	List<Pauta> findByAtivo(boolean b);

}
