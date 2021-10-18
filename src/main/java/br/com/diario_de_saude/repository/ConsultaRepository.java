package br.com.diario_de_saude.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.diario_de_saude.vo.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long>{

	@Query("from Consulta where usuario_id = :id")
	List<Consulta> findAllByUsuarioId(Long id);
	
}
