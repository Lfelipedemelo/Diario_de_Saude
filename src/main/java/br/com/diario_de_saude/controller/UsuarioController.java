package br.com.diario_de_saude.controller;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.diario_de_saude.repository.UsuarioRepository;
import br.com.diario_de_saude.service.UsuarioService;
import br.com.diario_de_saude.vo.Usuario;

@Controller
@RequestMapping
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
	

	@PostMapping("/register")
	public ModelAndView create(@Valid Usuario usuario) throws NoSuchAlgorithmException {
		return service.create(usuario);
	}

	@PostMapping("/login")
	public ModelAndView login(@Valid Usuario usuario, HttpSession session)
			throws NoSuchAlgorithmException {
		return service.login(usuario, session);
	}

	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		return new ModelAndView("login");
	}
}
