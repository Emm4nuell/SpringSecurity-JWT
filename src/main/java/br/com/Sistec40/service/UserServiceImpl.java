package br.com.Sistec40.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.Sistec40.entity.Funcionario;
import br.com.Sistec40.exception.SenhaInvalidaException;
import br.com.Sistec40.repository.FuncionarioRepository;

@Service
public class UserServiceImpl implements UserDetailsService {
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public UserDetails autenticar(Funcionario funcionario) {
		UserDetails user = loadUserByUsername(funcionario.getCpf());
		//comparador se senhas
		boolean comparadordesenhas = encoder.matches(funcionario.getSenha(), user.getPassword());
		if(comparadordesenhas) {
			return user;
		}
		throw new SenhaInvalidaException("Senha inválida!");
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Funcionario funcionario = funcionarioRepository.findByCpf(username)
		.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados!"));
		
		return User.builder()
				.username(funcionario.getCpf())
				.password(funcionario.getSenha())
				.roles(funcionario.getRoles().name())
				.build();
			
	}

}
