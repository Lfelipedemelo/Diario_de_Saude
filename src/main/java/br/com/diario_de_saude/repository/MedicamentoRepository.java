package br.com.diario_de_saude.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.diario_de_saude.vo.Medicamento;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

	@Query("from Medicamento where usuario_id = :id")
	List<Medicamento> findAllByUsuarioId(Long id);
	
	@Query("from Medicamento where usuario_id = :id order by id desc")
	List<Medicamento> findAllByUsuarioIdOrderByDesc(Long id);
}
