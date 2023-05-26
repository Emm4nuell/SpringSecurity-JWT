package br.com.Sistec40.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.Sistec40.entity.Funcionario;
import br.com.Sistec40.exception.ApiErrors;
import br.com.Sistec40.exception.SenhaInvalidaException;
import br.com.Sistec40.request.FuncionarioRequest;
import br.com.Sistec40.response.FuncionarioResponse;
import br.com.Sistec40.security.jwt.JwtService;
import br.com.Sistec40.service.FuncionarioService;
import br.com.Sistec40.service.UserServiceImpl;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private JwtService jwtservice;
	
	@PostMapping("save")
	@ResponseStatus(HttpStatus.CREATED)
	public Funcionario save(@RequestBody @Valid FuncionarioRequest request) {
		return funcionarioService.save(request);
	}
	
	@GetMapping("list")
	@ResponseStatus(HttpStatus.OK)
	public List<FuncionarioResponse> listAll(){
		return funcionarioService.list();
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public FuncionarioResponse findById(@PathVariable("id") Long id) {
		return funcionarioService.findById(id);
	}
	
	
	
	/*************************************************************************************/
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleMethodValidException(MethodArgumentNotValidException ex) {
		List<String> errors = ex.getBindingResult().getAllErrors().stream()
			.map(e -> e.getDefaultMessage()).collect(Collectors.toList());
		return new ApiErrors(errors);
	}
	
	
	
	/************************************************************************************/
	@PostMapping("/auth")
	public String login(@RequestBody Funcionario funcionario) {
		try {
			
			UserDetails details = userServiceImpl.autenticar(funcionario);
			
			String token = jwtservice.gerarToken(funcionario);
			System.err.println(token);
			return token;
			
		} catch (UsernameNotFoundException | SenhaInvalidaException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}
	}

}
