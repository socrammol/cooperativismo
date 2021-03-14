package br.com.cooperativismo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.cooperativismo.domain.Pauta;
import br.com.cooperativismo.domain.Usuario;
import br.com.cooperativismo.domain.Voto;
import br.com.cooperativismo.service.RetornoPautaDTO;

@Repository
public interface VotoRepository extends CrudRepository<Voto, Long>{

	Optional<Voto> findByUserAndPauta(Optional<Usuario> user, Optional<Pauta> pauta);

	

}
