package br.com.cooperativismo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.cooperativismo.domain.Usuario;

@Repository
public interface UsuarioRepository  extends CrudRepository<Usuario, Long>{

	Optional<Usuario> findByCpf(String cpf);

	

	

}
