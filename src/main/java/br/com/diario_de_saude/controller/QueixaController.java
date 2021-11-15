package br.com.diario_de_saude.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.diario_de_saude.service.QueixaService;
import br.com.diario_de_saude.vo.Queixa;
import br.com.diario_de_saude.vo.Usuario;

@Controller
@RequestMapping("/queixas")
public class QueixaController {

	@Autowired
	QueixaService service;
	
	@GetMapping
	public ModelAndView manterQueixas(HttpSession session) {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		ModelAndView mv = new ModelAndView("queixa");
		service.listQueixas(mv,usuarioLogado);
		return mv;
	}
	
	@PostMapping("/alergia")
	public ModelAndView createQueixaAlergia(@Valid Queixa alergia, HttpSession session) {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		ModelAndView mv = new ModelAndView("redirect:/queixas");
		service.createAlergia(alergia, usuarioLogado);
		return mv;
	}
	
	@PostMapping("/doenca")
	public ModelAndView createQueixaDoenca(@Valid Queixa doenca, HttpSession session) {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		ModelAndView mv = new ModelAndView("redirect:/queixas");
		service.createDoenca(doenca, usuarioLogado);
		return mv;
	}
	
	@GetMapping("/delete{id}")
	public ModelAndView deleteQueixa(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("redirect:/queixas");
		service.deleteQueixa(id);
		return mv;
	}
}
