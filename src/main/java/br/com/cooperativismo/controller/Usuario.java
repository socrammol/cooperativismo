package br.com.cooperativismo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.cooperativismo.domain.dto.BaseResponseDTO;
import br.com.cooperativismo.domain.dto.CadastroUsuarioDTO;
import br.com.cooperativismo.service.UsuarioService;

@RestController
@RequestMapping("/usuario")

public class Usuario extends BaseController {
	@Autowired
	UsuarioService usuarioService;

	@PostMapping(path = "/cadastro")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<BaseResponseDTO> cadastrar(@RequestBody CadastroUsuarioDTO cadastro) {
		try {
			return ok(usuarioService.cadastro(cadastro));
		} catch (Exception e) {
			return error(e.getMessage());
		}
	}
	@GetMapping(path = "/busca/cpf")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<BaseResponseDTO> buscar(@PathVariable String cpf) {
		try {
			return ok(usuarioService.buscaUnica(cpf));
		} catch (Exception e) {
			return error(e.getMessage());
		}
	}
	
	@GetMapping(path = "busca/")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<BaseResponseDTO> buscar() {
		try {
			return ok(usuarioService.bucasrTodos());
		} catch (Exception e) {
			return error(e.getMessage());
		}
	}
	

}
