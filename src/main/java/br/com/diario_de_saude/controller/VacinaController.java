package br.com.diario_de_saude.controller;

import java.time.LocalDate;
import java.util.List;

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

import br.com.diario_de_saude.repository.VacinaRepository;
import br.com.diario_de_saude.vo.Usuario;
import br.com.diario_de_saude.vo.Vacina;

@Controller
@RequestMapping("/vacinas")
public class VacinaController {

	@Autowired
	private VacinaRepository vacinaRep;

	@GetMapping("/")
	public ModelAndView listVacina(HttpSession session, Model model) {
		ModelAndView mv = new ModelAndView("vacina");
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		if(usuario != null) {
			List<Vacina> vacinas = vacinaRep.findAllByUsuarioId(usuario.getId());
			mv.addObject("vacinas", vacinas);
			mv.addObject("maxDate" ,LocalDate.now());
		}
		return mv;
	}

	@PostMapping
	public String create(@Valid Vacina vacina) {
		vacinaRep.save(vacina);
		return "redirect:/vacinas/";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		vacinaRep.deleteById(id);
		return "redirect:/vacinas/";
	}

	@PostMapping("/update")
	public String update(@Valid Vacina vacina) {
		System.out.println("ID: " + vacina.getId() + "\n");
		System.out.println("NOME: " + vacina.getNome() + "\n");
		vacinaRep.save(vacina);
		return "redirect:/vacinas/";
	}
}
