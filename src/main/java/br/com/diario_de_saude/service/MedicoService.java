package br.com.diario_de_saude.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diario_de_saude.repository.TokenRepository;
import br.com.diario_de_saude.vo.Token;

@Service
public class MedicoService {

	@Autowired
	TokenRepository tkRepo;
	
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
		
		if(validDate.getHours() < date.getHours() + 2) {
			return true;
		}
		System.out.println("validDate " + validDate.getHours() + "\ndate " + date.getHours());
		return false;
	}

}
