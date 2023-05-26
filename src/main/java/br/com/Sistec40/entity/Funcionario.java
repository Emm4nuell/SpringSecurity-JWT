package br.com.Sistec40.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.Sistec40.enuns.RolesEnum;
import br.com.Sistec40.enuns.SexoEnum;
import br.com.Sistec40.enuns.StatusEnum;
import lombok.Data;

@Data
@Entity
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 50)
	private String nome;
	
	@Column(length = 11)
	@Enumerated(EnumType.STRING)
	private SexoEnum sexo;
	
	private LocalDate datanascimento;
	
	private LocalDate datacadastro;
	
	private String email;
	
	@Column(unique = true, length = 11)
	private String cpf;
	
	@Column(length = 10)
	private String rg;
	
	private String senha;
	
	@Enumerated(EnumType.STRING)
	private StatusEnum status;
	
	@Enumerated(EnumType.STRING)
	private RolesEnum roles;
}
