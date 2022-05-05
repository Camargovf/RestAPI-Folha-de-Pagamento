package com.pagamento.hireus.domain.service;

import java.math.BigDecimal;
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
import com.pagamento.hireus.api.exceptionhandler.ValueMostBePositiveExcpetion;
import com.pagamento.hireus.api.model.CargoInputModel;
import com.pagamento.hireus.api.model.CargoOutputModel;
import com.pagamento.hireus.domain.model.Cargo;
import com.pagamento.hireus.domain.repository.CargoRepository;

@Service
public class CargoService {

	@Autowired
	private CargoRepository cargoRepository;
	
	@Autowired
	private ModelMapper modelMapper;	
	
	public ResponseEntity<CargoOutputModel> buscarCargoIdService(Long id) {
		Cargo cargo = buscarCargoPeloId(id);
		return ResponseEntity.ok(toModel(cargo));

	}
	
	public ResponseEntity<Cargo> salvarCargoService(CargoInputModel cargoInputModel) {
		Cargo cargo = toEntity(cargoInputModel);
		checarValorSalario(cargo.getSalarioCargo());
		checarValorHoras(cargo.getTotalHorasCargo());
		cargo = cargoRepository.save(cargo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(cargo.getId())
				.toUri();
		return ResponseEntity.created(uri).body(cargo);
	}
	
	public ResponseEntity<Cargo> atualizarCargoService(Long id, CargoInputModel cargoInputModel) {
		Cargo cargo = buscarCargoPeloId(id);
		cargo = toEntity(cargoInputModel);
		checarValorSalario(cargo.getSalarioCargo());
		checarValorHoras(cargo.getTotalHorasCargo());
		
		cargo.setId(id);
		cargo = cargoRepository.save(cargo);

		return ResponseEntity.ok(cargo);
	}
	
	public ResponseEntity<Cargo> excluirCargoService(Long id) {
		Cargo cargo = buscarCargoPeloId(id);
		cargoRepository.deleteById(cargo.getId());
		return ResponseEntity.noContent().build();
	}
	
	public CargoOutputModel toModel(Cargo cargo) {
		return modelMapper.map(cargo, CargoOutputModel.class);
	}
	
	public List<CargoOutputModel> toCollectionModel(List<Cargo> cargos){
		return cargos.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	
	public Cargo toEntity(CargoInputModel cargoInputModel) {
		return modelMapper.map(cargoInputModel, Cargo.class);
	}
	
	public Cargo buscarCargoPeloId(Long id) {
		Optional<Cargo> cargo = cargoRepository.findById(id);
		if (cargo.isEmpty()) {
			throw new EmptyRecurseException("Cargo não encontrado!");
		}
		return cargo.get();
	}
	
	private void checarValorSalario(BigDecimal valor) {
		if (valor.compareTo(BigDecimal.ZERO) <= 0) {
			throw new ValueMostBePositiveExcpetion("O valor informado para o salario tem que ser maior que 0");
		}
	}
	
	private void checarValorHoras (Integer valor) {
		if (valor <= 0) {
			throw new ValueMostBePositiveExcpetion("O valor informado para a total horas tem que ser maior que 0");
		}
	}
}