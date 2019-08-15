package com.websystique.springmvc.controller;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.websystique.springmvc.model.ventas.Pronosticoscte;
import com.websystique.springmvc.service.reportes.Meses_anioService;
import com.websystique.springmvc.service.tarjetas.Catalogo_clientes_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Catalogo_vendedores_sap_vwService;
import com.websystique.springmvc.service.ventas.PronosticoscteService;
import com.websystique.springmvc.service.ventas.WarningspronService;

@Controller
@RequestMapping("/qlikview")
public class VentasController {
	private Logger logger = Logger.getLogger(VentasController.class);
	
	@Autowired
	PronosticoscteService pss;
	@Autowired
	WarningspronService wps;	
	@Autowired
	Catalogo_vendedores_sap_vwService cvsvs;
	@Autowired
	Catalogo_clientes_sap_vwService ccsvs;
	@Autowired
	Meses_anioService mesesanio;
	
	@RequestMapping(value = {"/ventas/pronosticoscte____" }, method = RequestMethod.GET)
	public String uploadFileGET(ModelMap model) {
		try {
			Calendar calendar = Calendar.getInstance();
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			model.addAttribute("mesesanio", mesesanio.findallRegistros());
			model.addAttribute("selectedValue", String.valueOf(calendar.get(Calendar.YEAR)) + String.valueOf(calendar.get(Calendar.MONTH)+1));
			model.addAttribute("listavend", cvsvs.ListaVendedores());
			logger.info(AppController.getPrincipal() + " - pronosticoscte.");
		}
		catch(Exception e)
		{
			model.addAttribute("error",e.getMessage());
			logger.error(AppController.getPrincipal() + " - pronosticoscte. - " + e.getMessage());
		}
		return "ventas/pronosticoscte";
	}
	
	@RequestMapping(value = {"/ventas/buscarclientes"}, method = RequestMethod.GET)
	public @ResponseBody String buscarclientes(HttpServletRequest req, HttpServletResponse res)
	   throws Exception {
		String slpcode = req.getParameter("id");
		
		Gson g=new Gson();
		return g.toJson(ccsvs.ListaCtes(Integer.valueOf(slpcode)));
	
	}
	
	@RequestMapping(value = {"/ventas/guardarpron" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> guardarpron(ModelMap model,
			@RequestParam("idven") Integer idven,@RequestParam("idcte") String idcte, @RequestParam("anio") String anio,@RequestParam("mes") String mes, @RequestParam("pron") Double pron) {
		String mensaje = "";
		try 
		{
			pss.Guardar(idven, idcte, anio, mes, pron,1,0);
			logger.info(AppController.getPrincipal() + " - ventas/guardarpron. ");
			return new ResponseEntity<Object>("OK", HttpStatus.OK);
		}
		catch(Exception e)
		{
			mensaje = e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage();
			model.addAttribute("mensajes", mensaje);
			logger.info(AppController.getPrincipal() + " - ventas/guardarpron. " + mensaje);
			return new ResponseEntity<Object>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	
	@RequestMapping(value = {"/ventas/buscarpron" }, method = RequestMethod.GET)
	@ResponseBody
	public String buscarpron(ModelMap model,
			@RequestParam("idven") Integer idven,@RequestParam("idcte") String idcte,  @RequestParam("anio") String anio,@RequestParam("mes") String mes, @RequestParam("pron") Double pron) {
		String mensaje = "";
		try 
		{
			List<Pronosticoscte> Lista = pss.Lista(anio, mes, idven, idcte, 0,3);
			Gson obj = new Gson();
			return obj.toJson(Lista);
		}
		catch(Exception e)
		{
			mensaje = e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage();
			return mensaje;
		}
	
	}
	
	@RequestMapping(value = {"/ventas/comparativo" }, method = RequestMethod.GET)
	public String comparativo(ModelMap model) {
		try {
			Calendar calendar = Calendar.getInstance();
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			model.addAttribute("mesesanio", mesesanio.findallRegistros());
			model.addAttribute("selectedValue", String.valueOf(calendar.get(Calendar.YEAR)) + String.valueOf(calendar.get(Calendar.MONTH)+1));
			model.addAttribute("listavend", cvsvs.ListaVendedores());
			logger.info(AppController.getPrincipal() + " - pronosticoscte.");
		}
		catch(Exception e)
		{
			model.addAttribute("error",e.getMessage());
			logger.error(AppController.getPrincipal() + " - pronosticoscte. - " + e.getMessage());
		}
		return "ventas/comparativo";
	}
	
	@RequestMapping(value = {"/ventas/comparativobuscar" }, method = RequestMethod.GET)
	@ResponseBody
	public String comparativo(ModelMap model, @RequestParam(value = "idven", defaultValue = "0", required = false) Integer idven, 
											  @RequestParam(value = "bancte", defaultValue = "0", required = false) Integer bancte, 
											  @RequestParam(value = "anio", defaultValue = "0", required = false) String anio, 
											  @RequestParam(value = "mes", defaultValue = "0", required = false) String mes) {
		try {
			
			List<Pronosticoscte> Lista = pss.Lista(anio, mes, idven, "", bancte,4);
			Gson obj = new Gson();
			logger.info(AppController.getPrincipal() + " - comparativobuscar.");
			return obj.toJson(Lista);
			
		}
		catch(Exception e)
		{
			logger.error(AppController.getPrincipal() + " - comparativobuscar. - " + e.getMessage());
			return e.getMessage();
		}
	}
	
	/*@RequestMapping(value = {"/ventas/subirqv" }, method = RequestMethod.GET)
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
	}*/
		
}
