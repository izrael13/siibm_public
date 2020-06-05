package com.websystique.springmvc.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.json.JSONObject;
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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.tarjetas.Catalogo_cajas_sap_vw;
import com.websystique.springmvc.model.tarjetas.Catalogo_direcciones_sap_vw;
import com.websystique.springmvc.model.tarjetas.Catalogo_especialidades_sap_vw;
import com.websystique.springmvc.model.tarjetas.Catalogo_resistencias_sap_vw;
import com.websystique.springmvc.model.tarjetas.Catalogo_vendedores_sap_vw;
import com.websystique.springmvc.model.tarjetas.Codigo_barras_cotizador;
import com.websystique.springmvc.model.tarjetas.Comision_comisionista_sap_vw;
import com.websystique.springmvc.model.tarjetas.Comision_directo_sap_vw;
import com.websystique.springmvc.model.tarjetas.Especialidades_cotizacion;
import com.websystique.springmvc.model.tarjetas.Vendedores_especiales_comision_sap_vw;
import com.websystique.springmvc.model.tarjetas.cotizador.Cotizador;
import com.websystique.springmvc.model.tarjetas.cotizador.CotizadorDataBean;
import com.websystique.springmvc.model.tarjetas.cotizador.CotizadorValidator;
import com.websystique.springmvc.model.tarjetas.cotizador.Cotizador_detalles;
import com.websystique.springmvc.model.tarjetas.cotizador.Cotizador_detallesValidator;
import com.websystique.springmvc.model.tarjetas.fabricacion.Tarjeta_fabricacion;
import com.websystique.springmvc.service.UserService;
import com.websystique.springmvc.service.tarjetas.Catalogo_bolsas_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Catalogo_cajas_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Catalogo_clientes_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Catalogo_coloresService;
import com.websystique.springmvc.service.tarjetas.Catalogo_direcciones_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Catalogo_especialidades_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Catalogo_herramentalesService;
import com.websystique.springmvc.service.tarjetas.Catalogo_resistencias_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Catalogo_sellosService;
import com.websystique.springmvc.service.tarjetas.Catalogo_vendedores_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Codigo_barras_cotizadorService;
import com.websystique.springmvc.service.tarjetas.Comision_comisionista_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Comision_directo_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Especialidades_cotizacionService;
import com.websystique.springmvc.service.tarjetas.Vendedores_especiales_comision_sap_vwService;
import com.websystique.springmvc.service.tarjetas.commons.CotizadorTarjetasService;
import com.websystique.springmvc.service.tarjetas.cotizador.CotizadorService;
import com.websystique.springmvc.service.tarjetas.cotizador.Cotizador_detallesService;
import com.websystique.springmvc.service.tarjetas.fabricacion.Tarjeta_fabricacionService;
import com.websystique.springmvc.service.tarjetas.fabricacion.Tarjetas_fabricacion_imagenesService;
import com.websystique.springmvc.utilities.SendMailGmail;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
@RequestMapping("/cotizador")
public class CotizadorController {
	
	private Logger logger = Logger.getLogger(CotizadorController.class);
	
	@Autowired UserService us;
	@Autowired Catalogo_clientes_sap_vwService ccavs;
	@Autowired Catalogo_direcciones_sap_vwService cdsv;
	@Autowired CotizadorService cs;
	@Autowired Catalogo_cajas_sap_vwService ccss;
	@Autowired Catalogo_resistencias_sap_vwService crss;
	@Autowired Catalogo_sellosService css;
	@Autowired Catalogo_especialidades_sap_vwService ces;
	@Autowired Cotizador_detallesService cds;
	@Autowired Cotizador_detallesValidator cdvalidator;
	@Autowired CotizadorValidator cvalidator;
	@Autowired Catalogo_vendedores_sap_vwService cvss; 
	@Autowired Vendedores_especiales_comision_sap_vwService vecs;
	@Autowired Comision_directo_sap_vwService cdss;
	@Autowired Comision_comisionista_sap_vwService ccs;
	@Autowired Especialidades_cotizacionService ecs;
	@Autowired Catalogo_bolsas_sap_vwService cbs;
	@Autowired Catalogo_coloresService ccos;
	@Autowired Codigo_barras_cotizadorService cbsc;
	@Autowired Tarjeta_fabricacionService tfs;
	@Autowired Catalogo_herramentalesService chs;
	@Autowired Tarjetas_fabricacion_imagenesService tfis;
	@Autowired CotizadorTarjetasService ctsc;
	
	Integer idN = 0,idND = 0;
	
