package com.pagamento.hireus.api.controller;

import java.util.List;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

@Api(value = "API REST Cargos")
@RestController
@RequestMapping("/cargos")
public class CargoController {
	
	@Autowired
	private CargoRepository cargoRepository;
	
	@Autowired
	private CargoService cargoService;

	@ApiOperation(value = "Retorna todos os cargos")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<CargoOutputModel> listarCargo(){
		return cargoService.toCollectionModel(cargoRepository.findAll());
	}

	@ApiOperation(value = "Retorna um id relacionado ao cargo")
	@GetMapping("/{id}")
	public ResponseEntity<CargoOutputModel> buscarCargoId(@PathVariable Long id){
		return cargoService.buscarCargoIdService(id);
	}

	@ApiOperation(value = "Cria um cargo")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Cargo> salvarCargo(@Valid @RequestBody CargoInputModel cargoInputModel) {
		return cargoService.salvarCargoService(cargoInputModel);
	}

	@ApiOperation(value = "Deleta um cargo")
	@DeleteMapping("/{id}")
	public ResponseEntity<Cargo> excluirCargo(@PathVariable Long id) {
		return cargoService.excluirCargoService(id);
	}

	@ApiOperation(value = "Atualiza um cargo")
	@PutMapping("/{id}")
	public ResponseEntity<Cargo> atualizarCargo(@PathVariable Long id, 
			@Valid @RequestBody CargoInputModel cargoInputModel) {
		return cargoService.atualizarCargoService(id, cargoInputModel);
	}
	
}
