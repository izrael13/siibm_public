package com.websystique.springmvc.controller;

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
import com.websystique.springmvc.model.tarjetas.fabricacion.Tarjeta_fabricacion;
import com.websystique.springmvc.model.tarjetas.fabricacion.Tarjeta_fabricacion_Busqueda;
import com.websystique.springmvc.model.tarjetas.fabricacion.Tarjetas_fabricacion_imagenes;
import com.websystique.springmvc.service.UserService;
import com.websystique.springmvc.service.tarjetas.fabricacion.Tarjeta_fabricacionService;
import com.websystique.springmvc.service.tarjetas.fabricacion.Tarjetas_fabricacion_imagenesService;

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
				return "/tarjetas/cotizador/cotizador";
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
	
	
	
}