	////////////////////////////////////***COTIZADOR***////////////////////////////
	@RequestMapping(value = {"/vendedor/cotizadorabc" }, method = RequestMethod.GET)
	public String cotizadotget(ModelMap model, @RequestParam(value = "id", defaultValue = "0", required = false) String id, @RequestParam(value = "iddet", defaultValue = "0", required = false) String iddet) throws UnsupportedEncodingException {
		try {				
				List<Catalogo_especialidades_sap_vw> ListaEsp = ces.ListaEsp(1);
				List<Catalogo_resistencias_sap_vw> ListaResis = new ArrayList<Catalogo_resistencias_sap_vw>();
				User user = us.findBySSO(AppController.getPrincipal());
				model.addAttribute("bolsas", cbs.ListaBolsas());
				model.addAttribute("clientes", ccavs.ListaCtes(user.getCvevendedor_sap()));
				model.addAttribute("listacajas", ccss.ListaCajas());
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
					Cotizador cot = cs.BuscarxId(Integer.valueOf(id),user.getId());
					cdb.setCotizador(cot == null ? (new Cotizador()): cot);
					if(cot != null && Integer.valueOf(iddet) > 0)
					{	
						Cotizador_detalles cotDet = cds.BuscarxId(Integer.valueOf(id),Integer.valueOf(iddet),user.getId());
						cdb.setCotizador_detalles(cotDet == null ? (new Cotizador_detalles()): cotDet );
						
						ListaResis = ListaResis(cotDet.getIdcaja_sap());
						
						List<Especialidades_cotizacion> ListaEspDet = new ArrayList<Especialidades_cotizacion>();
						for(int i = 0; i < ListaEsp.size(); i++)
						{
							Especialidades_cotizacion  o = new Especialidades_cotizacion();
							o = ecs.EspDet(Integer.valueOf(id),Integer.valueOf(iddet), ListaEsp.get(i).getCode());
							ListaEspDet.add(o);
						}
						
						cdb.getCotizador_detalles().setEspecialidades_cotizacion(ListaEspDet);
						cdb.getCotizador_detalles().setCodigo_barra_cotizador(cbsc.BuscarXCotDet(Integer.valueOf(id), Integer.valueOf(iddet)));
					}
					
					model.addAttribute("listaresisbarca", ListaResis);
					model.addAttribute("cotizadordatabean", cdb);
					model.addAttribute("direcciones", cdsv.ListaDirCardCode(cdb.getCotizador().getCardcode()));
					model.addAttribute("direccionSelect", cdsv.ListaDirCardCodeNumLine(cdb.getCotizador().getCardcode(),cdb.getCotizador().getLinenum_dir_entrega()));
				}	
					
				logger.info(AppController.getPrincipal() + " - cotizadorabc.");
		}
		catch(Exception e)
		{
			model.addAttribute("error",e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage());
			logger.error(AppController.getPrincipal() + " - cotizadorabc. - " + e);
		}
		return "/tarjetas/cotizador/cotizador";
	}
	
	@RequestMapping(value = {"/vendedor/cotizadorabc" }, method = RequestMethod.POST)
	public String cotizadotpost(@Valid @ModelAttribute("cotizadordatabean") CotizadorDataBean cotizadorDataBean, BindingResult result, ModelMap model) {
		try 
		{
			String msj = "";
			logger.info(AppController.getPrincipal() + " - cotizadotpost.");
			User user = us.findBySSO(AppController.getPrincipal());
			model.addAttribute("clientes", ccavs.ListaCtes(user.getCvevendedor_sap()));
			model.addAttribute("loggedinuser", AppController.getPrincipal());			
			model.addAttribute("direcciones", cdsv.ListaDirCardCode(cotizadorDataBean.getCotizador().getCardcode()));
			model.addAttribute("direccionSelect", cdsv.ListaDirCardCodeNumLine(cotizadorDataBean.getCotizador().getCardcode(),cotizadorDataBean.getCotizador().getLinenum_dir_entrega()));
			model.addAttribute("bolsas", cbs.ListaBolsas());
			model.addAttribute("listacajas", ccss.ListaCajas());
			model.addAttribute("listaresisbarca", ListaResis(cotizadorDataBean.getCotizador_detalles().getIdcaja_sap()));
			model.addAttribute("listaresiscte", css.ListaSellos());
			model.addAttribute("especialidades", ces.ListaEsp(1));
			model.addAttribute("colores", ccos.ListaColores());

			java.util.Date date = new java.util.Date();
			
			cotizadorDataBean.getCotizador_detalles().setBan(null);
			cotizadorDataBean.getCotizador_detalles().setIdtiporequerimiento(cotizadorDataBean.getCotizador().getIdtiporequerimiento());
			
			cvalidator.validate(cotizadorDataBean.getCotizador(), result);
			cdvalidator.validate(cotizadorDataBean.getCotizador_detalles(), result);
			/*for (ObjectError error : result.getAllErrors()) {
			       String fieldErrors [] = error.getCodes();
			       System.out.println(fieldErrors[0]);
			   } */
			if (result.hasErrors() )
				return "/tarjetas/cotizador/cotizador";
			
			cotizadorDataBean.getCotizador().setFecha_update(date);
			cotizadorDataBean.getCotizador().setUsuario_update(user.getId());
			cotizadorDataBean.getCotizador_detalles().setFecha_update(date);
			cotizadorDataBean.getCotizador_detalles().setUsuario_update(user.getId());
			idN = 0;
			idND = 0;

			if((cotizadorDataBean.getCotizador().getId() == null ? 0 : cotizadorDataBean.getCotizador().getId()) == 0)
			{
				cotizadorDataBean.getCotizador().setFecha_insert(date);
				cotizadorDataBean.getCotizador().setUsuario_insert(user.getId());
				idN = cs.Maximo("id");
				idN = (idN == null ? 1 : ++idN);
				cotizadorDataBean.getCotizador().setId(idN);
				cs.Guardar(cotizadorDataBean.getCotizador());
				//idN = cs.Guardar(cotizadorDataBean.getCotizador());
				cotizadorDataBean.getCotizador().setId(idN);
				msj= "COTIZACIÓN: "+idN+" GRABADA SATISFACTORIAMENTE";
			}
			else
			{
				idN = cotizadorDataBean.getCotizador().getId();
				cs.Actualizar(cotizadorDataBean.getCotizador());
				msj= "COTIZACIÓN: "+idN+" ACTUALIZADA SATISFACTORIAMENTE";
			}
			
			if((cotizadorDataBean.getCotizador_detalles().getIddetalle() == null ? 0 : cotizadorDataBean.getCotizador_detalles().getIddetalle()) == 0)
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
						obj.setCm(ListaEsp.get(i).getCm());
						obj.setPropiedadoitm(ListaEsp.get(i).getPropiedadoitm());
						ecs.Guardar(obj);
					}
				}
			}
			
			List<Codigo_barras_cotizador> ListaCodBarrasBorrar = cbsc.BuscarXCotDet(idN, idND);
			List<Codigo_barras_cotizador> ListaCodBarras = cotizadorDataBean.getCotizador_detalles().getCodigo_barra_cotizador();
			
			if(ListaCodBarrasBorrar.size() > 0)
				cbsc.Borrar(ListaCodBarrasBorrar);
			
			if(ListaCodBarras.size() > 0)
			{
				for(int i = 0; i < ListaCodBarras.size(); i++)
				{
					if( (ListaCodBarras.get(i).getIdcodigo() == null ? 0 : ListaCodBarras.get(i).getIdcodigo().trim().length()) > 0 )
					{
						Codigo_barras_cotizador objcb = new Codigo_barras_cotizador();
						objcb.setIdcodigo(ListaCodBarras.get(i).getIdcodigo());
						objcb.setObservaciones(ListaCodBarras.get(i).getObservaciones());
						objcb.setIdcotizacion(idN);
						objcb.setIddetalle(idND);
						cbsc.Guardar(objcb);
					}
				}
				
			} 

			model.addAttribute("mensajes", msj);
		}
		catch(Exception e)
		{
			model.addAttribute("error",e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage());
			logger.error(AppController.getPrincipal() + " - cotizadorabc. - " + e);
		}
		return "/tarjetas/cotizador/cotizador";
	}
	
	@RequestMapping(value = {"/vendedor/buscardirecciones"}, method = RequestMethod.GET)
	public @ResponseBody String buscardirecciones(HttpServletRequest req, HttpServletResponse res)
	   throws Exception {
		String cardcode = req.getParameter("cardcode");
		List<Catalogo_direcciones_sap_vw> ListaDir = cdsv.ListaDirCardCode(cardcode);
		Gson g=new Gson();
		return g.toJson(ListaDir);
	
	}
	
	@RequestMapping(value = {"/vendedor/buscarinfodir"}, method = RequestMethod.GET)
	public @ResponseBody String buscarinfodir(HttpServletRequest req, HttpServletResponse res)
	   throws Exception {
		String cardcode = req.getParameter("cardcode");
		String linenum = req.getParameter("linenum");
		
		List<Catalogo_direcciones_sap_vw> ListaDir = cdsv.ListaDirCardCodeNumLine(cardcode, Integer.parseInt(linenum));
		Gson g=new Gson();
		return g.toJson(ListaDir);
	
	}
	
	@RequestMapping(value = {"/vendedor/cotizadorbusqueda" }, method = RequestMethod.GET)
	public String cotizadorbusqueda(ModelMap model,@RequestParam("id") Integer id,@RequestParam("cardcode") String cardcode) {
		try
		{
			logger.info(AppController.getPrincipal() + " - cotizadorbusqueda. ");
			List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
			List<JSONObject> ListaCot = new ArrayList<JSONObject>();
			User user = us.findBySSO(AppController.getPrincipal());
			if(id > 0)
				Params.add(new ParamsGeneral(1,"id",id,"EQ"));
			
			else
				Params.add(new ParamsGeneral(1,"cardcode",cardcode,"EQ"));
			
			Params.add(new ParamsGeneral(1,"idtiporequerimiento",3,"NE"));
			Params.add(new ParamsGeneral(3,"usuario_insert",user.getId(),"EQ"));
			
			cs.ListasCotAut(Params).forEach(a -> {
				ListaCot.add(ctsc.DataSourceJasperCot(a.getId(),1));
			});
			
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.serializeNulls().create();
			
			model.addAttribute("lista",gson.fromJson(ListaCot.toString(), Object[].class));
			
		}
		catch(Exception e)
		{
			model.addAttribute("error",e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage());
			logger.error(AppController.getPrincipal() + " - cotizadorbusqueda. - " + e);			
		}
		
		return "/tarjetas/cotizador/cotizador_busqueda";

	}
	
	@RequestMapping(value = {"/vendedor/buscarinforesistenciabarca"}, method = RequestMethod.GET)
	public @ResponseBody String buscarinforesistenciabarca(HttpServletRequest req, HttpServletResponse res)
	   throws Exception {
		try
		{
			String id = req.getParameter("id");
			
			Catalogo_resistencias_sap_vw crs = new Catalogo_resistencias_sap_vw();
			crs = crss.BuscarxId(Integer.valueOf(id));
			Gson g=new Gson();
			
			return g.toJson(crs);
		}
		catch(Exception e)
		{
			return e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage();
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = {"/vendedor/calculardatos"}, method = RequestMethod.GET)
	public @ResponseBody String calculardatos(HttpServletRequest req, HttpServletResponse res) throws Exception
	{
		DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.getDefault());
		formatSymbols.setDecimalSeparator('.');
		
		DecimalFormat decimal2 = new DecimalFormat("###########0.##", formatSymbols);
		DecimalFormat decimal4 = new DecimalFormat("###########0.####", formatSymbols);
		
		try
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
				
				String MedLamina = decimal2.format(LargoVar) + " x " + decimal2.format(AnchoVar);
				object.addProperty("MedLamina", MedLamina);
				
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
				String esquemascm = jsonObjectParams.get("cm").getAsString();
				
				Double TotCostoEsp = 0.0;
				if(ids.length() > 1)
				{
					String[] idsarr = ids.split("\\|");
					String[] costoscaparr = costoscap.split("\\|");
					String[] ajustescaparr = ajustescap.split("\\|");
					String[] esquemascaparr = esquemascap.split("\\|");
					String[] cmcaparr = esquemascm.split("\\|");
					
					for(int i = 0; i < idsarr.length; i++)
					{
						JsonObject objEsp = new JsonObject();
						//Catalogo_especialidades_sap_vw Esp = ces.BuscaxId(Integer.valueOf(idsarr[i]));
						Integer NEsquema = (esquemascaparr[i].trim().length() > 0 ? Integer.valueOf(esquemascaparr[i].trim()) : 0 );
						String Costo = calcular_especialidades((ajustescaparr[i].trim().length() > 0 ? Double.valueOf(ajustescaparr[i].trim()) : 0.0 ),NEsquema,AreaUni,LargoVar,AnchoVar,jsonObjectParams.get("pzasxtar").getAsInt(),
																jsonObjectParams.get("fondo").getAsDouble(),objCaja.getDesami(),costoscaparr[i], jsonObjectParams.get("precioobj").getAsDouble(), (cmcaparr[i].trim().length() > 0 ? Double.valueOf(cmcaparr[i].trim()) : 0.0 ), objCaja.getGrupo());
						objEsp.addProperty("id", idsarr[i]);
						objEsp.addProperty("costo", NEsquema == 8 ? Costo : decimal2.format(Double.valueOf(Costo)));
						arr.add(objEsp);
						TotCostoEsp = TotCostoEsp + Double.valueOf(decimal2.format(Double.valueOf(Costo)));
					}
				}
				object.addProperty("Esp", arr.toString());
				object.addProperty("TotCostoEsp", TotCostoEsp); 
				
				Double ComisionDirecto = PrecioNeto > 0 ? ((1 - (jsonObjectParams.get("precioobj").getAsDouble() / PrecioNeto))  * 100) : 0.0;
				object.addProperty("ComisionDirecto", decimal2.format(ComisionDirecto));
				
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
								Supplier<Stream<Comision_comisionista_sap_vw>> streamc = () -> ListaC.stream().filter(a -> a.getCode() <= CPSC && a.getName() >= CPSC);
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
				object.addProperty("PesoTotal",decimal4.format(PesoPza * jsonObjectParams.get("pzasxjgo").getAsInt()));
				object.addProperty("PK_Teorico",decimal4.format( (jsonObjectParams.get("cantpedmes").getAsDouble() / 1000) * (jsonObjectParams.get("precioobj").getAsDouble() / KG)));
				
			}//Fin Si hay Caja seleccionada
			return object.toString();
		}
		catch(Exception e)
		{
			return e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage();
		}
		
	}
	
	public String calcular_especialidades(Double ajuste,Integer esquema,Double area_uni, Double largopliego, Double anchopliego,
										  Integer pzasxtar,Double fondo,Double desami, String costoscap, Double precio_obj, Double cm, Integer grupo)
	   throws Exception {
		
		Double costo = 0.0;
		if(esquema == 0) //Area
			costo = (area_uni * ajuste) * 1000.00;
		else
		{
			if(esquema == 1)//Largo pliego
				costo = ((largopliego / 100.00) * ajuste) * 1000.00;
			else
			{
				if(esquema == 2)//Ancho pliego
					costo = ((anchopliego / 100.00) * ajuste) * 1000.00;
				else
				{
					if(esquema == 7)//Desbarbe
						costo =  ajuste;
					else
					{
						if(esquema == 6  || esquema == 5)//Tarimas especiales y viajeras  y tarima standard
						{
							
							costo = (pzasxtar == 0 ? 0.0 : (ajuste / pzasxtar) * 1000.00);
						}
						else
						{
							if(esquema == 4)//pegado-grapado
							{
								if(grupo == 3)//suajes
									costo = (cm * ajuste) * 1000.00;
								else//las demas
									costo = ((fondo + desami) * ajuste) * 1000.00;
							}
							else
							{
								if(esquema == 8)//Captura
									return costoscap;
								else
								{
									if(esquema == 9)//Doble paso
										costo = precio_obj * 0.07;
									
								}
							}
						}
					}
					
				}
			}
		}		
				
		return String.valueOf(costo);
	}
	
	@RequestMapping(value = {"/vendedor/enviaragerenteventasprog" }, method = RequestMethod.POST)
	public @ResponseBody String enviaragerenteventasprog(ModelMap model, @RequestParam("idcot") String idcot)
	{
		String msj = "";
		try
		{
			User user = us.findBySSO(AppController.getPrincipal());
			Cotizador c = cs.BuscarxId(Integer.valueOf(idcot), user.getId());
			Cotizador_detalles cd = cds.BuscarxId(Integer.valueOf(idcot), 1, user.getId());
			
			java.util.Date date = new java.util.Date();
			
			if(c.getIdtiporequerimiento() == 0)//diseño
			{
				if((c.getUsuario_envia_ventas() == null && c.getFecha_envia_ventas() == null) || (c.getUsuario_rech_ventas() != null && c.getFecha_rech_ventas() != null))
				{
					
					if(cd.getComision_directo() > cd.getDescuento_vendedor() )
					{
						c.setUsuario_envia_ventas(user.getId());
						c.setFecha_envia_ventas(date);
						c.setUsuario_rech_ventas(null);
						c.setFecha_rech_ventas(null);
					}
					else
					{
						c.setUsuario_envia_ventas(user.getId());
						c.setFecha_envia_ventas(date);
						
						c.setUsuario_aut_ventas(16);
						c.setFecha_aut_ventas(date);
						c.setObservaciones_ventas("Autorización automática por sistema.");
						
						/*///ENVÍO DE EMAIL
						SendMailGmail Email = new SendMailGmail();
						String emailMsj="Cotización: ("+c.getId()+") autorizada automáticamente por precio a través del sistema.";
						User userInsertCot = us.findById(c.getUsuario_insert());
						Email.sendMail(userInsertCot.getEmail(), emailMsj, "Cotización autorizada");
						////*/
						
						c.setUsuario_rech_ventas(null);
						c.setFecha_rech_ventas(null);
					}
				}				
				
				if((c.getUsuario_envia_a_prog() == null && c.getFecha_envia_a_prog() == null) || (c.getUsuario_rech_prog() != null && c.getFecha_rech_prog() != null))
				{
					c.setUsuario_envia_a_prog(user.getId());
					c.setFecha_envia_a_prog(date);
					c.setUsuario_rech_prog(null);
					c.setFecha_rech_prog(null);
				}
				
				c.setUsuario_rech_diseniador(null);
				c.setFecha_rech_diseniador(null);
			}
			else//muestras
			{
				c.setFecha_envia_arrmues(date);
				c.setUsuario_envia_arrmues(user.getId());
				c.setFecha_rech_arrastre(null);
				c.setUsuario_rech_arrastre(null);
				c.setObservaciones_arrastre(null);
			}
			
			cs.Actualizar(c);
			logger.info(AppController.getPrincipal() + " - enviaragerenteventasprog.");
			return "OK";
		}
		catch(Exception e)
		{
			msj = e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage();
			logger.info(AppController.getPrincipal() + " - enviaragerenteventasprog :"+ msj);
			return msj;
		}
	}

	@RequestMapping(value = {"/vendedor/cancelarcotizacion" }, method = RequestMethod.POST)
	public @ResponseBody String cancelarcotizacion(ModelMap model, @RequestParam("idcot") String idcot) {
		String msj = "";
		try
		{
			User user = us.findBySSO(AppController.getPrincipal());
			Cotizador c = cs.BuscarxId(Integer.valueOf(idcot), user.getId()); 
			java.util.Date date = new java.util.Date();
			c.setUsuario_cancel(user.getId());
			c.setFecha_cancel(date);
			cs.Actualizar(c);
			
			////ENVÍO DE EMAIL
			SendMailGmail Email = new SendMailGmail();
			String emailMsj="Cotización cancelada: ("+c.getId()+") por el usuario: "+user.getFirstName()+ " " +user.getLastName()+ " (" + user.getSsoId() + ") \r\n\n Contacto: "+user.getEmail();
			User userInsertCot = us.findById(c.getUsuario_insert());
			Email.sendMail(userInsertCot.getEmail(), emailMsj, "Cotización cancelada");
			/////
			
			logger.info(AppController.getPrincipal() + " - cancelarcotizacion.");
			return "OK";
		}
		catch(Exception e)
		{
			msj = e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage();
			logger.info(AppController.getPrincipal() + " - cancelarcotizacion :"+ msj);
			return msj;
		}
		
	}
		
	////////////////////////////////////AUTORIZACIÓNES***//////////////////////////
	@RequestMapping(value = {"/ventas/autorizacion_cotizacion_vtas" }, method = RequestMethod.GET)
	public String autventas(ModelMap model) {
		
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		model.addAttribute("listaDet",ListaCotAut(3));
		logger.info(AppController.getPrincipal() + " - autorizacion_cotizacion_vtas.");
		
		return "/tarjetas/cotizador/autorizacion_cotizacion_vtas";
	}
	
	@RequestMapping(value = {"/programacion/autorizacion_cotizacion_prog" }, method = RequestMethod.GET)
	public String autorizacion_cotizacion_prog(ModelMap model) {		
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		model.addAttribute("listaDet",ListaCotAut(1));
		logger.info(AppController.getPrincipal() + " - autorizacion_cotizacion_prog.");
		
		return "/tarjetas/cotizador/autorizacion_cotizacion_prog";
	}
	
	private Object[] ListaCotAut(Integer ban)
	{
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		if(ban == 1) {//programacion
			Params.add(new ParamsGeneral(1,"fecha_envia_a_prog","NE"));
			Params.add(new ParamsGeneral(1,"usuario_envia_a_prog","NE"));
			Params.add(new ParamsGeneral(1,"fecha_aut_prog","EQ"));
			Params.add(new ParamsGeneral(1,"usuario_aut_prog","EQ"));
			Params.add(new ParamsGeneral(1,"fecha_rech_prog","EQ"));
			Params.add(new ParamsGeneral(1,"usuario_rech_prog","EQ"));
			Params.add(new ParamsGeneral(1,"usuario_cancel","EQ"));
			Params.add(new ParamsGeneral(1,"fecha_cancel","EQ"));
			Params.add(new ParamsGeneral(1,"idtiporequerimiento",0,"EQ"));
		}
		else
		{
			if(ban == 2)//req abc
			{
				Params.add(new ParamsGeneral(1,"fecha_aut_prog","NE"));
				Params.add(new ParamsGeneral(1,"usuario_aut_prog","NE"));
				Params.add(new ParamsGeneral(1,"fecha_aut_ventas","NE"));
				Params.add(new ParamsGeneral(1,"usuario_aut_ventas","NE"));
				Params.add(new ParamsGeneral(1,"usuario_cancel","EQ"));
				Params.add(new ParamsGeneral(1,"fecha_cancel","EQ"));
				Params.add(new ParamsGeneral(1,"usuario_diseniador","EQ"));
				Params.add(new ParamsGeneral(1,"fecha_asign_diseniador","EQ"));
				Params.add(new ParamsGeneral(1,"usuario_rech_diseniador","EQ"));
				Params.add(new ParamsGeneral(1,"fecha_rech_diseniador","EQ"));
				Params.add(new ParamsGeneral(1,"idtiporequerimiento",0,"EQ"));
			}
			else
			{
				if(ban == 3)//aut ventas
				{
					Params.add(new ParamsGeneral(1,"fecha_envia_ventas","NE"));
					Params.add(new ParamsGeneral(1,"usuario_envia_ventas","NE"));
					Params.add(new ParamsGeneral(1,"fecha_aut_ventas","EQ"));
					Params.add(new ParamsGeneral(1,"usuario_aut_ventas","EQ"));
					Params.add(new ParamsGeneral(1,"fecha_rech_ventas","EQ"));
					Params.add(new ParamsGeneral(1,"usuario_rech_ventas","EQ"));
					Params.add(new ParamsGeneral(1,"usuario_cancel","EQ"));
					Params.add(new ParamsGeneral(1,"fecha_cancel","EQ"));
					Params.add(new ParamsGeneral(1,"idtiporequerimiento",0,"EQ"));
				}
				else
				{
					if(ban == 4)//arrasttres ASGINA
					{
						Params.add(new ParamsGeneral(1,"fecha_envia_arrmues","NE"));
						Params.add(new ParamsGeneral(1,"usuario_envia_arrmues","NE"));
						Params.add(new ParamsGeneral(1,"idtiporequerimiento",3,"EQ"));
						Params.add(new ParamsGeneral(1,"usuario_cancel","EQ"));
						Params.add(new ParamsGeneral(1,"fecha_cancel","EQ"));
						Params.add(new ParamsGeneral(1,"usuario_rech_arrastre","EQ"));
						Params.add(new ParamsGeneral(1,"fecha_rech_arrastre","EQ"));
						Params.add(new ParamsGeneral(1,"usuario_asigna_arrastre","EQ"));
						Params.add(new ParamsGeneral(1,"fecha_asigna_arrastre","EQ"));
					}
					else
					{
						if(ban == 5)//arrasttres LIBERA
						{
							Params.add(new ParamsGeneral(1,"fecha_envia_arrmues","NE"));
							Params.add(new ParamsGeneral(1,"usuario_envia_arrmues","NE"));
							Params.add(new ParamsGeneral(1,"idtiporequerimiento",3,"EQ"));
							Params.add(new ParamsGeneral(1,"usuario_cancel","EQ"));
							Params.add(new ParamsGeneral(1,"fecha_cancel","EQ"));
							Params.add(new ParamsGeneral(1,"usuario_rech_arrastre","EQ"));
							Params.add(new ParamsGeneral(1,"fecha_rech_arrastre","EQ"));
							Params.add(new ParamsGeneral(1,"usuario_asigna_arrastre","NE"));
							Params.add(new ParamsGeneral(1,"fecha_asigna_arrastre","NE"));
							Params.add(new ParamsGeneral(1,"usuario_libera_arrastre","EQ"));
							Params.add(new ParamsGeneral(1,"fecha_libera_arrastre","EQ"));
							
							User user = us.findBySSO(AppController.getPrincipal());
							Params.add(new ParamsGeneral(1,"usuario_asigna_arrastre",user.getId(),"EQ"));
						}
						else
						{
							if(ban == 6)//muestras ASIGNA SIMPLE
							{
								Params.add(new ParamsGeneral(1,"fecha_envia_arrmues","NE"));
								Params.add(new ParamsGeneral(1,"usuario_envia_arrmues","NE"));
								Params.add(new ParamsGeneral(1,"idtiporequerimiento",1,"EQ"));
								Params.add(new ParamsGeneral(1,"usuario_cancel","EQ"));
								Params.add(new ParamsGeneral(1,"fecha_cancel","EQ"));
								Params.add(new ParamsGeneral(1,"usuario_rech_arrastre","EQ"));
								Params.add(new ParamsGeneral(1,"fecha_rech_arrastre","EQ"));
								Params.add(new ParamsGeneral(1,"usuario_asigna_arrastre","EQ"));
								Params.add(new ParamsGeneral(1,"fecha_asigna_arrastre","EQ"));
							}
							else
							{
								if(ban == 7)//muestras ASIGNA VESTIDA
								{
									Params.add(new ParamsGeneral(1,"fecha_envia_arrmues","NE"));
									Params.add(new ParamsGeneral(1,"usuario_envia_arrmues","NE"));
									Params.add(new ParamsGeneral(1,"idtiporequerimiento",2,"EQ"));
									Params.add(new ParamsGeneral(1,"usuario_cancel","EQ"));
									Params.add(new ParamsGeneral(1,"fecha_cancel","EQ"));
									Params.add(new ParamsGeneral(1,"usuario_rech_arrastre","EQ"));
									Params.add(new ParamsGeneral(1,"fecha_rech_arrastre","EQ"));
									Params.add(new ParamsGeneral(1,"usuario_asigna_arrastre","EQ"));
									Params.add(new ParamsGeneral(1,"fecha_asigna_arrastre","EQ"));
								}
								else
								{
									if(ban == 8)//muestras LIBERA simple
									{
										Params.add(new ParamsGeneral(1,"fecha_envia_arrmues","NE"));
										Params.add(new ParamsGeneral(1,"usuario_envia_arrmues","NE"));
										Params.add(new ParamsGeneral(1,"idtiporequerimiento",1,"EQ"));
										Params.add(new ParamsGeneral(1,"usuario_cancel","EQ"));
										Params.add(new ParamsGeneral(1,"fecha_cancel","EQ"));
										Params.add(new ParamsGeneral(1,"usuario_rech_arrastre","EQ"));
										Params.add(new ParamsGeneral(1,"fecha_rech_arrastre","EQ"));
										Params.add(new ParamsGeneral(1,"usuario_asigna_arrastre","NE"));
										Params.add(new ParamsGeneral(1,"fecha_asigna_arrastre","NE"));
										Params.add(new ParamsGeneral(1,"usuario_libera_arrastre","EQ"));
										Params.add(new ParamsGeneral(1,"fecha_libera_arrastre","EQ"));
										
										User user = us.findBySSO(AppController.getPrincipal());
										Params.add(new ParamsGeneral(1,"usuario_asigna_arrastre",user.getId(),"EQ"));
									}
									else
									{
										if(ban == 9)//muestras LIBERA vestida
										{
											Params.add(new ParamsGeneral(1,"fecha_envia_arrmues","NE"));
											Params.add(new ParamsGeneral(1,"usuario_envia_arrmues","NE"));
											Params.add(new ParamsGeneral(1,"idtiporequerimiento",2,"EQ"));
											Params.add(new ParamsGeneral(1,"usuario_cancel","EQ"));
											Params.add(new ParamsGeneral(1,"fecha_cancel","EQ"));
											Params.add(new ParamsGeneral(1,"usuario_rech_arrastre","EQ"));
											Params.add(new ParamsGeneral(1,"fecha_rech_arrastre","EQ"));
											Params.add(new ParamsGeneral(1,"usuario_asigna_arrastre","NE"));
											Params.add(new ParamsGeneral(1,"fecha_asigna_arrastre","NE"));
											Params.add(new ParamsGeneral(1,"usuario_libera_arrastre","EQ"));
											Params.add(new ParamsGeneral(1,"fecha_libera_arrastre","EQ"));
											
											User user = us.findBySSO(AppController.getPrincipal());
											Params.add(new ParamsGeneral(1,"usuario_asigna_arrastre",user.getId(),"EQ"));
										}
									}
								}
							}
						}
					}
				}
			}
		}
		List<Cotizador> ListaCot = cs.ListasCotAut(Params);
		List<JSONObject> ListaCotJson = new ArrayList<JSONObject>();
		ListaCot.forEach(a -> {	
			ListaCotJson.add(ctsc.DataSourceJasperCot(a,1));
		});
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().create();
		return gson.fromJson(ListaCotJson.toString(), Object[].class);
	}
	
	@RequestMapping(value = {"/ventas/autorizacion_cotizacion_vtas_desicion" }, method = RequestMethod.POST)
	public @ResponseBody String autorizacion_cotizacion_vtas_desicion(ModelMap model, @RequestParam("idcot") String idcot, @RequestParam("coment") String coment, @RequestParam("ban") String ban) {
		String msj = "";
		try
		{
			User user = us.findBySSO(AppController.getPrincipal());
			Cotizador c = cs.BuscarxId(Integer.valueOf(idcot)); 
			java.util.Date date = new java.util.Date();
			String emailMsj = "";
			String asunto = "";
			if(Integer.valueOf(ban) == 1)
			{
				c.setUsuario_aut_ventas(user.getId());
				c.setFecha_aut_ventas(date);
				c.setObservaciones_ventas(coment);
				emailMsj="Cotización: ("+c.getId()+") autorizada (PRECIO) por el usuario: "+user.getFirstName()+ " " +user.getLastName()+ " (" + user.getSsoId() + ") \r\n\n Contacto: "+user.getEmail()+" \r\n\n Motivo: "+coment ;
				asunto = "Cotización autorizada";
			}
			else
			{			
				c.setUsuario_envia_ventas(null);
				c.setFecha_envia_ventas(null);
				c.setUsuario_envia_a_prog(null);
				c.setFecha_envia_a_prog(null);
				c.setObservaciones_prog(null);
				c.setFecha_aut_prog(null);
				c.setUsuario_aut_prog(null);
				
				c.setUsuario_rech_ventas(user.getId());
				c.setFecha_rech_ventas(date);
				c.setObservaciones_ventas(coment);
				emailMsj="Cotización: ("+c.getId()+") rechazada (PRECIO) por el usuario: "+user.getFirstName()+ " " +user.getLastName()+ " (" + user.getSsoId() + ") \r\n\n Contacto: "+user.getEmail()+" \r\n\n Motivo: "+coment ;
				asunto = "Cotización rechazada";
			}
			cs.Actualizar(c);
			////ENVÍO DE EMAIL
			SendMailGmail Email = new SendMailGmail();
			User userInsertCot = us.findById(c.getUsuario_insert());
			Email.sendMail(userInsertCot.getEmail(), emailMsj, asunto);
			/////
			logger.info(AppController.getPrincipal() + " - autorizacion_cotizacion_vtas_desicion :"+ msj);
			return "OK";
		}
		catch(Exception e)
		{
			msj = e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage();
			logger.info(AppController.getPrincipal() + " - autorizacion_cotizacion_vtas_desicion :"+ msj);
			return msj;
		}
		
	}
	
	@RequestMapping(value = {"/programacion/autorizacion_cotizacion_prog_desicion" }, method = RequestMethod.POST)
	public @ResponseBody String autorizacion_cotizacion_prog_desicion(ModelMap model, @RequestParam("idcot") String idcot, @RequestParam("coment") String coment, @RequestParam("ban") String ban) {
		String msj = "";
		try
		{
			User user = us.findBySSO(AppController.getPrincipal());
			Cotizador c = cs.BuscarxId(Integer.valueOf(idcot)); 
			java.util.Date date = new java.util.Date();
			String emailMsj = "";
			String asunto = "";
			if(Integer.valueOf(ban) == 1)
			{
				c.setUsuario_aut_prog(user.getId());
				c.setFecha_aut_prog(date);
				c.setObservaciones_prog(coment);
				emailMsj="Cotización: ("+c.getId()+") autorizada (PROGRAMACIÓN) por el usuario: "+user.getFirstName()+ " " +user.getLastName()+ " (" + user.getSsoId() + ") \r\n\n Contacto: "+user.getEmail()+" \r\n\n Motivo: "+coment ;
				asunto = "Cotización autorizada departamento Programación";
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
				
				c.setUsuario_envia_a_prog(null);
				c.setFecha_envia_a_prog(null);
				c.setObservaciones_ventas(null);
				emailMsj="Cotización: ("+c.getId()+") rechazada (PROGRAMACIÓN) por el usuario: "+user.getFirstName()+ " " +user.getLastName()+ " (" + user.getSsoId() + ") \r\n\n Contacto: "+user.getEmail()+" \r\n\n Motivo: "+coment ;
				asunto = "Cotización rechazada";
			}
			cs.Actualizar(c);
			
			////ENVÍO DE EMAIL
			SendMailGmail Email = new SendMailGmail();
			User userInsertCot = us.findById(c.getUsuario_insert());
			Email.sendMail(userInsertCot.getEmail(), emailMsj, asunto);
			/////
			
			logger.info(AppController.getPrincipal() + " - autorizacion_cotizacion_prog_desicion :"+ msj);
			return "OK";
		}
		catch(Exception e)
		{
			msj = e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage();
			logger.info(AppController.getPrincipal() + " - autorizacion_cotizacion_prog_desicion :"+ msj);
			return msj;
		}
		
	}
	////////////////////////////////////IMPRIMIR JASPER/////////////////////////////
	@RequestMapping(value = "/ventas/imprimircotizador", method = RequestMethod.GET)
    @ResponseBody
    public void getRpt1(HttpServletResponse response,HttpServletRequest request,ModelMap model,@RequestParam("id") Integer id) throws JRException, IOException {
		String msj = "";
		try
		{
			InputStream jasperStream = this.getClass().getResourceAsStream("/jasperreports/cotizador/Cotizador2.jasper");
			Map<String,Object> params = new HashMap<>();
			
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
			//JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(cs.ListaCotizacionesJasper(Integer.valueOf(id),false));
			//params.put("dataSource", dataSource);
			ByteArrayInputStream jsonDataStream = new ByteArrayInputStream(stripAccents(ctsc.DataSourceJasperCot(id,1).toString()).getBytes("UTF-8"));
			JsonDataSource dataSource = new JsonDataSource(jsonDataStream);
			params.put("Imagen",request.getServletContext().getRealPath("/"));
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
			
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "inline");
			
			    final OutputStream outStream = response.getOutputStream();
			    JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
			logger.info(AppController.getPrincipal() + " - imprimircotizador :"+ msj);
		}
		catch(Exception e)
		{
			msj = e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage();
			logger.info(AppController.getPrincipal() + " - imprimircotizador :"+ msj);
		}
	} 
		
	
	@RequestMapping(value = {"/ingenieria/requerimientoabc" }, method = RequestMethod.GET)
	public String requerimientoabcget(ModelMap model) {
		String msj = "";
		try 
		{
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			model.addAttribute("listaDet",ListaCotAut(2));
			logger.info(AppController.getPrincipal() + " - requerimientoabcget.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			msj = e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage();
		}
		logger.info(AppController.getPrincipal() + " - requerimientoabcget :"+ msj);
		model.addAttribute("mensajes", msj);
		return "/tarjetas/cotizador/requerimientoabc";
	}
	
	@RequestMapping(value = "/ingenieria/imprimirreq", method = RequestMethod.GET)
    @ResponseBody
    public void getRpt2(HttpServletResponse response,HttpServletRequest request,ModelMap model,@RequestParam("id") Integer id) throws JRException, IOException {
		String msj = "";
		try
		{
			InputStream jasperStream = this.getClass().getResourceAsStream("/jasperreports/cotizador/Requerimiento.jasper");
			Map<String,Object> params = new HashMap<>();
			
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
			//JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(cs.ListaCotizacionesJasper(Integer.valueOf(id),false));
			//params.put("dataSource", dataSource);
			//ByteArrayInputStream jsonDataStream = new ByteArrayInputStream(stripAccents(ctsc.DataSourceJasperReq(id).toString()).getBytes("UTF-8"));
			ByteArrayInputStream jsonDataStream = new ByteArrayInputStream(stripAccents(ctsc.DataSourceJasperCot(id,1).toString()).getBytes("UTF-8"));
			JsonDataSource dataSource = new JsonDataSource(jsonDataStream);
			params.put("Imagen",request.getServletContext().getRealPath("/"));
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
			
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "inline");
			
			    final OutputStream outStream = response.getOutputStream();
			    JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
			logger.info(AppController.getPrincipal() + " - imprimircotizador :"+ msj);
		}
		catch(Exception e)
		{
			msj = e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage();
			logger.info(AppController.getPrincipal() + " - imprimircotizador :"+ msj);
		}
	}
	
	private String stripAccents(String s) 
	{
	    s = Normalizer.normalize(s, Normalizer.Form.NFD);
	    s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
	    return s;
	}
	
	@SuppressWarnings("null")
	@RequestMapping(value = "/ingenieria/convertiratarjeta", method = RequestMethod.POST)
    public @ResponseBody String convertiratarjeta(ModelMap model, @RequestParam("idcot") String idcot, @RequestParam("coment") String coment, @RequestParam("ban") String ban) throws JRException, IOException {
		String msj = "";
		try
		{
			User user = us.findBySSO(AppController.getPrincipal());
			Cotizador c = cs.BuscarxId(Integer.valueOf(idcot));
			java.util.Date date = new java.util.Date();
			
			if(Integer.valueOf(ban) == 0)
			{
				c.setObservaciones_diseniador(coment);
				cs.Actualizar(c);
				logger.info(AppController.getPrincipal() + " - convertiratarjeta. Grabar commentario");
				msj="OK";
			}
			else
			{
				String emailMsj = "";
				String asunto = "";
				if(Integer.valueOf(ban) == 1)
				{					
					List<Cotizador_detalles>  ListaDetalles = cds.BuscarxCotId(Integer.valueOf(idcot));					
					
					if(ListaDetalles.size() > 0)
					{
						List<Tarjeta_fabricacion> ListaTar = new ArrayList<Tarjeta_fabricacion>();
						for(int i = 0; i < ListaDetalles.size(); i++)
						{
							Tarjeta_fabricacion Tarjeta = new Tarjeta_fabricacion();
							Tarjeta.setIdcotizacion(ListaDetalles.get(i).getIdcotizacion());
							Tarjeta.setIddetalle(ListaDetalles.get(i).getIddetalle());
							Catalogo_cajas_sap_vw objCaja = ccss.BuscarxId(ListaDetalles.get(i).getIdcaja_sap());
							StringTokenizer stPalabras = new StringTokenizer(objCaja.getNombrelargo());
							Tarjeta.setDescripcion_factura(stPalabras.nextToken() + " de cartón corrugado. Símbolo: " + ListaDetalles.get(i).getSimbolo());
							Tarjeta.setFolio_tarjeta(ListaDetalles.get(i).getIddetalle() == 1 ? ListaDetalles.get(i).getIdcotizacion().toString() : ListaDetalles.get(i).getIdcotizacion().toString() +"-"+ (char)(63+ListaDetalles.get(i).getIddetalle()));					
							
							Tarjeta.setIddiseniador(user.getId());
							Tarjeta.setFecha_asig_diseniador(date);
							Tarjeta.setCardcode(c.getCardcode());
							
							if(objCaja.getGrupo() != null)//Caja tiene asignado un Grupo???
							{
								//GRAPADO/PEGADO
								if(ecs.ListaEspDet(ListaDetalles.get(i).getIdcotizacion(), ListaDetalles.get(i).getIddetalle()).stream()
								.filter(a -> a.getIdespecialidad() == 20 || a.getIdespecialidad() == 8).count() > 0 )
								{
									if(objCaja.getGrupo() == 3)
									{
										Double cm = 0.0;
										cm =ecs.EspDet(ListaDetalles.get(i).getIdcotizacion(), ListaDetalles.get(i).getIddetalle(), 8).getCm();
										if(cm != null || cm == 0)
											cm =ecs.EspDet(ListaDetalles.get(i).getIdcotizacion(), ListaDetalles.get(i).getIddetalle(), 20).getCm();
										Tarjeta.setPegado_grapado(cm); //Suaje: Pendiente, se va agregar un campo en Cotizador para el vendedor capture el valor
									}
									else
									{
										if(objCaja.getGrupo() == 1 || objCaja.getGrupo() == 2 )
											Tarjeta.setPegado_grapado(ListaDetalles.get(i).getFondo());
									}
								}
								
								
								DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.getDefault());
								formatSymbols.setDecimalSeparator('.');
								
								DecimalFormat format = new DecimalFormat("##########0.0", formatSymbols);							
								
								//Double deci = 0.0;
								//RAYADO 1
								Double rayado1 = 0.0;
								if(objCaja.getGrupo() == 1)
									rayado1 = ListaDetalles.get(i).getAncho() / 2 + (objCaja.getCorrugado().equals("S") ? 0.3 : 0.5);
								
								if(objCaja.getGrupo() == 2)
									rayado1 = ListaDetalles.get(i).getFondo() + (objCaja.getCorrugado().equals("S") ? 0.4 : 0.8);
	
								rayado1 = Double.valueOf(format.format(rayado1));
								/*deci = rayado1 - rayado1.intValue();
								 if(deci >= 0.5) rayado1 = rayado1 + (1 - deci);
								 */
								
								//RAYADO 2
								Double rayado2 = 0.0;
								if(objCaja.getGrupo() == 1)
									rayado2 = ListaDetalles.get(i).getFondo() + (objCaja.getCorrugado().equals("S") ? 0.7 : 1.5);
								if(objCaja.getGrupo() == 2)
									rayado2 = ListaDetalles.get(i).getAncho() / 2 + (objCaja.getCorrugado().equals("S") ? 0.3 : 0.5);
								
								rayado2 = Double.valueOf(format.format(rayado2));
//								deci = rayado2 - rayado2.intValue();
//								if(deci >= 0.5)
//									rayado2 = rayado2 + (1 - deci);
								
								//RAYADO 3
								Double rayado3 = 0.0;
								if(objCaja.getGrupo() == 1)
									rayado3 = objCaja.getCorrugado().equals("S") ? ListaDetalles.get(i).getAncho() / 2 + 0.3 : ListaDetalles.get(i).getFondo() + 1.5;
								
								rayado3 = Double.valueOf(format.format(rayado3));
//								deci = rayado3 - rayado3.intValue();
//								if(deci >= 0.5)
//									rayado3 = rayado3 + (1 - deci);
								
								Tarjeta.setRayado1(rayado1);
								Tarjeta.setRayado2(rayado2);
								Tarjeta.setRayado3(rayado3);
								
								msj= "\n"+msj+"OK Tarjetas creada: "+Tarjeta.getFolio_tarjeta()+"\n";
								
								if( ((objCaja.getGrupo() == 1) && (rayado1 > 0 && rayado2 > 0 && rayado3 > 0)) || 
									(objCaja.getGrupo() != 1 && objCaja.getGrupo() != 2) ||
									((objCaja.getGrupo() == 2) && (rayado1 > 0 && rayado2 > 0))) 
									ListaTar.add(Tarjeta);
								else
								{
									msj = "Los rayados dan Cero (0). Favor de verificar los datos de la Cotización.";
									ListaTar.clear();
									break;
								}
							}
							else
							{
								msj = "Debe asignar un Grupo a la caja: "+objCaja.getNombrelargo();
								ListaTar.clear();
								break;
							}
						}
						
						ListaTar.stream().forEach(a -> tfs.Guardar(a));
						
						if(ListaTar.size() > 0) {
							c.setUsuario_diseniador(user.getId());
							c.setFecha_asign_diseniador(date);
							c.setObservaciones_diseniador(coment);
							cs.Actualizar(c);
							emailMsj="Cotización: ("+c.getId()+") convertida a Tarjeta de Fabricación por el usuario: "+user.getFirstName()+ " " +user.getLastName()+ " (" + user.getSsoId() + ") \r\n\n Contacto: "+user.getEmail()+" \r\n\n Motivo: "+coment ;
							asunto = "Cotización convertida a Tarjeta de Fabricación";
						}
					}

					logger.info(AppController.getPrincipal() + " - convertiratarjeta.Convertir a tarjeta");
				}
				else
				{
					if(Integer.valueOf(ban) == 2)
					{
						c.setUsuario_cancel(user.getId());
						c.setFecha_cancel(date);
						c.setObservaciones_diseniador(coment);
						cs.Actualizar(c);
						
						emailMsj="Cotización: ("+c.getId()+") cancelada por el usuario: "+user.getFirstName()+ " " +user.getLastName()+ " (" + user.getSsoId() + ") \r\n\n Contacto: "+user.getEmail()+" \r\n\n Motivo: "+coment ;
						asunto = "Cotización cancelada";
						msj="OK";
						logger.info(AppController.getPrincipal() + " - convertiratarjeta - cancelarcotizacion ingeniería.");
					}
					else
					{
						c.setUsuario_rech_prog(null);
						c.setFecha_rech_prog(null);
						c.setObservaciones_prog(null);
						c.setUsuario_envia_a_prog(null);
						c.setFecha_envia_a_prog(null);
						c.setUsuario_aut_prog(null);
						c.setFecha_aut_prog(null);
						
						c.setUsuario_aut_ventas(null);
						c.setFecha_aut_ventas(null);
						c.setUsuario_envia_ventas(null);
						c.setFecha_envia_ventas(null);
						c.setObservaciones_ventas(null);
						c.setUsuario_envia_ventas(null);
						c.setFecha_envia_ventas(null);
						
						c.setUsuario_rech_diseniador(user.getId());
						c.setFecha_rech_diseniador(date);
						c.setObservaciones_diseniador(coment);
						cs.Actualizar(c);
						
						emailMsj="Cotización: ("+c.getId()+") rechazada por el usuario: "+user.getFirstName()+ " " +user.getLastName()+ " (" + user.getSsoId() + ") \r\n\n Contacto: "+user.getEmail()+" \r\n\n Motivo: "+coment ;
						asunto = "Cotización rechazada";						
						msj="OK";
						logger.info(AppController.getPrincipal() + " - convertiratarjeta - rechazarcotizacion ingeniería.");
					}
				}
				
				////ENVÍO DE EMAIL
				if(!emailMsj.equals("")) {
					SendMailGmail Email = new SendMailGmail();
					User userInsertCot = us.findById(c.getUsuario_insert());
					Email.sendMail(userInsertCot.getEmail(), emailMsj, asunto);
				}
				/////
				
			}
			return msj;
		}
		catch(Exception e)
		{
			msj = e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage() + " "+e.getClass().getName();
			logger.info(AppController.getPrincipal() + " - convertiratarjeta :"+ msj);
			e.printStackTrace();
			return msj;
		}
	}
	
	@RequestMapping(value = {"/vendedor/buscarresistenciasbarca"}, method = RequestMethod.GET)
	public @ResponseBody String buscarresistenciasbarca(HttpServletRequest req, HttpServletResponse res)
	   throws Exception {
		String idcaja = req.getParameter("idcaja");
		
		Gson g=new Gson();
		return g.toJson(ListaResis(Integer.valueOf(idcaja)));	
	}
	
	private List<Catalogo_resistencias_sap_vw> ListaResis(Integer idcaja)
	{
		Catalogo_cajas_sap_vw objCaja = ccss.BuscarxId(Integer.valueOf(idcaja));//Datos de la caja seleccionada.
		List<Catalogo_resistencias_sap_vw> ListaResis = new ArrayList<Catalogo_resistencias_sap_vw>();
		if(idcaja > 0) {
			if(objCaja.getCorrugado() != null)
			{
				if(objCaja.getCorrugado().equals("D"))
				{
					ListaResis = crss.ListaResis("BC");
				}
				else
				{
					if(objCaja.getCorrugado().equals("SD"))
					{
						ListaResis = crss.ListaResis();
					}
					else
					{
						if(objCaja.getCorrugado().equals("S"))
						{
							ListaResis = Stream.of(crss.ListaResis("B"),crss.ListaResis("C")).flatMap(Collection::stream).collect(Collectors.toList());
						}
					}
					
				}
			}
		}
		return ListaResis;
		
	}
	
	@RequestMapping(value = {"/vendedor/arrastresabc" }, method = RequestMethod.GET)
	public String reqmuestrasarrget(ModelMap model, @RequestParam(value = "id", defaultValue = "0", required = false) Integer id, 
													@RequestParam(value = "iddet", defaultValue = "0", required = false) Integer iddet) {
		User user = us.findBySSO(AppController.getPrincipal());
		model.addAttribute("clientes", ccavs.ListaCtes(user.getCvevendedor_sap()));
		model.addAttribute("colores", ccos.ListaColores());
		model.addAttribute("resistencias", crss.ListaResis());
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		
		if(id == 0)
			model.addAttribute("cotizadordatabean", new CotizadorDataBean());
		else
		{
			Cotizador cot = new Cotizador();
			Cotizador_detalles cotdet = new Cotizador_detalles();
			cot = cs.BuscarxIdArr(id, user.getId());
			cotdet = cot == null ? null : cds.BuscarxId(id, iddet, user.getId());
			CotizadorDataBean cbd = new CotizadorDataBean();
			cbd.setCotizador(cot);
			cbd.setCotizador_detalles(cotdet);
			model.addAttribute("direcciones", cdsv.ListaDirCardCode(cot.getCardcode()));
			model.addAttribute("direccionSelect", cdsv.ListaDirCardCodeNumLine(cot.getCardcode(),cot.getLinenum_dir_entrega()));
			model.addAttribute("cotizadordatabean", cbd);
		}
		
		return "/tarjetas/cotizador/arrastresabc";
	}
	
	@RequestMapping(value = {"/vendedor/arrastresabc" }, method = RequestMethod.POST)
	public String arrastresabcpost(@Valid @ModelAttribute("cotizadordatabean") CotizadorDataBean cotizadorDataBean, BindingResult result, ModelMap model) {
		try
		{
			logger.info(AppController.getPrincipal() + " - arrastresabcpost.");
			String msj = "";
			User user = us.findBySSO(AppController.getPrincipal());
			model.addAttribute("clientes", ccavs.ListaCtes(user.getCvevendedor_sap()));
			model.addAttribute("colores", ccos.ListaColores());
			model.addAttribute("direcciones", cdsv.ListaDirCardCode(cotizadorDataBean.getCotizador().getCardcode()));
			model.addAttribute("direccionSelect", cdsv.ListaDirCardCodeNumLine(cotizadorDataBean.getCotizador().getCardcode(),cotizadorDataBean.getCotizador().getLinenum_dir_entrega()));
			model.addAttribute("resistencias", crss.ListaResis());
			
			java.util.Date date = new java.util.Date();
			
			cotizadorDataBean.getCotizador_detalles().setBan(null);
			cotizadorDataBean.getCotizador_detalles().setIdtiporequerimiento(cotizadorDataBean.getCotizador().getIdtiporequerimiento());
			//cotizadorDataBean.getCotizador().setCardcode_factura(cotizadorDataBean.getCotizador().getCardcode());
			
			cvalidator.validate(cotizadorDataBean.getCotizador(), result);
			cdvalidator.validate(cotizadorDataBean.getCotizador_detalles(), result);
			/*for (ObjectError error : result.getAllErrors()) {
		       String fieldErrors [] = error.getCodes();
		       System.out.println(fieldErrors[0]);
		   }*/ 
			if (result.hasErrors() )
			{
				return "/tarjetas/cotizador/arrastresabc";
			}
			
			cotizadorDataBean.getCotizador().setFecha_update(date);
			cotizadorDataBean.getCotizador().setUsuario_update(user.getId());
			cotizadorDataBean.getCotizador_detalles().setFecha_update(date);
			cotizadorDataBean.getCotizador_detalles().setUsuario_update(user.getId());
			idN = 0;
			idND = 0;
			if(cotizadorDataBean.getCotizador().getId() == null || cotizadorDataBean.getCotizador().getId() == 0)
			{
				cotizadorDataBean.getCotizador().setFecha_insert(date);
				cotizadorDataBean.getCotizador().setUsuario_insert(user.getId());
				cotizadorDataBean.getCotizador_detalles().setFecha_insert(date);
				cotizadorDataBean.getCotizador_detalles().setUsuario_insert(user.getId());
				idN = cs.Maximo("id");
				idN = (idN == null ? 1 : ++idN);
				cotizadorDataBean.getCotizador().setId(idN);
				cs.Guardar(cotizadorDataBean.getCotizador());
				//idN = cs.Guardar(cotizadorDataBean.getCotizador());
				cotizadorDataBean.getCotizador_detalles().setIdcotizacion(idN);
				cotizadorDataBean.getCotizador_detalles().setIddetalle(cds.BuscarxCotId(idN).size() + 1);
				idND = cds.Guardar(cotizadorDataBean.getCotizador_detalles());
				msj= "ARRASTRE: "+idN+" Y DETALLE: " +idND+" GRABADO SATISFACTORIAMENTE";
			}
			else
			{
				cs.Actualizar(cotizadorDataBean.getCotizador());
				cds.Actualizar(cotizadorDataBean.getCotizador_detalles());
				msj= "ARRASTRE: "+cotizadorDataBean.getCotizador().getId()+" Y DETALLE: " +cotizadorDataBean.getCotizador_detalles().getIddetalle()+" ACTUALIZADO SATISFACTORIAMENTE";
			}
			model.addAttribute("mensajes", msj);
			
			return "/tarjetas/cotizador/arrastresabc";
		}
		catch(Exception e)
		{
			model.addAttribute("error",e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage());
			logger.error(AppController.getPrincipal() + " - arrastresabc. - " + e);
			return "/tarjetas/cotizador/arrastresabc";
		}
	}
	
	@RequestMapping(value = {"/vendedor/cancelararrastre" }, method = RequestMethod.POST)
	public @ResponseBody String cancelararrastre(ModelMap model, @RequestParam("idcot") String idcot) {
		String msj = "";
		try
		{
			User user = us.findBySSO(AppController.getPrincipal());
			Cotizador c = cs.BuscarxIdArr(Integer.valueOf(idcot), user.getId()); 
			java.util.Date date = new java.util.Date();
			c.setUsuario_cancel(user.getId());
			c.setFecha_cancel(date);
			cs.Actualizar(c);
			
			logger.info(AppController.getPrincipal() + " - cancelararrastre.");
			return "OK";
		}
		catch(Exception e)
		{
			msj = e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage();
			logger.info(AppController.getPrincipal() + " - cancelararrastre :"+ e);
			return msj;
		}
		
	}//enviararrastre
	
	@RequestMapping(value = {"/vendedor/arrastrebusqueda" }, method = RequestMethod.GET)
	public String arrastrebusqueda(ModelMap model,@RequestParam("id") Integer id,@RequestParam("cardcode") String cardcode) {
		String msj = "";
		try
		{
			List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
			List<JSONObject> ListaCot = new ArrayList<JSONObject>();
			User user = us.findBySSO(AppController.getPrincipal());
			if(id > 0)
				Params.add(new ParamsGeneral(1,"id",id,"EQ"));
			
			else
				Params.add(new ParamsGeneral(1,"cardcode",cardcode,"EQ"));
			
			Params.add(new ParamsGeneral(1,"idtiporequerimiento",3,"EQ"));
			Params.add(new ParamsGeneral(3,"usuario_insert",user.getId(),"EQ"));
			
			cs.ListasCotAut(Params).forEach(a -> {
				ListaCot.add(ctsc.DataSourceJasperCot(a.getId(),1));
			});
			
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.serializeNulls().create();
			
			model.addAttribute("lista",gson.fromJson(ListaCot.toString(), Object[].class));
			
		}
		catch(Exception e)
		{
			msj = e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage();
			model.addAttribute("mensajes", msj);			
		}
		
		logger.info(AppController.getPrincipal() + " - arrastrebusqueda. "+msj);
		return "/tarjetas/cotizador/cotizador_busqueda";

	}
	
	@RequestMapping(value = {"/vendedor/enviararrastre" }, method = RequestMethod.POST)
	public @ResponseBody String enviararrastre(ModelMap model, @RequestParam("idcot") String idcot)
	{
		String msj = "";
		try
		{
			java.util.Date date = new java.util.Date();
			User user = us.findBySSO(AppController.getPrincipal());
			Cotizador c = cs.BuscarxIdArr(Integer.valueOf(idcot), user.getId());
			c.setFecha_envia_arrmues(date);
			c.setUsuario_envia_arrmues(user.getId());
			c.setFecha_rech_arrastre(null);
			c.setUsuario_rech_arrastre(null);
			c.setObservaciones_arrastre(null);
			cs.Actualizar(c);
			logger.info(AppController.getPrincipal() + " - enviararrastre.");
			return "OK";
		}
		catch(Exception e)
		{
			msj = e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage();
			logger.info(AppController.getPrincipal() + " - enviararrastre :"+ msj);
			return msj;
		}
	}
	
	@RequestMapping(value = {"/arrastres/asignar_arrastres" }, method = RequestMethod.GET)
	public String asignar_arrastres(ModelMap model)
	{
		
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		model.addAttribute("listaDet",ListaCotAut(4));
		logger.info(AppController.getPrincipal() + " - asignar_arrastres.");
		return "/tarjetas/cotizador/asignar_arrastres";
	}
	
	@RequestMapping(value = {"/arrastres/arrastresabc" }, method = RequestMethod.POST)
	public @ResponseBody String arrastresabc(ModelMap model, @RequestParam("idcot") Integer idcot, @RequestParam("coment") String coment, @RequestParam("ban") Integer ban)
	{
		String msj = "";
		try
		{
			java.util.Date date = new java.util.Date();
			User user = us.findBySSO(AppController.getPrincipal());
			Cotizador c = cs.BuscarxId(idcot);
			
			if(ban == 1)//Asignar
			{
				c.setUsuario_asigna_arrastre(user.getId());
				c.setFecha_asigna_arrastre(date);
			}
			else
			{
				String emailMsj = "";
				if(ban == 2)//Rechazar
				{
					c.setFecha_rech_arrastre(date);
					c.setUsuario_rech_arrastre(user.getId());
					c.setUsuario_envia_arrmues(null);
					c.setFecha_envia_arrmues(null);
					emailMsj="Arrastre rechazado: ("+c.getId()+") por el usuario: "+user.getFirstName()+ " " +user.getLastName()+ " (" + user.getSsoId() + ") \r\n\n Contacto: "+user.getEmail();
				}
				else
				{
					if(ban == 3)//Liberar
					{
						c.setUsuario_libera_arrastre(user.getId());
						c.setFecha_libera_arrastre(date);
						emailMsj="Arrastre liberado: ("+c.getId()+") por el usuario: "+user.getFirstName()+ " " +user.getLastName()+ " (" + user.getSsoId() + ") \r\n\n Contacto: "+user.getEmail();
					}
				}
				
				////ENVÍO DE EMAIL
				SendMailGmail Email = new SendMailGmail();
				User userInsertCot = us.findById(c.getUsuario_insert());
				Email.sendMail(userInsertCot.getEmail(), emailMsj, "Arrastre info");
				/////
			}
			c.setObservaciones_arrastre(coment);
			cs.Actualizar(c);
			logger.info(AppController.getPrincipal() + " - enviararrastre.");
			return "OK";
		}
		catch(Exception e)
		{
			msj = e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage();
			logger.info(AppController.getPrincipal() + " - enviararrastre :"+ msj);
			return msj;
		}
	}
	
	@RequestMapping(value = {"/arrastres/liberar_arrastres" }, method = RequestMethod.GET)
	public String liberar_arrastres(ModelMap model)
	{
		
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		model.addAttribute("listaDet",ListaCotAut(5));
		logger.info(AppController.getPrincipal() + " - liberar_arrastres.");
		return "/tarjetas/cotizador/liberar_arrastres";
	}
	
	@RequestMapping(value = {"/muestras/asignar_muestras" }, method = RequestMethod.GET)
	public String asignar_muestras(ModelMap model)
	{
		
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		model.addAttribute("listaDetms",ListaCotAut(6));
		model.addAttribute("listaDetmv",ListaCotAut(7));
		logger.info(AppController.getPrincipal() + " - asignar_muestras.");
		return "/tarjetas/cotizador/asignar_muestras";
	}
	
	@RequestMapping(value = {"/muestras/muestrasabc" }, method = RequestMethod.POST)
	public @ResponseBody String muestrasabc(ModelMap model, @RequestParam("idcot") Integer idcot, @RequestParam("coment") String coment, @RequestParam("ban") Integer ban)
	{
		String msj = "";
		try
		{
			java.util.Date date = new java.util.Date();
			User user = us.findBySSO(AppController.getPrincipal());
			Cotizador c = cs.BuscarxId(idcot);
			
			if(ban == 1)//Asignar
			{
				c.setUsuario_asigna_arrastre(user.getId());
				c.setFecha_asigna_arrastre(date);
			}
			else
			{
				String emailMsj = "";
				if(ban == 2)//Rechazar
				{
					c.setFecha_rech_arrastre(date);
					c.setUsuario_rech_arrastre(user.getId());
					c.setUsuario_envia_arrmues(null);
					c.setFecha_envia_arrmues(null);
					emailMsj="Muestra rechazada: ("+c.getId()+") por el usuario: "+user.getFirstName()+ " " +user.getLastName()+ " (" + user.getSsoId() + ") \r\n\n Contacto: "+user.getEmail();
				}
				else
				{
					if(ban == 3)//Liberar
					{
						c.setUsuario_libera_arrastre(user.getId());
						c.setFecha_libera_arrastre(date);
						emailMsj="Muestra liberada: ("+c.getId()+") por el usuario: "+user.getFirstName()+ " " +user.getLastName()+ " (" + user.getSsoId() + ") \r\n\n Contacto: "+user.getEmail();
					}
				}
				
				////ENVÍO DE EMAIL
				SendMailGmail Email = new SendMailGmail();
				User userInsertCot = us.findById(c.getUsuario_insert());
				Email.sendMail(userInsertCot.getEmail(), emailMsj, "Arrastre info");
				/////
			}
			c.setObservaciones_arrastre(coment);
			cs.Actualizar(c);
			logger.info(AppController.getPrincipal() + " - enviararrastre.");
			return "OK";
		}
		catch(Exception e)
		{
			msj = e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage();
			logger.info(AppController.getPrincipal() + " - enviararrastre :"+ msj);
			return msj;
		}
	}
	
	@RequestMapping(value = {"/muestras/liberar_muestras" }, method = RequestMethod.GET)
	public String liberar_muestars(ModelMap model)
	{		
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		model.addAttribute("listaDetms",ListaCotAut(8));
		model.addAttribute("listaDetmv",ListaCotAut(9));
		logger.info(AppController.getPrincipal() + " - liberar_muestras.");
		return "/tarjetas/cotizador/liberar_muestras";
	}
	
	@RequestMapping(value = {"/vendedor/seguimiento_arrastres_muestras" }, method = RequestMethod.GET)
	public String seguimiento_arrastres_muestras(ModelMap model,
			@RequestParam(value = "folio", defaultValue = "", required = false) String folio,
			@RequestParam(value = "emp", defaultValue = "", required = false) String emp,
			@RequestParam(value = "ven", defaultValue = "", required = false) String ven,
			@RequestParam(value = "est", defaultValue = "", required = false) String est,
			@RequestParam(value = "tipo", defaultValue = "", required = false) String tipo)
	{		
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		List<ParamsGeneral> params = new ArrayList<ParamsGeneral>();
		List<JSONObject> Lista = new ArrayList<JSONObject>();
		User user = us.findBySSO(AppController.getPrincipal());
		int c = 0;
		c = (int)(user.getUserProfiles().stream().filter(s -> 
															s.getType().equals("ADMIN") || 
															s.getType().equals("VENTAS") ||
															s.getType().equals("INGENIERIA") ||
															s.getType().equals("INGENIERIA_GERENCIA")
														).count());
		
		if(c == 0)
		{
			params.add(new ParamsGeneral(1,"usuario_insert",user.getId(),"EQ"));
		}
		else
		{	
			model.addAttribute("vendedores", cvss.ListaVendedores());
			List<User> users = null;		
			users = us.findAllUsers().stream()
			.filter(a -> a.getUserProfiles().stream()
					.filter(b -> b.getType().equals("ARRASTRE") || b.getType().equals("MUESTRISTA")).count() > 0).collect(Collectors.toList());
			model.addAttribute("ListaEmp", users);
		}
		
		if(!folio.equals(""))
		{
			params.add(new ParamsGeneral(1,"id",Integer.valueOf(folio),"EQ"));
		}
		
		if(!emp.equals(""))
		{
			params.add(new ParamsGeneral(1,"usuario_asigna_arrastre",Integer.valueOf(emp),"EQ"));
			
		}
		
		if(!ven.equals(""))
		{
			params.add(new ParamsGeneral(1,"usuario_insert",Integer.valueOf(ven),"EQ"));
		}
		
		if(!est.equals(""))
		{
			Integer estatus = Integer.valueOf(est);
			if(estatus == 1)
				params.add(new ParamsGeneral(1,"fecha_envia_arrmues","NE"));
			if(estatus == 2)
				params.add(new ParamsGeneral(1,"fecha_asigna_arrastre","NE"));
			if(estatus == 3)
				params.add(new ParamsGeneral(1,"fecha_libera_arrastre","NE"));
			if(estatus == 4)
				params.add(new ParamsGeneral(1,"fecha_cancel","NE"));
			if(estatus == 5)
				params.add(new ParamsGeneral(1,"fecha_rech_arrastre","NE"));
		}
		
		if(!tipo.equals("") && !tipo.equals("0"))
		{
			params.add(new ParamsGeneral(1,"idtiporequerimiento",Integer.valueOf(tipo),"EQ"));
		
			if(params.size() > 0)
			{
				cs.ListasCotAut(params).forEach(a -> {
						Lista.add(ctsc.DataSourceJasperCot(a.getId(),1));
				});
			}
		}
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().create();
		
		model.addAttribute("Lista", gson.fromJson(Lista.toString(), Object[].class));
		
		logger.info(AppController.getPrincipal() + " - seguimiento_arrastres_muestras.");
		return "/tarjetas/cotizador/seguimiento_arrastres_muestras";
	}
	
	@RequestMapping(value = {"/vendedor/seguimiento_cot" }, method = RequestMethod.GET)
	public String seguimiento_cot(ModelMap model,
			@RequestParam(value = "folio", defaultValue = "", required = false) String folio,
			@RequestParam(value = "cte", defaultValue = "", required = false) String cte,
			@RequestParam(value = "ven", defaultValue = "", required = false) String ven,
			@RequestParam(value = "est", defaultValue = "", required = false) String est)
	{
		
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		User user = us.findBySSO(AppController.getPrincipal());		
		
		if(user.getUserProfiles().stream()
				.filter(b -> b.getType().equals("VENTAS")).count() == 0)
		{
			
			model.addAttribute("ListaCte", ccavs.ListaCtes(user.getCvevendedor_sap()));
			model.addAttribute("CteVen", user.getCvevendedor_sap());
		}
		else
			model.addAttribute("ListaCte", ccavs.ListaCtes());
		
		model.addAttribute("ListaVen", cvss.ListaVendedores());
		List<ParamsGeneral> params = new ArrayList<ParamsGeneral>();
		List<JSONObject> Lista = new ArrayList<JSONObject>();
		if(!folio.equals(""))
		{
			params.add(new ParamsGeneral(1,"id",Integer.valueOf(folio),"EQ"));
		}
		
		if(!cte.equals(""))
		{
			params.add(new ParamsGeneral(1,"cardcode",cte,"EQ"));			
		}
		
		/*if(!ven.equals(""))
		{
			params.add(new ParamsGeneral(1,"usuario_insert",Integer.valueOf(ven),"EQ"));
		}*/
		
		if(!est.equals(""))
		{
			Integer estatus = Integer.valueOf(est);
			if(estatus == 1) {//Pendientes de enviar ventas/programación
				params.add(new ParamsGeneral(1,"fecha_envia_ventas","EQ"));
				params.add(new ParamsGeneral(1,"fecha_envia_a_prog","EQ"));
			}				
			if(estatus == 2) {//En ventas
				params.add(new ParamsGeneral(1,"fecha_aut_ventas","EQ"));
				params.add(new ParamsGeneral(1,"fecha_envia_ventas","NE"));
			}
			if(estatus == 3) {//En programación
				params.add(new ParamsGeneral(1,"fecha_aut_prog","EQ"));
				params.add(new ParamsGeneral(1,"fecha_envia_a_prog","NE"));
			}
			if(estatus == 4)//Pendientes de asignar diseniador
			{
				params.add(new ParamsGeneral(1,"fecha_aut_prog","NE"));
				params.add(new ParamsGeneral(1,"fecha_aut_ventas","NE"));
				params.add(new ParamsGeneral(1,"fecha_asign_diseniador","EQ"));
			}
			if(estatus == 5)//Convertidas a tarjetas
				params.add(new ParamsGeneral(1,"fecha_asign_diseniador","NE"));
			
			if(estatus == 6)//Canceladas
				params.add(new ParamsGeneral(1,"fecha_cancel","NE"));
			
			if(estatus == 7)//Rechazadas x ventas
				params.add(new ParamsGeneral(1,"fecha_rech_ventas","NE"));
			if(estatus == 8)//Rechazadas x programación
				params.add(new ParamsGeneral(1,"usuario_rech_prog","NE"));
			if(estatus == 9)//Rechazadas x diseñador
				params.add(new ParamsGeneral(1,"fecha_rech_diseniador","NE"));
			
		}
		
		//if(params.size() > 0)
		//{
			params.add(new ParamsGeneral(1,"idtiporequerimiento",0,"EQ"));//Solo cotizaciones
			if(ven.equals(""))
			{
				if(params.size() > 1)
				{
					cs.ListasCotAut(params).forEach(a -> {
							Lista.add(ctsc.DataSourceJasperCot(a,1));
					});
				}
			}
			else
			{
				Integer CveVen = user.getCvevendedor_sap();
				us.findCveVenUsers(CveVen).forEach(usr -> {
					params.add(new ParamsGeneral(10,"usuario_insert", usr.getId(),"EQ"));
					cs.ListasCotAut(params).forEach(a -> {
						Lista.add(ctsc.DataSourceJasperCot(a,1));
					});
					params.remove(params.size() -1);
				});
			}
		//}
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().create();
		
		model.addAttribute("Lista", gson.fromJson(Lista.toString(), Object[].class));
		logger.info(AppController.getPrincipal() + " - seguimiento_cot.");
		return "/tarjetas/cotizador/seguimiento_cot";
	}
	
	@RequestMapping(value = {"/vendedor/seguimiento_cot_hist" }, method = RequestMethod.GET)
	public String seguimiento_cot_hist(ModelMap model,
			@RequestParam(value = "id", defaultValue = "0", required = false) Integer id,
			@RequestParam(value = "iddet", defaultValue = "0", required = false) Integer iddet)
	{		
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		logger.info(AppController.getPrincipal() + " - seguimiento_cot_hist.");
		User user = us.findBySSO(AppController.getPrincipal());		
		try
		{
			Cotizador Cot = new Cotizador();
			
			if(id > 0 && iddet > 0)
			{
				GsonBuilder builder = new GsonBuilder();
				Gson gson = builder.serializeNulls().create();
				
				if(user.getUserProfiles().stream().filter(b -> b.getType().equals("VENTAS") || b.getType().equals("INGENIERIA") || b.getType().equals("PROGRAMACION")).count() == 0)
				{					
					for(User usr : us.findCveVenUsers(user.getCvevendedor_sap()))
					{
						Cot = cs.BuscarxId(id, usr.getId());
						if(Cot != null)
							break;
					}
					
				}
				else
					Cot = cs.BuscarxId(id);
				
				if(Cot != null)
				{
					if(Cot.getIdtiporequerimiento() == 0)
					{
						model.addAttribute("cot", gson.fromJson(ctsc.DataSourceJasperCot(id,0).toString(),Object.class));
						model.addAttribute("cotdet", gson.fromJson(ctsc.addSpecificDetalle(id,iddet).toString(), Object.class));
						JSONObject Tar = new JSONObject();
						Tar = ctsc.DataSourceJasperTF(id, iddet, 0,0);
						if(Tar != null)
							model.addAttribute("tar", gson.fromJson(Tar.toString(),Object.class));
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "/tarjetas/cotizador/seguimiento_cot_hist";
	}
	
	@RequestMapping(value = "/vendedor/imprimirtarjetacte", method = RequestMethod.GET)
    @ResponseBody
    public void getRpt1(HttpServletResponse response,HttpServletRequest request,ModelMap model,
    				    @RequestParam("id") Integer id, @RequestParam("iddet") Integer iddet) throws JRException, IOException {
		String msj = "";
		try
		{
			InputStream jasperStream = this.getClass().getResourceAsStream("/jasperreports/cotizador/Tarjeta_fabricacionCTE.jasper");
			Map<String,Object> params = new HashMap<>();
			
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
			ByteArrayInputStream jsonDataStream = new ByteArrayInputStream(stripAccents(ctsc.DataSourceJasperTF(id, iddet, 1, 0).toString()).getBytes("UTF-8"));
			JsonDataSource dataSource = new JsonDataSource(jsonDataStream);
			params.put("Imagen",request.getServletContext().getRealPath("/"));
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
			
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "inline");
			
			    final OutputStream outStream = response.getOutputStream();
			    JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
			logger.info(AppController.getPrincipal() + " - imprimirtarjetacte :"+ msj);
		}
		catch(Exception e)
		{
			msj = e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage();
			e.printStackTrace();
			logger.info(AppController.getPrincipal() + " - imprimirtarjetacte :"+ e);
		}
	} 
	
}

