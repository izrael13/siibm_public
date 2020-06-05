package com.websystique.springmvc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.produccion.OS_ProgCorrugado;
import com.websystique.springmvc.model.produccion.RollosPapel;
import com.websystique.springmvc.model.produccion.Validacion_ped_rollo;
import com.websystique.springmvc.service.UserService;
import com.websystique.springmvc.service.produccion.OS_ProgCorrugadoService;
import com.websystique.springmvc.service.produccion.RollosPapelService;
import com.websystique.springmvc.service.produccion.Validacion_ped_rolloService;

@Controller
@RequestMapping("/produccion")
public class ProduccionController {
	private Logger logger = Logger.getLogger(ProduccionController.class);
	
	@Autowired	UserService us;
	@Autowired	OS_ProgCorrugadoService pro_cor;
	@Autowired	RollosPapelService paper;
	@Autowired 	Validacion_ped_rolloService vali_pr;
		
	@RequestMapping("/validacion/pedidopapel")
	public ModelAndView search(@RequestParam(value="keyword_num_programa", required=false, defaultValue ="0")String keyword_num_programa,
							   @RequestParam(value="keyword_num_rollo", required=false, defaultValue ="")String keyword_num_rollo) {
	
		ModelAndView mav = new ModelAndView("/produccion/validado");	
		
		return mav;
		
	}// FIN /produccion/validado
	
	
	@RequestMapping(value = {"/validacion/validar"}, method = RequestMethod.GET)
	public @ResponseBody String validar(@RequestParam(value="programa", required=false, defaultValue ="0")String Programa,
							   @RequestParam(value="rollo", required=false, defaultValue ="")String rollo,
							   @RequestParam(value="tipopapel", required=false, defaultValue ="")String tipopapel) {
		
		logger.info(AppController.getPrincipal() + " - validacion/validar");
		List<OS_ProgCorrugado>ListoS_ProgCorrugado= pro_cor.OS_ProgCorrugado(Programa);//Pedido
		RollosPapel rollosPapel = paper.RollosPapel(rollo);//LISTA ROLLO
		Boolean ban = false;
		if(rollosPapel != null && ListoS_ProgCorrugado .size() > 0) {
			double anchoRolloObj = rollosPapel.getAncho();
			anchoRolloObj = (anchoRolloObj > 100 ? anchoRolloObj / 100 : anchoRolloObj);
			double anchoProgCorrObj = 0.0;
			
			for(OS_ProgCorrugado progCorrObj : ListoS_ProgCorrugado)
			{
				switch (tipopapel)
				{
					case "L1": anchoProgCorrObj = progCorrObj.getLINER1WIDTH(); break;
					case "L2": anchoProgCorrObj = progCorrObj.getLINER2WIDTH(); break;
					case "L3": anchoProgCorrObj = progCorrObj.getLINER3WIDTH(); break;
					case "M1": anchoProgCorrObj = progCorrObj.getMEDIUM1WIDTH(); break;
					case "M2": anchoProgCorrObj = progCorrObj.getMEDIUM2WIDTH(); break;
					default: anchoProgCorrObj = 0.0; break;
					
				}
				
				anchoProgCorrObj = (anchoProgCorrObj > 100 ? anchoProgCorrObj / 100 : anchoProgCorrObj);
				if(anchoProgCorrObj == anchoRolloObj) {
					ban = true;
					break;
				}
			}
		}
		if(ban)
		{
			List<JSONObject> ListaJsonProg = new ArrayList<JSONObject>();
			ListaJsonProg = ListaProg(ListoS_ProgCorrugado);
			Validacion_ped_rollo prog=new Validacion_ped_rollo();
			Date date = new Date();
			User user = us.findBySSO(AppController.getPrincipal());
			prog.setUSER_ID(user.getId());
			prog.setDate_valid(date);
			prog.setNumerorolloid(rollo);
			prog.setProgCorru_ID(Programa);
			if(vali_pr.BuscarxPedRollo(Programa, rollo) == null)
				vali_pr.Save(prog);
			
			List<JSONObject> ListaJsonRollos = new ArrayList<JSONObject>();
			ListaJsonRollos = ListaRollos(vali_pr.ListValid(Programa));
			logger.info(AppController.getPrincipal() + " - validacion/validar - save");
			return ListaJsonProg.toString()+"||"+ListaJsonRollos.toString();
		}
		else		
			return "0";
	}
	
	private List<JSONObject> ListaRollos(List<Validacion_ped_rollo> Lista)
	{
		List<JSONObject> ListaJsonRollos = new ArrayList<JSONObject>();
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().setDateFormat("yyyy-MM-dd HH:mm").create();
		for(Validacion_ped_rollo val : Lista)
		{
			RollosPapel rollosPapel = new RollosPapel();
			rollosPapel = paper.RollosPapel(val.getNumerorolloid());//LISTA ROLLO
			JSONObject Rollo = new JSONObject(gson.toJson(rollosPapel));
			Rollo.put("fecha", val.getDate_valid());
			ListaJsonRollos.add(Rollo);
		}
		return ListaJsonRollos;
	}
	
	private List<JSONObject> ListaProg(List<OS_ProgCorrugado> Lista)
	{
		List<JSONObject> ListaJsonProg = new ArrayList<JSONObject>();
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().setDateFormat("yyyy-MM-dd HH:mm").create();
		for(OS_ProgCorrugado progCorrObj : Lista)
		{
			JSONObject Prog = new JSONObject(gson.toJson(progCorrObj));
			ListaJsonProg.add(Prog);
		}
		return ListaJsonProg;
	}
	
}
