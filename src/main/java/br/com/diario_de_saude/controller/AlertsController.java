package br.com.diario_de_saude.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.diario_de_saude.service.AlertsService;

@Controller
@RequestMapping("/home")
public class AlertsController {

	@Autowired
	AlertsService service = new AlertsService();
	
	@GetMapping
	public ModelAndView index(HttpSession session) {
		ModelAndView mv = service.setAlerts(session);
		return mv;
	}
}
