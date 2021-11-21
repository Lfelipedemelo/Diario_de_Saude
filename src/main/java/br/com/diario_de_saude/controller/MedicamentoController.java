package br.com.diario_de_saude.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.diario_de_saude.service.MedicamentoService;
import br.com.diario_de_saude.vo.Medicamento;
import br.com.diario_de_saude.vo.Usuario;

@Controller
@RequestMapping("/medicamentos")
public class MedicamentoController {

	@Autowired
	private MedicamentoService service;

	@GetMapping
	private ModelAndView manterMedicamentos(HttpSession session, Model model) {
		ModelAndView mv = new ModelAndView("medicamento");
		try {
			Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
			if (usuario != null) {
				mv = service.listarMedicamentos(session, usuario.getId());
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return mv;
	}

	@PostMapping
	private ModelAndView incluirMedicamento(@Valid Medicamento medicamento, HttpSession session) {
		ModelAndView mv = new ModelAndView("medicamento");
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		try {
			mv = service.incluirMedicamento(medicamento, usuario);
		} catch (Exception e) {
			e.getMessage();
		}
		return mv;
	}

	@GetMapping("/delete{id}")
	public ModelAndView deletarMedicamento(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("medicamento");
		if (service.deletarMedicamento(id)) {
			mv.setViewName("redirect:/medicamentos");
		}
		return mv;
	}

	@PostMapping("/update")
	public ModelAndView alterarMedicamento(@Valid Medicamento medicamento, HttpSession session) {
		ModelAndView mv = new ModelAndView("medicamentos");
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		if (service.alterarMedicamento(medicamento, usuario)) {
			mv.setViewName("redirect:/medicamentos");
		}
		return mv;
	}

}
