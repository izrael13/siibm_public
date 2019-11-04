package com.websystique.springmvc.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.tarjetas.Catalogo_cajas_sap_vw;
import com.websystique.springmvc.model.tarjetas.Catalogo_colores;
import com.websystique.springmvc.model.tarjetas.Catalogo_maquinas_sap_vw;
import com.websystique.springmvc.model.tarjetas.Catalogo_resistencias_sap_vw;
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
import com.websystique.springmvc.service.tarjetas.cotizador.CotizadorService;
import com.websystique.springmvc.service.tarjetas.cotizador.Cotizador_detallesService;
import com.websystique.springmvc.service.tarjetas.fabricacion.Tarjeta_fabricacionService;
import com.websystique.springmvc.service.tarjetas.fabricacion.Tarjetas_fabricacion_imagenesService;
import com.websystique.springmvc.utilities.SendMailGmail;

@Controller
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
	
	@RequestMapping(value = {"/ingenieria/tarjeta_fabricacion" }, method = RequestMethod.GET)
	public String tarjeta_fabricacion(ModelMap model, @RequestParam(value = "folio", defaultValue = "", required = false) String folio) {
		
		Tarjeta_fabricacion tf = new Tarjeta_fabricacion();
		Cotizador cot = new Cotizador();
		Cotizador_detalles cotdet = new Cotizador_detalles();
		Catalogo_cajas_sap_vw objCaja = new Catalogo_cajas_sap_vw();
		List<Object> ListaEsp = new ArrayList<Object>();
		
		TarjetaDataBean tdb = new TarjetaDataBean();
		
		if(!folio.trim().equals(""))
		{
			User user = us.findBySSO(AppController.getPrincipal());
			tf = tfs.BuscarxFolio(folio,user.getId());
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
			
			DataSourceJasperTF(2,1,tf.getFolio_tarjeta());
			
		}		
		
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		model.addAttribute("tdb", tdb);
		model.addAttribute("clientes", ccavs.ListaCtes());
		model.addAttribute("grabados", chs.BuscarxTipo(2));
		model.addAttribute("suajes", chs.BuscarxTipo(1));
		ListaEsp = tfs.BuscarEsp(tf.getIdcotizacion(), tf.getIddetalle());
		model.addAttribute("esp", ListaEsp);
		model.addAttribute("colores", ccos.ListaColores());		
		model.addAttribute("maquinas", BuscarMaquinas(ListaEsp,objCaja.getGrupo(),cotdet.getNum_tintas(),cotdet.getCierre(),tf.getFolio_tarjeta()));
		
		logger.info(AppController.getPrincipal() + " - tarjeta_fabricacion.");
				
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
		try 
		{
			DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.getDefault());
			formatSymbols.setDecimalSeparator('.');
			DecimalFormat decimal4 = new DecimalFormat("###########0.####");
			
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
				
				medPliego =  LargoPliego.toString() + " X " +  AnchoPliego.toString();
			}	
			else
			{
				area_total = npartes == 1 ? ((cotdet.getLargo_pliego() * pzasxlargo) * (cotdet.getAncho_pliego() * pzasxancho)) / 10000 :
					((cotdet.getLargo_pliego() * pzasxlargo / npartes) * (cotdet.getAncho_pliego() * pzasxancho)) / 10000;
				
				LargoPliego = (npartes == 1 ? cotdet.getLargo_pliego() * pzasxlargo : (cotdet.getLargo_pliego() * pzasxlargo) / 2 ); 
				AnchoPliego = cotdet.getAncho_pliego() * pzasxancho;
				
				medPliego =  LargoPliego.toString() + " x " +  AnchoPliego.toString();
			}
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
			Catalogo_cajas_sap_vw objCaja = new Catalogo_cajas_sap_vw();
			List<Object> ListaEsp = new ArrayList<Object>();
			tdb.getTarjeta_fabricacion().setBan(1);
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			tdb.getTarjeta_fabricacion().setTarjeta_img(tfis.BuscarxIdCotidDert(tdb.getTarjeta_fabricacion().getIdcotizacion(), tdb.getTarjeta_fabricacion().getIddetalle()));
			model.addAttribute("clientes", ccavs.ListaCtes());
			model.addAttribute("grabados", chs.BuscarxTipo(2));
			model.addAttribute("suajes", chs.BuscarxTipo(1));
			ListaEsp = tfs.BuscarEsp(tdb.getTarjeta_fabricacion().getIdcotizacion(), tdb.getTarjeta_fabricacion().getIddetalle());
			model.addAttribute("esp", ListaEsp);
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

			if(tdb.getCotizador_detalles().getPiezasxtarima() < cotdet.getPiezasxtarima()) {
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
			logger.info(AppController.getPrincipal() + " - cotizadotpost. " + e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage());
			return "/tarjetas/fabricacion/tarjeta_fabricacion";
		}
		
	}
	
	private List<Catalogo_maquinas_sap_vw> BuscarMaquinas(List<Object>ListaEsp, Integer Grupo, Integer NumTintas, String cierre, String Folio)
	{
		List<Catalogo_maquinas_sap_vw> Lista = new ArrayList<Catalogo_maquinas_sap_vw>();
		if(Folio != null)
		{
			Lista = cms.ListaMaquinas(0);
			Lista.addAll(cms.ListaMaquinas(Grupo,NumTintas));
			Long bgra = ListaEsp.stream().filter(a -> Integer.valueOf(String.valueOf(((Object[])a)[3])) == 8).count();
			if(cierre.equals("grapada") && bgra.intValue() == 0)
				Lista.addAll(cms.ListaMaquinas(true));
		}
		return Lista;
	}
	
	@RequestMapping(value = {"/ingenieria/subir_imagen_tarjeta" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> subir_imagen_tarjeta(@RequestParam("file")  MultipartFile[] file, @RequestParam("folio_tarjeta") String folio_tarjeta,
			@RequestParam("idcotizacion") Integer idcotizacion,@RequestParam("iddetalle") Integer iddetalle, @RequestParam("cama") Boolean cama,
			ModelMap model,HttpServletResponse response,HttpServletRequest request) {
		String mensaje = "";
		try 
		{
			MultipartFile img =  file[0];
			String fileName = img.getOriginalFilename();
		    String folderPath = request.getServletContext().getRealPath("/")+"static\\img_tarjetas\\";
		    String filePath = folderPath + fileName;

		    byte[] bytes = img.getBytes();
		    Path path = Paths.get(filePath);
            Files.write(path, bytes);
            
            List<Tarjetas_fabricacion_imagenes> ListimgTar = tfis.BuscarxIdCotidDert(idcotizacion, iddetalle);

            if(cama)
            {            	
            	ListimgTar.stream().filter(a -> a.getCama() == true).forEach((p) -> {
            		p.setCama(false);
            		tfis.Actualizar(p);
            	});
            }
            
            java.util.Date date = new java.util.Date();
            if(ListimgTar.stream().filter(a -> a.getNombre().equals(fileName)).count() == 0)
            {
	            
	            Tarjetas_fabricacion_imagenes imgTar = new Tarjetas_fabricacion_imagenes();
	            imgTar.setIdcotizacion(idcotizacion);
	            imgTar.setIddetalle(iddetalle);
	            imgTar.setNombre(fileName);
	            imgTar.setPath(folderPath);
	            imgTar.setFecha_insert(date);
	            imgTar.setCama(cama);
	            
	            tfis.Guardar(imgTar);
            }
            else
            {
            	ListimgTar.stream().filter(a -> a.getNombre().equals(fileName)).forEach((p) ->
            		{
            			p.setCama(cama);
            			p.setFecha_insert(date);
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
			mensaje = e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage();
			model.addAttribute("mensajes", mensaje);
			logger.info(AppController.getPrincipal() + " - subir_imagen_tarjeta. " + mensaje);
			return new ResponseEntity<Object>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = {"/ingenieria/borrar_imagen_tarjeta" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> borrar_imagen_tarjeta(ModelMap model,
			@RequestParam("idcotizacion") Integer idcotizacion,@RequestParam("iddetalle") Integer iddetalle, @RequestParam("nombre") String nombre) {
		String mensaje = "";
		try 
		{
			tfis.BuscarxId(idcotizacion, iddetalle, nombre).stream().forEach(a -> tfis.Borrar(a));
			
			List<Tarjetas_fabricacion_imagenes> ListimgTar = tfis.BuscarxIdCotidDert(idcotizacion, iddetalle);
            Gson  g = new Gson();
            logger.info(AppController.getPrincipal() + " - borrar_imagen_tarjeta. " + mensaje);
			return new ResponseEntity<Object>(g.toJson(ListimgTar), HttpStatus.OK);
		}
		catch(Exception e)
		{
			mensaje = e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage();
			model.addAttribute("mensajes", mensaje);
			logger.info(AppController.getPrincipal() + " - borrar_imagen_tarjeta. " + mensaje);
			return new ResponseEntity<Object>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
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
					String fullpath = b.getPath()+b.getNombre();
					File file = new File(fullpath);
					if(file.exists())
						file.delete();
					
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
			tf = tfs.BuscarxFolio(folio, user.getId());
			
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
			tf = tfs.BuscarxFolio(folio, user.getId());
			
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
	
	@RequestMapping(value = {"/ingenieria/tarjeta_aut_ingenieria" }, method = RequestMethod.GET)
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
	
	@RequestMapping(value = {"/ingenieria/tarjeta_aut_ingenieria_desicion" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> tarjeta_aut_ingenieria(ModelMap model,
			@RequestParam("folio") String folio,@RequestParam("coment") String coment, @RequestParam("ban") Integer ban) {
		String mensaje = "";
		try 
		{
			Tarjeta_fabricacion tf = new Tarjeta_fabricacion();
			java.util.Date date = new java.util.Date();
			User user = us.findBySSO(AppController.getPrincipal());
			tf = tfs.BuscarxFolio(folio, user.getId());
			
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
		User user = us.findBySSO(AppController.getPrincipal());
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"usuario_aut_ing","NE"));
		Params.add(new ParamsGeneral(2,"fecha_aut_ing","NE"));
		Params.add(new ParamsGeneral(3,"usuario_aut_cliente","EQ"));
		Params.add(new ParamsGeneral(4,"fecha_aut_cliente","EQ"));
		Params.add(new ParamsGeneral(5,"usuario_cancela","EQ"));
		Params.add(new ParamsGeneral(6,"fecha_cancela","EQ"));
		Params.add(new ParamsGeneral(7,"cardcode",user.getCardcode_sap()));
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
		String mensaje = "";
		try 
		{
			Tarjeta_fabricacion tf = new Tarjeta_fabricacion();
			java.util.Date date = new java.util.Date();
			User user = us.findBySSO(AppController.getPrincipal());
			tf = tfs.BuscarxFolio(folio, user.getId());
			
			if(ban == 1) {
				tf.setUsuario_aut_cliente(user.getId());
				tf.setFecha_aut_cliente(date);
			}
			else {
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
			}
				
			tf.setObservaciones_ing(coment);
			tfs.Actualizar(tf);
			logger.info(AppController.getPrincipal() + " - tarjeta_aut_cliente_desicion. " + mensaje);
			return new ResponseEntity<Object>("OK", HttpStatus.OK);
		}
		catch(Exception e)
		{
			mensaje = e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage();
			model.addAttribute("mensajes", mensaje);
			logger.info(AppController.getPrincipal() + " - tarjeta_aut_cliente_desicion. " + mensaje);
			return new ResponseEntity<Object>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	
	@RequestMapping(value = {"/ingenieria/tarjetas_seguimiento" }, method = RequestMethod.GET)
	public String tarjetas_seguimiento(ModelMap model, @RequestParam(value = "FolioTF", defaultValue = "", required = false) String folio,
													  @RequestParam(value = "Cot", defaultValue = "0", required = false) Integer idCot,
													  @RequestParam(value = "Status", defaultValue = "0", required = false) Integer status,
													  @RequestParam(value = "CardCode", defaultValue = "", required = false) String cardcode) {

		model.addAttribute("loggedinuser", AppController.getPrincipal());
		model.addAttribute("clientes", ccavs.ListaCtes());
		model.addAttribute("lista", tfs.ListaSeguimiento(folio,idCot,status,cardcode));
		logger.info(AppController.getPrincipal() + " - tarjetas_seguimiento.");
				
		return "/tarjetas/fabricacion/tarjetas_seguimiento";
	}
	
	@RequestMapping(value = {"/ingenieria/tarjeta_seguimiento_info" }, method = RequestMethod.GET)
	public String tarjeta_seguimiento_info(ModelMap model, @RequestParam(value = "folio", required = true) String folio) {
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		Tarjeta_fabricacion tf = new Tarjeta_fabricacion();
		Cotizador_detalles cotdet = new Cotizador_detalles();
		tf = tfs.BuscarxFolio(folio);
		cotdet = cds.BuscarxIdDet(tf.getIdcotizacion(), tf.getIddetalle());
		tf.setTarjeta_img(tfis.BuscarxIdCotidDert(tf.getIdcotizacion(), tf.getIddetalle()));
		model.addAttribute("tarjeta", tf);
		model.addAttribute("cotizacion", cs.BuscarxId(tf.getIdcotizacion()));
		model.addAttribute("detalle", cotdet);
		model.addAttribute("cliente", ccavs.cat_cte_sap(tf.getCardcode()));
		model.addAttribute("resistencia", AppController.getPrincipal());
		model.addAttribute("caja", ccss.BuscarxId(cotdet.getIdcaja_sap()));
		model.addAttribute("cod_barras", cbsc.BuscarXCotDet(tf.getIdcotizacion(), tf.getIddetalle()));
		model.addAttribute("especialidades", tfs.BuscarEsp(tf.getIdcotizacion(), tf.getIddetalle()));
		
		model.addAttribute("color1", ccos.BuscarxId(cotdet.getColor1()));
		model.addAttribute("color2", ccos.BuscarxId(cotdet.getColor2()));
		model.addAttribute("color3", ccos.BuscarxId(cotdet.getColor3()));
		model.addAttribute("color4", ccos.BuscarxId(cotdet.getColor4()));
		model.addAttribute("color5", ccos.BuscarxId(cotdet.getColor5()));
		model.addAttribute("color6", ccos.BuscarxId(cotdet.getColor6()));
		model.addAttribute("color7", ccos.BuscarxId(cotdet.getColor7()));
		//model.addAttribute("ruta", AppController.getPrincipal());
		
		logger.info(AppController.getPrincipal() + " - tarjeta_seguimiento_info.");
		return "/tarjetas/fabricacion/tarjetas_seguimiento_info";
	}
	
	@SuppressWarnings("rawtypes")
	private void DataSourceJasperTF(Integer id, Integer iddet, String folio)
	{		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().create();
		User user = new User();
		Catalogo_colores color = new Catalogo_colores();
		Cotizador cot = new Cotizador();
		Cotizador_detalles cotdet = new Cotizador_detalles();
		Tarjeta_fabricacion tf= new Tarjeta_fabricacion();
		
		cot = cs.BuscarxId(id);
		cotdet = cds.BuscarxIdDet(id, iddet);
		tf = tfs.BuscarxFolio(folio);
		tf.setTarjeta_img(tfis.BuscarxIdCotidDert(id, iddet));
		
		JSONObject JsonTF = new JSONObject(gson.toJson(tf));		
		user = us.findById(tf.getUsuario_aut_diseniador() == null ? 0 : tf.getUsuario_aut_diseniador());
		JsonTF.put("diseniador", tf.getUsuario_aut_diseniador() == null ? "" : user.getFirstName() + " " +user.getLastName());
		
		user = us.findById(tf.getUsuario_aut_calidad() == null ? 0 : tf.getUsuario_aut_calidad());
		JsonTF.put("aut_calidad", tf.getUsuario_aut_calidad() == null ? "" : user.getFirstName() + " " +user.getLastName());
		
		user = us.findById(tf.getUsuario_aut_cliente() == null ? 0 : tf.getUsuario_aut_cliente());
		JsonTF.put("aut_cliente", tf.getUsuario_aut_cliente() == null ? "" : user.getFirstName() + " " +user.getLastName());
		
		user = us.findById(tf.getUsuario_aut_ing() == null ? 0 : tf.getUsuario_aut_ing());
		JsonTF.put("aut_ingenieria", tf.getUsuario_aut_ing() == null ? "" : user.getFirstName() + " " +user.getLastName());
		
		user = us.findById(tf.getUsuario_aut_produccion() == null ? 0 :tf.getUsuario_aut_produccion());
		JsonTF.put("aut_produccion", tf.getUsuario_aut_produccion() == null ? "" : user.getFirstName() + " " +user.getLastName());
		
		JsonTF.put("suaje", tf.getSuaje() == null ? "" : chs.BuscarxId(tf.getSuaje()).getNombre());
		JsonTF.put("grabado", tf.getGrabado() == null ? "" : chs.BuscarxId(tf.getGrabado()).getNombre());
		
		JSONObject JsonCot = new JSONObject(gson.toJson(cot));
		JsonCot.put("cliente", ccavs.cat_cte_sap(cot.getCardcode()).getCardname());
		
		JSONObject JsonCotdet = new JSONObject(gson.toJson(cotdet));
		color = ccos.BuscarxId(cotdet.getColor1() == null ? 0 : cotdet.getColor1());		
		JsonCotdet.put("color1n", cotdet.getColor1() == null ? "" : color.getColor());
		JsonCotdet.put("color1c", cotdet.getColor1() == null ? "" : "#"+color.getColor_est().trim());
		
		color = ccos.BuscarxId(cotdet.getColor2() == null ? 0 : cotdet.getColor2());		
		JsonCotdet.put("color2n", cotdet.getColor2() == null ? "" : color.getColor());
		JsonCotdet.put("color2c", cotdet.getColor2() == null ? "" : "#"+color.getColor_est().trim());
		
		color = ccos.BuscarxId(cotdet.getColor3() == null ? 0 : cotdet.getColor3());		
		JsonCotdet.put("color3n", cotdet.getColor3() == null ? "" : color.getColor());
		JsonCotdet.put("color3c", cotdet.getColor3() == null ? "" : "#"+color.getColor_est().trim());
		
		color = ccos.BuscarxId(cotdet.getColor4() == null ? 0 : cotdet.getColor4());		
		JsonCotdet.put("color4n", cotdet.getColor4() == null ? "" : color.getColor());
		JsonCotdet.put("color4c", cotdet.getColor4() == null ? "" : "#"+color.getColor_est().trim());
		
		color = ccos.BuscarxId(cotdet.getColor5() == null ? 0 : cotdet.getColor5());		
		JsonCotdet.put("color5n", cotdet.getColor5() == null ? "" : color.getColor());
		JsonCotdet.put("color5c", cotdet.getColor5() == null ? "" : "#"+color.getColor_est().trim());
		
		color = ccos.BuscarxId(cotdet.getColor6() == null ? 0 : cotdet.getColor6());		
		JsonCotdet.put("color6n", cotdet.getColor6() == null ? "" : color.getColor());
		JsonCotdet.put("color6c", cotdet.getColor6() == null ? "" : "#"+color.getColor_est().trim());
		
		color = ccos.BuscarxId(cotdet.getColor7() == null ? 0 : cotdet.getColor7());		
		JsonCotdet.put("color7n", cotdet.getColor7() == null ? "" : color.getColor());
		JsonCotdet.put("color7c", cotdet.getColor7() == null ? "" : "#"+color.getColor_est().trim());
		
		List<JSONObject> ListaJsonEsp = new ArrayList<JSONObject>();
		ecs.ListaEspDet(id, iddet).forEach(b ->{
			JSONObject JsonEsp = new JSONObject();
			JsonEsp.put("count", b.getCount());
			JsonEsp.put("iddetalle", b.getIddetalle());
			JsonEsp.put("idcotizacion", b.getIdcotizacion());
			JsonEsp.put("especialidad", ces.BuscaxId(b.getIdespecialidad()).getName());
			JsonEsp.put("costo", b.getCosto());
			JsonEsp.put("ajuste", b.getAjuste());
			JsonEsp.put("esquema", b.getEsquema());
			JsonEsp.put("cm", b.getCm());
			ListaJsonEsp.add(JsonEsp);
		}); 
		JsonCotdet.put("especialidades_cotizacion", ListaJsonEsp);
		
		JsonCotdet.put("estilo_caja", ccss.BuscarxId(cotdet.getIdcaja_sap()).getNombrecorto());
		Catalogo_resistencias_sap_vw objResis = new Catalogo_resistencias_sap_vw();
		objResis = crss.BuscarxId(cotdet.getIdresistencia_barca());
		JsonCotdet.put("resistencia", objResis.getResistencia());
		JsonCotdet.put("flauta", objResis.getCorrugado());
		JsonCotdet.put("papel", objResis.getColor());
		JsonCotdet.put("SUBREPORT_DIR", "/jasperreports/cotizador/");
		JsonCotdet.put("resis_cte", css.BuscarxId(cotdet.getResistencia_cte()).getNombre());
		
		Iterator itCot = JsonCot.keys();
		Iterator itCotDet = JsonCotdet.keys();
		
		while(itCot.hasNext()) {
            String key = (String) itCot.next();
            JsonTF.put(key, JsonCot.get(key));
        }
		
		while(itCotDet.hasNext()) {
            String key = (String) itCotDet.next();
            JsonTF.put(key, JsonCotdet.get(key));
        }
		
		 System.out.println(JsonTF);
	}
	
}
