package br.com.diario_de_saude.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.diario_de_saude.service.TokenService;
import br.com.diario_de_saude.vo.Usuario;

@Controller
@RequestMapping("/token")
public class TokenController {

	@Autowired
	private TokenService service;

	@GetMapping
	private ModelAndView listToken(HttpSession session) {
		ModelAndView mv = new ModelAndView("token");
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		mv.addObject("token", service.listToken(usuarioLogado).getCodigo());
		return mv;
	}
	
	@PostMapping
	private ModelAndView generateToken(HttpSession session) {
		ModelAndView mv = new ModelAndView("token");
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		mv.addObject("token", service.generateToken(usuarioLogado).getCodigo());
		return mv;
	}
}
