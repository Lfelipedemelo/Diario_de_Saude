package br.com.diario_de_saude.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.diario_de_saude.repository.ExameRepository;
import br.com.diario_de_saude.utils.FileUploadUtil;
import br.com.diario_de_saude.vo.Exame;
import br.com.diario_de_saude.vo.Usuario;

@Controller
@RequestMapping("/exames")
public class ExameController {

	@Autowired
	private ExameRepository repository;
	
	@GetMapping
	public ModelAndView listExames(HttpSession session) {
		ModelAndView mv = new ModelAndView("exame");
		List<Exame> exames = repository.findAll();
		mv.addObject("exames",exames);
		mv.addObject("maxDate" ,LocalDate.now());
		return mv;
	}
	
	@PostMapping
	public ModelAndView adicionar(Exame exame, HttpSession session, @RequestParam("imagem") MultipartFile mpFile) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/exames");
		String fileName = StringUtils.cleanPath(mpFile.getOriginalFilename());
		exame.setUsuario((Usuario) session.getAttribute("usuarioLogado"));
		exame.setArquivo(fileName);
		Exame exameSalvo = repository.save(exame);
		String diretorio = "exame-img/" + exameSalvo.getUsuario().getId();
		FileUploadUtil.saveFile(diretorio, fileName, mpFile);
		return mv;
	}
}
