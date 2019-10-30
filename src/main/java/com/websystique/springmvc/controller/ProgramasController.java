package com.websystique.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/programas")
public class ProgramasController {
	
	@RequestMapping(value = {"/programacion/programacionabc" }, method = RequestMethod.GET)
	public String tarjeta_fabricacion(ModelMap model) {
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		
		return "/tarjetas/programas/programacionabc";
	}

}
