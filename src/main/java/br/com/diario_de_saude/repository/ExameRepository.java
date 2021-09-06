package br.com.diario_de_saude.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diario_de_saude.vo.Exame;

public interface ExameRepository extends JpaRepository<Exame, Long> {

}
