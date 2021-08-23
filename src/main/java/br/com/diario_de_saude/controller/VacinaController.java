package br.com.diario_de_saude.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.diario_de_saude.repository.VacinaRepository;
import br.com.diario_de_saude.vo.Vacina;

@Controller
@RequestMapping("/vacinas")
public class VacinaController {

	@Autowired
	private VacinaRepository vacinaRep;

	@GetMapping("/")
	public ModelAndView listVacina(Model model) {
		ModelAndView mv = new ModelAndView("vacina");
		List<Vacina> vacinas = vacinaRep.findAll();
		mv.addObject("vacinas", vacinas);
		mv.addObject("maxDate" ,LocalDate.now());
		return mv;
	}

	@PostMapping
	public String create(@Valid Vacina vacina, BindingResult result) {
		vacinaRep.save(vacina);
		return "redirect:/vacinas/";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		vacinaRep.deleteById(id);
		return "redirect:/vacinas/";
	}
}
