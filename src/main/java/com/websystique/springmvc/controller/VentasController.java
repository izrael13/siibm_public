package com.websystique.springmvc.controller;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.websystique.springmvc.service.ventas.PronosticoscteService;
import com.websystique.springmvc.service.ventas.WarningspronService;

@Controller
@RequestMapping("/ventas")
public class VentasController {
	private Logger logger = Logger.getLogger(VentasController.class);
	
	@Autowired
	PronosticoscteService pss;
	
	@Autowired
	WarningspronService wps;
	
	Calendar cal= Calendar.getInstance();
	
	@RequestMapping(value = {"/pronosticoscte____" }, method = RequestMethod.GET)
	public String uploadFileGET(ModelMap model) {
		try {
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			model.addAttribute("anioact", cal.get(Calendar.YEAR));
			model.addAttribute("mesact", cal.get(Calendar.MONTH)+1);
			logger.info(AppController.getPrincipal() + " - pronosticoscte.");
		}
		catch(Exception e)
		{
			model.addAttribute("error",e.getMessage());
			logger.error(AppController.getPrincipal() + " - pronosticoscte. - " + e.getMessage());
		}
		return "ventas/pronosticoscte";
	}
	
	@RequestMapping(value = {"/upload" }, method = RequestMethod.POST)
	public String uploadFilePOST(ModelMap model,@RequestParam("file") MultipartFile file) {
		try
		{
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			model.addAttribute("anioact", cal.get(Calendar.YEAR));
			model.addAttribute("mesact", cal.get(Calendar.MONTH)+1);
			if(file.isEmpty())
			{
				model.addAttribute("mensaje","Archivo vacío");
				//return new ModelAndView("/ventas/pronosticoscte","mensaje","Archivo vacío");
			}
			else
			{
				model.addAttribute("filepath", file.getOriginalFilename());
				wps.truncateTable();
				model.addAttribute("file",pss.readFile(file));
				model.addAttribute("map", wps.map());
			}
			logger.error(AppController.getPrincipal() + " - upload.");
		}
		catch(Exception e)
		{
			model.addAttribute("error",e.getMessage());
			logger.error(AppController.getPrincipal() + " - upload. - "+e.getMessage());
		}
		return "/ventas/pronosticoscte";
	}
	
	@RequestMapping(value = {"/subirqv" }, method = RequestMethod.GET)
	public String subirqv(ModelMap model,@RequestParam("mes") String mes, @RequestParam("anio") String anio) {
		try {
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			model.addAttribute("anioact", cal.get(Calendar.YEAR));
			model.addAttribute("mesact", mes);
			model.addAttribute("mensaje", wps.subirQV(Integer.parseInt(mes), Integer.parseInt(anio)));
			logger.error(AppController.getPrincipal() + " - subirqv.");
		}
		catch(Exception e)
		{
			model.addAttribute("error", e.getMessage());
			logger.error(AppController.getPrincipal() + " - subirqv. - " + e.getMessage());
		}
		return "ventas/pronosticoscte";
	}
		
}
