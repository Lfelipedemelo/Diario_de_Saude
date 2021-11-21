package br.com.diario_de_saude.service;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.com.diario_de_saude.repository.VacinaRepository;
import br.com.diario_de_saude.vo.Usuario;
import br.com.diario_de_saude.vo.Vacina;

@Service
public class VacinaService {


	@Autowired
	private VacinaRepository vacinaRep;
	
	public ModelAndView createVacina(@Valid Vacina vacina, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		vacina.setUsuario(usuario);
		vacinaRep.save(vacina);
		ModelAndView mv = new ModelAndView("redirect:/vacinas");
		return mv;
	}
}
