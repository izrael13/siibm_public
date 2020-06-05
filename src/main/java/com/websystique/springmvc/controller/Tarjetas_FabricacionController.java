package com.websystique.springmvc.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
//import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.tarjetas.Catalogo_cajas_sap_vw;
import com.websystique.springmvc.model.tarjetas.Catalogo_maquinas_sap_vw;
import com.websystique.springmvc.model.tarjetas.cotizador.Cotizador;
import com.websystique.springmvc.model.tarjetas.cotizador.Cotizador_detalles;
import com.websystique.springmvc.model.tarjetas.cotizador.Cotizador_detallesValidator;
import com.websystique.springmvc.model.tarjetas.fabricacion.TarjetaDataBean;
import com.websystique.springmvc.model.tarjetas.fabricacion.Tarjeta_fabricacion;
import com.websystique.springmvc.model.tarjetas.fabricacion.Tarjeta_fabricacionValidator;
import com.websystique.springmvc.model.tarjetas.fabricacion.Tarjetas_fabricacion_imagenes;
import com.websystique.springmvc.service.UserService;
import com.websystique.springmvc.service.tarjetas.Catalogo_cajas_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Catalogo_clientes_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Catalogo_coloresService;
import com.websystique.springmvc.service.tarjetas.Catalogo_especialidades_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Catalogo_herramentalesService;
import com.websystique.springmvc.service.tarjetas.Catalogo_maquinas_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Catalogo_resistencias_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Catalogo_sellosService;
import com.websystique.springmvc.service.tarjetas.Codigo_barras_cotizadorService;
import com.websystique.springmvc.service.tarjetas.Especialidades_cotizacionService;
import com.websystique.springmvc.service.tarjetas.commons.CotizadorTarjetasService;
import com.websystique.springmvc.service.tarjetas.cotizador.CotizadorService;
import com.websystique.springmvc.service.tarjetas.cotizador.Cotizador_detallesService;
import com.websystique.springmvc.service.tarjetas.fabricacion.Tarjeta_fabricacionService;
import com.websystique.springmvc.service.tarjetas.fabricacion.Tarjetas_fabricacion_imagenesService;
import com.websystique.springmvc.utilities.SendMailGmail;

import _1._0._0._127.SAP.AddObjectResponseAddObjectResult;
import _1._0._0._127.SAP.DIServerSoapProxy;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
@PropertySource(value = { "classpath:diserver.properties"})
@RequestMapping("/tarjeta")
public class Tarjetas_FabricacionController {
	private Logger logger = Logger.getLogger(Tarjetas_FabricacionController.class);
	
	@Autowired
	Tarjeta_fabricacionService tfs;
	@Autowired
	Tarjetas_fabricacion_imagenesService tfis;
	@Autowired
	UserService us;
	@Autowired
	CotizadorService cs;
	@Autowired
	Catalogo_clientes_sap_vwService ccavs;
	@Autowired
	Cotizador_detallesService cds;
	@Autowired
	Catalogo_especialidades_sap_vwService ces;
	@Autowired
	Especialidades_cotizacionService ecs;
	@Autowired
	Codigo_barras_cotizadorService cbsc;
	@Autowired
	Tarjeta_fabricacionValidator tfv;
	@Autowired
	Cotizador_detallesValidator cdv;
	@Autowired
	Catalogo_herramentalesService chs;
	@Autowired
	Catalogo_coloresService ccos;
	@Autowired
	Catalogo_cajas_sap_vwService ccss;
	@Autowired
	Catalogo_resistencias_sap_vwService crss;
	@Autowired
	Catalogo_sellosService css;
	@Autowired
	Catalogo_maquinas_sap_vwService cms;
	@Autowired
	CotizadorTarjetasService ctsc;
	@Autowired
    Environment environment;
	
	@RequestMapping(value = {"/ingenieria/tarjeta_fabricacion" }, method = RequestMethod.GET)
	public String tarjeta_fabricacion(ModelMap model, @RequestParam(value = "folio", defaultValue = "", required = false) String folio) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		logger.info(AppController.getPrincipal() + " - tarjeta_fabricacion.");
		User user = us.findBySSO(AppController.getPrincipal());
		Tarjeta_fabricacion tf = new Tarjeta_fabricacion();
		Cotizador cot = new Cotizador();
		Cotizador_detalles cotdet = new Cotizador_detalles();
		Catalogo_cajas_sap_vw objCaja = new Catalogo_cajas_sap_vw();
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().create();
		List<JSONObject> ListaEsp = new ArrayList<JSONObject>();
		
		TarjetaDataBean tdb = new TarjetaDataBean();
		
		if(!folio.trim().equals(""))
		{
			tf = tfs.BuscarxFolio(folio, user.getId());
			if(tf != null)
			{	
				tf.setTarjeta_img(tfis.BuscarxIdCotidDert(tf.getIdcotizacion(), tf.getIddetalle()));
				
				cot = cs.BuscarxId(tf.getIdcotizacion());
				cotdet = cds.BuscarxIdDet(tf.getIdcotizacion(), tf.getIddetalle());
				cotdet.setCodigo_barra_cotizador(cbsc.BuscarXCotDet(Integer.valueOf(cotdet.getIdcotizacion()), Integer.valueOf(cotdet.getIddetalle())));
				tdb.setTarjeta_fabricacion(tf);
				tdb.setCotizador(cot);
				tdb.setCotizador_detalles(cotdet);
	
				objCaja = ccss.BuscarxId(cotdet.getIdcaja_sap());
				model.addAttribute("caja", objCaja);
				model.addAttribute("resis", crss.BuscarxId(cotdet.getIdresistencia_barca()));
				model.addAttribute("sello", css.BuscarxId(cotdet.getResistencia_cte()));
				model.addAttribute("cliente_factura", cot.getCardcode_factura() != null ? ccavs.cat_cte_sap(cot.getCardcode_factura()).getCardname() : "");
				ListaEsp = ctsc.addEspecialidades(tf.getIdcotizacion(), tf.getIddetalle());
				model.addAttribute("esp", gson.fromJson(ListaEsp.toString(),Object[].class));
				//ctsc.DataSourceJasperTF(cot.getId(), cotdet.getIddetalle(), 1);
				/*try {
					SAP_UPDATE_OITM(tdb.getTarjeta_fabricacion().getIdcotizacion(),tdb.getTarjeta_fabricacion().getIddetalle(),tdb.getTarjeta_fabricacion().getFolio_tarjeta());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} */
			}
		}
		
		model.addAttribute("tdb", tdb);
		model.addAttribute("grabados", chs.BuscarxTipo(2));
		model.addAttribute("suajes", chs.BuscarxTipo(1));		
		model.addAttribute("colores", ccos.ListaColores());		
		model.addAttribute("maquinas", tf != null ? BuscarMaquinas(ListaEsp,objCaja.getGrupo(),cotdet.getNum_tintas(),cotdet.getCierre(),tf.getFolio_tarjeta()) : new ArrayList<Catalogo_maquinas_sap_vw>());
		model.addAttribute("clientes", ccavs.ListaCtes());
				
