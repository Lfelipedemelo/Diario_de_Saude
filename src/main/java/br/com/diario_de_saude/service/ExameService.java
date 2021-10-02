package br.com.diario_de_saude.service;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.diario_de_saude.repository.ExameRepository;
import br.com.diario_de_saude.utils.Constants;
import br.com.diario_de_saude.utils.FileUploadUtil;
import br.com.diario_de_saude.vo.Exame;
import br.com.diario_de_saude.vo.Usuario;

@Service
public class ExameService {

	@Autowired
	private ExameRepository repository;

	public ModelAndView listExames(Integer paginaAtual, long idUsuario) {
		ModelAndView mv = new ModelAndView("exame");
		if (paginaAtual == null) {
			paginaAtual = 0;
		}
		Pageable pageable = PageRequest.of(paginaAtual, Constants.EXAMES_POR_PAGINA);
		List<Exame> exames = repository.findByUsuarioId((long) idUsuario, pageable);
		mv.addObject("nPaginas", getNumeroDePaginas(idUsuario));
		mv.addObject("exames", exames);
		mv.addObject("maxDate", LocalDate.now());
		return mv;
	}

	public ModelAndView adicionar(Exame exame, HttpSession session, @RequestParam("imagem") MultipartFile mpFile)
			throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/exames");
		try {
			String fileName = StringUtils.cleanPath(mpFile.getOriginalFilename());
			exame.setUsuario((Usuario) session.getAttribute("usuarioLogado"));
			exame.setArquivo(fileName);
			Exame exameSalvo = repository.save(exame);
			String diretorio = "exame-img/" + exameSalvo.getUsuario().getId();
			FileUploadUtil.saveFile(diretorio, fileName, mpFile);
		} catch (Exception e) {
		}
		return mv;
	}

	private int[] getNumeroDePaginas(Long id) {
		Double qtdeExames = (double) repository.findByUsuarioId(id).size();
		int paginas = (int) Math.round(qtdeExames / Constants.EXAMES_POR_PAGINA);
		if (qtdeExames / Constants.EXAMES_POR_PAGINA > paginas) {
			paginas = paginas + 1;
		}
		int[] nPaginas = new int[paginas];
		for (int i = 0; i < paginas; i++) {
			nPaginas[i] = i;
		}
		return nPaginas;
	}
	
}
