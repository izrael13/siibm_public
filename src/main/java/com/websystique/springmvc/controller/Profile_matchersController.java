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

import com.websystique.springmvc.model.Profile_matchers;
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
	
}
