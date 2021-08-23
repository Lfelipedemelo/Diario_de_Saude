package br.com.diario_de_saude.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.diario_de_saude.vo.Vacina;

@Repository
public interface VacinaRepository extends JpaRepository<Vacina, Long> {
}
