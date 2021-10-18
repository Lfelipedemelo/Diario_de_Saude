package br.com.diario_de_saude.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.com.diario_de_saude.repository.ConsultaRepository;
import br.com.diario_de_saude.repository.VacinaRepository;
import br.com.diario_de_saude.vo.Consulta;
import br.com.diario_de_saude.vo.Usuario;
import br.com.diario_de_saude.vo.Vacina;

@Service
public class AlertsService {

	@Autowired
	ConsultaRepository cRep;
	
	@Autowired
	VacinaRepository vRep;
	
	public ModelAndView setAlerts(HttpSession session) {
		ModelAndView mv = new ModelAndView("home");
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		List<Consulta> consultas = cRep.findAllByUsuarioId(usuario.getId());
		List<Vacina> vacinas = vRep.findAllByUsuarioId(usuario.getId());
		Date date = new Date();
		for (Consulta consulta : consultas) {
			if (consulta.getDataConsulta().getDate() <= date.getDate() + 10
					&& consulta.getDataConsulta().getYear() == date.getYear()
					&& consulta.getDataConsulta().getMonth() == date.getMonth()) {
			} else {
				continue;
			}
		}
		vacinas = validarVacinas(vacinas);
		mv.addObject("alertaConsultas", consultas);
		mv.addObject("alertaVacinas", vacinas);
		session.setAttribute("nAlertas", consultas.size() + vacinas.size());
		return mv;
	}

	private List<Vacina> validarVacinas(List<Vacina> vacinas) {
		List<Vacina> vacinasValidadas = new ArrayList();
		Date date = new Date();
		for (Vacina vacina : vacinas) {
			if (!vacina.isDose1Confirm() && vacina.getDose1() != null
					&& vacina.getDose1().getDate() <= date.getDate() + 10
					&& vacina.getDose1().getYear() == date.getYear()
					&& vacina.getDose1().getMonth() == date.getMonth()) {
				vacinasValidadas.add(vacina);
			} else if (!vacina.isDose2Confirm() && vacina.getDose2() != null
					&& vacina.getDose2().getDate() <= date.getDate() + 10
					&& vacina.getDose2().getYear() == date.getYear()
					&& vacina.getDose2().getMonth() == date.getMonth()){				
					vacinasValidadas.add(vacina);
			} else if (!vacina.isDose3Confirm() && vacina.getDose3() != null
					&& vacina.getDose3().getDate() <= date.getDate() + 10
					&& vacina.getDose3().getYear() == date.getYear()
					&& vacina.getDose3().getMonth() == date.getMonth()){
				vacinasValidadas.add(vacina);
			}
		}
		return vacinasValidadas;
	}

}
