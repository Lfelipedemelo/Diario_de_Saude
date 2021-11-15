package br.com.diario_de_saude.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.com.diario_de_saude.repository.QueixaRepository;
import br.com.diario_de_saude.vo.Queixa;
import br.com.diario_de_saude.vo.Usuario;

@Service
public class QueixaService {
	
	@Autowired
	QueixaRepository repo;

	private static final String TIPO_ALERGIA = "ALERGIA";
	private static final String TIPO_DOENCA = "DOENCA";
	
	public void listQueixas(ModelAndView mv, Usuario usuarioLogado) {
		try {
			mv.addObject("alergias", repo.findAllByUsuarioId(usuarioLogado.getId(), TIPO_ALERGIA));
			mv.addObject("doencas", repo.findAllByUsuarioId(usuarioLogado.getId(), TIPO_DOENCA));
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	public void createAlergia(@Valid Queixa alergia, Usuario usuarioLogado) {
		try {
			Queixa persistente = repo.findByNome(usuarioLogado.getId(), alergia.getNome().substring(0,1).toUpperCase() + alergia.getNome().substring(1));
			if(persistente == null) {
				alergia.setUsuario(usuarioLogado);
				alergia.setTipo(TIPO_ALERGIA);
				alergia.setNome(alergia.getNome().substring(0,1).toUpperCase() + alergia.getNome().substring(1));
				repo.save(alergia);
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void deleteQueixa(Long id) {
		try {
			repo.deleteById(id);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void createDoenca(@Valid Queixa doenca, Usuario usuarioLogado) {
		try {
			Queixa persistente = repo.findByNome(usuarioLogado.getId(), doenca.getNome().substring(0,1).toUpperCase() + doenca.getNome().substring(1));
			if(persistente == null) {
			doenca.setUsuario(usuarioLogado);
			doenca.setTipo(TIPO_DOENCA);
			doenca.setNome(doenca.getNome().substring(0,1).toUpperCase() + doenca.getNome().substring(1));
			repo.save(doenca);
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
