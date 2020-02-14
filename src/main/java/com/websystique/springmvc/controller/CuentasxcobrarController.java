package com.websystique.springmvc.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.websystique.springmvc.service.cxc.conciliacion.ConciliacionService;

@Controller
@RequestMapping("/cuentasxcobrar")
public class CuentasxcobrarController {
	private Logger logger = Logger.getLogger(CuentasxcobrarController.class);
	
	@Autowired ConciliacionService cs;
	
	@RequestMapping(value = {"/conciliacion/gestionarchivos" }, method = RequestMethod.GET)
	public String gestionarchivos(ModelMap model) {
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		logger.info(AppController.getPrincipal() + " - gestionarchivos.");
		
		return "/cuentasxcobrar/conciliacion/gestionarchivos";
	}
	
	@RequestMapping(value = {"/conciliacion/gestionarchivos" }, method = RequestMethod.POST)
	public ResponseEntity<?> gestionarchivospost(ModelMap model,@RequestParam("file")  MultipartFile[] file, @RequestParam("fecha")  String fecha) throws Exception, Exception {
		logger.info(AppController.getPrincipal() + " - gestionarchivospost.");
		
		if(file.length == 0)
			return new ResponseEntity<Object>("Seleccione archivo...",HttpStatus.LENGTH_REQUIRED);
		else
			return LeerArchivo(file[0], fecha);
		
	}
	
	private ResponseEntity<?> LeerArchivo(MultipartFile file, String fecha) throws EncryptedDocumentException, InvalidFormatException, ParseException
	{
		try 
		{
			logger.error(AppController.getPrincipal() + " - /cuentasxcobrar/conciliacion/subir_archivo. - ");
			
			SimpleDateFormat oldPattern = new SimpleDateFormat("dd-MMM-yyyy", Locale.forLanguageTag("es-MX"));
			SimpleDateFormat newPattern = new SimpleDateFormat("yyyy-MM-dd", Locale.forLanguageTag("es-MX"));
			
			 BufferedReader br;
			 String line;
			 String[] arrayLine;
		     InputStream is = file.getInputStream();
		     br = new BufferedReader(new InputStreamReader(is));
		     br.readLine();
		     List<JSONObject> ListaJson = new ArrayList<JSONObject>();
		     while ((line = br.readLine()) != null) {
		    	 line = line.replace("\"", "");
		    	 arrayLine = line.split("\t");
		    	 if(arrayLine.length > 1)
		    	 {			         
			         JSONObject Json = new JSONObject();
			         Json.put("cfdi", arrayLine[0]);
			         Json.put("fecha_fac", newPattern.format(oldPattern.parse(arrayLine[1])));
			         Json.put("importe", Double.valueOf(arrayLine[4].replace(",", "")));
			         Json.put("fecha_conci", fecha);
			         cs.GrabarArchivo(arrayLine[0], newPattern.format(oldPattern.parse(arrayLine[1])), fecha, Double.valueOf(arrayLine[4].replace(",", "")));
			         ListaJson.add(Json);
		    	 }
		     }
		    
			return new ResponseEntity<Object>(ListaJson.toString(),HttpStatus.OK);
		} 
		catch (IOException e) 
		{
			logger.error(AppController.getPrincipal() + " - cuentasxcobrar/conciliacion/subir_archivo. - ", e);
			return new ResponseEntity<Object>("El archivo no tiene el formato correcto...",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	    
	}
}
