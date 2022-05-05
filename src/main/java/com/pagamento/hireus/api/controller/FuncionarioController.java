package com.pagamento.hireus.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pagamento.hireus.api.model.FuncionarioInputModel;
import com.pagamento.hireus.api.model.FuncionarioOutputModel;
import com.pagamento.hireus.domain.model.Funcionario;
import com.pagamento.hireus.domain.repository.FuncionarioRepository;
import com.pagamento.hireus.domain.service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<FuncionarioOutputModel> listarFuncionario(){
		return funcionarioService.toCollectionModel(funcionarioRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FuncionarioOutputModel> buscarFuncionarioId(@PathVariable Long id){
		return funcionarioService.buscarFuncionarioIdService(id);
	}
	
	@GetMapping("/nome/{nomeFunc}")
	public List<FuncionarioOutputModel> listarFuncionarioNome(@PathVariable String nomeFunc){
		return funcionarioService.toCollectionModel(funcionarioRepository.findByNomeFuncionarioContaining(nomeFunc));
	}
	
	@GetMapping("/mat/{matFunc}")
	public List<FuncionarioOutputModel> listarFuncionarioMatricula(@PathVariable String matFunc){
		return  funcionarioService.toCollectionModel(funcionarioRepository.findByMatriculaFuncionarioContaining(matFunc));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Funcionario> salvarFuncionario(@Valid @RequestBody FuncionarioInputModel funcionarioInputModel) {
		return funcionarioService.salvarFuncionarioService(funcionarioInputModel);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Funcionario> excluirFuncionario(@PathVariable Long id) {		
		return funcionarioService.excluirFuncionarioService(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Funcionario> atualizarFuncionario(@PathVariable Long id,
			@Valid @RequestBody FuncionarioInputModel funcionarioInputModel) {
		return funcionarioService.atualizarFuncionarioService(id, funcionarioInputModel);
	}
	
	@PutMapping("/desligar/{id}")
	public ResponseEntity<Funcionario> desligarFuncionario(@PathVariable Long id, @Valid @RequestBody FuncionarioInputModel funcionarioInputModel) {
		return funcionarioService.desligarFuncionarioService(id);
	}
}
