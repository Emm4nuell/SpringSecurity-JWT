package br.com.Sistec40.response;

import java.time.LocalDate;

import br.com.Sistec40.enuns.RolesEnum;
import br.com.Sistec40.enuns.SexoEnum;
import br.com.Sistec40.enuns.StatusEnum;
import lombok.Data;

@Data
public class FuncionarioResponse {

	private Long id;
	private String nome;
	private SexoEnum sexo;
	private String email;
	private LocalDate datanascimento;
	private LocalDate datacadastro;
	private String cpf;
	private String rg;
	private String senha;
	private StatusEnum status;
	private RolesEnum roles;
}
