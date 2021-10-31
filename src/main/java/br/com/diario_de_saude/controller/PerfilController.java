package br.com.diario_de_saude.controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.diario_de_saude.repository.PerfilRepository;
import br.com.diario_de_saude.service.PerfilService;
import br.com.diario_de_saude.vo.Perfil;
import br.com.diario_de_saude.vo.Usuario;

@Controller
@RequestMapping("/perfil")
public class PerfilController {

	@Autowired
	private PerfilService service;
	
	@GetMapping
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("perfil");
		return mv;
	}
	
	@PostMapping
	public ModelAndView salvar(Perfil perfil, HttpSession session, @RequestParam("imagem") MultipartFile mpFile) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/perfil");
		try {
			service.salvarPerfil(perfil, session, mpFile);
		} catch (Exception e) {
			e.getMessage();
		}
		return mv;
	}
	
	@PostMapping("/trocarSenha")
	public ModelAndView trocarSenha(@RequestParam("senha") String senhaAtual,@RequestParam("novaSenha") String novaSenha,@RequestParam("novaSenha2") String novaSenha2 ,HttpSession session) {
		ModelAndView mv = new ModelAndView("perfil");
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		if(senhaAtual.equals(usuarioLogado.getSenha())
				&& novaSenha != senhaAtual
				&& novaSenha.equals(novaSenha2)) {
			try {
				service.trocarSenha(novaSenha, usuarioLogado);
				mv.addObject("msgSucesso","sucesso");
			} catch (Exception e) {
				e.getMessage();
			}
		} else if(!senhaAtual.equals(usuarioLogado.getSenha())) {
			mv.addObject("msgErro", "A senha atual esta incorreta!");
		} else if(senhaAtual.equals(usuarioLogado.getSenha())
				&& !novaSenha.equals(novaSenha2)) {
			mv.addObject("msgErro", "A senha repetida deve ser igual a senha nova!");
		}
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/validarImagem/{path}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] testphoto(@PathVariable String path, HttpSession session) throws IOException {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		if(usuarioLogado == null) {
			usuarioLogado = (Usuario) session.getAttribute("usuarioValidado");
		}
		if(usuarioLogado != null) {
			File f = new File("exame-img/" + usuarioLogado.getId() + "/perfil/" + path);
			FileInputStream fis = new FileInputStream(f);
			DataInputStream dis = new DataInputStream(fis);
			byte[] conteudo = new byte[fis.available()];
			dis.readFully(conteudo);			
			return conteudo;
		} else {
			return null;
		}
	}
	
}
