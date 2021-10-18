package br.com.diario_de_saude.service;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
		ModelAndView mv = new ModelAndView("redirect:/perfil");
		Date date = new Date();
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		perfil.setId(usuario.getPerfil().getId());
		if(!mpFile.isEmpty()) {
			String fileName = String.valueOf(date.getTime() + "." + mpFile.getOriginalFilename().split("\\.")[1]);
			perfil.setArquivo(fileName);
			String diretorio = "exame-img/" + usuario.getId() + "/perfil";
			FileUploadUtil.saveFile(diretorio, fileName, mpFile);
			File f = new File("exame-img/" + usuario.getId() + "/perfil/" + usuario.getPerfil().getArquivo());
			f.delete();
		} else {
			perfil.setArquivo(usuario.getPerfil().getArquivo());
		}
		rep.save(perfil);
		usuario.setPerfil(perfil);
		session.setAttribute("usuarioLogado", usuario);
		return mv;
	}

}
