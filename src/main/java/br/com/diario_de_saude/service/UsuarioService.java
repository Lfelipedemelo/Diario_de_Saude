package br.com.diario_de_saude.service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Date;

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
		if(!validarDataNascimento(usuario.getPerfil().getDtaNascimento())) {
			mv.addObject("msg", "Data de nascimento invalida!");
		} else if (usuario != null && rep.findByEmail(usuario.getEmail()) == null) {
			usuario.setSenha(Encrypt.password(usuario.getSenha())); 
			rep.save(usuario);
			mv.addObject("msgSuccess", "Usuário cadastrado com sucesso!");
		} else {
			mv.addObject("msg", "Erro ao cadastrar novo usuário!");
		}
		mv.addObject("maxDate", LocalDate.now());
		return mv;
	}

	private boolean validarDataNascimento(Date dtaNascimento) {
		Date date = new Date();
		if(dtaNascimento.getYear() < 90 || dtaNascimento.getYear() > date.getYear()
				|| dtaNascimento.getDay() < 1 || dtaNascimento.getDay() > 31
				|| dtaNascimento.getMonth() < 1 || dtaNascimento.getMonth() > 12) {
			return false;
		}
		return true;
	}

	public ModelAndView login(Usuario usuario, HttpSession session)
			throws NoSuchAlgorithmException {
		ModelAndView mv = new ModelAndView("login");
			usuario.setSenha(Encrypt.password(usuario.getSenha()));			
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
