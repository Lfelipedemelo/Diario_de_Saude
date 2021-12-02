package br.com.diario_de_saude.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.diario_de_saude.vo.Exame;

@Repository
public interface ExameRepository extends JpaRepository<Exame, Long> {
	
	@Query("from Exame where usuario_id = :id")
	List<Exame> findByUsuarioId(Long id, Pageable pageable);
	@Query("from Exame where usuario_id = :id")
	List<Exame> findByUsuarioId(Long id);
	@Query("from Exame where usuario_id = :id order by dta_exame desc")
	List<Exame> findByUsuarioIdOrderByData(Long id);
	@Query("from Exame where id = :id")
	Exame findByExameId(Long id);
}
