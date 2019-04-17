package com.websystique.springmvc.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.websystique.springmvc.model.UserProfile;
import com.websystique.springmvc.service.UserProfileService;

@Controller
@RequestMapping("/perfiles")
public class PerfilesController {

private Logger logger = Logger.getLogger(PerfilesController.class);
		
	@Autowired
	MessageSource messageSource;	

	@Autowired
	UserProfileService ups;

	@RequestMapping(value = {"/list" }, method = RequestMethod.GET)
	public String perfileslistget(ModelMap model) {
		try {
			UserProfile up = new UserProfile();
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			model.addAttribute("perfileslist", ups.findAll());
			model.addAttribute("userprofile",up);
			logger.info(AppController.getPrincipal() + " - perfileslistget.");
		}
		catch(Exception e)
		{
			logger.error(AppController.getPrincipal() + " - perfileslistget. - " + e.getMessage());
		}
		return "/appconfig/perfiles";
	}
	
	@RequestMapping(value = {"/list" }, method = RequestMethod.POST)
	public String perfileslistpost(@Valid @ModelAttribute("userprofile") UserProfile userprofile,BindingResult result,ModelMap  model) {
		try {
				
			
			/*for (ObjectError error : result.getAllErrors()) { // 1.
			       String fieldErrors [] = error.getCodes();
			       System.out.println(fieldErrors[0]);
			   }*/
			
				if (result.hasErrors())
				{
					model.addAttribute("error", result);
					model.addAttribute("userprofile",userprofile);
					model.addAttribute("perfileslist", ups.findAll());
					model.addAttribute("loggedinuser", AppController.getPrincipal());
					logger.info(AppController.getPrincipal() + " - perfileslistpost.");
					return "/appconfig/perfiles";
				}
			
					ups.save(userprofile);
					UserProfile up = new UserProfile();
					model.addAttribute("loggedinuser", AppController.getPrincipal());
					model.addAttribute("userprofile",up);
					logger.info(AppController.getPrincipal() + " - perfileslistpost.");
					model.addAttribute("perfileslist", ups.findAll());
					return "/appconfig/perfiles";
				
		}
		catch(Exception e)
		{
			logger.error(AppController.getPrincipal() + " - perfileslistpost. - " + e.getMessage());
			return "/appconfig/perfiles";
		}
	}
}
