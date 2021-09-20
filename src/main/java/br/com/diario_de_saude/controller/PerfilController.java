package br.com.diario_de_saude.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.diario_de_saude.repository.PerfilRepository;
import br.com.diario_de_saude.service.PerfilService;
import br.com.diario_de_saude.vo.Perfil;
import br.com.diario_de_saude.vo.Usuario;

@Controller
@RequestMapping("/perfil")
public class PerfilController {

	@Autowired
	private PerfilService service;
	
	@GetMapping
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("perfil");
		return mv;
	}
	
	@PostMapping
	public ModelAndView salvar(Perfil perfil, HttpSession session) {
		return service.salvarPerfil(perfil, session);
	}

}
