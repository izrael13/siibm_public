package com.websystique.springmvc.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.tarjetas.Catalogo_cajas_sap_vw;
import com.websystique.springmvc.model.tarjetas.Catalogo_direcciones_sap_vw;
import com.websystique.springmvc.model.tarjetas.Catalogo_resistencias_sap_vw;
import com.websystique.springmvc.model.tarjetas.Catalogo_vendedores_sap_vw;
import com.websystique.springmvc.model.tarjetas.Comision_comisionista_sap_vw;
import com.websystique.springmvc.model.tarjetas.Comision_directo_sap_vw;
import com.websystique.springmvc.model.tarjetas.Vendedores_especiales_comision_sap_vw;
import com.websystique.springmvc.model.tarjetas.cotizador.CotizadorDataBean;
import com.websystique.springmvc.model.tarjetas.cotizador.Cotizador_detallesValidator;
import com.websystique.springmvc.service.UserService;
import com.websystique.springmvc.service.tarjetas.Catalogo_cajas_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Catalogo_clientes_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Catalogo_direcciones_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Catalogo_especialidades_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Catalogo_resistencias_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Catalogo_sellosService;
import com.websystique.springmvc.service.tarjetas.Catalogo_vendedores_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Comision_comisionista_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Comision_directo_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Vendedores_especiales_comision_sap_vwService;
import com.websystique.springmvc.service.tarjetas.cotizador.CotizadorService;
import com.websystique.springmvc.service.tarjetas.cotizador.Cotizador_detallesService;

@Controller
@RequestMapping("/ventas/tarjetas/cotizador")
public class CotizadorController {
	
	private Logger logger = Logger.getLogger(CotizadorController.class);
	
	@Autowired
	UserService us;
	@Autowired
	Catalogo_clientes_sap_vwService ccavs;
	@Autowired
	Catalogo_direcciones_sap_vwService cdsv;
	@Autowired
	CotizadorService cs;
	@Autowired
	Catalogo_cajas_sap_vwService ccss;
	@Autowired
	Catalogo_resistencias_sap_vwService crss;
	@Autowired
	Catalogo_sellosService css;
	@Autowired
	Catalogo_especialidades_sap_vwService ces;
	@Autowired
	Cotizador_detallesService cds;
	@Autowired
	Cotizador_detallesValidator cdvalidator;
	@Autowired
	Catalogo_vendedores_sap_vwService cvss; 
	@Autowired
	Vendedores_especiales_comision_sap_vwService vecs;
	@Autowired
	Comision_directo_sap_vwService cdss;
	@Autowired
	Comision_comisionista_sap_vwService ccs;
	
	Integer idN = 0;
	
	@RequestMapping(value = {"/cotizadorabc" }, method = RequestMethod.GET)
	public String cotizadotget(ModelMap model, @RequestParam("id") String id) {
		try {
				User user = us.findBySSO(AppController.getPrincipal());
				model.addAttribute("clientes", ccavs.ListaCtes(user.getCvevendedor_sap()));
				model.addAttribute("listacajas", ccss.ListaCajas());
				model.addAttribute("listaresisbarca", crss.ListaResis());
				model.addAttribute("listaresiscte", css.ListaSellos());
				model.addAttribute("especialidades", ces.ListaEsp());
				model.addAttribute("loggedinuser", AppController.getPrincipal());
				
				if(Integer.valueOf(id)  == 0)
				{
					model.addAttribute("cotizadordatabean", new CotizadorDataBean());
					model.addAttribute("direcciones", cdsv.ListaDirCardCode("0"));
					model.addAttribute("direccionSelect", cdsv.ListaDirCardCodeNumLine("0",0));
				}
				else
				{
					CotizadorDataBean cdb = new CotizadorDataBean();
					cdb.setCotizador(cs.BuscarxId(Integer.valueOf(id)));
					model.addAttribute("cotizadordatabean", cdb);
					model.addAttribute("direcciones", cdsv.ListaDirCardCode(cdb.getCotizador().getCardcode()));
					model.addAttribute("direccionSelect", cdsv.ListaDirCardCodeNumLine(cdb.getCotizador().getCardcode(),cdb.getCotizador().getLinenum_dir_entrega()));
				}	
					
				logger.info(AppController.getPrincipal() + " - cotizadorabc.");
		}
		catch(Exception e)
		{
			logger.error(AppController.getPrincipal() + " - cotizadorabc. - " + e.getMessage());
		}
		return "/tarjetas/cotizador/cotizador";
	}
	
