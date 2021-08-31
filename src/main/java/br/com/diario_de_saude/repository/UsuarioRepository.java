package br.com.diario_de_saude.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.diario_de_saude.vo.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query("from Usuario where email = :email and senha = :senha")
	Usuario verificarLogin(String email, String senha);
	
	Usuario findByEmail(String email);
	
}
