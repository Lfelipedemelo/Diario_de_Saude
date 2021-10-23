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
import br.com.diario_de_saude.utils.Constants;
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
		
		List<Consulta> consultas = 
				validarConsultas(
						cRep.findAllByUsuarioId(usuario.getId()));
		List<Vacina> vacinas = 
				validarVacinas(
						vRep.findAllByUsuarioId(usuario.getId()));

		mv.addObject("alertaConsultas", consultas);
		mv.addObject("alertaVacinas", vacinas);
		session.setAttribute("nAlertas", consultas.size() + vacinas.size());
		return mv;
	}

	private List<Vacina> validarVacinas(List<Vacina> vacinas) {
		List<Vacina> vacinasValidadas = new ArrayList();
		Date date = new Date();

		for (Vacina vacina : vacinas) {
			
			if (vacina.isDose1Confirm() == false
					&& vacina.getDose1() != null
					&& vacina.getDose1().getTime() < date.getTime() + Constants.DIAS_PARA_ALERTA
					&& vacina.getDose1().getTime() > date.getTime() - Constants.UM_DIA_EM_MILI) {
				vacinasValidadas.add(vacina);
			} else if (vacina.isDose2Confirm() == false
					&& vacina.getDose2() != null
					&& vacina.getDose2().getTime() < date.getTime() + Constants.DIAS_PARA_ALERTA
					&& vacina.getDose2().getTime() > date.getTime() - Constants.UM_DIA_EM_MILI){				
				vacinasValidadas.add(vacina);
			} else if (vacina.isDose3Confirm() == false
					&& vacina.getDose3() != null
					&& vacina.getDose3().getTime() < date.getTime() + Constants.DIAS_PARA_ALERTA
					&& vacina.getDose3().getTime() > date.getTime() - Constants.UM_DIA_EM_MILI){
				vacinasValidadas.add(vacina);
			}
		}
		return vacinasValidadas;
	}

	private List<Consulta> validarConsultas(List<Consulta> consultas){
		
		List<Consulta> consultasValidadas = new ArrayList<Consulta>();
		Date date = new Date();
		for (Consulta consulta : consultas) {
			if (consulta.getDataConsulta().getTime() < date.getTime() + Constants.DIAS_PARA_ALERTA
				&& consulta.getDataConsulta().getTime() > date.getTime() - Constants.UM_DIA_EM_MILI) {
				consultasValidadas.add(consulta);
			} else {
				continue;
			}
		}
		return consultasValidadas;
	}
	
}
