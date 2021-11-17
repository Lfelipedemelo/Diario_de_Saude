package br.com.diario_de_saude.service;



import java.util.UUID;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diario_de_saude.repository.UsuarioRepository;
import br.com.diario_de_saude.vo.Usuario;

@Service
public class EmailService {
	
	@Autowired
	UsuarioRepository repo;
	
	public void sendEmail(String to, String novaSenha) throws EmailException {
		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("diariodesaudenoreply@gmail.com", "usxieqwnubakvuwp"));
		email.setSSLOnConnect(true);
		email.setFrom("diariodesaudenoreply@gmail.com");
		email.setSubject("Olá aqui está a sua nova senha!");
		email.setMsg("Sua nova senha é :  " + novaSenha + "\nAconselhamos a troca-lá assim que possível!"
				+ "\npara trocar sua senha vá até o menu Perfil após fazer o login no sistema");
		email.addTo(to);
		email.send();
	}

	public boolean recuperarSenha(String email) throws EmailException {
		Usuario usuario = repo.findByEmail(email);
		if(usuario == null) {
			return false;
		}
		String uuid = UUID.randomUUID().toString().toUpperCase();
		usuario.setSenha(uuid.substring(0, 8));
		repo.save(usuario);
		sendEmail(email, usuario.getSenha());
		return true;
	}
}
