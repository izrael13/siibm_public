package com.websystique.springmvc.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
//import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.tarjetas.Catalogo_sellos;
import com.websystique.springmvc.service.UserService;
import com.websystique.springmvc.service.tarjetas.Catalogo_sellosService;

@Controller
@RequestMapping("/sellos")
public class SellosController {
	private Logger logger = Logger.getLogger(SellosController.class);
	
	@Autowired
	Catalogo_sellosService css;
	@Autowired
	UserService us;
	
	@RequestMapping(value = {"/ingenieria/sellosabc" }, method = RequestMethod.GET)
	public String sellosabc(ModelMap model) {
		try 
		{
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			model.addAttribute("sellos", css.ListaSellos());
			model.addAttribute("sello", new Catalogo_sellos());
			logger.info(AppController.getPrincipal() + " - ingenieria/sellosabc.");
		}
		catch(Exception e)
		{
			model.addAttribute("error",e.getMessage());
			logger.error(AppController.getPrincipal() + " - ingenieria/sellosabc. - " + e.getMessage());
		}
		return "/tarjetas/sellos/sellosabc";
	}
	
	@RequestMapping(value = {"/ingenieria/sellosabc" }, method = RequestMethod.POST)
	public String sellosabcpost(@Valid @ModelAttribute("sello") Catalogo_sellos sello, BindingResult result,ModelMap model) {
		try 
		{
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			sello.setNombre(sello.getSellos());
			User user = us.findBySSO(AppController.getPrincipal());
			java.util.Date date = new java.util.Date();
			sello.setIdusuario_captura(user.getId());
			sello.setFecha_insert(date);
			
			
			/*for (ObjectError error : result.getAllErrors()) { // 1.
		       String fieldErrors [] = error.getCodes();
		       System.out.println(fieldErrors[0]);
		   }*/
			
			if (result.hasErrors()) {
				return "/tarjetas/sellos/sellosabc";
			}
			//System.out.println(sello.toString());
			if(sello.getId() != null)
				css.Actualizar(sello);
			else
				css.Guardar(sello);
			
			model.addAttribute("sello", new Catalogo_sellos());
			model.addAttribute("mensaje", "Sello registrado correctamente.");
			model.addAttribute("sellos", css.ListaSellos());
			logger.info(AppController.getPrincipal() + " - sellosabcpost.");
			return "/tarjetas/sellos/sellosabc";
		}
		catch(Exception e)
		{
			model.addAttribute("error", e.getMessage()+ " " + e.getStackTrace() + " " + e.getCause());
			logger.error(AppController.getPrincipal() + " - sellosabcpost. - " + e.getMessage());
		}
		return "/tarjetas/sellos/sellosabc";
	}
}
