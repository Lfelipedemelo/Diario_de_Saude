package br.com.diario_de_saude.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.diario_de_saude.repository.ExameRepository;
import br.com.diario_de_saude.service.ExameService;
import br.com.diario_de_saude.utils.FileUploadUtil;
import br.com.diario_de_saude.vo.Exame;
import br.com.diario_de_saude.vo.Usuario;

@Controller
@RequestMapping("/exames")
public class ExameController {

	@Autowired
	private ExameRepository repository;
	
	@Autowired
	private ExameService service;
	
	@GetMapping
	public ModelAndView listExames(HttpSession session) {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		return service.listExames(0, usuarioLogado.getId());
	}
	
	@GetMapping("/{paginaAtual}")
	public ModelAndView listExamesPaginado(HttpSession session, @PathVariable Integer paginaAtual) {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		return service.listExames(paginaAtual, usuarioLogado.getId());
	}
	
	@PostMapping
	public ModelAndView adicionar(Exame exame, HttpSession session, @RequestParam("imagem") MultipartFile mpFile) throws Exception {
		return service.adicionar(exame, session, mpFile);
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		repository.deleteById(id);
		return "redirect:/exames";
	}
}