	@RequestMapping(value = {"/cotizadorabc" }, method = RequestMethod.POST)
	public String cotizadotpost(@Valid @ModelAttribute("cotizadordatabean") CotizadorDataBean cotizadorDataBean, BindingResult result, ModelMap model) {
		//try {
			
			User user = us.findBySSO(AppController.getPrincipal());
			model.addAttribute("clientes", ccavs.ListaCtes(user.getCvevendedor_sap()));
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			
			model.addAttribute("cotizadordatabean", cotizadorDataBean);
			model.addAttribute("direcciones", cdsv.ListaDirCardCode(cotizadorDataBean.getCotizador().getCardcode()));
			model.addAttribute("direccionSelect", cdsv.ListaDirCardCodeNumLine(cotizadorDataBean.getCotizador().getCardcode(),cotizadorDataBean.getCotizador().getLinenum_dir_entrega()));
			
			model.addAttribute("listacajas", ccss.ListaCajas());
			model.addAttribute("listaresisbarca", crss.ListaResis());
			model.addAttribute("listaresiscte", css.ListaSellos());
			model.addAttribute("especialidades", ces.ListaEsp());
			
			java.util.Date date = new java.util.Date();
			
			
			cdvalidator.validate(cotizadorDataBean.getCotizador_detalles(), result);
						System.out.println(cotizadorDataBean.getCotizador_detalles().toString());
			for (ObjectError error : result.getAllErrors()) {
			       String fieldErrors [] = error.getCodes();
			       System.out.println(fieldErrors[0]);
			   }
			if (result.hasErrors() )
			{
				return "/tarjetas/cotizador/cotizador";
			}
			
			cotizadorDataBean.getCotizador().setFecha_update(date);
			cotizadorDataBean.getCotizador().setUsuario_update(user.getId());
			cotizadorDataBean.getCotizador_detalles().setFecha_update(date);
			cotizadorDataBean.getCotizador_detalles().setUsuario_update(user.getId());
			idN = 0;
			if(cotizadorDataBean.getCotizador().getId() == 0)
			{
				cotizadorDataBean.getCotizador().setFecha_insert(date);			
				cotizadorDataBean.getCotizador().setUsuario_insert(user.getId());
				cotizadorDataBean.getCotizador().setIdtiporequerimiento(0);
				idN = cs.Guardar(cotizadorDataBean.getCotizador());
			}
			else
			{
				idN = cotizadorDataBean.getCotizador().getId();
				cs.Actualizar(cotizadorDataBean.getCotizador());
			}
			
			if(cotizadorDataBean.getCotizador_detalles().getIddetalle() == 0)
			{
				cotizadorDataBean.getCotizador_detalles().setIddetalle(cds.BuscarxCotId(idN).size() + 1);
				cotizadorDataBean.getCotizador_detalles().setIdcotizacion(idN);
				cotizadorDataBean.getCotizador_detalles().setFecha_insert(date);			
				cotizadorDataBean.getCotizador_detalles().setUsuario_insert(user.getId());
				cds.Guardar(cotizadorDataBean.getCotizador_detalles());
			}
			else
			{
				cotizadorDataBean.getCotizador_detalles().setIdcotizacion(idN);
				cds.Actualizar(cotizadorDataBean.getCotizador_detalles());
			}

			logger.info(AppController.getPrincipal() + " - cotizadotpost.");
/*		}
		catch(Exception e)
		{
			logger.error(AppController.getPrincipal() + " - cotizadotpost. - " + e.getMessage());
		}*/
		return "/tarjetas/cotizador/cotizador";
	}
	
	@RequestMapping(value = {"/buscardirecciones"}, method = RequestMethod.GET)
	public @ResponseBody String buscardirecciones(HttpServletRequest req, HttpServletResponse res)
	   throws Exception {
		String cardcode = req.getParameter("cardcode");
		List<Catalogo_direcciones_sap_vw> ListaDir = cdsv.ListaDirCardCode(cardcode);
		Gson g=new Gson();
		return g.toJson(ListaDir);
	
	}
	
