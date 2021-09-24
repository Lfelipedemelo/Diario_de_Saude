package br.com.diario_de_saude.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.diario_de_saude.vo.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

	@Query("from Token where usuario_id = :id")
	Token findByUsuarioId(Long id);
	
	@Query("from Token where codigo = :codigo")
	Token findByToken(String codigo);
}
