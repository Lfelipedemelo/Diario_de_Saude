package br.com.diario_de_saude.controller;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.diario_de_saude.service.EmailService;

@Controller
@RequestMapping("/recuperarSenha")
public class EmailController {

	@Autowired
	EmailService service;
	
	@PostMapping
	public ModelAndView recuperarSenha(@RequestParam("emailRecuperar") String email) throws EmailException {
		ModelAndView mv = new ModelAndView("login");
		if(service.recuperarSenha(email)) {
			mv.addObject("msgSuccess", "Email enviado com sucesso");			
		} else {
			mv.addObject("msg", "Email não encontrado");
		}
		return mv;
	}
}
