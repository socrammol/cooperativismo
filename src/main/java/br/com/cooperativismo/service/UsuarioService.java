package br.com.cooperativismo.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.cooperativismo.domain.Usuario;
import br.com.cooperativismo.domain.dto.CadastroUsuarioDTO;
import br.com.cooperativismo.repository.UsuarioRepository;

@Service
public class UsuarioService {
	private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);
	private static final String USUARIO_JA_CADASTRADO = "Usuario ja cadastrado em nossa base de dados!";
	private static final String ERRO_CPF = "CPF invalido";
	private static final String USUARIO_NAO_CADASTRADO = "Usuario não se enconta na base , favor cadastrar";
	@Autowired
	UsuarioRepository usuarioRepository;

	public Object cadastro(CadastroUsuarioDTO cadastro) {
		cadastro.setCpf(formataDadosCpf(cadastro.getCpf()));
		Optional<Usuario> usuario = usuarioRepository.findByCpf(cadastro.getCpf());
		if (usuario.isPresent()) {
			logger.info("Usuario com cpf:" + cadastro.getCpf() + " ja esta cadastrado");
			throw new IllegalArgumentException(USUARIO_JA_CADASTRADO);
		}
		logger.info("Validando cpf:" + cadastro.getCpf());
		validaCpf(cadastro.getCpf());
		logger.info("Usuario com cpf:"+cadastro.getCpf()+" cadastrado com sucesso");
		return usuarioRepository.save(dtoToEntity(cadastro));
	}

	private Usuario dtoToEntity(CadastroUsuarioDTO cadastro) {
		Usuario user = new Usuario();
		user.setNome(cadastro.getNome());
		user.setCpf(cadastro.getCpf());
		return user;
	}

	private void validaCpf(String cpf) {
		RestTemplate restTemplate = new RestTemplate();
		final String uri = "https://user-info.herokuapp.com/users/" + cpf;
		try {
			ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new IllegalArgumentException(ERRO_CPF);
		}
	}

	private String formataDadosCpf(String cpf) {
		cpf = cpf.replaceAll("[^0-9]+", "");
		return cpf;
	}

	public Object buscaUnica(String cpf) {
		cpf = formataDadosCpf(cpf);
		Optional<Usuario> usuario = usuarioRepository.findByCpf(cpf);
		if (usuario.isPresent()) {
			return usuario.get();
		} else {
			logger.error("Usuario nao cadastrado");
			throw new IllegalArgumentException(USUARIO_NAO_CADASTRADO);
		}
	}

	public Object bucasrTodos() {
		return usuarioRepository.findAll();
	}

}
