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
import br.com.diario_de_saude.vo.Usuario;

@Controller
@RequestMapping
public class UsuarioController {

	@Autowired
	private UsuarioRepository rep;
	
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
	

	@PostMapping("/register")
	public ModelAndView create(@Valid Usuario usuario) {
		ModelAndView mv = new ModelAndView("login");
		if (usuario != null && rep.findByEmail(usuario.getEmail()) == null) {
			rep.save(usuario);
			mv.addObject("msgSuccess", "Usuário cadastrado com sucesso!");
		} else {
			mv.addObject("msg", "Erro ao cadastrar novo usuário!");
		}
		return mv;
	}

	@GetMapping("/register")
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView("cadastro");
		return mv;
	}

	@PostMapping("/login")
	public ModelAndView login(@Valid Usuario usuario, HttpSession session)
			throws NoSuchAlgorithmException {
		ModelAndView mv = new ModelAndView("login");
		usuario = rep.verificarLogin(usuario.getEmail(), usuario.getSenha());
		if (usuario != null) {
			session.setAttribute("usuarioLogado", usuario);
			mv.setViewName("redirect:/home");
		} else {
			mv.addObject("msg", "Email ou senha incorreto!");
		}
		return mv;
	}

	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mv = new ModelAndView("login");
		session.invalidate();
		return mv;
	}
}
