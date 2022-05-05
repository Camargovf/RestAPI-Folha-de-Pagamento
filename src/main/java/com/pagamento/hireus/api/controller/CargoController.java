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

import com.pagamento.hireus.api.model.CargoInputModel;
import com.pagamento.hireus.api.model.CargoOutputModel;
import com.pagamento.hireus.domain.model.Cargo;
import com.pagamento.hireus.domain.repository.CargoRepository;
import com.pagamento.hireus.domain.service.CargoService;

@RestController
@RequestMapping("/cargos")
public class CargoController {
	
	@Autowired
	private CargoRepository cargoRepository;
	
	@Autowired
	private CargoService cargoService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<CargoOutputModel> listarCargo(){
		return cargoService.toCollectionModel(cargoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CargoOutputModel> buscarCargoId(@PathVariable Long id){
		return cargoService.buscarCargoIdService(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Cargo> salvarCargo(@Valid @RequestBody CargoInputModel cargoInputModel) {
		return cargoService.salvarCargoService(cargoInputModel);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Cargo> excluirCargo(@PathVariable Long id) {
		return cargoService.excluirCargoService(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cargo> atualizarCargo(@PathVariable Long id, 
			@Valid @RequestBody CargoInputModel cargoInputModel) {
		return cargoService.atualizarCargoService(id, cargoInputModel);
	}
	
}
