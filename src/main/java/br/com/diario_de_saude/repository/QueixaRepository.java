package br.com.diario_de_saude.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.diario_de_saude.vo.Queixa;

@Repository
public interface QueixaRepository extends JpaRepository<Queixa, Long>{

	@Query("from Queixa where usuario_id = :id and tipo = :tipo")
	List<Queixa> findAllByUsuarioId(Long id, String tipo);
	
	@Query("from Queixa where usuario_id = :id")
	List<Queixa> findAllByUsuarioId(Long id);

	@Query("from Queixa where usuario_id = :id and nome = :nome")
	Queixa findByNome(Long id,String nome);
}
