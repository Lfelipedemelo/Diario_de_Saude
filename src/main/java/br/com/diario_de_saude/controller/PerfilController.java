package br.com.diario_de_saude.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.diario_de_saude.repository.PerfilRepository;
import br.com.diario_de_saude.vo.Perfil;
import br.com.diario_de_saude.vo.Usuario;

@Controller
@RequestMapping("/perfil")
public class PerfilController {

	@Autowired
	private PerfilRepository rep;
	
	@GetMapping
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("perfil");
		return mv;
	}
	
	@PostMapping
	public ModelAndView salvar(Perfil perfil, HttpSession session) {
		ModelAndView mv = new ModelAndView("perfil");
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		perfil.setId(usuario.getPerfil().getId());
		rep.save(perfil);
		usuario.setPerfil(perfil);
		session.setAttribute("usuarioLogado", usuario);
		return mv;
	}
}
