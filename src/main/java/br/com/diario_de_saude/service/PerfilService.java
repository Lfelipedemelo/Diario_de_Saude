package br.com.diario_de_saude.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.diario_de_saude.repository.PerfilRepository;
import br.com.diario_de_saude.utils.FileUploadUtil;
import br.com.diario_de_saude.vo.Perfil;
import br.com.diario_de_saude.vo.Usuario;

@Service
public class PerfilService {

	@Autowired
	private PerfilRepository rep;
	
	public ModelAndView salvarPerfil(Perfil perfil, HttpSession session, @RequestParam("imagem") MultipartFile mpFile) throws Exception {
		ModelAndView mv = new ModelAndView("perfil");
		String fileName = StringUtils.cleanPath(mpFile.getOriginalFilename());
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		perfil.setId(usuario.getPerfil().getId());
		perfil.setArquivo(fileName);
		rep.save(perfil);
		String diretorio = "exame-img/" + usuario.getId() + "/perfil";
		FileUploadUtil.saveFile(diretorio, fileName, mpFile);
		usuario.setPerfil(perfil);
		session.setAttribute("usuarioLogado", usuario);
		return mv;
	}
}
