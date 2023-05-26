package br.com.Sistec40.security.jwt;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.Sistec40.entity.Funcionario;

@Service
public class JwtService {

	public String gerarToken(Funcionario funcionario) {

		return JWT.create()
				.withSubject(funcionario.getCpf())
				.withExpiresAt(LocalDateTime.now().plusMinutes(1).toInstant(ZoneOffset.of("-03:00")))
				.sign(Algorithm.HMAC512("ZWR1YXJkbyBlbW1hbnVlbA=="));

	}
	
	public DecodedJWT obterToken(String token) {
		DecodedJWT decoder = JWT.require(Algorithm.HMAC512("ZWR1YXJkbyBlbW1hbnVlbA==")).build().verify(token);
		return decoder;
	}
	
	public boolean tokenValido(String token) {
		try {			
			DecodedJWT decoder = obterToken(token);
			Date dataExpiracao = decoder.getExpiresAt();
			LocalDateTime localDateTime = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			return !LocalDateTime.now().isAfter(localDateTime);
		} catch (TokenExpiredException e) {
			return false;
		}
	}
	
	public String obterLoginUsuario(String token) throws TokenExpiredException {
		return (String) obterToken(token).getSubject();
	}
}
