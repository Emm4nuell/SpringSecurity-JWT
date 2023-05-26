package br.com.Sistec40.service;

import java.util.List;

import br.com.Sistec40.entity.Funcionario;
import br.com.Sistec40.request.FuncionarioRequest;
import br.com.Sistec40.response.FuncionarioResponse;

public interface FuncionarioService {
	
	Funcionario save(FuncionarioRequest request);
	
	List<FuncionarioResponse> list();
	
	FuncionarioResponse findById(Long id);

}
