package com.websystique.springmvc.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.tarjetas.cotizador.Cotizador;
import com.websystique.springmvc.model.tarjetas.fabricacion.Tarjeta_fabricacion;
import com.websystique.springmvc.model.tarjetas.fabricacion.Tarjeta_fabricacion_Busqueda;
import com.websystique.springmvc.model.tarjetas.fabricacion.Tarjetas_fabricacion_imagenes;
import com.websystique.springmvc.service.UserService;
import com.websystique.springmvc.service.tarjetas.cotizador.CotizadorService;
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
	
	@RequestMapping(value = {"/ingenieria/tarjeta_fabricacion" }, method = RequestMethod.GET)
	public String tarjeta_fabricacion(ModelMap model, @RequestParam(value = "folio", defaultValue = "", required = false) String folio) {
		Tarjeta_fabricacion tf = new Tarjeta_fabricacion();
		if(!folio.trim().equals(""))
		{
			tf = tfs.BuscarxFolio(folio);
			tf.setTarjeta_img(tfis.BuscarxIdCotidDert(tf.getIdcotizacion(), tf.getIddetalle()));
		}
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		model.addAttribute("tarjeta_fabricacion", (tf == null ? (new Tarjeta_fabricacion()): tf));
		logger.info(AppController.getPrincipal() + " - tarjeta_fabricacion.");
				
		return "/tarjetas/fabricacion/tarjeta_fabricacion";
	}
	
	@RequestMapping(value = {"/ingenieria/tarjeta_fabricacion_buscar" }, method = RequestMethod.GET)
	public String tarjeta_fabricacion_buscar(ModelMap model,@RequestParam("idcot") Integer idcot,@RequestParam("foliotf") String foliotf) {
		List<Tarjeta_fabricacion_Busqueda> Lista = new ArrayList<Tarjeta_fabricacion_Busqueda>();
		Lista = tfs.TarjetaBusqueda(idcot, foliotf);
		model.addAttribute("lista", Lista);
		
		logger.info(AppController.getPrincipal() + " - tarjeta_fabricacion_buscar.");
				
		return "/tarjetas/fabricacion/tarjeta_fabricacion_buscar";
	}
	
	@RequestMapping(value = {"/ingenieria/tarjeta_fabricacion" }, method = RequestMethod.POST)
	public String tarjeta_fabricacionpost(@Valid @ModelAttribute("tarjeta_fabricacion") Tarjeta_fabricacion tarjeta_fabricacion, BindingResult result, ModelMap model) {
		try 
		{
			tarjeta_fabricacion.setBan(1);
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			model.addAttribute("tarjeta_fabricacion", tarjeta_fabricacion);
			
			for (ObjectError error : result.getAllErrors()) {
			       String fieldErrors [] = error.getCodes();
			       System.out.println(fieldErrors[0]);
			   }
			 
			if (result.hasErrors() )
			{
				return "/tarjetas/fabricacion/tarjeta_fabricacion";
			}
			
			logger.info(AppController.getPrincipal() + " - tarjeta_fabricacionpost.");
			tarjeta_fabricacion.setTarjeta_img(tfis.BuscarxIdCotidDert(tarjeta_fabricacion.getIdcotizacion(), tarjeta_fabricacion.getIddetalle()));
			tfs.Actualizar(tarjeta_fabricacion);
		}
		catch(Exception e)
		{
			model.addAttribute("mensajes", e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage());
			logger.info(AppController.getPrincipal() + " - cotizadotpost. " + e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage());
		}
		return "/tarjetas/fabricacion/tarjeta_fabricacion";
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
            //System.out.println(cama);
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
			
			tf = tfs.BuscarxFolio(folio_tarjeta);
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
			List<Tarjeta_fabricacion> ListaTar = tfs.BuscarXIdCot(idcotizacion);
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
		model.addAttribute("tarjetas",tfs.BuscarXAut("usuario_aut_diseniador", "fecha_aut_diseniador","usuario_aut_calidad","fecha_aut_calidad"));
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
		model.addAttribute("tarjetas",tfs.BuscarXAut("usuario_aut_calidad", "fecha_aut_calidad","usuario_aut_produccion","fecha_aut_produccion"));
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
	
	@RequestMapping(value = {"/ingenieria/tarjeta_aut_ingenieria" }, method = RequestMethod.GET)
	public String tarjeta_aut_ingenieria(ModelMap model) {

		model.addAttribute("loggedinuser", AppController.getPrincipal());
		model.addAttribute("tarjetas",tfs.BuscarXAut("usuario_aut_produccion", "fecha_aut_produccion","usuario_aut_ing","fecha_aut_ing"));
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
		List<Tarjeta_fabricacion> Lista = tfs.BuscarXAut("usuario_aut_ing", "fecha_aut_ing","usuario_aut_cliente","fecha_aut_cliente");
		Lista.stream().forEach(tar -> {
			if(tfs.BuscarXIdCot(tar.getIdcotizacion()).stream().filter(a -> a.getUsuario_aut_ing() == null && a.getFecha_aut_ing() == null).count() > 0)
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
			tf = tfs.BuscarxFolio(folio);
			
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
	
}
