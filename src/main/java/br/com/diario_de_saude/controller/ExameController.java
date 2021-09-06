package br.com.diario_de_saude.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.diario_de_saude.repository.ExameRepository;
import br.com.diario_de_saude.vo.Exame;

@Controller
@RequestMapping("/exames")
public class ExameController {

	@Autowired
	private ExameRepository repository;
	
	@GetMapping
	public ModelAndView listExames(HttpSession session) {
		ModelAndView mv = new ModelAndView("exame");
		List<Exame> exames = repository.findAll();
		mv.addObject("exames",exames);
		
		return mv;
	}
	
	@PostMapping
	public ModelAndView adicionar(@ModelAttribute Exame exame, HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:/exame");
		repository.save(exame);
		return mv;
	}
}
