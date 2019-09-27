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
import org.springframework.web.bind.annotation.RequestParam;

import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.tarjetas.Catalogo_herramentales;
import com.websystique.springmvc.service.UserService;
import com.websystique.springmvc.service.tarjetas.Catalogo_herramentalesService;

@Controller
@RequestMapping("/herramentales")
public class HerramentalesController {
	
	private Logger logger = Logger.getLogger(HerramentalesController.class);
	
	@Autowired
	Catalogo_herramentalesService chs;
	@Autowired
	UserService us;
	
	@RequestMapping(value = {"/ingenieria/herramentalesabc" }, method = RequestMethod.GET)
	public String herramentalesabc(@RequestParam(value = "id", defaultValue = "0", required = false) Integer id,ModelMap model) {
		try 
		{
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			model.addAttribute("herramental", id > 0 ? chs.BuscarxId(id) : new Catalogo_herramentales());
			model.addAttribute("listaSuaj", chs.BuscarxTipo(1));
			model.addAttribute("listaGrab", chs.BuscarxTipo(2));
			logger.info(AppController.getPrincipal() + " - herramentalesabc.");
		}
		catch(Exception e)
		{
			logger.error(AppController.getPrincipal() + " - herramentalesabc. - " + e.getMessage());
		}
		return "/tarjetas/herramentales/herramentales";
	}
	
	@RequestMapping(value = {"/ingenieria/herramentalesabc" }, method = RequestMethod.POST)
	public String herramentalesabcpost(@Valid @ModelAttribute("herramental") Catalogo_herramentales herramental, BindingResult result,ModelMap model) {
		try 
		{	
			User user = us.findBySSO(AppController.getPrincipal());
			java.util.Date date = new java.util.Date();
			
			if(herramental.getId() != null)
			{
				herramental.setFecha_update(date);
				herramental.setUsuario_update(user.getId());
				chs.Actualziar(herramental);
			}
			else
			{
				herramental.setFecha_registro(date);
				herramental.setUsuario_registro(user.getId());
				chs.Guardar(herramental);
			}
			
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			model.addAttribute("herramental", new Catalogo_herramentales());
			model.addAttribute("listaSuaj", chs.BuscarxTipo(1));
			model.addAttribute("listaGrab", chs.BuscarxTipo(2));
			logger.info(AppController.getPrincipal() + " - herramentalesabcpost.");
		}
		catch(Exception e)
		{
			logger.error(AppController.getPrincipal() + " - herramentalesabcpost. - " + e.getMessage());
		}
		return "/tarjetas/herramentales/herramentales";
	}
	

}
