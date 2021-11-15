package br.com.diario_de_saude.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diario_de_saude.repository.ExameRepository;
import br.com.diario_de_saude.repository.QueixaRepository;
import br.com.diario_de_saude.repository.TokenRepository;
import br.com.diario_de_saude.repository.VacinaRepository;
import br.com.diario_de_saude.vo.Exame;
import br.com.diario_de_saude.vo.Queixa;
import br.com.diario_de_saude.vo.Token;
import br.com.diario_de_saude.vo.Vacina;

@Service
public class MedicoService {

	@Autowired
	TokenRepository tkRepo;
	@Autowired
	ExameRepository exameRepository;
	@Autowired
	VacinaRepository vacinaRepository;
	@Autowired
	QueixaRepository queixaRepository;
	
	public Token authentication(String codigo) {
		Token validToken = tkRepo.findByToken(codigo);
		if(validToken != null) {
			if(validarDate(validToken.getDataCriacao())) {
				return validToken;				
			};
		}
		return null;
	}
	
	public boolean validarDate(Date validDate) {	
		Date date = new Date();

		date.setMinutes(validDate.getMinutes());;
		
		if(validDate.getHours() < date.getHours() + 2
				&& validDate.getDay() == date.getDay()
				&& validDate.getMonth() == date.getMonth()
				&& validDate.getYear() == date.getYear()) {
			return true;
		}
		return false;
	}
	
	public List<Vacina> getVacinas(long idUsuario, String filtro){
		List<Vacina> vacinas = vacinaRepository.findByUsusarioIdFiltro(idUsuario, filtro);
		return vacinas;
	}
	

	public List<Exame> getExames(long idUsuario){
		List<Exame> exames = exameRepository.findByUsuarioIdOrderByData((long) idUsuario);
		return exames;
	}

	public Object getAlergias(long id) {
		List<Queixa> alergias = queixaRepository.findAllByUsuarioId(id, "ALERGIA");
		return alergias;
	}
	
	public Object getDoencas(long id) {
		List<Queixa> doencas = queixaRepository.findAllByUsuarioId(id, "DOENCA");
		return doencas;
	}
}
