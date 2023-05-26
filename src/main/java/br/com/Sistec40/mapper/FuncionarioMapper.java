package br.com.Sistec40.mapper;

import java.util.ArrayList;
import java.util.List;

import br.com.Sistec40.entity.Funcionario;
import br.com.Sistec40.request.FuncionarioRequest;
import br.com.Sistec40.response.FuncionarioResponse;

public class FuncionarioMapper {
	
	public static Funcionario toFuncionario(FuncionarioRequest request) {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(request.getNome());
		funcionario.setSexo(request.getSexo());
		funcionario.setDatanascimento(request.getDatanascimento());
		funcionario.setDatacadastro(request.getDatacadastro());
		funcionario.setEmail(request.getEmail());
		funcionario.setCpf(request.getCpf());
		funcionario.setRg(request.getRg());
		funcionario.setSenha(request.getSenha());
		funcionario.setStatus(request.getStatus());
		funcionario.setRoles(request.getRoles());
		return funcionario;
	}
	
	public static FuncionarioResponse toResponse(Funcionario funcionario) {
		FuncionarioResponse response = new FuncionarioResponse();
		response.setId(funcionario.getId());
		response.setNome(funcionario.getNome());
		response.setSenha(funcionario.getSenha());
		response.setDatanascimento(funcionario.getDatanascimento());
		response.setDatacadastro(funcionario.getDatacadastro());
		response.setEmail(funcionario.getEmail());
		response.setCpf(funcionario.getCpf());
		response.setRg(funcionario.getRg());
		response.setSenha(funcionario.getSenha());
		response.setStatus(funcionario.getStatus());
		return response;
	}
	
	public static List<FuncionarioResponse> toListResponse(List<Funcionario> funcionarios){
		List<FuncionarioResponse> list = new ArrayList<FuncionarioResponse>();
		for (Funcionario funcionario : funcionarios) {
			list.add(toResponse(funcionario));
		}
		return list;
	}

}
