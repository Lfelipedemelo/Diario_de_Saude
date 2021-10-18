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

import br.com.diario_de_saude.service.ConsultaService;
import br.com.diario_de_saude.vo.Consulta;
import br.com.diario_de_saude.vo.Usuario;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

	@Autowired
	private ConsultaService service;

	@GetMapping
	public ModelAndView listarConsultas(HttpSession session) {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		ModelAndView mv = service.listarConsultas(usuarioLogado);
		return mv;
	}

	@PostMapping
	public ModelAndView createVacina(@Valid Consulta consulta, HttpSession session) {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		ModelAndView mv = service.createConsulta(consulta, usuarioLogado);
		return mv;
	}

	@GetMapping("/delete{id}")
	public ModelAndView delete(@PathVariable Long id) {
		ModelAndView mv = service.delete(id);
		return mv;
	}

	@PostMapping("/update")
	public ModelAndView update(@Valid Consulta consulta, HttpSession session) {
		consulta.setUsuario((Usuario) session.getAttribute("usuarioLogado"));
		ModelAndView mv = service.update(consulta);
		return mv;
	}
}
