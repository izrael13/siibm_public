package com.websystique.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.websystique.springmvc.model.programas.Programas_reg_barca;

@Controller
@RequestMapping("/programas")
public class ProgramasController {
	
	@RequestMapping(value = {"/programacion/programacionabc" }, method = RequestMethod.GET)
	public String tarjeta_fabricacion(ModelMap model) {
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		model.addAttribute("Programa", new Programas_reg_barca());
		return "/tarjetas/programas/programacionabc";
	}

}
