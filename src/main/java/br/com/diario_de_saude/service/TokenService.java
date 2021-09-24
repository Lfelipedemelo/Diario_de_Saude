package br.com.diario_de_saude.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diario_de_saude.repository.TokenRepository;
import br.com.diario_de_saude.vo.Token;
import br.com.diario_de_saude.vo.Usuario;

@Service
public class TokenService {

	@Autowired
	private TokenRepository repository;

	public Token generateToken(Usuario usuario) {
		Token token = repository.findByUsuarioId(usuario.getId());
		if (token == null) {
			token = new Token();
			token.setUsuario(usuario);
		}
			String uuid = UUID.randomUUID().toString().toUpperCase();
			token.setCodigo(uuid.substring(0, 8));
			while (repository.findByToken(token.getCodigo()) != null) {
				uuid = UUID.randomUUID().toString();
				token.setCodigo(uuid.substring(0, 8).toUpperCase());
			}
			Date date = new Date();
			token.setDataCriacao(date);
			repository.save(token);
		return token;
	}

	public Token listToken(Usuario usuarioLogado) {
		Token token = repository.findByUsuarioId(usuarioLogado.getId());
		if(token == null) {
			token = new Token();
			token.setCodigo("--------");
		}
		return token;
	}

}
