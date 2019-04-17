package com.websystique.springmvc.controller;

//import java.sql.Date;
/*import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
//import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.websystique.springmvc.model.viajes.Viajes_embarques;
import com.websystique.springmvc.service.viajes.Viajes_detalleService;
import com.websystique.springmvc.service.viajes.Viajes_embarquesService;
import com.websystique.springmvc.utilities.JsonFunctions; */

//@Controller
//@RequestMapping("/viajes")
public class Viajes_embarquesController {
	
	//private Logger logger = Logger.getLogger(Viajes_embarquesController.class);
	
	//@Autowired
	//Viajes_embarquesService svt;
	
	//@Autowired
	//Viajes_detalleService svd;
	
/*	@RequestMapping(value = {"/aut_embarques" }, method = RequestMethod.GET)
	public String autoriza_embarques(ModelMap model) {
		try {
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		model.addAttribute("viajes",svt.findAllViajesByUserSap(0));
		logger.info(AppController.getPrincipal() + " - aut_embarques.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - aut_embarques. - " + e.getMessage());
		}
		return "/viajes_embarques/autorizacion_embarques";
	}
	
	@RequestMapping(value = {"/detalle" }, method = RequestMethod.GET)
	public String viajes_detalle(ModelMap model,@RequestParam("num_viaje") String num_viaje) {
		try {
		model.addAttribute("num_viaje",num_viaje);
		model.addAttribute("detalle",svd.findDetalleByViaje(num_viaje));
		logger.info(AppController.getPrincipal() + " - detalle.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - detalle. - " + e.getMessage());
		}
		return "/viajes_embarques/viajes_detalle";
	}

	 @RequestMapping(value = {"/act_viaje"},produces=MediaType.APPLICATION_JSON_VALUE , method = RequestMethod.POST)
	public @ResponseBody String act_viaje(@RequestBody String note, HttpServletRequest req, HttpServletResponse res)
	   throws Exception {
		 List<Viajes_embarques> ve = null;
		 Viajes_embarques v= new Viajes_embarques();
		 Gson g=new Gson();
		 try {
		 //String result = "";
			 JsonFunctions jf = new JsonFunctions();
			 JSONObject jsonObject = jf.jsonObject(note);
			 ve = svt.updateViajes(String.valueOf(((Long)jsonObject.get("ajxnum_viaje"))), 
					 				Integer.parseInt((String)jsonObject.get("ajxDemoras")), 
					 				Integer.parseInt((String)jsonObject.get("ajxDevoluciones")),
					 				Integer.parseInt((String)jsonObject.get("ajxCboEdo"))); 

		
		@SuppressWarnings("rawtypes")
		Iterator itr = ve.iterator();
		Object[] obj = (Object[]) itr.next();
		//JSONArray jsonA = JSONArray.fromObject(ve);
		//System.out.println(String.valueOf(obj[2]));
			 v.setCode(String.valueOf(obj[0]));
			 v.setName(String.valueOf(obj[1]));
			 v.setU_num_viaje(String.valueOf(obj[2]));
			 //v.setU_fecha_reg(Date.valueOf(String.valueOf(obj[3])));
			 v.setU_kilos(Double.parseDouble(String.valueOf(obj[4])));
			 v.setU_repartos(Integer.parseInt(String.valueOf(obj[5])));
			 v.setU_cambio_edo(Integer.parseInt(String.valueOf(obj[6])));
			 v.setU_costo(Double.parseDouble(String.valueOf(obj[7])));
			 v.setU_demoras(Integer.parseInt(String.valueOf(obj[8])));
			 v.setU_devoluciones(Integer.parseInt(String.valueOf(obj[9])));
			 v.setU_aut_ambarques(String.valueOf(obj[10]));
			 v.setU_aut_logistica(String.valueOf(obj[11]));
			 v.setU_maniobras(Integer.parseInt(String.valueOf(obj[12])));
		
			 logger.info(AppController.getPrincipal() + " - act_viaje.");
			}
			catch(Exception e) {
				logger.error(AppController.getPrincipal() + " - act_viaje. - " + e.getMessage());
			}

	    return g.toJson(v);
	 
	} */
	 
	/* @RequestMapping(value = {"/set_aut_embarques"} , method = RequestMethod.GET)
	public String aut_emb(ModelMap model,@RequestParam("num_viaje") String num_viaje)
	   throws Exception {
		 try {
		 	String result = svt.updateAutEmbarques(num_viaje);
		 	model.addAttribute("loggedinuser", AppController.getPrincipal());
		 	model.addAttribute("viajes",svt.findAllViajesByUserSap(0));
		 	model.addAttribute("result",result);
		 	logger.info(AppController.getPrincipal() + " - set_aut_embarques.");
			}
			catch(Exception e) {
				logger.error(AppController.getPrincipal() + " - set_aut_embarques. - " + e.getMessage());
			}
		 	return "/viajes_embarques/autorizacion_embarques";
	}
	
	 @RequestMapping(value = {"/aut_logistica" }, method = RequestMethod.GET)
	public String autoriza_logistica(ModelMap model) {
		 try {
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			model.addAttribute("viajes",svt.findAllViajesByUserSapLog(0));
			logger.info(AppController.getPrincipal() + " - aut_logistica.");
			}
			catch(Exception e) {
				logger.error(AppController.getPrincipal() + " - aut_logistica. - " + e.getMessage());
			}
			return "/viajes_embarques/autorizacion_logistica";
		}*/
	 
	/* @RequestMapping(value = {"/set_aut_logistica"} , method = RequestMethod.GET)
	public String aut_log(ModelMap model,@RequestParam("num_viaje") String num_viaje)
	   throws Exception {
		 try {
		 	String result = svt.updateAutLogistica(num_viaje);
		 	model.addAttribute("loggedinuser", AppController.getPrincipal());
		 	model.addAttribute("viajes",svt.findAllViajesByUserSapLog(0));
		 	model.addAttribute("result",result);
		 	logger.info(AppController.getPrincipal() + " - set_aut_logistica.");
			}
			catch(Exception e) {
				logger.error(AppController.getPrincipal() + " - set_aut_logistica. - " + e.getMessage());
			}
		 	return "/viajes_embarques/autorizacion_logistica";
	}
	 
	 @RequestMapping(value = {"/set_reg_embarques"} , method = RequestMethod.GET)
	
	public String reg_emb(ModelMap model,@RequestParam("num_viaje") String num_viaje)
	   throws Exception {
		 try {
		 	String result = svt.updateRegAemb(num_viaje);
		 	model.addAttribute("loggedinuser", AppController.getPrincipal());
		 	model.addAttribute("viajes",svt.findAllViajesByUserSapLog(0));
		 	model.addAttribute("result",result);
		 	logger.info(AppController.getPrincipal() + " - set_reg_embarques.");
			}
			catch(Exception e) {
				logger.error(AppController.getPrincipal() + " - set_reg_embarques. - " + e.getMessage());
			}
		 	return "/viajes_embarques/autorizacion_logistica";
	}*/
	/* 
	 @RequestMapping(value = {"/hisotia___"} , method = RequestMethod.GET)
	public String historial(ModelMap model)
	   throws Exception {
		 try {
		 	model.addAttribute("loggedinuser", AppController.getPrincipal());
		 	model.addAttribute("viajes",svt.findAllViajesHistorial());
		 	logger.info(AppController.getPrincipal() + " - hisotia___.");
			}
			catch(Exception e) {
				logger.error(AppController.getPrincipal() + " - hisotia___. - " + e.getMessage());
			}
		 	return "/viajes_embarques/viajes_historial";
	} */
	 
}
