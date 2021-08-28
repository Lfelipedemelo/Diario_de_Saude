package br.com.diario_de_saude.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Vacina implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private String nome;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dose1;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dose2;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dose3;

	@Column
	private boolean reforco;

	public Vacina() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDose1() {
		return dose1;
	}

	public void setDose1(Date dose1) {
		this.dose1 = dose1;
	}

	public Date getDose2() {
		return dose2;
	}

	public void setDose2(Date dose2) {
		this.dose2 = dose2;
	}

	public Date getDose3() {
		return dose3;
	}

	public void setDose3(Date dose3) {
		this.dose3 = dose3;
	}

	public boolean isReforco() {
		return reforco;
	}

	public void setReforco(boolean reforco) {
		this.reforco = reforco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
		Vacina other = (Vacina) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
