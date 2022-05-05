package com.pagamento.hireus.domain.service;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pagamento.hireus.api.exceptionhandler.EmptyRecurseException;
import com.pagamento.hireus.api.exceptionhandler.FuncionarioAlreadyExistingException;
import com.pagamento.hireus.api.model.FuncionarioInputModel;
import com.pagamento.hireus.api.model.FuncionarioOutputModel;
import com.pagamento.hireus.domain.model.Cargo;
import com.pagamento.hireus.domain.model.Funcionario;
import com.pagamento.hireus.domain.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CargoService cargoService;

	public ResponseEntity<FuncionarioOutputModel> buscarFuncionarioIdService(Long id) {
		Funcionario funcionario = buscarFuncionarioPeloId(id);
		return ResponseEntity.ok(toModel(funcionario));
	}

	public ResponseEntity<Funcionario> salvarFuncionarioService(FuncionarioInputModel funcionarioInputModel) {
		Funcionario funcionario = toEntity(funcionarioInputModel);
		checarMatriculaFuncionario(funcionario);
		Cargo cargo = cargoService.buscarCargoPeloId(funcionarioInputModel.getCargoId());
		funcionario.setDataAdimissao(OffsetDateTime.now());
		funcionario.setAtivo(true);
		funcionario.setCargo(cargo);
		funcionario = funcionarioRepository.save(funcionario);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(funcionario.getId())
				.toUri();
		return ResponseEntity.created(uri).body(funcionario);
	}

	public ResponseEntity<Funcionario> excluirFuncionarioService(Long id) {
		Funcionario funcionario = buscarFuncionarioPeloId(id);
		funcionarioRepository.deleteById(funcionario.getId());
		return ResponseEntity.noContent().build();
	}

	public ResponseEntity<Funcionario> atualizarFuncionarioService(Long id,
			FuncionarioInputModel funcionarioInputModel) {
		Funcionario funcionario = buscarFuncionarioPeloId(id);

		funcionario.setId(id);
		funcionario.setNomeFuncionario(funcionarioInputModel.getNomeFuncionario());
		funcionario.setMatriculaFuncionario(funcionarioInputModel.getMatriculaFuncionario());
		Cargo cargo = cargoService.buscarCargoPeloId(funcionarioInputModel.getCargoId());
		funcionario.setCargo(cargo);
		checarMatriculaFuncionario(funcionario);
		funcionario = funcionarioRepository.save(funcionario);

		return ResponseEntity.ok(funcionario);
	}

	public FuncionarioOutputModel toModel(Funcionario funcionario) {
		return modelMapper.map(funcionario, FuncionarioOutputModel.class);
	}

	public List<FuncionarioOutputModel> toCollectionModel(List<Funcionario> funcionarios) {
		return funcionarios.stream().map(this::toModel).collect(Collectors.toList());
	}

	private Funcionario toEntity(FuncionarioInputModel funcionarioInputModel) {
		return modelMapper.map(funcionarioInputModel, Funcionario.class);
	}

	public Funcionario buscarFuncionarioPeloId(Long id) {
		Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
		if (funcionario.isEmpty()) {
			throw new EmptyRecurseException("Funcionário não encontrado!");
		}
		return funcionario.get();
	}

	private void checarMatriculaFuncionario(Funcionario funcionario) {
		boolean matriculaLocalizada = funcionarioRepository.findByMatriculaFuncionario(funcionario.getMatriculaFuncionario())
				.stream().anyMatch(funcionarioExistente -> !funcionarioExistente.equals(funcionario));
		if (matriculaLocalizada) {
			throw new FuncionarioAlreadyExistingException("Já existe um funcionário com essa matricula!");			
		}
	}

	public ResponseEntity<Funcionario> desligarFuncionarioService(Long id) {
		Funcionario funcionario = buscarFuncionarioPeloId(id);
		funcionario.setAtivo(false);
		funcionario.setDataDesligamento(OffsetDateTime.now());
		funcionarioRepository.save(funcionario);
		return ResponseEntity.ok(funcionario);
	}

}
