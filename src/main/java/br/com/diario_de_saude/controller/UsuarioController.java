package br.com.diario_de_saude.controller;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
	
	@GetMapping("/dashboard")
	public String dashboard() {
		return "home";
	}
	
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}

//	@PostMapping("/register")
//	public ModelAndView create(@Valid Usuario usuario) {
//		ModelAndView mv = new ModelAndView("redirect:cadastro");
//		if (service.criarUsuario(usuario)) {
//			mv.addObject("msg", "Usuário cadastrado com sucesso!");
//		} else {
//			mv.addObject("msg", "Email já cadastrado!");
//			mv.setViewName("cadastro");
//		}
//		return mv;
//	}

	@PostMapping("/login")
	public ModelAndView login(@Valid Usuario usuario, HttpSession session, BindingResult result) throws NoSuchAlgorithmException {
		ModelAndView mv = new ModelAndView("home");
			usuario = rep.verificarLogin(usuario.getEmail(), usuario.getSenha());
		if (usuario != null) {
			mv.addObject("msg", "Bem vindo" + usuario.getEmail());
			System.out.print("Email: " + usuario.getEmail() + "\nSenha: " + usuario.getSenha());
			session.setAttribute("usuarioLogado", usuario);
		} else {
			mv.addObject("msg", "Email ou senha incorreto!");
			mv.setViewName("login");
		}
		return mv;
	}
	
	@GetMapping("/logout")
	public ModelAndView logou(HttpSession session) {
		ModelAndView mv = new ModelAndView("login");
		session.invalidate();
		return mv;
	}
	
}

