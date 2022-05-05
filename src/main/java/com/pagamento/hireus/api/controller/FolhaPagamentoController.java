package com.pagamento.hireus.api.controller;

import java.util.List;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

import com.pagamento.hireus.api.model.FolhaPagamentoInputModel;
import com.pagamento.hireus.api.model.FolhaPagamentoOutputModel;
import com.pagamento.hireus.domain.model.FolhaPagamento;
import com.pagamento.hireus.domain.repository.FolhaPagamentoRepository;
import com.pagamento.hireus.domain.service.FolhaPagamentoService;

@Api(value = "API REST Folha de Pagamentos")
@RestController
@RequestMapping("/pagamento")
public class FolhaPagamentoController {
	
	@Autowired
	FolhaPagamentoService pagamentoService;
	
	@Autowired
	FolhaPagamentoRepository pagamentoRepository;

	@ApiOperation(value = "Lista Folhas de Pagamentos de todos funcionários")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<FolhaPagamentoOutputModel> listarFolhasPagamentos(){
		return pagamentoService.toCollectionModel(pagamentoRepository.findAll());
	}

	@ApiOperation(value = "Lista Folha de Pagamento de um id relacionado a um funcionário")
	@GetMapping("/{id}")
	public ResponseEntity<FolhaPagamentoOutputModel> buscarFolhaPagamentoId(@PathVariable Long id){
		return pagamentoService.buscarPagamentoIdService(id);
	}

	@ApiOperation(value = "Cria Folha de Pagamento de um id relacionado a um funcionário")
	@PostMapping
	public ResponseEntity<FolhaPagamento> gerarFolha(@Valid @RequestBody FolhaPagamentoInputModel folhaPagamentoInputModel) {
		return pagamentoService.gerarFolhaService(folhaPagamentoInputModel);
	}	
}
