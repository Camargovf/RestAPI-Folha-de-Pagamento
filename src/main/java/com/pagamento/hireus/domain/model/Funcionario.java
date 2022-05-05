package com.pagamento.hireus.domain.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 70)
	private String nomeFuncionario;
	@Column(nullable = false, unique = true, length = 10)
	private String matriculaFuncionario;
	@Column(nullable = false)
	private Boolean ativo;
	@Column(nullable = false)
	private OffsetDateTime dataAdimissao;
	private OffsetDateTime dataDesligamento;
	@ManyToOne
	private Cargo cargo;
	@JsonIgnore
	@OneToMany(mappedBy = "funcionario")
	private List<FolhaPagamento> folhaPagamento = new ArrayList<>();


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((matriculaFuncionario == null) ? 0 : matriculaFuncionario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (matriculaFuncionario == null) {
			if (other.matriculaFuncionario != null)
				return false;
		} else if (!matriculaFuncionario.equals(other.matriculaFuncionario))
			return false;
		return true;
	}

}
