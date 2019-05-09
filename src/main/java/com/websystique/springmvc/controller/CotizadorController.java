package com.websystique.springmvc.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/ventas/tarjetas/cotizador")
public class CotizadorController {
	
	private Logger logger = Logger.getLogger(CotizadorController.class);
	
	@RequestMapping(value = {"/cotizadorabc" }, method = RequestMethod.GET)
	public String cotizadotget(ModelMap model) {
		try {
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			logger.info(AppController.getPrincipal() + " - cotizadorabc.");
		}
		catch(Exception e)
		{
			logger.error(AppController.getPrincipal() + " - cotizadorabc. - " + e.getMessage());
		}
		return "/tarjetas/cotizador/cotizador";
	}
}
