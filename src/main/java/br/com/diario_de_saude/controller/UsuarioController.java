package br.com.diario_de_saude.controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.diario_de_saude.repository.UsuarioRepository;
import br.com.diario_de_saude.vo.Usuario;

@Controller
@RequestMapping
public class UsuarioController {

	@Autowired
	private UsuarioRepository rep;

	@GetMapping("/dashboard")
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("home");
		return mv;
	}

	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}

	@PostMapping("/register")
	public ModelAndView create(@Valid Usuario usuario) {
		ModelAndView mv = new ModelAndView("cadastro");
		if (usuario != null && rep.findByEmail(usuario.getEmail()) == null) {
			rep.save(usuario);
			mv.addObject("msgSuccess", "Usuário cadastrado com sucesso!");
		} else {
			mv.addObject("msg", "Email já cadastrado!");
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
			mv.setViewName("home");
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
