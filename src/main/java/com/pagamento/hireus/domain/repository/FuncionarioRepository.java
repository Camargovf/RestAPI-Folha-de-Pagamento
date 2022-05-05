package com.pagamento.hireus.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pagamento.hireus.domain.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

	List<Funcionario> findByNomeFuncionarioContaining(@Param("nome") String nome);
	List<Funcionario> findByMatriculaFuncionarioContaining(@Param("matricula") String matriculaFuncionario);
	Optional<Funcionario> findByMatriculaFuncionario(@Param("matricula") String matriculaFuncionaario);
}