	@RequestMapping(value = {"/buscarinfodir"}, method = RequestMethod.GET)
	public @ResponseBody String buscarinfodir(HttpServletRequest req, HttpServletResponse res)
	   throws Exception {
		String cardcode = req.getParameter("cardcode");
		String linenum = req.getParameter("linenum");
		
		List<Catalogo_direcciones_sap_vw> ListaDir = cdsv.ListaDirCardCodeNumLine(cardcode, Integer.parseInt(linenum));
		Gson g=new Gson();
		return g.toJson(ListaDir);
	
	}
	
	@RequestMapping(value = {"/cotizadorbusqueda" }, method = RequestMethod.GET)
	public String cotizadorbusqueda(ModelMap model,@RequestParam("id") String id,@RequestParam("cardcode") String cardcode) {
		
		User user = us.findBySSO(AppController.getPrincipal());
		model.addAttribute("lista",cs.ListaBusquedaxIdCardCode(Integer.valueOf(id), cardcode, user.getId()));
		
		logger.info(AppController.getPrincipal() + " - cotizadorbusqueda.");
		
		return "/tarjetas/cotizador/cotizador_busqueda";
	}
	
	@RequestMapping(value = {"/buscarinforesistenciabarca"}, method = RequestMethod.GET)
	public @ResponseBody String buscarinforesistenciabarca(HttpServletRequest req, HttpServletResponse res)
	   throws Exception {
		String id = req.getParameter("id");
		
		Catalogo_resistencias_sap_vw crs = new Catalogo_resistencias_sap_vw();
		crs = crss.BuscarxId(Integer.valueOf(id));
		Gson g=new Gson();
		
		//calcularDatos(64.4,0.0,0.0,0.0,0,34.6,50000,1832.00,1,0.0,5544.00,"C-000802",0.4054,16077.39,30.54,8,4263.57);
		
		return g.toJson(crs);
	}
	//calculardatos
	@RequestMapping(value = {"/calculardatos"}, method = RequestMethod.GET)
	public @ResponseBody String calculardatos(HttpServletRequest req, HttpServletResponse res)
	{
		String mystring = req.getParameter("mystring");
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonTree = jsonParser.parse(mystring);
		JsonObject jsonObjectParams = jsonTree.getAsJsonObject();
		JsonObject object = new JsonObject();//Objeto JSon
		Catalogo_cajas_sap_vw objCaja = ccss.BuscarxId(jsonObjectParams.get("idcaja").getAsInt());//Datos de la caja seleccionada.
		String[] AnchoStr = String.valueOf(jsonObjectParams.get("ancho").getAsDouble()).split("\\.");//Split del Ancho para obtener los decimales.
		List<Integer> vals = Arrays.asList(0,2,4,6,8);//Lista de valores enteros pares.
		User user = us.findBySSO(AppController.getPrincipal());
		Catalogo_vendedores_sap_vw Vendedor = cvss.BuscarXid(user.getCvevendedor_sap());
		Double AnchoVar = 0.0;
		Double LargoVar = 0.0;
		
		if(objCaja != null) {
			AnchoVar = ((objCaja.getMaa() * jsonObjectParams.get("ancho").getAsDouble()) + (objCaja.getMaf() * jsonObjectParams.get("fondo").getAsDouble()) + (objCaja.getTr() * ((jsonObjectParams.get("espsup").getAsDouble() + jsonObjectParams.get("espinf").getAsDouble()) / 2)) + (vals.contains(Integer.valueOf(AnchoStr[1])) ? objCaja.getDesami() : objCaja.getDesam()));		
			LargoVar = (objCaja.getMll() * jsonObjectParams.get("largo").getAsDouble()) + (objCaja.getMla() * jsonObjectParams.get("ancho").getAsDouble() + objCaja.getMlf() * jsonObjectParams.get("fondo").getAsDouble()) + (jsonObjectParams.get("score").getAsInt() == 1 ? objCaja.getDeslm() : objCaja.getDeslmi());
		}

		Double AreaUni = (LargoVar * AnchoVar) / 10000;
		object.addProperty("AreaUni", AreaUni);
		
		Double PesoTeorico = AreaUni * jsonObjectParams.get("pesoresis").getAsDouble();
		object.addProperty("PesoTeorico", PesoTeorico);
		
		Double PrecioNeto = jsonObjectParams.get("preciom2resis").getAsDouble() * AreaUni;
		object.addProperty("PrecioNeto", PrecioNeto);
		
		Double M2 = jsonObjectParams.get("cantpedmes").getAsDouble() * AreaUni;
		object.addProperty("M2", M2);
		
		Double PesoPza = AreaUni * jsonObjectParams.get("pesoresis").getAsDouble();
		object.addProperty("PesoPza", PesoPza);
		
		Double KG = PesoPza * jsonObjectParams.get("cantpedmes").getAsDouble() ;
		object.addProperty("KG", KG);
		
		String MedLamina = String.valueOf(LargoVar) + " x " + AnchoVar;
		object.addProperty("MedLamina", MedLamina);
		
		Double ComisionDirecto = PrecioNeto > 0 ? (1 - (jsonObjectParams.get("precioobj").getAsDouble() / PrecioNeto)) * 100 : 0.0;
		object.addProperty("ComisionDirecto", ComisionDirecto);
		
		Double CostoPapel = jsonObjectParams.get("costopapelresis").getAsDouble() * AreaUni * jsonObjectParams.get("pzasxjgo").getAsDouble();
		object.addProperty("CostoPapel", CostoPapel);
		
		Double CostoFlete = 0.0;
		try {
			CostoFlete = (jsonObjectParams.get("totalflete").getAsDouble() / (4500 /(PesoPza * jsonObjectParams.get("pzasxjgo").getAsDouble()))) * 1000;
		}catch(Exception e) {CostoFlete = 0.0;}
		object.addProperty("CostoFlete", CostoFlete);
		
		Double CPSC = (jsonObjectParams.get("precioobj").getAsDouble() - jsonObjectParams.get("costoespcialidades").getAsDouble() - CostoFlete) > 0 ? (CostoPapel / (jsonObjectParams.get("precioobj").getAsDouble() - jsonObjectParams.get("costoespcialidades").getAsDouble() - CostoFlete)) * 100 : 0.0;
		
		object.addProperty("CPSC", CPSC);
		
		Double PorcComision = 0.0;
		List<Vendedores_especiales_comision_sap_vw> ListaVES = vecs.VenEsp(user.getCvevendedor_sap(), jsonObjectParams.get("cardcode").getAsString());
		if(ListaVES.size() > 0)
		{	
			PorcComision = ListaVES.get(0).getU_comision();
		}
		else
		{			
			ListaVES.clear();
			ListaVES = vecs.VenEsp(user.getCvevendedor_sap(), "");
			if(ListaVES.size() > 0)
			{
				PorcComision = ListaVES.get(0).getU_comision();
			}
			else
			{
				if(PorcComision == 0.0 && Vendedor.getClasevendedor().equals("Directo"))
				{
					List<Comision_directo_sap_vw> ListaD = cdss.ListaCDSV();
					PorcComision = ListaD.stream().filter(a -> a.getCode() <= ComisionDirecto && a.getName() >= ComisionDirecto).findFirst().get().getU_comision();
				}
				else
				{
					if(PorcComision == 0.0 && Vendedor.getClasevendedor().equals("Comisionista"))
					{
						List<Comision_comisionista_sap_vw> ListaC = ccs.ListaCCSV();
						PorcComision = ListaC.stream().filter(a -> a.getName() <= CPSC && a.getCode() <= CPSC).findFirst().get().getU_comision();
					}
				}
			}
		}		
		object.addProperty("PorcComision", PorcComision);
		Double ComXmillar = (PorcComision  / 100) * jsonObjectParams.get("precioobj").getAsDouble();
		object.addProperty("ComXmillar", ComXmillar);

		Double CPCC  = (jsonObjectParams.get("precioobj").getAsDouble() - jsonObjectParams.get("costoespcialidades").getAsDouble() - CostoFlete - ComXmillar) > 0 ? (CostoPapel / (jsonObjectParams.get("precioobj").getAsDouble() - jsonObjectParams.get("costoespcialidades").getAsDouble() - CostoFlete - ComXmillar)) * 100 : 0.0;
		object.addProperty("CPCC", CPCC);
		
		Double LimVendedor = PrecioNeto * (jsonObjectParams.get("descven").getAsDouble() / 100);
		
		Double PrecioSugerido = PrecioNeto - LimVendedor;
		object.addProperty("PrecioSugerido", PrecioSugerido);
		
		Double PorcFlete =  jsonObjectParams.get("precioobj").getAsDouble() > 0 ? (CostoFlete * 100) / jsonObjectParams.get("precioobj").getAsDouble() : 0.0;
		object.addProperty("PorcFlete", PorcFlete);
		//System.out.println(object.toString());
		return object.toString();
	}
	
}
