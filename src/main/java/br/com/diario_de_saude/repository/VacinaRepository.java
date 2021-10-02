package br.com.diario_de_saude.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.diario_de_saude.vo.Vacina;

@Repository
public interface VacinaRepository extends JpaRepository<Vacina, Long> {
	
	@Query("from Vacina where usuario_id = :id")
	List<Vacina> findAllByUsuarioId(Long id);
	@Query("from Vacina where usuario_id = :idUsuario and nome like %:filtro%")
	List<Vacina> findByUsusarioIdFiltro(long idUsuario, String filtro);
}
