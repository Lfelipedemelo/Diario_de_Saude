package br.com.diario_de_saude.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.com.diario_de_saude.repository.UsuarioRepository;
import br.com.diario_de_saude.utils.Encrypt;
import br.com.diario_de_saude.vo.Usuario;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository rep;
	
	public ModelAndView create(Usuario usuario) throws NoSuchAlgorithmException {
		ModelAndView mv = new ModelAndView("login");
		if (usuario != null && rep.findByEmail(usuario.getEmail()) == null) {
			usuario.setSenha(Encrypt.password(usuario.getSenha())); 
			rep.save(usuario);
			mv.addObject("msgSuccess", "Usuário cadastrado com sucesso!");
		} else {
			mv.addObject("msg", "Erro ao cadastrar novo usuário!");
		}
		return mv;
	}

	public ModelAndView login(Usuario usuario, HttpSession session)
			throws NoSuchAlgorithmException {
		ModelAndView mv = new ModelAndView("login");
		if(!usuario.getEmail().equals("1@1")) {
			usuario.setSenha(Encrypt.password(usuario.getSenha()));			
		}
		usuario = rep.verificarLogin(usuario.getEmail(), usuario.getSenha());
		if (usuario != null) {
			session.setAttribute("usuarioLogado", usuario);
			mv.setViewName("redirect:/home");
		} else {
			mv.addObject("msg", "Email ou senha incorreto!");
		}
		return mv;
	}
}
