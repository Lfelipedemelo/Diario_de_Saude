package br.com.diario_de_saude.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.diario_de_saude.service.ExameService;
import br.com.diario_de_saude.service.MedicoService;
import br.com.diario_de_saude.vo.Token;

@Controller
@RequestMapping("/areaMedica")
public class medicoController {
	
	@Autowired
	MedicoService service;
	
	@Autowired
	ExameService exameService;
	
	@GetMapping()
	public ModelAndView authentication(@RequestParam("codigo") String codigo, HttpSession session) throws IOException {
		ModelAndView mv = new ModelAndView("login");
		Token validToken = service.authentication(codigo);
		if(validToken == null) {
			mv.addObject("msgAreaMedica", "Código de acesso invalido ou expirado!");
		} else {
			Long uId = validToken.getUsuario().getId();
			session.setAttribute("usuarioValidado", validToken.getUsuario());
			mv.addObject("exames", service.getExames(uId));
			mv.addObject("vacinas", service.getVacinas(uId, ""));
			mv.addObject("alergias", service.getAlergias(uId));
			mv.addObject("doencas", service.getDoencas(uId));
			mv.setViewName("prontuario");
		}
		return mv;
	}
	

}
