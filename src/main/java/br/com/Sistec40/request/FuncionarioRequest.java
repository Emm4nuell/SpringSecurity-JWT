package br.com.Sistec40.request;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import br.com.Sistec40.enuns.RolesEnum;
import br.com.Sistec40.enuns.SexoEnum;
import br.com.Sistec40.enuns.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioRequest {
	
	@NotBlank(message = "Campo nome é obrigatório!")
	private String nome;
	private SexoEnum sexo;
	private String email;
	private LocalDate datanascimento;
	private LocalDate datacadastro;
	
	@NotBlank(message = "Campo cpf é obrigatório!")
	private String cpf;
	private String rg;
	
	@NotBlank(message = "Campo senha é obrigatório!")
	private String senha;
	private StatusEnum status;
	private RolesEnum roles;

}
