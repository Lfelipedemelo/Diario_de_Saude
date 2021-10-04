package br.com.diario_de_saude.vo;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.diario_de_saude.utils.Constants;

@Entity
public class Perfil {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nome;
	
	private String sobrenome;
	
	private String telefone;
	
	private String sexo;
	
	private String altura;
	
	private String tipoSanguineo;
	
	private ArrayList<String> alergias;
	
	private String arquivo;
		
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dtaNascimento;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public String getTipoSanguineo() {
		return tipoSanguineo;
	}

	public void setTipoSanguineo(String tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}

	public Date getDtaNascimento() {
		return dtaNascimento;
	}

	public void setDtaNascimento(Date dtaNascimento){
		this.dtaNascimento = dtaNascimento;
	}
	
	public int getIdade() {
		if(this.getDtaNascimento() == null) {
			return 0;
		} else {
			int ano = dtaNascimento.getYear();
			Date date = new Date();
			if(dtaNascimento.getMonth() > date.getMonth()) {
				return date.getYear() - ano -1;
			}
			return date.getYear() - ano;			
		}
	}
	
	public String getCaminhoImagem() {
		if (arquivo == null) {
			return null;
		} else {
			return Constants.CAMINHO_IMAGEM + arquivo;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Perfil other = (Perfil) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public ArrayList<String> getAlergias() {
		return alergias;
	}

	public void setAlergias(ArrayList<String> alergias) {
		this.alergias = alergias;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}


}
