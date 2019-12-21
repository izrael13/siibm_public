package com.websystique.springmvc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.websystique.springmvc.model.Catalogo_enlaces;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.Profile_matchers;
import com.websystique.springmvc.service.Catalogo_enlacesService;
import com.websystique.springmvc.service.Profile_matchersService;
import com.websystique.springmvc.service.UserProfileService;


@Controller
@RequestMapping("/permisos_perfiles")
public class Profile_matchersController {
	private Logger logger = Logger.getLogger(Profile_matchersController.class);
	
	@Autowired
	Profile_matchersService pms;
	
	@Autowired
	UserProfileService ups ;
	
	@Autowired
	Catalogo_enlacesService ces;
	
	@RequestMapping(value = {"/list" }, method = RequestMethod.GET)
	public String permisos_perfilesget(ModelMap model) {
		try {
			Profile_matchers pm = new Profile_matchers();
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			model.addAttribute("perfilesmatcherslist", pms.findall());
			model.addAttribute("perfileslist", ups.findAll());
			model.addAttribute("profilematchers",pm);
			logger.info(AppController.getPrincipal() + " - permisos_perfilesget.");
		}
		catch(Exception e)
		{
			logger.error(AppController.getPrincipal() + " - permisos_perfilesget. - " + e.getMessage());
		}
		return "/appconfig/profile_matchers";
	}
	
	@RequestMapping(value = {"/list" }, method = RequestMethod.POST)
	public String permisos_perfilespost(@Valid @ModelAttribute("profilematchers") Profile_matchers profile_matchers,BindingResult result,ModelMap  model) {
		try {
			/*for (ObjectError error : result.getAllErrors()) { // 1.
		       String fieldErrors [] = error.getCodes();
		       System.out.println(fieldErrors[0]);
		   }*/
				if (result.hasErrors())
				{
					model.addAttribute("loggedinuser", AppController.getPrincipal());
					model.addAttribute("perfilesmatcherslist", pms.findall());
					model.addAttribute("perfileslist", ups.findAll());
					model.addAttribute("profilematchers",profile_matchers);
					logger.info(AppController.getPrincipal() + " - permisos_perfilespost.");
					return "/appconfig/profile_matchers";
				}
				
				pms.save(profile_matchers);
				Profile_matchers pm = new Profile_matchers();
				model.addAttribute("loggedinuser", AppController.getPrincipal());
				model.addAttribute("perfilesmatcherslist", pms.findall());
				model.addAttribute("perfileslist", ups.findAll());
				model.addAttribute("profilematchers",pm);
				logger.info(AppController.getPrincipal() + " - permisos_perfilespost.");
				return "/appconfig/profile_matchers";
			}
		catch(Exception e)
		{
			logger.error(AppController.getPrincipal() + " - permisos_perfilespost. - " + e.getMessage());
		}
		return "/appconfig/profile_matchers";
	}
	
	@RequestMapping(value = {"/delete" }, method = RequestMethod.GET)
	public String permisos_perfilesdelete(@RequestParam("ID") Integer ID, ModelMap model) {
		try {
				Profile_matchers obj = pms.findById(ID);
				if(obj != null)
				{
					pms.borrar(obj);
				}
					
				/*Profile_matchers pm = new Profile_matchers();
				model.addAttribute("loggedinuser", AppController.getPrincipal());
				model.addAttribute("perfilesmatcherslist", pms.findall());
				model.addAttribute("perfileslist", ups.findAll());
				model.addAttribute("profilematchers",pm);*/
				logger.info(AppController.getPrincipal() + " - permisos_perfilesdelete.");
		}
		catch(Exception e)
		{
			logger.error(AppController.getPrincipal() + " - permisos_perfilesdelete. - " + e.getMessage());
		}
		return "redirect:/permisos_perfiles/list"; 
	}
	
	@RequestMapping(value = {"/enlaces" }, method = RequestMethod.GET)
	public String enlacesget(ModelMap model, @RequestParam(value = "id", defaultValue = "0", required = false) Integer id) {
		try 
		{			
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			logger.info(AppController.getPrincipal() + " - enlacesget");
			model.addAttribute("Enlaces", (id == 0 ? new Catalogo_enlaces() : ces.BuscarxId(id)));
			model.addAttribute("perfileslist", ups.findAll());
			List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
			Map<String,String> mOrd =  new HashMap<String, String>();
			model.addAttribute("ListaEnlaces", ces.BuscarxParams(Params, mOrd));
		}
		catch(Exception e)
		{
			logger.error(AppController.getPrincipal() + " - enlacesget - " + e);
		}
		return "/appconfig/enlaces";
	}
	
	@RequestMapping(value = {"/enlaces" }, method = RequestMethod.POST)
	public String enlaces(@Valid @ModelAttribute("Enlaces") Catalogo_enlaces Enlaces,BindingResult result,ModelMap  model) {
		try 
		{			
			model.addAttribute("perfileslist", ups.findAll());
			if (result.hasErrors())
			{					
				return "/appconfig/enlaces";
			}
			
			Enlaces.setNivel(Enlaces.getDescripcion().split("/").length);
			
			if(Enlaces.getId() == null)
				ces.Guardar(Enlaces);
			else
				ces.Actualizar(Enlaces);
			
			model.addAttribute("mensaje", "Enlace registrado correctamente.");
		}
		catch(Exception e)
		{
			model.addAttribute("error", e.getMessage());
			logger.error(AppController.getPrincipal() + " - permisos_perfilespost. - " + e);
		}
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		model.addAttribute("ListaEnlaces", ces.BuscarxParams(Params, mOrd));
		return "/appconfig/enlaces";
	}
	
	@RequestMapping(value = {"/enlacesdel" }, method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Object> enlacesdelete(ModelMap model, @RequestParam(value = "id", defaultValue = "0", required = true) Integer id) {
		try 
		{			
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			logger.info(AppController.getPrincipal() + " - enlacesget");
			ces.Eliminar(ces.BuscarxId(id));
			return new ResponseEntity<Object>("Borrado ok...",HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error(AppController.getPrincipal() + " - enlacesdelete - " + e);
			return new ResponseEntity<Object>("Error..."+ e.getMessage()+"-"+e.getCause(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
