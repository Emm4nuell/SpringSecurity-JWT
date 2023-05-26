package br.com.Sistec40.exception;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class ApiErrors {
	
	@Getter
	private List<String> errors;
	
	/*Tratamento do erro 500*/
	public ApiErrors(String errors) {
		this.errors = Arrays.asList(errors);
	}

	/*Tratamento das validações*/
	public ApiErrors(List<String> errors) {
		super();
		this.errors = errors;
	}
	
	
}
