package br.com.diario_de_saude.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.diario_de_saude.vo.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long>{

}
