package br.com.Sistec40.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.Sistec40.entity.Funcionario;
import br.com.Sistec40.mapper.FuncionarioMapper;
import br.com.Sistec40.repository.FuncionarioRepository;
import br.com.Sistec40.request.FuncionarioRequest;
import br.com.Sistec40.response.FuncionarioResponse;
import br.com.Sistec40.security.jwt.JwtService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	/*Criptografar senha*/
	private BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*Desfaz se algo der errado*/
	@Transactional
	@Override
	public Funcionario save(FuncionarioRequest request) {
		request.setDatacadastro(LocalDate.now());
		request.setSenha(passwordEncoder().encode(request.getSenha()));
		return funcionarioRepository.save(FuncionarioMapper.toFuncionario(request));
	}

	@Override
	public List<FuncionarioResponse> list() {
		List<Funcionario> lista = funcionarioRepository.findAll();
		return FuncionarioMapper.toListResponse(lista);
	}

	@Override
	public FuncionarioResponse findById(Long id) {
		Funcionario funcionario = funcionarioRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados!")); 
		JwtService jwt = new JwtService();
		String token = jwt.gerarToken(funcionario);
		System.out.println(token);
		return FuncionarioMapper.toResponse(funcionario);
	}
	
	

}
