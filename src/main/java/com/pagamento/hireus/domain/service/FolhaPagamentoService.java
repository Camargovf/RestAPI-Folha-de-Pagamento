package com.pagamento.hireus.domain.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pagamento.hireus.api.exceptionhandler.EmptyRecurseException;
import com.pagamento.hireus.api.exceptionhandler.FuncionarioFiredException;
import com.pagamento.hireus.api.model.FolhaPagamentoInputModel;
import com.pagamento.hireus.api.model.FolhaPagamentoOutputModel;
import com.pagamento.hireus.domain.model.FolhaPagamento;
import com.pagamento.hireus.domain.model.Funcionario;
import com.pagamento.hireus.domain.repository.FolhaPagamentoRepository;

@Service
public class FolhaPagamentoService {
	

	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private FolhaPagamentoRepository pagamentoRepository; 
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ResponseEntity<FolhaPagamento> gerarFolhaService(FolhaPagamentoInputModel folhaPagamentoInputModel){
		FolhaPagamento folhaPagamento = toEntity(folhaPagamentoInputModel);
		Long idFuncionarioBuscado = folhaPagamento.getFuncionario().getId();
		Funcionario funcionarioBuscado = funcionarioService.buscarFuncionarioPeloId(idFuncionarioBuscado);
		
		if(funcionarioBuscado.getAtivo().equals(false)) {
			throw new FuncionarioFiredException("Não é possível gerar uma folha de pagamento com um funcionário inativo.");
		}

		folhaPagamento.setFuncionario(funcionarioBuscado);
		folhaPagamento.setInss();
		folhaPagamento.setIrrf();
		folhaPagamento.setSalarioLiquido();
		
		pagamentoRepository.save(folhaPagamento);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(folhaPagamento.getId())
				.toUri();
		return ResponseEntity.created(uri).body(folhaPagamento);
	}
	
	public ResponseEntity<FolhaPagamentoOutputModel> buscarPagamentoIdService(Long id) {
		FolhaPagamento folhaPagamento = buscarFolhaPagamentoPeloId(id);
		return ResponseEntity.ok(toModel(folhaPagamento));
	}

	private FolhaPagamento buscarFolhaPagamentoPeloId(Long id) {
		Optional<FolhaPagamento> pagamento = pagamentoRepository.findById(id);
		if (pagamento.isEmpty()) {
			throw new EmptyRecurseException("Folha de pagamento não encontrado!");
		}
		return pagamento.get();
	}
	
	public FolhaPagamentoOutputModel toModel(FolhaPagamento folhaPagamento) {
		return modelMapper.map(folhaPagamento, FolhaPagamentoOutputModel.class);
	}

	public List<FolhaPagamentoOutputModel> toCollectionModel(List<FolhaPagamento> folhaPagamento) {
		return folhaPagamento.stream().map(this::toModel).collect(Collectors.toList());
	}

	public FolhaPagamento toEntity(FolhaPagamentoInputModel folhaPagamentoInputModel) {
		return modelMapper.map(folhaPagamentoInputModel, FolhaPagamento.class);

	}	
}
