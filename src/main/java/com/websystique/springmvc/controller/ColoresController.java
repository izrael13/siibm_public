package com.websystique.springmvc.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.tarjetas.Catalogo_colores;
import com.websystique.springmvc.service.UserService;
import com.websystique.springmvc.service.tarjetas.Catalogo_coloresService;

@Controller
@RequestMapping("/colores")
public class ColoresController {
	
	private Logger logger = Logger.getLogger(ColoresController.class);
	
	@Autowired
	Catalogo_coloresService ccs;
	@Autowired
	UserService us;
	
	@RequestMapping(value = {"/ingenieria/coloresabc" }, method = RequestMethod.GET)
	public String coloresabc(ModelMap model) {
		try 
		{
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			model.addAttribute("listacolores", ccs.ListaColores());
			model.addAttribute("color", new Catalogo_colores());
			logger.info(AppController.getPrincipal() + " - ingenieria/coloresabc.");
		}
		catch(Exception e)
		{
			model.addAttribute("error",e.getMessage());
			logger.error(AppController.getPrincipal() + " - ingenieria/coloresabc. - " + e.getMessage());
		}
		return "/tarjetas/colores/coloresabc";
	}
	
	@RequestMapping(value = {"/ingenieria/coloresabc" }, method = RequestMethod.POST)
	public String coloresabcpost(@Valid @ModelAttribute("color") Catalogo_colores color, BindingResult result,ModelMap model) {
		try 
		{	
			User user = us.findBySSO(AppController.getPrincipal());
			java.util.Date date = new java.util.Date();
			color.setIdusuario_captura(user.getId());
			color.setFecha_captura(date);
			color.setColor_est(color.getColor_est().substring(1));
			if(color.getId() != null)
				ccs.Actualizar(color);
			else
				ccs.Guardar(color);
			
			model.addAttribute("listacolores", ccs.ListaColores());
			model.addAttribute("color", new Catalogo_colores());
			model.addAttribute("mensaje", "Color registrado correctamente.");
			logger.info(AppController.getPrincipal() + " - coloresabcpost.");
		}
		catch(Exception e)
		{
			model.addAttribute("error", e.getMessage()+ " " + e.getStackTrace() + " " + e.getCause());
			logger.error(AppController.getPrincipal() + " - coloresabcpost. - " + e.getMessage());
		}
		return "/tarjetas/colores/coloresabc";
	}
}
