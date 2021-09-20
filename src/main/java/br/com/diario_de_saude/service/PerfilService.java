package br.com.diario_de_saude.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.com.diario_de_saude.repository.PerfilRepository;
import br.com.diario_de_saude.vo.Perfil;
import br.com.diario_de_saude.vo.Usuario;

@Service
public class PerfilService {

	@Autowired
	private PerfilRepository rep;
	
	public ModelAndView salvarPerfil(Perfil perfil, HttpSession session) {
		ModelAndView mv = new ModelAndView("perfil");
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		perfil.setId(usuario.getPerfil().getId());
		rep.save(perfil);
		usuario.setPerfil(perfil);
		session.setAttribute("usuarioLogado", usuario);
		return mv;
	}
}
