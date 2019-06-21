package com.websystique.springmvc.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.websystique.springmvc.model.Catalogo_municipios;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.tarjetas.prospectos.ProspectosDataBean;
import com.websystique.springmvc.model.tarjetas.prospectos.Prospectos_ventas;
import com.websystique.springmvc.model.tarjetas.prospectos.Prospectos_ventas_detalle;
import com.websystique.springmvc.service.Catalogo_estadosService;
import com.websystique.springmvc.service.Catalogo_municipiosService;
import com.websystique.springmvc.service.UserService;
import com.websystique.springmvc.service.tarjetas.Catalogo_clientes_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Catalogo_giros_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Catalogo_vendedores_sap_vwService;
import com.websystique.springmvc.service.tarjetas.prospectos.Prospectos_ventasService;
import com.websystique.springmvc.service.tarjetas.prospectos.Prospectos_ventas_detalleService;

@Controller
@RequestMapping("/prospectos")
public class Prospectos_ventasController {
	
	private Logger logger = Logger.getLogger(Prospectos_ventasController.class);
	
	@Autowired
	Catalogo_clientes_sap_vwService ccavs;
	@Autowired
	Catalogo_giros_sap_vwService cgssv;
	@Autowired
	Catalogo_estadosService ces;
	@Autowired
	Catalogo_municipiosService cms;
	@Autowired
	UserService us;
	@Autowired
	Prospectos_ventasService pvs;
	@Autowired
	Prospectos_ventas_detalleService pvds;
	@Autowired
	Catalogo_vendedores_sap_vwService cvsv;
	
	@RequestMapping(value = {"/vendedor/prospectosabc" }, method = RequestMethod.GET)
	public String abc(ModelMap model,@RequestParam("id") String id) {
			User user = us.findBySSO(AppController.getPrincipal());
			ProspectosDataBean pdb = new ProspectosDataBean();
			if(Integer.valueOf(id) > 0)
			{
				Prospectos_ventas pv = pvs.buscarPorId(Integer.valueOf(id));
				model.addAttribute("ciudades",cms.ListMunicipiosXEstado(pv.getCve_estado()));
				pdb.setProspectos_ventas(pv);
				//model.addAttribute("prospectos",pv);
				model.addAttribute("prospectos_detallelist",pvds.BuscarPorIdPV(pv.getId()));
			}
			else
			{
				model.addAttribute("prospectos_detallelist",pvds.BuscarPorIdPV(0));
				pdb.setProspectos_ventas(new Prospectos_ventas());
				//model.addAttribute("prospectos",new Prospectos_ventas());
				model.addAttribute("ciudades",cms.ListMunicipiosXEstado(0));
				
			}
			//System.out.println(user.getCvevendedor_sap());
			pdb.setProspectos_ventas_detalle(new Prospectos_ventas_detalle());
			model.addAttribute("prospectos_bean",pdb);
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			model.addAttribute("clientes", ccavs.ListaCtes(user.getCvevendedor_sap()));
			model.addAttribute("estados",ces.ListEstados());
			logger.info(AppController.getPrincipal() + " - prospectosabc.");
		
		return "/tarjetas/prospectos/prospectos_abc";
	}
	
	@RequestMapping(value = {"/vendedor/buscarciudadxestado"}, method = RequestMethod.GET)
	public @ResponseBody String buscarciudadxestado(HttpServletRequest req, HttpServletResponse res)
	   throws Exception {
		String cve_estado = req.getParameter("cve_estado");
		//System.out.println(cve_estado);
		List<Catalogo_municipios> Ciudades = cms.ListMunicipiosXEstado(Integer.parseInt(cve_estado));
		Gson g=new Gson();
		return g.toJson(Ciudades);
	
	}
	
