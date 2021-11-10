package br.com.diario_de_saude.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/queixas")
public class QueixaController {

	@GetMapping
	public ModelAndView manterQueixas(HttpSession session) {
		ModelAndView mv = new ModelAndView("queixa");
		
		return mv;
	}
}