		return "/tarjetas/fabricacion/tarjeta_fabricacion";
	}
	
	@RequestMapping(value = {"/ingenieria/tarjeta_fabricacion_buscar" }, method = RequestMethod.GET)
	public String tarjeta_fabricacion_buscar(ModelMap model,@RequestParam("idcot") Integer idcot,@RequestParam("foliotf") String foliotf,@RequestParam("cardcode") String cardcode) {
		List<Tarjeta_fabricacion> Lista = new ArrayList<Tarjeta_fabricacion>();
		User user = us.findBySSO(AppController.getPrincipal());
		Lista = tfs.TarjetaBusqueda(idcot, foliotf,cardcode,user.getId());
		model.addAttribute("lista", Lista);
		
		logger.info(AppController.getPrincipal() + " - tarjeta_fabricacion_buscar.");
				
		return "/tarjetas/fabricacion/tarjeta_fabricacion_buscar";
	}
	
	@RequestMapping(value = {"/ingenieria/calculardatos" }, method = RequestMethod.POST)
	@ResponseBody
	public String calculardatos(ModelMap model, @RequestParam("idcotizacion") Integer idcotizacion,@RequestParam("iddetalle") Integer iddetalle, 
			@RequestParam("folio_tarjeta") String folio_tarjeta, @RequestParam("npartes") Integer npartes,
			@RequestParam("pzasxlargo") Integer pzasxlargo, @RequestParam("pzasxancho") Integer pzasxancho) {
		
		return CalcularDatos(idcotizacion,iddetalle,folio_tarjeta,npartes,pzasxlargo,pzasxancho);
	}
	
	private String CalcularDatos(Integer idcotizacion, Integer iddetalle, String folio_tarjeta,Integer npartes,Integer pzasxlargo,Integer pzasxancho)
	{
		try 
		{
			DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.getDefault());
			formatSymbols.setDecimalSeparator('.');
			DecimalFormat decimal4 = new DecimalFormat("###########0.####", formatSymbols);
			
			JsonObject object = new JsonObject();//Objeto JSon
			Cotizador_detalles cotdet = cds.BuscarxIdDet(idcotizacion, iddetalle);
			//Double LPliego = cotdet.getLargo_pliego() / npartes;
			//String medlamina =  LPliego.toString() + " x " + cotdet.getAncho_pliego().toString();
			//object.addProperty("medlamina", medlamina);
			
			Double LargoPliego = 0.0;
			Double AnchoPliego = 0.0;
			
			String medPliego =  "";
			
			Catalogo_cajas_sap_vw caja = ccss.BuscarxId(cotdet.getIdcaja_sap());
			Double area_total = 0.0;
			if(caja.getGrupo() == 3)
			{				 
				area_total = npartes == 1 ? ((cotdet.getLargo() * pzasxlargo +  2) * (cotdet.getAncho() * pzasxancho + 2)) / 10000 : 
					((cotdet.getLargo_pliego() * pzasxlargo /  2) * (cotdet.getAncho() * pzasxancho + 2)) / 10000;
				
				LargoPliego = (npartes == 1 ?  (cotdet.getLargo() * pzasxlargo) + 2 : (cotdet.getLargo_pliego() * pzasxlargo) / 2);
				AnchoPliego = cotdet.getAncho() * pzasxancho + 2;
				
			}	
			else
			{
				area_total = npartes == 1 ? ((cotdet.getLargo_pliego() * pzasxlargo) * (cotdet.getAncho_pliego() * pzasxancho)) / 10000 :
					((cotdet.getLargo_pliego() * pzasxlargo / npartes) * (cotdet.getAncho_pliego() * pzasxancho)) / 10000;
				
				LargoPliego = (npartes == 1 ? cotdet.getLargo_pliego() * pzasxlargo : (cotdet.getLargo_pliego() * pzasxlargo) / 2 ); 
				AnchoPliego = cotdet.getAncho_pliego() * pzasxancho;
				
			}
			medPliego =  decimal4.format(LargoPliego) + " X " +  decimal4.format(AnchoPliego);
			object.addProperty("area_total", decimal4.format(area_total));
			object.addProperty("med_pliego", medPliego);
			
			return object.toString();
		}
		catch(Exception e)
		{
			return e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage();
		}
	}
	
	@RequestMapping(value = {"/ingenieria/tarjeta_fabricacion" }, method = RequestMethod.POST)
	public String tarjeta_fabricacionpost(@ModelAttribute("tdb") TarjetaDataBean tdb, BindingResult result, ModelMap model) {
		try 
		{
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.serializeNulls().create();
			Catalogo_cajas_sap_vw objCaja = new Catalogo_cajas_sap_vw();
			List<JSONObject> ListaEsp = new ArrayList<JSONObject>();
			tdb.getTarjeta_fabricacion().setBan(1);
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			tdb.getTarjeta_fabricacion().setTarjeta_img(tfis.BuscarxIdCotidDert(tdb.getTarjeta_fabricacion().getIdcotizacion(), tdb.getTarjeta_fabricacion().getIddetalle()));
			model.addAttribute("clientes", ccavs.ListaCtes());
			model.addAttribute("grabados", chs.BuscarxTipo(2));
			model.addAttribute("suajes", chs.BuscarxTipo(1));
			ListaEsp = ctsc.addEspecialidades(tdb.getTarjeta_fabricacion().getIdcotizacion(), tdb.getTarjeta_fabricacion().getIddetalle());
			model.addAttribute("esp", gson.fromJson(ListaEsp.toString(),Object[].class));
			model.addAttribute("colores", ccos.ListaColores());
			Cotizador cot = new Cotizador();
			Cotizador_detalles cotdet = new Cotizador_detalles();
			cot = cs.BuscarxId(tdb.getTarjeta_fabricacion().getIdcotizacion());
			cotdet = cds.BuscarxIdDet(tdb.getTarjeta_fabricacion().getIdcotizacion(), tdb.getTarjeta_fabricacion().getIddetalle());
			cot.setTarimaxunitizado(tdb.getCotizador().getTarimaxunitizado());
			cotdet.setAltura_pallet(tdb.getCotizador_detalles().getAltura_pallet());
			cotdet.setCamas_pallet(tdb.getCotizador_detalles().getCamas_pallet());
			cotdet.setFlejes_pallet(tdb.getCotizador_detalles().getFlejes_pallet());
			cotdet.setFlejes_atado(tdb.getCotizador_detalles().getFlejes_atado());
			cotdet.setPzas_atado(tdb.getCotizador_detalles().getPzas_atado());
			cotdet.setAtados_cama(tdb.getCotizador_detalles().getAtados_cama());
			cotdet.setIdtiporequerimiento(0);
			
			if((tdb.getCotizador_detalles().getPiezasxtarima() != null) && (tdb.getCotizador_detalles().getPiezasxtarima() < cotdet.getPiezasxtarima())) {
				cotdet.setBan(1);
			}

			cotdet.setPiezasxtarima(tdb.getCotizador_detalles().getPiezasxtarima());
			tdb.setCotizador(cot);
			tdb.setCotizador_detalles(cotdet);
			model.addAttribute("tdb", tdb);
			objCaja = ccss.BuscarxId(cotdet.getIdcaja_sap());
			model.addAttribute("caja", objCaja);
			model.addAttribute("resis", crss.BuscarxId(cotdet.getIdresistencia_barca()));
			model.addAttribute("sello", css.BuscarxId(cotdet.getResistencia_cte()));
			model.addAttribute("maquinas", BuscarMaquinas(ListaEsp,objCaja.getGrupo(),cotdet.getNum_tintas(),cotdet.getCierre(),tdb.getTarjeta_fabricacion().getFolio_tarjeta()));
						
			tfv.validate(tdb.getTarjeta_fabricacion(), result);
			cdv.validate(cotdet, result);
			
			/*for (ObjectError error : result.getAllErrors()) {
			       String fieldErrors [] = error.getCodes();
			       System.out.println(fieldErrors[0]);
			   }*/
			
			if (result.hasErrors())
			{
				return "/tarjetas/fabricacion/tarjeta_fabricacion";
			}
			
			tfs.Actualizar(tdb.getTarjeta_fabricacion());
			cs.Actualizar(cot);
			cds.Actualizar(cotdet);
			
			logger.info(AppController.getPrincipal() + " - tarjeta_fabricacionpost.");
			model.addAttribute("mensajes", "Tarjeta actualizada correctamente.");
			return "/tarjetas/fabricacion/tarjeta_fabricacion";
		}
		catch(Exception e)
		{
			//model.addAttribute("mensajes", e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage());
			e.printStackTrace();
			model.addAttribute("error", e);
			logger.info(AppController.getPrincipal() + " - tarjeta_fabricacionpost. " + e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage());
			return "/tarjetas/fabricacion/tarjeta_fabricacion";
		}
		
	}
	
	private List<Catalogo_maquinas_sap_vw> BuscarMaquinas(List<JSONObject> ListaEsp, Integer Grupo, Integer NumTintas, String cierre, String Folio) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
	{
		List<Catalogo_maquinas_sap_vw> Lista = new ArrayList<Catalogo_maquinas_sap_vw>();
		if(Folio != null)
		{			
			Lista = cms.ListaMaquinas(0);
			Lista.addAll(cms.ListaMaquinas(Grupo,NumTintas));
			
			Long bgra = ListaEsp.stream().filter(a -> Integer.valueOf(a.get("iddespecialidad").toString())  == 8).count();

			if(cierre.equals("grapada") && bgra.intValue() == 0)
				Lista.addAll(cms.ListaMaquinas(true));
		}
		return Lista;
	}
	
	@RequestMapping(value = {"/ingenieria/subir_imagen_tarjeta" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> subir_imagen_tarjeta(@RequestParam("file")  MultipartFile[] file, @RequestParam("folio_tarjeta") String folio_tarjeta,
			@RequestParam("idcotizacion") Integer idcotizacion,@RequestParam("iddetalle") Integer iddetalle, @RequestParam("cama") Boolean cama,
			@RequestParam("principal") Boolean principal, ModelMap model,HttpServletResponse response,HttpServletRequest request) {
			
			return SubirImagen(file, request,idcotizacion,iddetalle,cama,principal);
	}
	
	private ResponseEntity<Object> SubirImagen( MultipartFile[] file,HttpServletRequest request,Integer idcotizacion,Integer iddetalle,Boolean cama, Boolean principal)
	{
		String mensaje = "";
		try 
		{
			MultipartFile img =  file[0];
			String fileName = img.getOriginalFilename();
		    String folderPath = request.getServletContext().getRealPath("/")+"static\\img_tarjetas\\";
		    String filePath = folderPath + fileName;
		    System.out.println(filePath);
		    User user = us.findBySSO(AppController.getPrincipal());
		    byte[] bytes = img.getBytes();
		    Path path = Paths.get(filePath);
            Files.write(path, bytes);
            java.util.Date date = new java.util.Date();
            List<Tarjetas_fabricacion_imagenes> ListimgTar = tfis.BuscarxIdCotidDert(idcotizacion, iddetalle);

            if(cama)
            {            	
            	ListimgTar.stream().filter(a -> a.getCama() == true).forEach((p) -> {
            		p.setCama(false);
            		p.setUsuario_insert(user.getId());
            		p.setFecha_insert(date);
            		tfis.Actualizar(p);
            	});
            }
            
            if(principal)
            {            	
            	ListimgTar.stream().filter(a -> a.getPrincipal() == true).forEach((p) -> {
            		p.setPrincipal(false);
            		p.setUsuario_insert(user.getId());
            		p.setFecha_insert(date);
            		tfis.Actualizar(p);
            	});
            }
            
            if(ListimgTar.stream().filter(a -> a.getNombre().equals(fileName)).count() == 0)
            {
	            
	            Tarjetas_fabricacion_imagenes imgTar = new Tarjetas_fabricacion_imagenes();
	            imgTar.setIdcotizacion(idcotizacion);
	            imgTar.setIddetalle(iddetalle);
	            imgTar.setNombre(fileName);
	            imgTar.setPath(folderPath);
	            imgTar.setUsuario_insert(user.getId());
	            imgTar.setFecha_insert(date);
	            imgTar.setCama(cama);
	            imgTar.setPrincipal(principal);
	            tfis.Guardar(imgTar);
            }
            else
            {
            	ListimgTar.stream().filter(a -> a.getNombre().equals(fileName)).forEach((p) ->
            		{
            			p.setCama(cama);
            			p.setPrincipal(principal);
            			p.setFecha_insert(date);
            			p.setUsuario_insert(user.getId());
            			tfis.Actualizar(p);
            		}
            		
            	); 
            }
            ListimgTar.clear();
            ListimgTar = tfis.BuscarxIdCotidDert(idcotizacion, iddetalle);
            Gson  g = new Gson();
            logger.info(AppController.getPrincipal() + " - subir_imagen_tarjeta. " + mensaje);
			return new ResponseEntity<Object>(g.toJson(ListimgTar), HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			mensaje = e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage();
			logger.info(AppController.getPrincipal() + " - subir_imagen_tarjeta. " + mensaje);
			return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = {"/ingenieria/borrar_imagen_tarjeta" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> borrar_imagen_tarjeta(ModelMap model,
			@RequestParam("idcotizacion") Integer idcotizacion,@RequestParam("iddetalle") Integer iddetalle, @RequestParam("nombre") String nombre) {
			return BorrarImagen(idcotizacion,iddetalle,nombre);
	}
	
	private ResponseEntity<Object> BorrarImagen(Integer idcotizacion, Integer iddetalle, String nombre)
	{
		String mensaje = "";
		try 
		{
			tfis.BuscarxId(idcotizacion, iddetalle, nombre).stream().forEach(a -> {
				if(DeleteFileImg(a.getPath(),a.getNombre()))
					tfis.Borrar(a);
			});
			
			
			
			List<Tarjetas_fabricacion_imagenes> ListimgTar = tfis.BuscarxIdCotidDert(idcotizacion, iddetalle);
            Gson  g = new Gson();
            logger.info(AppController.getPrincipal() + " - borrar_imagen_tarjeta. " + mensaje);
			return new ResponseEntity<Object>(g.toJson(ListimgTar), HttpStatus.OK);
		}
		catch(Exception e)
		{
			mensaje = e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage();
			logger.info(AppController.getPrincipal() + " - borrar_imagen_tarjeta. " + mensaje);
			return new ResponseEntity<Object>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private Boolean DeleteFileImg(String path, String name)
	{
		try 
		{
			String fullpath = path+name;
			File file = new File(fullpath);
			if(file.exists())
				file.delete();
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
		
	}
	
	@RequestMapping(value = {"/ingenieria/enviar_tarjeta_aut" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> enviar_tarjeta_aut(ModelMap model,
			@RequestParam("idcotizacion") Integer idcotizacion,@RequestParam("iddetalle") Integer iddetalle, @RequestParam("folio_tarjeta") String folio_tarjeta) {
		String mensaje = "";
		try 
		{
			Tarjeta_fabricacion tf = new Tarjeta_fabricacion();
			java.util.Date date = new java.util.Date();
			User user = us.findBySSO(AppController.getPrincipal());
			
			tf = tfs.BuscarxFolio(folio_tarjeta, user.getId());
			tf.setFecha_aut_diseniador(date);
			tf.setUsuario_aut_diseniador(user.getId());
			
			tf.setUsuario_rech_calidad(null);
			tf.setFecha_rech_calidad(null);
			tf.setUsuario_rech_produccion(null);
			tf.setFecha_rech_produccion(null);
			tf.setUsuario_recha_ing(null);
			tf.setFecha_rech_ing(null);
			tf.setUsuario_rech_cliente(null);
			tf.setFecha_rech_cliente(null);
			
			tfs.Actualizar(tf);
			logger.info(AppController.getPrincipal() + " - enviar_tarjeta_aut. " + mensaje);
			return new ResponseEntity<Object>("OK", HttpStatus.OK);
		}
		catch(Exception e)
		{
			mensaje = e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage();
			model.addAttribute("mensajes", mensaje);
			logger.info(AppController.getPrincipal() + " - enviar_tarjeta_aut. " + mensaje);
			return new ResponseEntity<Object>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	
	@RequestMapping(value = {"/ingenieria/cancelar_tarjetas" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> cancelar_tarjetas(ModelMap model,
			@RequestParam("idcotizacion") Integer idcotizacion,@RequestParam("iddetalle") Integer iddetalle, 
			@RequestParam("folio_tarjeta") String folio_tarjeta, @RequestParam("coment_diseniador") String coment_diseniador) {
		String mensaje = "";
		User user = us.findBySSO(AppController.getPrincipal());
		java.util.Date date = new java.util.Date();
		try 
		{
			List<Tarjeta_fabricacion> ListaTar = tfs.BuscarXIdCot(idcotizacion, user.getId());
			ListaTar.stream().forEach(a -> {
				a.setUsuario_cancela(user.getId());
				a.setFecha_cancela(date);
				a.setObservaciones_disenador(coment_diseniador);
				tfs.Actualizar(a);
				
				tfis.BuscarxIdCotidDert(a.getIdcotizacion(), a.getIddetalle()).stream().forEach(b -> {
					/*String fullpath = b.getPath()+b.getNombre();
					File file = new File(fullpath);
					if(file.exists())
						file.delete();*/
					if(DeleteFileImg(b.getPath(),b.getNombre()))
						tfis.Borrar(b);
				});
			});
			
			Cotizador cot = new Cotizador();
			cot = cs.BuscarxId(idcotizacion);
			cot.setUsuario_cancel(user.getId());
			cot.setFecha_cancel(date);
			cs.Actualizar(cot);
			
			////ENVÍO DE EMAIL
			String emailMsj="Cotización: ("+cot.getId()+") cancelada (DISEÑADOR - INGENIERÍA) por el usuario: "+user.getFirstName()+ " " +user.getLastName()+ " (" + user.getSsoId() + ") \r\n\n Contacto: "+user.getEmail()+" \r\n\n Motivo: "+coment_diseniador ;
			String asunto = "Cotización cancelada";			
			SendMailGmail Email = new SendMailGmail();
			User userInsertCot = us.findById(cot.getUsuario_insert());
			Email.sendMail(userInsertCot.getEmail(), emailMsj, asunto);
			/////
			
			logger.info(AppController.getPrincipal() + " - cancelar_tarjetas. " + mensaje);
			return new ResponseEntity<Object>("OK", HttpStatus.OK);
		}
		catch(Exception e)
		{
			mensaje = e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage();
			model.addAttribute("mensajes", mensaje);
			logger.info(AppController.getPrincipal() + " - cancelar_tarjetas. " + mensaje);
			return new ResponseEntity<Object>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	
	@RequestMapping(value = {"/calidad/tarjeta_aut_calidad" }, method = RequestMethod.GET)
	public String tarjeta_aut_calidad(ModelMap model) {

		model.addAttribute("loggedinuser", AppController.getPrincipal());
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"usuario_aut_diseniador","NE"));
		Params.add(new ParamsGeneral(2,"fecha_aut_diseniador","NE"));
		Params.add(new ParamsGeneral(3,"usuario_aut_calidad","EQ"));
		Params.add(new ParamsGeneral(4,"fecha_aut_calidad","EQ"));
		Params.add(new ParamsGeneral(5,"usuario_cancela","EQ"));
		Params.add(new ParamsGeneral(6,"fecha_cancela","EQ"));
		model.addAttribute("tarjetas",tfs.BuscarXAut(Params));
		logger.info(AppController.getPrincipal() + " - tarjeta_aut_calidad.");
				
		return "/tarjetas/fabricacion/tarjeta_aut_calidad";
	}
	
	@RequestMapping(value = {"/calidad/tarjeta_aut_calidad_desicion" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> tarjeta_aut_calidad(ModelMap model,
			@RequestParam("folio") String folio,@RequestParam("coment") String coment, @RequestParam("ban") Integer ban) {
		String mensaje = "";
		try 
		{
			Tarjeta_fabricacion tf = new Tarjeta_fabricacion();
			java.util.Date date = new java.util.Date();
			User user = us.findBySSO(AppController.getPrincipal());
			tf = tfs.BuscarxFolio(folio);
			
			if(ban == 1) {
				tf.setUsuario_aut_calidad(user.getId());
				tf.setFecha_aut_calidad(date);
			}
			else {
				tf.setUsuario_rech_calidad(user.getId());
				tf.setFecha_rech_calidad(date);
				tf.setUsuario_aut_diseniador(null);
				tf.setFecha_aut_diseniador(null);
			}
				
			tf.setObservaciones_calidad(coment);
			tfs.Actualizar(tf);
			logger.info(AppController.getPrincipal() + " - tarjeta_aut_calidad_desicion. " + mensaje);
			return new ResponseEntity<Object>("OK", HttpStatus.OK);
		}
		catch(Exception e)
		{
			mensaje = e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage();
			model.addAttribute("mensajes", mensaje);
			logger.info(AppController.getPrincipal() + " - tarjeta_aut_calidad_desicion. " + mensaje);
			return new ResponseEntity<Object>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	
	@RequestMapping(value = {"/produccion/tarjeta_aut_produccion" }, method = RequestMethod.GET)
	public String tarjeta_aut_produccion(ModelMap model) {

		model.addAttribute("loggedinuser", AppController.getPrincipal());
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"usuario_aut_calidad","NE"));
		Params.add(new ParamsGeneral(2,"fecha_aut_calidad","NE"));
		Params.add(new ParamsGeneral(3,"usuario_aut_produccion","EQ"));
		Params.add(new ParamsGeneral(4,"fecha_aut_produccion","EQ"));
		Params.add(new ParamsGeneral(5,"usuario_cancela","EQ"));
		Params.add(new ParamsGeneral(6,"fecha_cancela","EQ"));
		model.addAttribute("tarjetas",tfs.BuscarXAut(Params));
		logger.info(AppController.getPrincipal() + " - tarjeta_aut_produccion.");
				
		return "/tarjetas/fabricacion/tarjeta_aut_produccion";
	}
		
	@RequestMapping(value = {"/produccion/tarjeta_aut_produccion_desicion" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> tarjeta_aut_produccion(ModelMap model,
			@RequestParam("folio") String folio,@RequestParam("coment") String coment, @RequestParam("ban") Integer ban) {
		String mensaje = "";
		try 
		{
			Tarjeta_fabricacion tf = new Tarjeta_fabricacion();
			java.util.Date date = new java.util.Date();
			User user = us.findBySSO(AppController.getPrincipal());
			tf = tfs.BuscarxFolio(folio);
			
			if(ban == 1) {
				tf.setUsuario_aut_produccion(user.getId());
				tf.setFecha_aut_produccion(date);
			}
			else {
				tf.setUsuario_rech_produccion(user.getId());
				tf.setFecha_rech_produccion(date);
				tf.setUsuario_aut_calidad(null);
				tf.setFecha_aut_calidad(null);
				tf.setUsuario_aut_diseniador(null);
				tf.setFecha_aut_diseniador(null);
			}
				
			tf.setObservaciones_produccion(coment);
			tfs.Actualizar(tf);
			logger.info(AppController.getPrincipal() + " - tarjeta_aut_produccion_desicion. " + mensaje);
			return new ResponseEntity<Object>("OK", HttpStatus.OK);
		}
		catch(Exception e)
		{
			mensaje = e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage();
			model.addAttribute("mensajes", mensaje);
			logger.info(AppController.getPrincipal() + " - tarjeta_aut_produccion_desicion. " + mensaje);
			return new ResponseEntity<Object>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}	
	
	@RequestMapping(value = {"/ingenieria_gerencia/tarjeta_aut_ingenieria" }, method = RequestMethod.GET)
	public String tarjeta_aut_ingenieria(ModelMap model) {

		model.addAttribute("loggedinuser", AppController.getPrincipal());
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"usuario_aut_produccion","NE"));
		Params.add(new ParamsGeneral(2,"fecha_aut_produccion","NE"));
		Params.add(new ParamsGeneral(3,"usuario_aut_ing","EQ"));
		Params.add(new ParamsGeneral(4,"fecha_aut_ing","EQ"));
		Params.add(new ParamsGeneral(5,"usuario_cancela","EQ"));
		Params.add(new ParamsGeneral(6,"fecha_cancela","EQ"));
		model.addAttribute("tarjetas",tfs.BuscarXAut(Params));
		logger.info(AppController.getPrincipal() + " - tarjeta_aut_ingenieria.");
				
		return "/tarjetas/fabricacion/tarjeta_aut_ingenieria";
	}
	
	@RequestMapping(value = {"/ingenieria_gerencia/tarjeta_aut_ingenieria_desicion" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> tarjeta_aut_ingenieria(ModelMap model,
			@RequestParam("folio") String folio,@RequestParam("coment") String coment, @RequestParam("ban") Integer ban) {
		String mensaje = "";
		try 
		{
			Tarjeta_fabricacion tf = new Tarjeta_fabricacion();
			java.util.Date date = new java.util.Date();
			User user = us.findBySSO(AppController.getPrincipal());
			tf = tfs.BuscarxFolio(folio);
			
			if(ban == 1) {
				tf.setUsuario_aut_ing(user.getId());
				tf.setFecha_aut_ing(date);
			}
			else {
				tf.setUsuario_recha_ing(user.getId());
				tf.setFecha_rech_ing(date);
				tf.setUsuario_aut_calidad(null);
				tf.setFecha_aut_calidad(null);
				tf.setUsuario_aut_diseniador(null);
				tf.setFecha_aut_diseniador(null);
				tf.setUsuario_aut_produccion(null);
				tf.setFecha_aut_produccion(null);
			}
				
			tf.setObservaciones_ing(coment);
			tfs.Actualizar(tf);
			logger.info(AppController.getPrincipal() + " - tarjeta_aut_produccion_desicion. " + mensaje);
			return new ResponseEntity<Object>("OK", HttpStatus.OK);
		}
		catch(Exception e)
		{
			mensaje = e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage();
			model.addAttribute("mensajes", mensaje);
			logger.info(AppController.getPrincipal() + " - tarjeta_aut_produccion_desicion. " + mensaje);
			return new ResponseEntity<Object>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	
	@RequestMapping(value = {"/cliente/tarjeta_aut_cliente" }, method = RequestMethod.GET)
	public String tarjeta_aut_cliente(ModelMap model) {

		model.addAttribute("loggedinuser", AppController.getPrincipal());
		//User user = us.findBySSO(AppController.getPrincipal());
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"usuario_aut_ing","NE"));
		Params.add(new ParamsGeneral(2,"fecha_aut_ing","NE"));
		Params.add(new ParamsGeneral(3,"usuario_aut_cliente","EQ"));
		Params.add(new ParamsGeneral(4,"fecha_aut_cliente","EQ"));
		Params.add(new ParamsGeneral(5,"usuario_cancela","EQ"));
		Params.add(new ParamsGeneral(6,"fecha_cancela","EQ"));
		//Params.add(new ParamsGeneral(7,"cardcode",user.getCardcode_sap()));
		List<Tarjeta_fabricacion> Lista = tfs.BuscarXAut(Params);
		Lista.stream().forEach(tar -> {
			if(tfs.BuscarXIdCot(tar.getIdcotizacion(), 0).stream().filter(a -> a.getUsuario_aut_ing() == null && a.getFecha_aut_ing() == null).count() > 0)
				Lista.remove(tar);
		});
		model.addAttribute("tarjetas",Lista);
		logger.info(AppController.getPrincipal() + " - tarjeta_aut_cliente.");
				
		return "/tarjetas/fabricacion/tarjeta_aut_cliente";
	}
	
	@RequestMapping(value = {"/cliente/tarjeta_aut_cliente_desicion" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> tarjeta_aut_cliente(ModelMap model,
			@RequestParam("folio") String folio,@RequestParam("coment") String coment, @RequestParam("ban") Integer ban) {
		try 
		{
			logger.info(AppController.getPrincipal() + " - tarjeta_aut_cliente_desicion. ");
			Tarjeta_fabricacion tf = new Tarjeta_fabricacion();
			java.util.Date date = new java.util.Date();
			User user = us.findBySSO(AppController.getPrincipal());
			tf = tfs.BuscarxFolio(folio);
			tf.setObservaciones_ing(coment);
			
			if(ban == 1) 
			{
				String MSJSAP=SAP_INSERT_OITM(tf.getIdcotizacion(),tf.getIddetalle(),folio);
				if(MSJSAP == "OK")
				{
					tf.setUsuario_aut_cliente(user.getId());
					tf.setFecha_aut_cliente(date);
					tfs.Actualizar(tf);
					return new ResponseEntity<Object>("OK", HttpStatus.OK);
				}
				else
				{
					return new ResponseEntity<Object>(MSJSAP, HttpStatus.BAD_REQUEST);
				}
			}
			else 
			{
				tf.setUsuario_rech_cliente(user.getId());
				tf.setFecha_rech_cliente(date);
				tf.setUsuario_aut_ing(null);
				tf.setFecha_aut_ing(null);
				tf.setUsuario_aut_calidad(null);
				tf.setFecha_aut_calidad(null);
				tf.setUsuario_aut_diseniador(null);
				tf.setFecha_aut_diseniador(null);
				tf.setUsuario_aut_produccion(null);
				tf.setFecha_aut_produccion(null);
				tfs.Actualizar(tf);
				return new ResponseEntity<Object>("OK", HttpStatus.OK);
			}
		
		}
		catch(Exception e)
		{
			model.addAttribute("mensajes", e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage());
			logger.info(AppController.getPrincipal() + " - tarjeta_aut_cliente_desicion. ", e);
			return new ResponseEntity<Object>(e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	
	@RequestMapping(value = {"/ingenieria/tarjetas_seguimiento" }, method = RequestMethod.GET)
	public String tarjetas_seguimiento(ModelMap model, @RequestParam(value = "FolioTF", defaultValue = "", required = false) String folio,
													  @RequestParam(value = "Cot", defaultValue = "0", required = false) Integer idCot,
													  @RequestParam(value = "Status", defaultValue = "0", required = false) Integer status,
													  @RequestParam(value = "CardCode", defaultValue = "", required = false) String cardcode) {

		model.addAttribute("loggedinuser", AppController.getPrincipal());
		User user = us.findBySSO(AppController.getPrincipal());	
		Integer usrinsert = 0;
		if(user.getUserProfiles().stream()
				.filter(b -> b.getType().equals("VENTAS") ||
							 b.getType().equals("INGENIERIA") ||
							 b.getType().equals("ADMIN") ||
							 b.getType().equals("INGENIERIA_GERENCIA")
				).count() == 0)
		{
			model.addAttribute("clientes", ccavs.ListaCtes(user.getCvevendedor_sap()));
			usrinsert = user.getId();
		}
		else
			model.addAttribute("clientes", ccavs.ListaCtes());
		
		if(!folio.equals("") || idCot > 0 || status > 0 || !cardcode.equals(""))
		{
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.serializeNulls().create();
			List<JSONObject> lista = new ArrayList<JSONObject>();
			tfs.ListaSeguimiento(folio,idCot,status,cardcode,usrinsert).forEach(a -> {
				lista.add(ctsc.DataSourceJasperTF(a, 1, 0));
			});
			model.addAttribute("lista", gson.fromJson(lista.toString(),Object[].class));
		}
		logger.info(AppController.getPrincipal() + " - tarjetas_seguimiento.");
				
		return "/tarjetas/fabricacion/tarjetas_seguimiento";
	}
	
	@RequestMapping(value = {"/ingenieria/tarjeta_seguimiento_info" }, method = RequestMethod.GET)
	public String tarjeta_seguimiento_info(ModelMap model, 
										   @RequestParam(value = "id", required = true) Integer id,
										   @RequestParam(value = "iddet", required = true) Integer iddet) {
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		try
		{
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.serializeNulls().create();
			
			model.addAttribute("tar", gson.fromJson(ctsc.DataSourceJasperTF(id, iddet, 0,0).toString(),Object.class));
			model.addAttribute("cot", gson.fromJson(ctsc.DataSourceJasperCot(id,0).toString(),Object.class));
			model.addAttribute("cotdet", gson.fromJson(ctsc.addSpecificDetalle(id,iddet).toString(), Object.class));
			
			logger.info(AppController.getPrincipal() + " - tarjeta_seguimiento_info.");
		}
		catch(Exception e)
		{
			model.addAttribute("mensajes", e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage());
			logger.info(AppController.getPrincipal() + " - tarjeta_seguimiento_info. " + e);
		}
		return "/tarjetas/fabricacion/tarjetas_seguimiento_info";
	}
	
	@RequestMapping(value = {"/ingenieria_gerencia/tarjeta_cambiosgi" }, method = RequestMethod.GET)
	public String tarjeta_cambiosgi(ModelMap model, @RequestParam(value = "folio", defaultValue = "", required = false) String folio) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		Tarjeta_fabricacion tf = new Tarjeta_fabricacion();
		Cotizador cot = new Cotizador();
		Cotizador_detalles cotdet = new Cotizador_detalles();
		Catalogo_cajas_sap_vw objCaja = new Catalogo_cajas_sap_vw();
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().create();
		List<JSONObject> ListaEsp = new ArrayList<JSONObject>();
		
		TarjetaDataBean tdb = new TarjetaDataBean();
		
		if(!folio.trim().equals(""))
		{
			List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
			Params.add(new ParamsGeneral(1,"folio_tarjeta",folio,"EQ"));
			Params.add(new ParamsGeneral(2,"fecha_aut_cliente","NE"));			
			Params.add(new ParamsGeneral(3,"usuario_aut_cliente","NE"));
			tf = tfs.BuscarTFPG(Params);
			
			//tf = tfs.BuscarxFolio(folio);
			if(tf != null)
			{
				tf.setTarjeta_img(tfis.BuscarxIdCotidDert(tf.getIdcotizacion(), tf.getIddetalle()));
				
				cot = cs.BuscarxId(tf.getIdcotizacion());
				cotdet = cds.BuscarxIdDet(tf.getIdcotizacion(), tf.getIddetalle());
				cotdet.setCodigo_barra_cotizador(cbsc.BuscarXCotDet(Integer.valueOf(cotdet.getIdcotizacion()), Integer.valueOf(cotdet.getIddetalle())));
				tdb.setTarjeta_fabricacion(tf);
				tdb.setCotizador(cot);
				tdb.setCotizador_detalles(cotdet);
	
				objCaja = ccss.BuscarxId(cotdet.getIdcaja_sap());
				model.addAttribute("caja", objCaja);
				model.addAttribute("resis", crss.BuscarxId(cotdet.getIdresistencia_barca()));
				model.addAttribute("sello", css.BuscarxId(cotdet.getResistencia_cte()));
				model.addAttribute("cliente_factura", cot.getCardcode_factura() != null ? ccavs.cat_cte_sap(cot.getCardcode_factura()).getCardname() : "");
				model.addAttribute("cliente", cot.getCardcode() != null ? ccavs.cat_cte_sap(cot.getCardcode()).getCardname() : "");
				ListaEsp = ctsc.addEspecialidades(tf.getIdcotizacion(), tf.getIddetalle());
				model.addAttribute("esp", gson.fromJson(ListaEsp.toString(),Object[].class));
				//ctsc.DataSourceJasperTF(cot.getId(), cotdet.getIddetalle(), 1);
			}
		}		
		
		model.addAttribute("tdb", tdb);
		model.addAttribute("grabados", chs.BuscarxTipo(2));
		model.addAttribute("suajes", chs.BuscarxTipo(1));		
		model.addAttribute("colores", ccos.ListaColores());		
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		model.addAttribute("maquinas", tf != null ? BuscarMaquinas(ListaEsp,objCaja.getGrupo(),cotdet.getNum_tintas(),cotdet.getCierre(),tf.getFolio_tarjeta()) : new ArrayList<Catalogo_maquinas_sap_vw>());
		logger.info(AppController.getPrincipal() + " - tarjeta_cambiosgi.");
				
		return "/tarjetas/fabricacion/tarjeta_fabricacion_cambiosgi";
	}
	
	@RequestMapping(value = {"/ingenieria_gerencia/calculardatosgi" }, method = RequestMethod.POST)
	@ResponseBody
	public String calculardatosgi(ModelMap model, @RequestParam("idcotizacion") Integer idcotizacion,@RequestParam("iddetalle") Integer iddetalle, 
			@RequestParam("folio_tarjeta") String folio_tarjeta, @RequestParam("npartes") Integer npartes,
			@RequestParam("pzasxlargo") Integer pzasxlargo, @RequestParam("pzasxancho") Integer pzasxancho) {
		
		return CalcularDatos(idcotizacion,iddetalle,folio_tarjeta,npartes,pzasxlargo,pzasxancho);
	}
	
	@RequestMapping(value = {"/ingenieria_gerencia/tarjeta_cambiosgi" }, method = RequestMethod.POST)
	public String tarjeta_cambiosgipost(@ModelAttribute("tdb") TarjetaDataBean tdb, BindingResult result, ModelMap model) {
		try 
		{
			logger.info(AppController.getPrincipal() + " - tarjeta_cambiosgipost.");
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.serializeNulls().create();
			Catalogo_cajas_sap_vw objCaja = new Catalogo_cajas_sap_vw();
			List<JSONObject> ListaEsp = new ArrayList<JSONObject>();
			tdb.getTarjeta_fabricacion().setBan(1);
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			tdb.getTarjeta_fabricacion().setTarjeta_img(tfis.BuscarxIdCotidDert(tdb.getTarjeta_fabricacion().getIdcotizacion(), tdb.getTarjeta_fabricacion().getIddetalle()));
			model.addAttribute("clientes", ccavs.ListaCtes());
			model.addAttribute("grabados", chs.BuscarxTipo(2));
			model.addAttribute("suajes", chs.BuscarxTipo(1));
			model.addAttribute("colores", ccos.ListaColores());
			Cotizador cot = new Cotizador();
			Cotizador_detalles cotdet = new Cotizador_detalles();
			if(tdb.getTarjeta_fabricacion().getFolio_tarjeta() != "")
			{
				ListaEsp = ctsc.addEspecialidades(tdb.getTarjeta_fabricacion().getIdcotizacion(), tdb.getTarjeta_fabricacion().getIddetalle());
				model.addAttribute("esp", gson.fromJson(ListaEsp.toString(),Object[].class));
				cot = cs.BuscarxId(tdb.getTarjeta_fabricacion().getIdcotizacion());
				cotdet = cds.BuscarxIdDet(tdb.getTarjeta_fabricacion().getIdcotizacion(), tdb.getTarjeta_fabricacion().getIddetalle());
				objCaja = ccss.BuscarxId(cotdet.getIdcaja_sap());
				model.addAttribute("caja", objCaja);
				model.addAttribute("resis", crss.BuscarxId(cotdet.getIdresistencia_barca()));
				model.addAttribute("sello", css.BuscarxId(cotdet.getResistencia_cte()));
				model.addAttribute("cliente_factura", cot.getCardcode_factura() != null ? ccavs.cat_cte_sap(cot.getCardcode_factura()).getCardname() : "");
				model.addAttribute("cliente", cot.getCardcode() != null ? ccavs.cat_cte_sap(cot.getCardcode()).getCardname() : "");
			}
			model.addAttribute("maquinas", tdb.getTarjeta_fabricacion().getFolio_tarjeta() != "" ? BuscarMaquinas(ListaEsp,objCaja.getGrupo(),cotdet.getNum_tintas(),cotdet.getCierre(),tdb.getTarjeta_fabricacion().getFolio_tarjeta()) : new ArrayList<Catalogo_maquinas_sap_vw>());
			cot.setTarimaxunitizado(tdb.getCotizador().getTarimaxunitizado());
			cotdet.setAltura_pallet(tdb.getCotizador_detalles().getAltura_pallet());
			cotdet.setCamas_pallet(tdb.getCotizador_detalles().getCamas_pallet());
			cotdet.setFlejes_pallet(tdb.getCotizador_detalles().getFlejes_pallet());
			cotdet.setFlejes_atado(tdb.getCotizador_detalles().getFlejes_atado());
			cotdet.setPzas_atado(tdb.getCotizador_detalles().getPzas_atado());
			cotdet.setAtados_cama(tdb.getCotizador_detalles().getAtados_cama());
			cotdet.setIdtiporequerimiento(0);
			
			if( tdb.getCotizador_detalles().getPiezasxtarima() != null && (tdb.getCotizador_detalles().getPiezasxtarima() < cotdet.getPiezasxtarima())) {
				cotdet.setBan(1);
			}
			cotdet.setPiezasxtarima(tdb.getCotizador_detalles().getPiezasxtarima());
			tdb.setCotizador(cot);
			tdb.setCotizador_detalles(cotdet);

			model.addAttribute("tdb", tdb);
						
			tfv.validate(tdb.getTarjeta_fabricacion(), result);
			cdv.validate(cotdet, result);
			
			if (result.hasErrors())
			{
				return "/tarjetas/fabricacion/tarjeta_fabricacion_cambiosgi";
			}
			
			String MSJSAP = SAP_UPDATE_OITM(tdb.getTarjeta_fabricacion().getIdcotizacion(),tdb.getTarjeta_fabricacion().getIddetalle(),tdb.getTarjeta_fabricacion().getFolio_tarjeta());
			if(MSJSAP == "OK")
			{
				tfs.Actualizar(tdb.getTarjeta_fabricacion());
				cs.Actualizar(cot);
				cds.Actualizar(cotdet);
				model.addAttribute("mensajes", "Tarjeta actualizada correctamente en SIIBM y SAP");
			}
			else
				model.addAttribute("mensajes", "No se pudo actualizar en SAP: "+ MSJSAP);
				
			
			return "/tarjetas/fabricacion/tarjeta_fabricacion_cambiosgi";
		}
		catch(Exception e)
		{
			logger.info(AppController.getPrincipal() + " - tarjeta_cambiosgipost. " + e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage());
			return "/tarjetas/fabricacion/tarjeta_fabricacion_cambiosgi";
		}
		
	}
	
	@RequestMapping(value = {"/ingenieria_gerencia/subir_imagen_tarjetagi" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> subir_imagen_tarjetagi(@RequestParam("file")  MultipartFile[] file, @RequestParam("folio_tarjeta") String folio_tarjeta,
			@RequestParam("idcotizacion") Integer idcotizacion,@RequestParam("iddetalle") Integer iddetalle, @RequestParam("cama") Boolean cama,
			@RequestParam("principal") Boolean principal, ModelMap model,HttpServletResponse response,HttpServletRequest request) {
			
			return SubirImagen(file, request,idcotizacion,iddetalle,cama,principal);
	}
	
	@RequestMapping(value = {"/ingenieria_gerencia/borrar_imagen_tarjetagi" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> borrar_imagen_tarjetagi(ModelMap model,
			@RequestParam("idcotizacion") Integer idcotizacion,@RequestParam("iddetalle") Integer iddetalle, @RequestParam("nombre") String nombre) {
			return BorrarImagen(idcotizacion,iddetalle,nombre);
	}
	
	private String stripAccents(String s) 
	{
	    s = Normalizer.normalize(s, Normalizer.Form.NFD);
	    s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
	    return s;
	}
	
	@RequestMapping(value = "/ingenieria/imprimirtf", method = RequestMethod.GET)
    @ResponseBody
    public void getRpt1(HttpServletResponse response,HttpServletRequest request,ModelMap model,
    				    @RequestParam("id") Integer id, @RequestParam("iddet") Integer iddet) throws JRException, IOException {
		String msj = "";
		try
		{
			InputStream jasperStream = this.getClass().getResourceAsStream("/jasperreports/cotizador/Tarjeta_fabricacion.jasper");
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
			logger.info(AppController.getPrincipal() + " - imprimirtf :"+ msj);
		}
		catch(Exception e)
		{
			msj = e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage();
			e.printStackTrace();
			logger.info(AppController.getPrincipal() + " - imprimircotizador :"+ e);
		}
	} 
	
	
	@RequestMapping(value = {"/ingenieria_gerencia/cancelar_reactivarTFXCTE" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> cancelar_reactivarTFXCTE(ModelMap model,
			@RequestParam("idcotizacion") Integer idcotizacion,@RequestParam("iddetalle") Integer iddetalle, 
			@RequestParam("folio_tarjeta") String folio_tarjeta, @RequestParam("coment") String coment) throws RemoteException {
		User user = us.findBySSO(AppController.getPrincipal());
		java.util.Date date = new java.util.Date();
		logger.info(AppController.getPrincipal() + " - cancelar_reactivarTFXCTE. ");
		DIServerSoapProxy DISERVER = new DIServerSoapProxy();
		String idsession = "";
		try 
		{
			Tarjeta_fabricacion a = tfs.BuscarxFolio(folio_tarjeta);
			JSONObject TFInfo = ctsc.DataSourceJasperTF(a.getIdcotizacion(), a.getIddetalle(), 1,0);
			idsession = DISERVER.login(environment.getRequiredProperty("diserver.dbserver"), environment.getRequiredProperty("diserver.dbname"), 
					   environment.getRequiredProperty("diserver.dbtype"), environment.getRequiredProperty("diserver.dbusername"), 
					   environment.getRequiredProperty("diserver.dbpassword"), environment.getRequiredProperty("diserver.compusername"), 
					   environment.getRequiredProperty("diserver.comppassword"), environment.getRequiredProperty("diserver.language"), 
					   environment.getRequiredProperty("diserver.licenseserver"), environment.getRequiredProperty("diserver.licese"));
			if(DISERVER.validate(idsession))
			{
				String xml ="<?xml version=\"1.0\" encoding=\"UTF-16\"?> " + 
						"<env:Envelope xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\"> " + 
							"<env:Header> " + 
								"<SessionID>"+idsession+"</SessionID> " + 
							"</env:Header> " + 
							"<env:Body> " + 
								"<dis:UpdateObject>" + 
									"<BOM> " + 
										"<BO> " + 
											"<AdmInfo> " + 
												"<Object>oItems</Object> " + 
											"</AdmInfo> " + 
											"<QueryParams> " + 
												"<U_TF>"+TFInfo.getString("folio_tarjeta")+"</U_TF> " +
												"<ItemName>"+TFInfo.optString("simbolo","")+"</ItemName> " +
												"<ItemsGroupCode>105</ItemsGroupCode> " +
											"</QueryParams> " + 
											"<Items> " + 
												"<row> "; 
													
				
				if(a.getFecha_cancelxcte() == null && a.getUsuario_cancelxcte() == null)
				{
					a.setUsuario_cancelxcte(user.getId());
					a.setFecha_cancelxcte(date);
					a.setObservaciones(coment);
										xml = xml + "<Properties62>tYES</Properties62> "; //Símbolo INactivo.
				}
				else
				{
					a.setUsuario_cancelxcte(null);
					a.setFecha_cancelxcte(null);
					a.setObservaciones(coment);
								xml = xml + "<Properties62>tNO</Properties62> "; //Símbolo activo.
				}
				
							xml = xml + "</row> " + 
								"</Items> " + 
							"</BO> " + 
						"</BOM> " + 
					"</dis:UpdateObject> " + 
				"</env:Body> " + 
				"</env:Envelope> ";
				
				String MSJ = DISERVER.interact(idsession, xml);
				Document doc = 
					    DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
					        new ByteArrayInputStream(
					        		MSJ.getBytes()  
					        )
					);
				Node e = null;
				e = doc.getElementsByTagName("UpdateObjectResponse").item(0);
				if(e == null )
				{
					logger.info(AppController.getPrincipal() + " - SAP. " + MSJ);
					return new ResponseEntity<Object>(MSJ, HttpStatus.INTERNAL_SERVER_ERROR);
				}
				else
				{
					tfs.Actualizar(a);
					logger.info(AppController.getPrincipal() + " - SAP. " + MSJ);
					return new ResponseEntity<Object>("OK", HttpStatus.OK);
				}
				
			}
			else
				return new ResponseEntity<Object>("No se pudo conectar a SAP", HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		catch(Exception e)
		{
			model.addAttribute("mensajes", e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage());
			logger.info(AppController.getPrincipal() + " - cancelar_reactivarTFXCTE. ", e);
			return new ResponseEntity<Object>(e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	
	private String SAP_INSERT_OITM(Integer idcotizacion, Integer iddetalle, String folio_tf) throws SAXException, IOException, ParserConfigurationException
	{
		DIServerSoapProxy DISERVER = new DIServerSoapProxy();
		String idsession = "";
		try
		{
			JSONObject TFInfo = ctsc.DataSourceJasperTF(idcotizacion, iddetalle, 1,0);
			JSONArray arrEsp = new JSONArray();
			arrEsp = TFInfo.getJSONArray("ListaEsp");
			String espXMLpart = "";
			String maqXMLpart = "";
			for(int i=0; i<arrEsp.length();i++)
			{
				JSONObject obj = arrEsp.getJSONObject(i);
				espXMLpart = espXMLpart + "<Properties"+obj.optInt("propiedadoitm",0)+">tYES</Properties"+obj.optInt("propiedadoitm",0)+"> ";
			}
			JSONArray arrMaq = new JSONArray();
			arrMaq = TFInfo.getJSONArray("catalogo_maquinas_sap_vw");
			for(int j=0; j<arrMaq.length(); j++)
			{
				JSONObject obj = arrMaq.getJSONObject(j);
				maqXMLpart = maqXMLpart + "<Properties"+obj.optInt("u_propiedad",0)+">tYES</Properties"+obj.optInt("u_propiedad",0)+"> ";
			}
			idsession = DISERVER.login(environment.getRequiredProperty("diserver.dbserver"), environment.getRequiredProperty("diserver.dbname"), 
					   environment.getRequiredProperty("diserver.dbtype"), environment.getRequiredProperty("diserver.dbusername"), 
					   environment.getRequiredProperty("diserver.dbpassword"), environment.getRequiredProperty("diserver.compusername"), 
					   environment.getRequiredProperty("diserver.comppassword"), environment.getRequiredProperty("diserver.language"), 
					   environment.getRequiredProperty("diserver.licenseserver"), environment.getRequiredProperty("diserver.licese"));
			
			if(DISERVER.validate(idsession))//Validar sesión en SAP 
			{
				
				String xmlValTFSol ="<?xml version=\"1.0\" encoding=\"utf-16\"?>\n" + 
						"<env:Envelope xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" + 
						"	<env:Header>\n" + 
						"		<SessionID>"+idsession+"</SessionID>\n" + 
						"	</env:Header>\n" + 
						"	<env:Body>\n" + 
						"		<dis:ExecuteSQL xmlns:dis=\"http://www.sap.com/SBO/DIS\">\n" + 
						"			<DoQuery>select count(0) n from oitm where U_TF = '"+TFInfo.getString("folio_tarjeta")+"' and ItmsGrpCod = 105</DoQuery>\n" + 
						"		</dis:ExecuteSQL>\n" + 
						"	</env:Body>\n" + 
						"</env:Envelope>";
				
				String xmlValTFRes = DISERVER.interact(idsession, xmlValTFSol);
				
				Document docresvaltf = convertStringToXMLDocument(xmlValTFRes);
		        NodeList nodesresvaltf = docresvaltf.getElementsByTagName("n");				

				if(nodesresvaltf.item(0).getTextContent().equals("0"))// Existe la tarjeta
				{
					String xmlNextSim = "<?xml version=\"1.0\" encoding=\"utf-16\"?>\n" + 
							"<env:Envelope xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" + 
							"	<env:Header>\n" + 
							"		<SessionID>"+idsession+"</SessionID>\n" + 
							"	</env:Header>\n" + 
							"	<env:Body>\n" + 
							"		<dis:ExecuteSQL xmlns:dis=\"http://www.sap.com/SBO/DIS\">\n" + 
							"			<DoQuery>select SUBSTRING(max(ItemCode), 4, 6) + 1 maxtf from OITM where ItmsGrpCod in(105,192)</DoQuery>\n" + 
							"		</dis:ExecuteSQL>\n" + 
							"	</env:Body>\n" + 
							"</env:Envelope>";
					 String ESRESR = DISERVER.interact(idsession, xmlNextSim);

					 Document docESRESR = convertStringToXMLDocument(ESRESR);
				     NodeList nodesESRESR = docESRESR.getElementsByTagName("maxtf");
				     String nextSim = nodesESRESR.item(0).getTextContent();

				    if(Integer.valueOf(nextSim) > 0)//Obtener el siguiente SI
					{
						String newSimbolo = "SI-" + StringUtils.leftPad(nextSim, 6,"0");
						
						String xml ="<BOM><BO> " + 
										"<AdmInfo> " + 
											"<Object>oItems</Object> " + 
										"</AdmInfo> " + 
										"<Items> " + 
											"<row> " + 
												"<ItemCode>"+newSimbolo+"</ItemCode> " + 
												"<ItemName>"+TFInfo.optString("simbolo","")+"</ItemName> " +
												"<ItemsGroupCode>105</ItemsGroupCode> " +
												"<InventoryItem>tYES</InventoryItem> " +
												"<PurchaseItem>tYES</PurchaseItem> " +
												"<SalesItem>tYES</SalesItem> " +
												"<ManageBatchNumbers>tYES</ManageBatchNumbers> " +
												"<BaseUnitName>Millar</BaseUnitName> " +
												"<PurchaseUnit>Millar</PurchaseUnit> " +
												"<SalesUnit>Millar</SalesUnit> " +
												"<InventoryUOM>Millar</InventoryUOM> " +
												"<DefaultWarehouse>PT-04</DefaultWarehouse> " +
												"<ManageStockByWarehouse>tYES</ManageStockByWarehouse> " +
												"<Properties63>"+(TFInfo.getInt("iddetalle") == 1 ? "tNO" : "tYES")+"</Properties63> " + //detalle
												"<Properties64>"+(TFInfo.getInt("iddetalle") == 1 ? "tYES" : "tNO")+"</Properties64> " + //principal
												"<U_CPADRE>"+(TFInfo.getInt("iddetalle") == 1 ? "" : TFInfo.getInt("id")) +"</U_CPADRE> " +
												"<U_TF>"+TFInfo.getString("folio_tarjeta")+"</U_TF> " +
												"<U_L>"+TFInfo.optDouble("largo",0.0)+"</U_L> " +
												"<U_A>"+TFInfo.optDouble("ancho",0.0)+"</U_A> " + 
												"<U_F>"+TFInfo.optDouble("fondo",0.0)+"</U_F> " +
												"<U_ESPSUP>"+TFInfo.optDouble("esp_sup",0.0)+"</U_ESPSUP> " + 
												"<U_ESPINF>"+TFInfo.optDouble("esp_inf",0.0)+"</U_ESPINF> " +
												"<U_CREST>"+TFInfo.optString("resistencia","")+"</U_CREST> " +
												"<U_TIPO>"+TFInfo.optString("estilo_caja","")+"</U_TIPO> " +
												"<U_AREA>"+TFInfo.optDouble("area_unitaria",0.0)+"</U_AREA> " +
												"<U_PESO>"+TFInfo.optDouble("peso_teorico",0.0)+"</U_PESO> " +
												"<U_NCAJASATADO>"+TFInfo.optDouble("pzas_atado",0.0)+"</U_NCAJASATADO> " + 
												"<U_SCORE>"+TFInfo.optInt("score",0)+"</U_SCORE> " +
												"<U_NORANU>"+TFInfo.optInt("num_raturas", 0)+"</U_NORANU> " +
												"<U_LPLIEGO>"+TFInfo.optDouble("largo_pliego",0.0)+"</U_LPLIEGO> " +
												"<U_APLIEGO>"+TFInfo.optDouble("ancho_pliego",0.0)+"</U_APLIEGO> " +
												"<U_PJUEGO>"+TFInfo.optInt("piezasxjuego",0)+"</U_PJUEGO> " +
												"<U_CLIENTE>"+TFInfo.optString("cardcode","")+"</U_CLIENTE> " +
												"<U_CLRIMPR>"+ //colores
													(TFInfo.optString("color1n","") + TFInfo.optString("color2n","") == "" ? "" : ", " +
													TFInfo.optString("color2n","") + TFInfo.optString("color3n","") == "" ? "" : ", " +
													TFInfo.optString("color3n","") + TFInfo.optString("color4n","") == "" ? "" : ", " +
													TFInfo.optString("color4n","") + TFInfo.optString("color5n","") == "" ? "" : ", " +
													TFInfo.optString("color5n","") + TFInfo.optString("color6n","") == "" ? "" : ", " +
													TFInfo.optString("color6n","") + TFInfo.optString("color7n","") == "" ? "" : ", " +
													TFInfo.optString("color7n","")) +
												"</U_CLRIMPR> " +
												"<U_SATCode>24121511</U_SATCode> " +
												"<U_CIERRE>"+TFInfo.optString("cierre","")+" "+TFInfo.optString("cierre_detalle","")+"</U_CIERRE> " +
												"<ForeignName>"+TFInfo.optString("descripcion_factura","")+"</ForeignName> " +
												"<U_PRECIONET>"+TFInfo.optDouble("precio_neto",0.0)+"</U_PRECIONET> " +
												"<U_VALESPECIAL></U_VALESPECIAL> " +//no se pa' que sirva
												"<U_PIEZAS>"+TFInfo.optInt("piezasxtarima",0)+"</U_PIEZAS> " +
												"<U_GOLPES>"+TFInfo.optInt("pzasxlargo",0) * TFInfo.optInt("pzasxancho",0)+"</U_GOLPES> " +
												"<U_RAYADO1>"+((int)(TFInfo.optDouble("rayado1",0.0)*10))+"</U_RAYADO1> " +
												"<U_RAYADO2>"+((int)(TFInfo.optDouble("rayado2",0.0)*10))+"</U_RAYADO2> " +
												"<U_RAYADO3>"+((int)(TFInfo.optDouble("rayado3",0.0)*10))+"</U_RAYADO3> " +
												"<U_RAYADO4>"+((int)(TFInfo.optDouble("rayado4",0.0)*10))+"</U_RAYADO4> " +
												"<U_RAYADO5>"+((int)(TFInfo.optDouble("rayado5",0.0)*10))+"</U_RAYADO5> " +
												"<U_PrecioVta>"+TFInfo.optDouble("precio_objetivo")+"</U_PrecioVta> " +
												"<U_CPCC>"+TFInfo.optDouble("cpcc",0.0)+"</U_CPCC> " +
												"<U_CPSC>"+TFInfo.optDouble("ref_para_comision",0.0)+"</U_CPSC> " +
												"<U_Ctopapel>"+TFInfo.optDouble("costo_papel",0.0)+"</U_Ctopapel> " +
												"<User_Text>"+TFInfo.optString("observaciones","")+"</User_Text> " +
												"<U_Ciudad>"+TFInfo.optString("ciudad","")+"</U_Ciudad> " +
												"<U_Estado>"+TFInfo.optString("estado","")+"</U_Estado> " +
												"<U_GRABADO>"+TFInfo.optString("grabado","")+"</U_GRABADO> " +
												"<U_SUAJE>"+TFInfo.optString("suaje","")+"</U_SUAJE> " +
												"<Properties62>tNO</Properties62> " + //Símbolo activo.
												espXMLpart +
												maqXMLpart +
											"</row> " + 
										"</Items> " + 
									"</BO></BOM> "; 

						AddObjectResponseAddObjectResult Mensajes = DISERVER.addObject(idsession, xml, folio_tf);
						String MSJ = Mensajes.get_any()[0].getAsString();
						Document doc = 
							    DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
							        new ByteArrayInputStream(
							        		MSJ.getBytes()  
							        )
							);
						Node e = null;
						e = doc.getElementsByTagName("env:Reason").item(0);
						if(e != null )
						{
							logger.info(AppController.getPrincipal() + " - SAP. " + MSJ);
							return MSJ;
						}
						else
						{
							e = doc.getElementsByTagName("AddObjectResponse").item(0);
							if(e == null )
							{
								logger.info(AppController.getPrincipal() + " - SAP. " + MSJ);
								return MSJ;
							}
							else
							{
								logger.info(AppController.getPrincipal() + " - SAP. " + MSJ);
								return "OK";
							}
						} 
					}
					else
					{
						logger.info(AppController.getPrincipal() + " - SAP. " + "Execute Query failed");
						return "SAPINSERT - Execute Query failed";
					}
				}
				else
				{
					logger.info(AppController.getPrincipal() + " - SAP. " + "Ya existe la tarjeta en SAP");
					return "Ya existe la tarjeta en SAP";
				} 
			}
			else
			{
				logger.info(AppController.getPrincipal() + " - SAP. " + "Sesión de SAP no válida...");
				return "Sesión de SAP no válida...";
			}    
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.info(AppController.getPrincipal(), e);
			return e.toString();
		}
		finally
		{
			DISERVER.logout(idsession);
		}
	}
	
	private String SAP_UPDATE_OITM(Integer idcotizacion, Integer iddetalle, String folio_tf) throws Exception
	{
		DIServerSoapProxy DISERVER = new DIServerSoapProxy();
		String idsession = "";
		try 
		{
			JSONObject TFInfo = ctsc.DataSourceJasperTF(idcotizacion, iddetalle, 1,0);

			JSONArray arrEsp = new JSONArray();
			arrEsp = TFInfo.getJSONArray("ListaEsp");
			String maqXMLpart = "";
			
			JSONArray arrMaq = new JSONArray();
			arrMaq = TFInfo.getJSONArray("catalogo_maquinas_sap_vw");
			for(int j=0; j<arrMaq.length(); j++)
			{
				JSONObject obj = arrEsp.getJSONObject(j);
				maqXMLpart = maqXMLpart + "<Properties"+obj.optInt("propiedad",0)+">tYES</Properties"+obj.optInt("u_propiedad",0)+"> ";
			}
			
			idsession = DISERVER.login(environment.getRequiredProperty("diserver.dbserver"), environment.getRequiredProperty("diserver.dbname"), 
									   environment.getRequiredProperty("diserver.dbtype"), environment.getRequiredProperty("diserver.dbusername"), 
									   environment.getRequiredProperty("diserver.dbpassword"), environment.getRequiredProperty("diserver.compusername"), 
									   environment.getRequiredProperty("diserver.comppassword"), environment.getRequiredProperty("diserver.language"), 
									   environment.getRequiredProperty("diserver.licenseserver"), environment.getRequiredProperty("diserver.licese"));

			if(DISERVER.validate(idsession))
			{
				String xml ="<?xml version=\"1.0\" encoding=\"UTF-16\"?> " + 
							"<env:Envelope xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\"> " + 
								"<env:Header> " + 
									"<SessionID>"+idsession+"</SessionID> " + 
								"</env:Header> " + 
								"<env:Body> " + 
									"<dis:UpdateObject>" + 
										"<BOM> " + 
											"<BO> " + 
												"<AdmInfo> " + 
													"<Object>oItems</Object> " + 
												"</AdmInfo> " + 
												"<QueryParams> " + 
													"<U_TF>"+TFInfo.getString("folio_tarjeta")+"</U_TF> " +
													"<ItemName>"+TFInfo.optString("simbolo","")+"</ItemName> " +
													"<ItemsGroupCode>105</ItemsGroupCode> " +
												"</QueryParams> " + 
												"<Items> " + 
													"<row> " + 
														"<U_AREA>"+TFInfo.optDouble("area_unitaria",0.0)+"</U_AREA> " +
														"<U_LPLIEGO>"+TFInfo.optDouble("largo_pliego",0.0)+"</U_LPLIEGO> " +
														"<U_APLIEGO>"+TFInfo.optDouble("ancho_pliego",0.0)+"</U_APLIEGO> " +
														"<U_PIEZAS>"+TFInfo.optInt("piezasxtarima",0)+"</U_PIEZAS> " +
														"<U_GOLPES>"+TFInfo.optInt("pzasxlargo",0) * TFInfo.optInt("pzasxancho",0)+"</U_GOLPES> " +
														"<U_RAYADO1>"+((int)(TFInfo.optDouble("rayado1",0.0)*10))+"</U_RAYADO1> " +
														"<U_RAYADO2>"+((int)(TFInfo.optDouble("rayado2",0.0)*10))+"</U_RAYADO2> " +
														"<U_RAYADO3>"+((int)(TFInfo.optDouble("rayado3",0.0)*10))+"</U_RAYADO3> " +
														"<U_RAYADO4>"+((int)(TFInfo.optDouble("rayado4",0.0)*10))+"</U_RAYADO4> " +
														"<U_RAYADO5>"+((int)(TFInfo.optDouble("rayado5",0.0)*10))+"</U_RAYADO5> " +
														"<User_Text>"+TFInfo.optString("observaciones","")+"</User_Text> " +
														"<U_GRABADO>"+TFInfo.optString("grabado","")+"</U_GRABADO> " +
														"<U_SUAJE>"+TFInfo.optString("suaje","")+"</U_SUAJE> " +
														maqXMLpart +
													"</row> " + 
												"</Items> " + 
											"</BO> " + 
										"</BOM> " + 
									"</dis:UpdateObject> " + 
								"</env:Body> " + 
							"</env:Envelope> ";
				String MSJ = DISERVER.interact(idsession, xml);
				Document doc = 
					    DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
					        new ByteArrayInputStream(
					        		MSJ.getBytes()  
					        )
					);
				Node e = null;
				e = doc.getElementsByTagName("UpdateObjectResponse").item(0);
				if(e == null )
				{
					logger.info(AppController.getPrincipal() + " - SAP. " + MSJ);
					return MSJ;
				}
				else
				{
					logger.info(AppController.getPrincipal() + " - SAP. " + MSJ);
					return "OK";
				}
			}
			else
			{
				logger.info(AppController.getPrincipal() + " - SAP. " + "Sesión inválida.");
				return "Sesión inválida.";
			}
		}
		catch (RemoteException e) 
		{
			logger.info(AppController.getPrincipal() + " - SAP. ", e);
			return e.getMessage()+"-"+e.getStackTrace()+""+e.getCause();
		}
		finally
		{
			DISERVER.logout(idsession);
		}
		
	}
	
	private static Document convertStringToXMLDocument(String xmlString) 
    {
		xmlString = xmlString.replace("<?xml version=\"1.0\"?>", "");
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         
        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try
        {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();
             
            //Parse the content to Document object
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return null;
    }	
	
}
