package com.websystique.springmvc.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/cotizador")
public class CotizadorController {
	
	private Logger logger = Logger.getLogger(CotizadorController.class);
	
	@RequestMapping(value = {"/cotizador" }, method = RequestMethod.GET)
	public String cotizadotget(ModelMap model) {
		try {
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			logger.info(AppController.getPrincipal() + " - cotizador.");
		}
		catch(Exception e)
		{
			logger.error(AppController.getPrincipal() + " - cotizadorget. - " + e.getMessage());
		}
		return "/cotizador/cotizador";
	}
}
