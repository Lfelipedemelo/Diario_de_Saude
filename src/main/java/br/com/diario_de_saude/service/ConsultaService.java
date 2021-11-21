package br.com.diario_de_saude.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.com.diario_de_saude.repository.ConsultaRepository;
import br.com.diario_de_saude.vo.Consulta;
import br.com.diario_de_saude.vo.Usuario;

@Service
public class ConsultaService {

	@Autowired
	ConsultaRepository rep;

	public ModelAndView listarConsultas(Usuario usuarioLogado) {
		ModelAndView mv = new ModelAndView("consulta");
		try {
			if (usuarioLogado != null) {
				List<Consulta> consultas = rep.findAllByUsuarioId(usuarioLogado.getId());
				mv.addObject("consultas", consultas);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return mv;
	}

	public ModelAndView createConsulta(@Valid Consulta consulta, Usuario usuarioLogado) {
		consulta.setUsuario(usuarioLogado);
		rep.save(consulta);
		ModelAndView mv = new ModelAndView("redirect:/consultas");
		return mv;
	}

	public ModelAndView delete(Long id) {
		ModelAndView mv = new ModelAndView("redirect:/consultas");
		try {
			rep.deleteById(id);
		} catch (Exception e) {
			e.getMessage();
		}
		return mv;
	}

	public ModelAndView update(@Valid Consulta consulta) {
		ModelAndView mv = new ModelAndView("redirect:/consultas");
		try {
			rep.save(consulta);
		} catch (Exception e) {
			e.getMessage();
		}
		return mv;
	}

}
