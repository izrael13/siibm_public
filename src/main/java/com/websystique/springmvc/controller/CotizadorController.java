package com.websystique.springmvc.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.tarjetas.Catalogo_cajas_sap_vw;
import com.websystique.springmvc.model.tarjetas.Catalogo_direcciones_sap_vw;
import com.websystique.springmvc.model.tarjetas.Catalogo_especialidades_sap_vw;
import com.websystique.springmvc.model.tarjetas.Catalogo_resistencias_sap_vw;
import com.websystique.springmvc.model.tarjetas.Catalogo_vendedores_sap_vw;
import com.websystique.springmvc.model.tarjetas.Comision_comisionista_sap_vw;
import com.websystique.springmvc.model.tarjetas.Comision_directo_sap_vw;
import com.websystique.springmvc.model.tarjetas.Especialidades_cotizacion;
import com.websystique.springmvc.model.tarjetas.Vendedores_especiales_comision_sap_vw;
import com.websystique.springmvc.model.tarjetas.cotizador.Cotizador;
import com.websystique.springmvc.model.tarjetas.cotizador.CotizadorDataBean;
import com.websystique.springmvc.model.tarjetas.cotizador.Cotizador_detallesValidator;
import com.websystique.springmvc.service.UserService;
import com.websystique.springmvc.service.tarjetas.Catalogo_bolsas_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Catalogo_cajas_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Catalogo_clientes_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Catalogo_coloresService;
import com.websystique.springmvc.service.tarjetas.Catalogo_direcciones_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Catalogo_especialidades_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Catalogo_resistencias_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Catalogo_sellosService;
import com.websystique.springmvc.service.tarjetas.Catalogo_vendedores_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Comision_comisionista_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Comision_directo_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Especialidades_cotizacionService;
import com.websystique.springmvc.service.tarjetas.Vendedores_especiales_comision_sap_vwService;
import com.websystique.springmvc.service.tarjetas.cotizador.CotizadorService;
import com.websystique.springmvc.service.tarjetas.cotizador.Cotizador_detallesService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
@RequestMapping("/ventas/tarjetas")
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
	@Autowired
	Especialidades_cotizacionService ecs;
	@Autowired
	Catalogo_bolsas_sap_vwService cbs;
	@Autowired
	Catalogo_coloresService ccos;
	
	Integer idN = 0;
	Integer idND = 0;
	DecimalFormat decimal2 = new DecimalFormat("###########0.##");
	DecimalFormat decimal4 = new DecimalFormat("###########0.####");
	////////////////////////////////////***COTIZADOR***////////////////////////////
	@RequestMapping(value = {"/cotizador/cotizadorabc" }, method = RequestMethod.GET)
	public String cotizadotget(ModelMap model, @RequestParam("id") String id, @RequestParam("iddet") String iddet) {
		try {
				List<Catalogo_especialidades_sap_vw> ListaEsp = ces.ListaEsp();
				User user = us.findBySSO(AppController.getPrincipal());
				model.addAttribute("bolsas", cbs.ListaBolsas());
				model.addAttribute("clientes", ccavs.ListaCtes(user.getCvevendedor_sap()));
				model.addAttribute("listacajas", ccss.ListaCajas());
				model.addAttribute("listaresisbarca", crss.ListaResis());
				model.addAttribute("listaresiscte", css.ListaSellos());
				model.addAttribute("especialidades", ListaEsp);
				model.addAttribute("colores", ccos.ListaColores());
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
					cdb.setCotizador(cs.BuscarxId(Integer.valueOf(id),user.getId()));
					if(Integer.valueOf(iddet) > 0)
					{	
						cdb.setCotizador_detalles(cds.BuscarxId(Integer.valueOf(id),Integer.valueOf(iddet),user.getId()));
						
						List<Especialidades_cotizacion> ListaEspDet = new ArrayList<Especialidades_cotizacion>();
						for(int i = 0; i < ListaEsp.size(); i++)
						{
							Especialidades_cotizacion  o = new Especialidades_cotizacion();
							o = ecs.EspDet(Integer.valueOf(id),Integer.valueOf(iddet), ListaEsp.get(i).getCode());
							ListaEspDet.add(o);
						}
						
						cdb.getCotizador_detalles().setEspecialidades_cotizacion(ListaEspDet);
					}
					
					//System.out.println(cdb.getCotizador_detalles().toString());
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
	
	@RequestMapping(value = {"/cotizador/cotizadorabc" }, method = RequestMethod.POST)
	public String cotizadotpost(@Valid @ModelAttribute("cotizadordatabean") CotizadorDataBean cotizadorDataBean, BindingResult result, ModelMap model) {
		try {
			String msj = "";
			User user = us.findBySSO(AppController.getPrincipal());
			model.addAttribute("clientes", ccavs.ListaCtes(user.getCvevendedor_sap()));
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			
			model.addAttribute("cotizadordatabean", cotizadorDataBean);
			model.addAttribute("direcciones", cdsv.ListaDirCardCode(cotizadorDataBean.getCotizador().getCardcode()));
			model.addAttribute("direccionSelect", cdsv.ListaDirCardCodeNumLine(cotizadorDataBean.getCotizador().getCardcode(),cotizadorDataBean.getCotizador().getLinenum_dir_entrega()));
			model.addAttribute("bolsas", cbs.ListaBolsas());
			model.addAttribute("listacajas", ccss.ListaCajas());
			model.addAttribute("listaresisbarca", crss.ListaResis());
			model.addAttribute("listaresiscte", css.ListaSellos());
			model.addAttribute("especialidades", ces.ListaEsp());
			model.addAttribute("colores", ccos.ListaColores());
			cotizadorDataBean.getCotizador().setIdtiporequerimiento(0);
			
			java.util.Date date = new java.util.Date();
						
			cdvalidator.validate(cotizadorDataBean.getCotizador_detalles(), result);
			
			/*System.out.println(cotizadorDataBean.getCotizador_detalles().toString());
			for (ObjectError error : result.getAllErrors()) {
			       String fieldErrors [] = error.getCodes();
			       System.out.println(fieldErrors[0]);
			   }*/
			  
			if (result.hasErrors() )
			{
				return "/tarjetas/cotizador/cotizador";
			}
			
			cotizadorDataBean.getCotizador().setFecha_update(date);
			cotizadorDataBean.getCotizador().setUsuario_update(user.getId());
			cotizadorDataBean.getCotizador_detalles().setFecha_update(date);
			cotizadorDataBean.getCotizador_detalles().setUsuario_update(user.getId());
			idN = 0;
			idND = 0;
			if(cotizadorDataBean.getCotizador().getId() == 0)
			{
				cotizadorDataBean.getCotizador().setFecha_insert(date);			
				cotizadorDataBean.getCotizador().setUsuario_insert(user.getId());
				idN = cs.Guardar(cotizadorDataBean.getCotizador());
				msj= "COTIZACIÓN: "+idN+" GRABADA SATISFACTORIAMENTE";
			}
			else
			{
				idN = cotizadorDataBean.getCotizador().getId();
				cs.Actualizar(cotizadorDataBean.getCotizador());
				msj= "COTIZACIÓN: "+idN+" ACTUALIZADA SATISFACTORIAMENTE";
			}
			
			if(cotizadorDataBean.getCotizador_detalles().getIddetalle() == 0)
			{				
				cotizadorDataBean.getCotizador_detalles().setIdcotizacion(idN);
				cotizadorDataBean.getCotizador_detalles().setIddetalle(cds.BuscarxCotId(idN).size() + 1);
				cotizadorDataBean.getCotizador_detalles().setFecha_insert(date);			
				cotizadorDataBean.getCotizador_detalles().setUsuario_insert(user.getId());
				
				idND = cds.Guardar(cotizadorDataBean.getCotizador_detalles());
				
				msj = msj + " Y DETALLE: "+idND+" GRABADO SATISFACTORIAMENTE.";
				
			}
			else
			{
				idND = cotizadorDataBean.getCotizador_detalles().getIddetalle();
				cotizadorDataBean.getCotizador_detalles().setIdcotizacion(idN);
				cds.Actualizar(cotizadorDataBean.getCotizador_detalles());
				msj = msj + " Y DETALLE: "+idND+" ACTUALIZADO SATISFACTORIAMENTE.";
			}
			
			List<Especialidades_cotizacion> ListaEsp = cotizadorDataBean.getCotizador_detalles().getEspecialidades_cotizacion();//NUEVOS
			List<Especialidades_cotizacion> ListaEspBorrar = ecs.ListaEspDet(idN, idND);//ANTERIORES-PARA BORRAR
			if(ListaEspBorrar.size() > 0)
				ecs.Borrar(ListaEspBorrar);
				
			if(ListaEsp.size() > 0)
			{
				for(int i = 0; i < ListaEsp.size(); i++)
				{					
					if(ListaEsp.get(i).getIdespecialidad() != null)
					{
						Especialidades_cotizacion obj = new Especialidades_cotizacion();
						obj.setCount(null);
						obj.setAjuste(ListaEsp.get(i).getAjuste());
						obj.setCosto(ListaEsp.get(i).getCosto());
						obj.setEsquema(ListaEsp.get(i).getEsquema());
						obj.setIdcotizacion(idN);
						obj.setIddetalle(idND);
						obj.setIdespecialidad(ListaEsp.get(i).getIdespecialidad());
						ecs.Guardar(obj);
					}
				}
			}
				
			logger.info(AppController.getPrincipal() + " - cotizadotpost.");
			model.addAttribute("mensajes", msj);
		}
		catch(Exception e)
		{
			model.addAttribute("mensajes", e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage());
		}
			return "/tarjetas/cotizador/cotizador";
	}
	
	@RequestMapping(value = {"/cotizador/buscardirecciones"}, method = RequestMethod.GET)
	public @ResponseBody String buscardirecciones(HttpServletRequest req, HttpServletResponse res)
	   throws Exception {
		String cardcode = req.getParameter("cardcode");
		List<Catalogo_direcciones_sap_vw> ListaDir = cdsv.ListaDirCardCode(cardcode);
		Gson g=new Gson();
		return g.toJson(ListaDir);
	
	}
	
	@RequestMapping(value = {"/cotizador/buscarinfodir"}, method = RequestMethod.GET)
	public @ResponseBody String buscarinfodir(HttpServletRequest req, HttpServletResponse res)
	   throws Exception {
		String cardcode = req.getParameter("cardcode");
		String linenum = req.getParameter("linenum");
		
		List<Catalogo_direcciones_sap_vw> ListaDir = cdsv.ListaDirCardCodeNumLine(cardcode, Integer.parseInt(linenum));
		Gson g=new Gson();
		return g.toJson(ListaDir);
	
	}
	
	@RequestMapping(value = {"/cotizador/cotizadorbusqueda" }, method = RequestMethod.GET)
	public String cotizadorbusqueda(ModelMap model,@RequestParam("id") String id,@RequestParam("cardcode") String cardcode) {
		
		User user = us.findBySSO(AppController.getPrincipal());
		model.addAttribute("lista",cs.ListaBusquedaxIdCardCode(Integer.valueOf(id), cardcode, user.getId()));
		model.addAttribute("listaDet",cs.ListaBusquedaxIdCardCodeDet(Integer.valueOf(id), cardcode, user.getId(), 0, false,false));
		
		logger.info(AppController.getPrincipal() + " - cotizadorbusqueda.");
		
		return "/tarjetas/cotizador/cotizador_busqueda";
	}
	
	@RequestMapping(value = {"/cotizador/buscarinforesistenciabarca"}, method = RequestMethod.GET)
	public @ResponseBody String buscarinforesistenciabarca(HttpServletRequest req, HttpServletResponse res)
	   throws Exception {
		String id = req.getParameter("id");
		
		Catalogo_resistencias_sap_vw crs = new Catalogo_resistencias_sap_vw();
		crs = crss.BuscarxId(Integer.valueOf(id));
		Gson g=new Gson();
		
		return g.toJson(crs);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = {"/cotizador/calculardatos"}, method = RequestMethod.GET)
	public @ResponseBody String calculardatos(HttpServletRequest req, HttpServletResponse res) throws Exception
	{
		String mystring = req.getParameter("mystring");
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonTree = jsonParser.parse(mystring);
		JsonObject jsonObjectParams = jsonTree.getAsJsonObject();
		JsonObject object = new JsonObject();//Objeto JSon
		Catalogo_cajas_sap_vw objCaja = ccss.BuscarxId(jsonObjectParams.get("idcaja").getAsInt());//Datos de la caja seleccionada.
		if(objCaja != null)
		{
			String[] AnchoStr = String.valueOf(jsonObjectParams.get("ancho").getAsDouble()).split("\\.");//Split del Ancho para obtener los decimales.
			List<Integer> vals = Arrays.asList(0,2,4,6,8);//Lista de valores enteros pares.
			User user = us.findBySSO(AppController.getPrincipal());
			Catalogo_vendedores_sap_vw Vendedor = cvss.BuscarXid(user.getCvevendedor_sap());
			
			Double AnchoVar = 0.0;
			Double LargoVar = 0.0;
			
			if(objCaja != null) {
				AnchoVar = ((objCaja.getMaa() * jsonObjectParams.get("ancho").getAsDouble()) + (objCaja.getMaf() * jsonObjectParams.get("fondo").getAsDouble()) + (objCaja.getTr() * ((jsonObjectParams.get("espsup").getAsDouble() + jsonObjectParams.get("espinf").getAsDouble()) / 2)) + (vals.contains(Integer.valueOf(AnchoStr[1])) ? objCaja.getDesami() : objCaja.getDesam()));
				object.addProperty("AnchoVar", decimal2.format(AnchoVar));
				LargoVar = (objCaja.getMll() * jsonObjectParams.get("largo").getAsDouble()) + (objCaja.getMla() * jsonObjectParams.get("ancho").getAsDouble() + objCaja.getMlf() * jsonObjectParams.get("fondo").getAsDouble()) + (jsonObjectParams.get("score").getAsInt() == 1 ? objCaja.getDeslm() : objCaja.getDeslmi());
				object.addProperty("LargoVar", decimal2.format(LargoVar));
			}
	
			Double AreaUni = (LargoVar * AnchoVar) / 10000;
			object.addProperty("AreaUni", decimal4.format(AreaUni));
			
			Double PesoTeorico = AreaUni * jsonObjectParams.get("pesoresis").getAsDouble();
			object.addProperty("PesoTeorico", decimal4.format(PesoTeorico));
			
			Double PrecioNeto = jsonObjectParams.get("preciom2resis").getAsDouble() * AreaUni;
			object.addProperty("PrecioNeto", decimal2.format(PrecioNeto));
			
			Double M2 = jsonObjectParams.get("cantpedmes").getAsDouble() * AreaUni;
			object.addProperty("M2", decimal4.format(M2));
			
			Double PesoPza = AreaUni * jsonObjectParams.get("pesoresis").getAsDouble();
			object.addProperty("PesoPza", decimal4.format(PesoPza));
			
			Double KG = PesoPza * jsonObjectParams.get("cantpedmes").getAsDouble() ;
			object.addProperty("KG", decimal2.format(KG));
			
			String MedLamina = String.valueOf(LargoVar) + " x " + AnchoVar;
			object.addProperty("MedLamina", MedLamina);
			
			Double ComisionDirecto = PrecioNeto > 0 ? (1 - (jsonObjectParams.get("precioobj").getAsDouble() / PrecioNeto)) * 100 : 0.0;
			object.addProperty("ComisionDirecto", decimal2.format(ComisionDirecto));
			
			Double CostoPapel = jsonObjectParams.get("costopapelresis").getAsDouble() * AreaUni * jsonObjectParams.get("pzasxjgo").getAsInt();
			object.addProperty("CostoPapel", decimal2.format(CostoPapel));
			
			Double CostoFlete = 0.0;
			try {
				CostoFlete = (jsonObjectParams.get("totalflete").getAsDouble() / (4500 /(PesoPza * jsonObjectParams.get("pzasxjgo").getAsDouble()))) * 1000;
			}catch(Exception e) {CostoFlete = 0.0;}
			object.addProperty("CostoFlete", decimal2.format(CostoFlete));
			
			////////CALCULOS ESPECIALIDADES
			JSONArray arr = new JSONArray();
			String ids = jsonObjectParams.get("idsesp").getAsString();
			String costoscap = jsonObjectParams.get("costoscapturados").getAsString();
			String ajustescap = jsonObjectParams.get("ajustes").getAsString();
			String esquemascap = jsonObjectParams.get("esquemas").getAsString();
			
			Double TotCostoEsp = 0.0;
			if(ids.length() > 1)
			{
				String[] idsarr = ids.split("\\|");
				String[] costoscaparr = costoscap.split("\\|");
				String[] ajustescaparr = ajustescap.split("\\|");
				String[] esquemascaparr = esquemascap.split("\\|");
				
				for(int i = 0; i < idsarr.length; i++)
				{
					JsonObject objEsp = new JsonObject();
					//Catalogo_especialidades_sap_vw Esp = ces.BuscaxId(Integer.valueOf(idsarr[i]));
					String Costo = calcular_especialidades((ajustescaparr[i].trim().length() > 0 ? Double.valueOf(ajustescaparr[i].trim()) : 0.0 ),(esquemascaparr[i].trim().length() > 0 ? Integer.valueOf(esquemascaparr[i].trim()) : 0 ),AreaUni,LargoVar,AnchoVar,jsonObjectParams.get("pzasxtar").getAsInt(),
															jsonObjectParams.get("fondo").getAsDouble(),objCaja.getDesami(),costoscaparr[i]);
					objEsp.addProperty("id", idsarr[i]);
					objEsp.addProperty("costo", Costo);
					arr.add(objEsp);
					TotCostoEsp = TotCostoEsp + Double.valueOf(Costo);
				}
			}
			object.addProperty("Esp", arr.toString());
			object.addProperty("TotCostoEsp", TotCostoEsp); 
			
			Double CPSC = (jsonObjectParams.get("precioobj").getAsDouble() - TotCostoEsp - CostoFlete) > 0 ? (CostoPapel / (jsonObjectParams.get("precioobj").getAsDouble() - TotCostoEsp - CostoFlete)) * 100 : 0.0;			
			object.addProperty("CPSC", decimal2.format(CPSC));
			
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
						Supplier<Stream<Comision_directo_sap_vw>> stream = () ->  ListaD.stream().filter(a -> a.getCode() <= ComisionDirecto && a.getName() >= ComisionDirecto);
						if(stream.get().count() > 0)
							PorcComision = stream.get().findFirst().get().getU_comision();
					}
					else
					{
						if(PorcComision == 0.0 && Vendedor.getClasevendedor().equals("Comisionista"))
						{
							List<Comision_comisionista_sap_vw> ListaC = ccs.ListaCCSV();
							Supplier<Stream<Comision_comisionista_sap_vw>> streamc = () -> ListaC.stream().filter(a -> a.getName() <= CPSC && a.getCode() <= CPSC);
							if(streamc.get().count() > 0)
								PorcComision = streamc.get().findFirst().get().getU_comision(); 
						}
					}
				}
			}		
			object.addProperty("PorcComision", decimal2.format(PorcComision));
			
			Double ComXmillar = (PorcComision  / 100) * jsonObjectParams.get("precioobj").getAsDouble();
			object.addProperty("ComXmillar", decimal2.format(ComXmillar));
			
			Double CPCC  = (jsonObjectParams.get("precioobj").getAsDouble() - TotCostoEsp - CostoFlete - ComXmillar) > 0 ? (CostoPapel / (jsonObjectParams.get("precioobj").getAsDouble() - TotCostoEsp - CostoFlete - ComXmillar)) * 100 : 0.0;
			object.addProperty("CPCC", decimal2.format(CPCC));
			
			Double LimVendedor = PrecioNeto * (jsonObjectParams.get("descven").getAsDouble() / 100);
			
			Double PrecioSugerido = PrecioNeto - LimVendedor;
			object.addProperty("PrecioSugerido", decimal2.format(PrecioSugerido));
			
			Double PorcFlete =  jsonObjectParams.get("precioobj").getAsDouble() > 0 ? (CostoFlete * 100) / jsonObjectParams.get("precioobj").getAsDouble() : 0.0;
			object.addProperty("PorcFlete", decimal2.format(PorcFlete));
			
			object.addProperty("AreaTotal",decimal4.format(AreaUni * jsonObjectParams.get("pzasxjgo").getAsInt()));
			object.addProperty("PesoTotal",decimal4.format(AreaUni * PesoPza));
			object.addProperty("PK_Teorico",decimal4.format( (jsonObjectParams.get("cantpedmes").getAsDouble() / 1000) * (jsonObjectParams.get("precioobj").getAsDouble() / KG)));
			
		}//Fin Si hay Caja seleccionada
		
		return object.toString();
	}
	
	public String calcular_especialidades(Double ajuste,Integer esquema,Double area_uni, Double largopliego, Double anchopliego,Integer pzasxtar,Double fondo,Double desami, String costoscap)
	   throws Exception {
		
		Double costo = 0.0;
		if(esquema == 0) //Area
			costo = (area_uni * ajuste) * 1000.00;
		else
		{
			if(esquema == 1)//Largo pliego
				costo = ((largopliego / 100.00) * ajuste);
			else
			{
				if(esquema == 2)//Ancho pliego
					costo = ((anchopliego / 100.00) * ajuste);
				else
				{
					if(esquema == 7)//Desbarbe
						costo =  ajuste;
					else
					{
						if(esquema == 6  || esquema == 5)//Tarimas especiales y viajeras  y tarima standard
						{
							costo = (ajuste / pzasxtar) * 1000.00;
						}
						else
						{
							if(esquema == 4)//pegado-grapado
								costo = (fondo + desami) * ajuste;
							else
							{
								if(esquema == 8)//Captura
									return costoscap;
							}
						}
					}
					
				}
			}
		}		
				
		return decimal2.format(costo);
	}
	
	@RequestMapping(value = {"/cotizador/enviaragerenteventasprog" }, method = RequestMethod.POST)
	public @ResponseBody String enviaragerenteventas(ModelMap model, @RequestParam("idcot") String idcot)
	{
		try
		{
			User user = us.findBySSO(AppController.getPrincipal());
			Cotizador c = cs.BuscarxId(Integer.valueOf(idcot), user.getId());
			java.util.Date date = new java.util.Date();
			
			if((c.getUsuario_envia_ventas() == null && c.getFecha_envia_ventas() == null) || (c.getUsuario_rech_ventas() != null && c.getFecha_rech_ventas() != null))
			{
				c.setUsuario_envia_ventas(user.getId());
				c.setFecha_envia_ventas(date);
				c.setUsuario_rech_ventas(null);
				c.setFecha_rech_ventas(null);
			}
			
			if((c.getUsuario_envia_a_prog() == null && c.getFecha_envia_a_prog() == null) || (c.getUsuario_rech_prog() != null && c.getFecha_rech_prog() != null))
			{
				c.setUsuario_envia_a_prog(user.getId());
				c.setFecha_envia_a_prog(date);
				c.setUsuario_rech_prog(null);
				c.setFecha_rech_prog(null);
			}
			
			cs.Actualizar(c);
			
			return "OK";
		}
		catch(Exception e)
		{
			return e.getMessage()+ " - "+e.getStackTrace();
		}
	}		
	@RequestMapping(value = {"/cotizador/cancelarcotizacion" }, method = RequestMethod.POST)
	public @ResponseBody String cancelarcotizacion(ModelMap model, @RequestParam("idcot") String idcot) {
		try
		{
			User user = us.findBySSO(AppController.getPrincipal());
			Cotizador c = cs.BuscarxId(Integer.valueOf(idcot), user.getId()); 
			java.util.Date date = new java.util.Date();
			c.setUsuario_cancel(user.getId());
			c.setFecha_cancel(date);
			cs.Actualizar(c);
			return "OK";
		}
		catch(Exception e)
		{
			return e.getMessage()+ " - "+e.getStackTrace();
		}
		
	}
		
	////////////////////////////////////AUTORIZACIÓNES***//////////////////////////
	@RequestMapping(value = {"/cotizador/autorizacion_cotizacion_vtas" }, method = RequestMethod.GET)
	public String autventas(ModelMap model) {
		
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		model.addAttribute("listaDet",cs.ListaBusquedaxIdCardCodeDet(0, "0", 0, 1, true,false));
		logger.info(AppController.getPrincipal() + " - autorizacion_cotizacion_vtas.");
		
		return "/tarjetas/cotizador/autorizacion_cotizacion_vtas";
	}
	
	@RequestMapping(value = {"/cotizador/autorizacion_cotizacion_prog" }, method = RequestMethod.GET)
	public String autorizacion_cotizacion_prog(ModelMap model) {
		
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		model.addAttribute("listaDet",cs.ListaCotizacionesJasper(0, true));
		logger.info(AppController.getPrincipal() + " - autorizacion_cotizacion_prog.");
		
		return "/tarjetas/cotizador/autorizacion_cotizacion_prog";
	}
	
	@RequestMapping(value = {"/cotizador/autorizacion_cotizacion_vtas_desicion" }, method = RequestMethod.POST)
	public @ResponseBody String autorizacion_cotizacion_vtas_desicion(ModelMap model, @RequestParam("idcot") String idcot, @RequestParam("coment") String coment, @RequestParam("ban") String ban) {
		try
		{
			User user = us.findBySSO(AppController.getPrincipal());
			Cotizador c = cs.BuscarxId(Integer.valueOf(idcot)); 
			java.util.Date date = new java.util.Date();
			if(Integer.valueOf(ban) == 1)
			{
				c.setUsuario_aut_ventas(user.getId());
				c.setFecha_aut_ventas(date);
				c.setObservaciones_ventas(coment);
			}
			else
			{
				c.setUsuario_rech_ventas(user.getId());
				c.setFecha_rech_ventas(date);
				c.setObservaciones_ventas(coment);
			}
			cs.Actualizar(c);
			return "OK";
		}
		catch(Exception e)
		{
			return e.getMessage()+ " - "+e.getStackTrace();
		}
		
	}
	
	@RequestMapping(value = {"/cotizador/autorizacion_cotizacion_prog_desicion" }, method = RequestMethod.POST)
	public @ResponseBody String autorizacion_cotizacion_prog_desicion(ModelMap model, @RequestParam("idcot") String idcot, @RequestParam("coment") String coment, @RequestParam("ban") String ban) {
		try
		{
			User user = us.findBySSO(AppController.getPrincipal());
			Cotizador c = cs.BuscarxId(Integer.valueOf(idcot)); 
			java.util.Date date = new java.util.Date();
			if(Integer.valueOf(ban) == 1)
			{
				c.setUsuario_aut_prog(user.getId());
				c.setFecha_aut_prog(date);
				c.setObservaciones_prog(coment);
			}
			else
			{
				c.setUsuario_rech_prog(user.getId());
				c.setFecha_rech_prog(date);
				c.setObservaciones_prog(coment);
				c.setUsuario_aut_ventas(null);
				c.setFecha_aut_ventas(null);
				c.setUsuario_envia_ventas(null);
				c.setFecha_envia_ventas(null);
			}
			cs.Actualizar(c);
			return "OK";
		}
		catch(Exception e)
		{
			return e.getMessage()+ " - "+e.getStackTrace();
		}
		
	}
	////////////////////////////////////IMPRIMIR JASPER/////////////////////////////
	@RequestMapping(value = "/cotizador/imprimircotizador", method = RequestMethod.GET)
    @ResponseBody
    public void getRpt1(HttpServletResponse response,HttpServletRequest request,ModelMap model,@RequestParam("id") String id) throws JRException, IOException {

	InputStream jasperStream = this.getClass().getResourceAsStream("/jasperreports/cotizador/Cotizador.jasper");
	Map<String,Object> params = new HashMap<>();
	
	JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
	JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(cs.ListaCotizacionesJasper(Integer.valueOf(id),false));
	params.put("dataSource", dataSource);
	params.put("Imagen",request.getServletContext().getRealPath("/"));
	
	JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
	
	response.setContentType("application/pdf");
	response.setHeader("Content-disposition", "inline");
	
	    final OutputStream outStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	} 
		
	////////////////////////////////////***REQUERIMIENTOS***////////////////////////////
	@RequestMapping(value = {"/requerimientos/requerimientoabc" }, method = RequestMethod.GET)
	public String requerimientoabcget(ModelMap model) {
		
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		
		logger.info(AppController.getPrincipal() + " - requerimientoabcget.");
		
		return "/tarjetas/requerimientos/requerimientoabc";
	}
	
}

