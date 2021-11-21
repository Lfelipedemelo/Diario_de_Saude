package br.com.diario_de_saude.service;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.com.diario_de_saude.repository.MedicamentoRepository;
import br.com.diario_de_saude.vo.Medicamento;
import br.com.diario_de_saude.vo.Usuario;

@Service
public class MedicamentoService {

	@Autowired
	private MedicamentoRepository repo;

	public ModelAndView listarMedicamentos(HttpSession session, Long usuarioId) {
		List<Medicamento> medicamentos = null;
		ModelAndView mv = new ModelAndView("medicamento");
		try {
			medicamentos = repo.findAllByUsuarioIdOrderByDesc(usuarioId);
		} finally {
			if (medicamentos != null && medicamentos.size() != 0) {
				mv.addObject("medicamentos", medicamentos);
			}
		}
		return mv;
	}

	public ModelAndView incluirMedicamento(@Valid Medicamento medicamento, Usuario usuario) {
		ModelAndView mv = new ModelAndView("redirect:/medicamentos");
		try {
			medicamento.setUsuario(usuario);
			repo.save(medicamento);
		} catch (Exception e) {
			e.getMessage();
			mv.addObject("msgErro", "Erro ao incluir medicamento!");
		}
		return mv;
	}

	public boolean deletarMedicamento(Long id) {
		try {
			repo.deleteById(id);
			return true;
		} catch (Exception e) {
			e.getMessage();
		}
		return false;
	}

	public boolean alterarMedicamento(@Valid Medicamento medicamento, Usuario usuario) {
		try {
			medicamento.setUsuario(usuario);
			repo.save(medicamento);
			return true;
		} catch (Exception e) {
			e.getMessage();
		}
		return false;
	}

}
