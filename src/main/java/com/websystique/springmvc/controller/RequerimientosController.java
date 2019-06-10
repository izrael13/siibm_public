package com.websystique.springmvc.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/ventas/tarjetas/requerimientos")
public class RequerimientosController {
	private Logger logger = Logger.getLogger(RequerimientosController.class);
	
	@RequestMapping(value = {"/requerimientoabc" }, method = RequestMethod.GET)
	public String requerimientoabcget(ModelMap model) {
		
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		
		logger.info(AppController.getPrincipal() + " - requerimientoabcget.");
		
		return "/tarjetas/requerimientos/requerimientoabc";
	}
	
}