	@RequestMapping(value = {"/vendedor/prospectosabc" }, method = RequestMethod.POST)
	public String abcpost(@Valid @ModelAttribute("prospectos_bean") ProspectosDataBean prospectosDataBean,BindingResult result,ModelMap model) {
		/*
		for (ObjectError error : result.getAllErrors()) { // 1.
	       String fieldErrors [] = error.getCodes();
	       System.out.println(fieldErrors[0]);
	   }*/
		String mensajes = "";
		User user = us.findBySSO(AppController.getPrincipal());
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		model.addAttribute("clientes", ccavs.ListaCtes(user.getCvevendedor_sap()));
		//model.addAttribute("giros", cgssv.ListaGiros());
		model.addAttribute("estados",ces.ListEstados());
		model.addAttribute("ciudades",cms.ListMunicipiosXEstado(prospectosDataBean.getProspectos_ventas().getCve_estado()));
		model.addAttribute("prospectos",prospectosDataBean.getProspectos_ventas());
		
		java.util.Date date = new java.util.Date();
		//SimpleDateFormat formatter1=new SimpleDateFormat("yyyy/MM/dd"); 
		//String fecha_up = formatter1.format(date);
		
		prospectosDataBean.getProspectos_ventas().setFecha_update(date);
		prospectosDataBean.getProspectos_ventas().setUsuario_update(user.getId());
		prospectosDataBean.getProspectos_ventas().setPorcentaje_avance(prospectosDataBean.getProspectos_ventas_detalle().getCve_actividad());
		
		prospectosDataBean.getProspectos_ventas_detalle().setUsuario_insert(user.getId());
		prospectosDataBean.getProspectos_ventas_detalle().setFecha_insert(date);
		//System.out.println("FEcha Datos: "+prospectos_ventas.toString());
		
		logger.info(AppController.getPrincipal() + " - prospectosabc.");
		
		if (result.hasErrors() )
		{
			return "/tarjetas/prospectos/prospectos_abc";
		}
		
		ProspectosDataBean pdb = new ProspectosDataBean();
		Integer id = 0;
		if(prospectosDataBean.getProspectos_ventas().getId() > 0)
		{
			pvs.Actualizar(prospectosDataBean.getProspectos_ventas());
			id = prospectosDataBean.getProspectos_ventas().getId();
			prospectosDataBean.getProspectos_ventas_detalle().setId_prospecto_ventas(id);
			pdb.setProspectos_ventas(pvs.buscarPorId(prospectosDataBean.getProspectos_ventas().getId()));
			mensajes = "Prospecto actualizado ";
		}
		else
		{
			prospectosDataBean.getProspectos_ventas().setUsuario_insert(user.getId());
			prospectosDataBean.getProspectos_ventas().setFecha_insert(date);
			id = pvs.Guardar(prospectosDataBean.getProspectos_ventas());
			prospectosDataBean.getProspectos_ventas_detalle().setId_prospecto_ventas(id);
			pdb.setProspectos_ventas(pvs.buscarPorId(id));
			mensajes = "Prospecto registrado ";
		}
		
		if( prospectosDataBean.getProspectos_ventas_detalle().getFecha_cierre().trim().length() > 0 &&
			prospectosDataBean.getProspectos_ventas_detalle().getCve_actividad() > 0 &&
			prospectosDataBean.getProspectos_ventas_detalle().getObservaciones().trim().length() > 0) 
		{
			pvds.Guardar(prospectosDataBean.getProspectos_ventas_detalle());
			mensajes = mensajes +  "y actividad registrada";
		}
		
		model.addAttribute("mensajes",mensajes);
		model.addAttribute("prospectos_detallelist",pvds.BuscarPorIdPV(id));
		pdb.setProspectos_ventas_detalle(new Prospectos_ventas_detalle());
		model.addAttribute("prospectos_bean",pdb);
		
		return "/tarjetas/prospectos/prospectos_abc";
	}
	
	@RequestMapping(value = {"/vendedor/prospectosbusqueda" }, method = RequestMethod.GET)
	public String prospectosbusqueda(ModelMap model,@RequestParam("id") String id,@RequestParam("cardcode") String cardcode) {
		
		User user = us.findBySSO(AppController.getPrincipal());
		//model.addAttribute("id",id);
		//model.addAttribute("cardcode",cardcode);
		
		//System.out.println(pvs.buscarPorIdCardCode(user.getId(), Integer.valueOf(id), cardcode).get(0).getCardname());
		model.addAttribute("lista",pvs.buscarPorIdCardCode(user.getId(), Integer.valueOf(id), cardcode,0,0.0,0,0.0,0,null,null));
		
		logger.info(AppController.getPrincipal() + " - prospectosbusqueda.");
		
		return "/tarjetas/prospectos/prospectos_busqueda";
	}
	
	@RequestMapping(value = {"/ventas/prospectosgerenteventas" }, method = RequestMethod.GET)
	public String prospectosgerenteventas(ModelMap model) {
			
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			model.addAttribute("clientes", ccavs.ListaCtes());
			model.addAttribute("vendedores", cvsv.ListaVendedores());
			logger.info(AppController.getPrincipal() + " - prospectosgerenteventas.");
		
		return "/tarjetas/prospectos/prospectos_gerente_ventas";
	}
	
	@RequestMapping(value = {"/ventas/vistagerenteventas" }, method = RequestMethod.GET)
	public String vistagerenteventas(ModelMap model,@RequestParam("id") String id,
													@RequestParam("cardcode") String cardcode,
													@RequestParam("cve_vendedor") Integer cve_vendedor,
													@RequestParam("porcavan") Double porcavan,
													@RequestParam("prioridad") Integer prioridad,
													@RequestParam("oporton") Double oporton,
													@RequestParam("estatus") Integer estatus,
													@RequestParam("fecha1") String fecha1,
													@RequestParam("fecha2") String fecha2
													) {
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		model.addAttribute("clientes", ccavs.ListaCtes());
		model.addAttribute("vendedores", cvsv.ListaVendedores());
		logger.info(AppController.getPrincipal() + " - prospectosgerenteventas.");
		
		java.sql.Date date1 = null;
		java.sql.Date date2 = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		if(!fecha1.trim().equals(""))
		{
			try {
				
				java.util.Date auxdate1 = formatter.parse(fecha1);
				date1 = new java.sql.Date(auxdate1.getTime());  
				
			} catch (ParseException e) {
				// FIXME Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(!fecha2.trim().equals(""))
		{
			try {
				java.util.Date auxdate2 = formatter.parse(fecha2);
				date2 = new java.sql.Date(auxdate2.getTime()); 
			} catch (ParseException e) {
				// FIXME Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		model.addAttribute("lista",pvs.buscarPorIdCardCode(0, 0, cardcode,cve_vendedor,porcavan,prioridad,oporton,estatus,date1,date2));
		
		logger.info(AppController.getPrincipal() + " - vistagerenteventas.");
		
		return "/tarjetas/prospectos/prospectos_gerente_ventas";
	}
	
}
