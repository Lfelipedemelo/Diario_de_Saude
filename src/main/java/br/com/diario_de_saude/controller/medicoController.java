package br.com.diario_de_saude.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.diario_de_saude.service.MedicoService;
import br.com.diario_de_saude.vo.Token;

@Controller
@RequestMapping("/areaMedica")
public class medicoController {
	
	@Autowired
	MedicoService service;
	
	@GetMapping
	public ModelAndView index(HttpSession session) {
		ModelAndView mv = new ModelAndView("acessoToken");
		return mv;	
	}
	
	@PostMapping("/tokenAuthentication")
	public ModelAndView authentication(@RequestParam("codigo") String codigo, HttpSession session) {
		ModelAndView mv = new ModelAndView("acessoToken");
		Token validToken = service.authentication(codigo);
		if(validToken == null) {
			mv.addObject("msg", "Código de acesso invalido!");
		} else {
			session.setAttribute("validToken", validToken);
			mv.setViewName("prontuario");
		}
		return mv;
	}

}
