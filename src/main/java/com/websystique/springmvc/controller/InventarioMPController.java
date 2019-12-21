package com.websystique.springmvc.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.websystique.springmvc.excel.ExcelInventarioMPConteo;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.mp.inventario.Catalogo_inventarios_mp;
import com.websystique.springmvc.model.mp.inventario.Conteo_inventario_mp;
import com.websystique.springmvc.service.UserService;
import com.websystique.springmvc.service.mp.inventario.Catalogo_inventarios_mpService;
import com.websystique.springmvc.service.mp.inventario.Conteo_inventario_mpService;

@Controller
@RequestMapping("/materia_prima/")
public class InventarioMPController {
	private Logger logger = Logger.getLogger(InventarioMPController.class);
	
	@Autowired Catalogo_inventarios_mpService cimps;
	@Autowired Conteo_inventario_mpService cmps;
	@Autowired UserService us;
	
	@RequestMapping(value = {"/genrentemp/catalogo_inventarios"}, method = RequestMethod.GET)
	public String inventarioabc(ModelMap model, @RequestParam(value = "id", defaultValue = "0", required = false) Integer id) 
	{
		try 
		{
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			logger.info(AppController.getPrincipal() + " - genrentemp/catalogo_inventarios.");
			
			if(id > 0)
				model.addAttribute("Catalogo", cimps.BuscarxId(id));
			else
				model.addAttribute("Catalogo", new Catalogo_inventarios_mp());
			
			List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
			Map<String,String> mOrd =  new HashMap<String, String>();
			mOrd.put("1", "fecha_fin");
			model.addAttribute("ListaCatalogos", cimps.BuscarxParamsList(Params, mOrd));
		}
		catch(Exception e)
		{
			model.addAttribute("error",e.getMessage());
			logger.error(AppController.getPrincipal() + " - genrentemp/catalogo_inventarios. - ", e);
		}
		return "/materia_prima/inventario/catalogo_inventarios_mp";
	}
	
	@RequestMapping(value = {"/genrentemp/catalogo_inventarios"}, method = RequestMethod.POST)
	public String inventarioabc(@Valid @ModelAttribute("Catalogo") Catalogo_inventarios_mp Catalogo, BindingResult result,ModelMap model) {
		try 
		{
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			logger.info(AppController.getPrincipal() + " - genrentemp/catalogo_inventarios.");
			
			if (result.hasErrors()) {
				return "/materia_prima/inventario/catalogo_inventarios_mp";
			}
			User user = us.findBySSO(AppController.getPrincipal());
			java.util.Date date = new java.util.Date();
			
			if(Catalogo.getId() == null)
			{
				Catalogo.setUsuario_insert(user.getId());
				Catalogo.setFecha_insert(date);
				cimps.Guardar(Catalogo);
			}
			else
			{
				Catalogo.setUsuario_update(user.getId());
				Catalogo.setFecha_update(date);
				cimps.Actualizar(Catalogo);
			}
			
			model.addAttribute("Catalogo", new Catalogo_inventarios_mp());
			List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
			Map<String,String> mOrd =  new HashMap<String, String>();
			mOrd.put("1", "fecha_fin");
			model.addAttribute("ListaCatalogos", cimps.BuscarxParamsList(Params, mOrd));
		}
		catch(Exception e)
		{
			model.addAttribute("error",e.getMessage());
			logger.error(AppController.getPrincipal() + " - genrentemp/catalogo_inventarios. - ", e);
		}
		return "/materia_prima/inventario/catalogo_inventarios_mp";
	}
	
	@RequestMapping(value = {"/genrentemp/gestion_archivo"}, method = RequestMethod.GET)
	public String gestion_archivoget(ModelMap model, @RequestParam(value = "idinv", defaultValue = "0", required = true) Integer idinv) 
	{
		try 
		{
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			logger.info(AppController.getPrincipal() + " - genrentemp/subir_archivo.");
			model.addAttribute("idinv", idinv);
			model.addAttribute("Conteo", new Conteo_inventario_mp());
			List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
			Params.add(new ParamsGeneral(1,"id_inv",idinv,"EQ"));
			Map<String,String> mOrd =  new HashMap<String, String>();
			model.addAttribute("ListaConteo", cmps.BuscarxParamsList(Params, mOrd));
		}
		catch(Exception e)
		{
			model.addAttribute("error",e.getMessage());
			logger.error(AppController.getPrincipal() + " - genrentemp/subir_archivo. - ", e);
		}
		return "/materia_prima/inventario/subir_archivo";
	}
	
	@RequestMapping(value = {"/genrentemp/gestion_archivo"}, method = RequestMethod.POST)
	public String gestion_archivopost(@Valid @ModelAttribute("Conteo") Conteo_inventario_mp Conteo, BindingResult result,ModelMap model) 
	{
		try 
		{
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			logger.info(AppController.getPrincipal() + " - genrentemp/subir_archivo.");
			model.addAttribute("idinv", Conteo.getId_inv());
			if (result.hasErrors()) {
				return "/materia_prima/inventario/subir_archivo";
			}
			User user = us.findBySSO(AppController.getPrincipal());
			java.util.Date date = new java.util.Date();
			Conteo.setUsuario_insert(user.getId());
			Conteo.setFecha_insert(date);
			cmps.Guardar(Conteo);
			model.addAttribute("Conteo", new Conteo_inventario_mp());
			List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
			Params.add(new ParamsGeneral(1,"id_inv",Conteo.getId_inv(),"EQ"));
			Map<String,String> mOrd =  new HashMap<String, String>();
			model.addAttribute("ListaConteo", cmps.BuscarxParamsList(Params, mOrd));
			model.addAttribute("mensaje", "Rollo grabado correctamente...");
		}
		catch(Exception e)
		{
			model.addAttribute("error",e.getMessage());
			logger.error(AppController.getPrincipal() + " - genrentemp/subir_archivo. - ", e);
		}
		return "/materia_prima/inventario/subir_archivo";
	}
	
	@RequestMapping(value = {"/genrentemp/subir_archivo" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> subir_archivo(@RequestParam("file")  MultipartFile[] file, @RequestParam("id") Integer id,
			ModelMap model,HttpServletResponse response,HttpServletRequest request) throws EncryptedDocumentException, InvalidFormatException {
		
		if(cimps.BuscarxId(id) == null)
			return new ResponseEntity<Object>("No existe ese inventario...",HttpStatus.BAD_REQUEST);
		else
		{
			List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
			Params.add(new ParamsGeneral(1,"id_inv",id,"EQ"));
			Map<String,String> mOrd =  new HashMap<String, String>();
			
			if(cmps.BuscarxParamsList(Params, mOrd).stream().filter(a -> a.getConteo() != null).count() > 0)
				return new ResponseEntity<Object>("Ya existe conteo, no debe subir el archivo...",HttpStatus.INTERNAL_SERVER_ERROR);
			else
			{
				if(file.length == 0)
					return new ResponseEntity<Object>("Seleccione archivo...",HttpStatus.LENGTH_REQUIRED);
				else
					return LeerExcel(file[0], id);
			}
		}
	}
	
	private ResponseEntity<?> LeerExcel(MultipartFile file, Integer id_inv) throws EncryptedDocumentException, InvalidFormatException
	{
		//File convFile = null;
		Workbook workbook = null;
		Sheet sheet = null;
		byte[] result = null;
		InputStream is = null;
		try 
		{
			logger.error(AppController.getPrincipal() + " - genrentemp/subir_archivo. - ");
			//convFile = new File( file.getOriginalFilename());
			result = file.getBytes(); 
			is = new ByteArrayInputStream(result);
		    workbook = WorkbookFactory.create(is);
		    sheet = workbook.getSheetAt(0);
		    Iterator<Row> rowIterator = sheet.rowIterator();
			Integer ban = 0;
			List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
			Params.add(new ParamsGeneral(1,"id_inv",id_inv,"EQ"));
			Map<String,String> mOrd =  new HashMap<String, String>();
			
			cmps.Eliminar(cmps.BuscarxParamsList(Params, mOrd));
			
			DataFormatter formatter = new DataFormatter();
			User user = us.findBySSO(AppController.getPrincipal());
			java.util.Date date = new java.util.Date();
			
			List<Conteo_inventario_mp> ListaConteo = new ArrayList<Conteo_inventario_mp>();
		    while (rowIterator.hasNext()) 
		    {
		    	Row row = rowIterator.next();
		    	if(ban > 3)
		    	{
		    		Conteo_inventario_mp con = new Conteo_inventario_mp();
		    		con.setId_inv(id_inv);
		    		con.setRollo_id(row.getCell(0).getStringCellValue());
		    		con.setTipo(row.getCell(1).getStringCellValue());
		    		con.setAncho(Double.valueOf(formatter.formatCellValue(row.getCell(2))));
		    		con.setPeso(Double.valueOf(formatter.formatCellValue(row.getCell(3))));
		    		//con.setUbicacion(row.getCell(4).getStringCellValue());
		    		con.setFecha_insert(date);
		    		con.setUsuario_insert(user.getId());
		    		ListaConteo.add(con);
		    	}
		    	ban++;
		    }
		    if(ListaConteo.size() > 0)
		    	cmps.Guardar(ListaConteo);
		    
			return new ResponseEntity<Object>("Archivo subido correctamente...",HttpStatus.OK);
		} 
		catch (IOException e) 
		{
			logger.error(AppController.getPrincipal() + " - genrentemp/subir_archivo. - ", e);
			return new ResponseEntity<Object>("El archivo no tiene el formato correcto...",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	    
	}
	
	@RequestMapping(value = {"/inventario/conteo"}, method = RequestMethod.GET)
	public String conteoget(ModelMap model, @RequestParam(value = "rolloid", defaultValue = "", required = false) String rolloid) 
	{
		try 
		{
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			logger.info(AppController.getPrincipal() + " - inventario/conteo.");
			if(rolloid.equals(""))
				model.addAttribute("Conteo", new Conteo_inventario_mp());
			else
			{
				Integer maxInv = cimps.Maximo("id"); 
				if(maxInv == null)
				{
					model.addAttribute("Conteo", new Conteo_inventario_mp());
					model.addAttribute("error","No hay inventarios disponibles");
				}
				else
				{
					List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
					Params.add(new ParamsGeneral(1,"rollo_id",rolloid,"EQ"));
					Params.add(new ParamsGeneral(1,"id_inv",maxInv,"EQ"));
					Conteo_inventario_mp Conteo = new Conteo_inventario_mp();
					Conteo = cmps.BuscarxParamsObj(Params);
					model.addAttribute("Conteo", Conteo == null ? new Conteo_inventario_mp() : Conteo);
				}
			}
		}
		catch(Exception e)
		{
			model.addAttribute("error",e.getMessage());
			logger.error(AppController.getPrincipal() + " - inventario/conteo. - ", e);
		}
		return "/materia_prima/inventario/conteomp";
	}
	
	@RequestMapping(value = {"/inventario/conteo"}, method = RequestMethod.POST)
	public String conteopost(@Valid @ModelAttribute("Conteo") Conteo_inventario_mp Conteo, BindingResult result,ModelMap model) 
	{
		try 
		{
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			logger.info(AppController.getPrincipal() + " - inventario/conteo.");
			if (result.hasErrors()) {
				return "/materia_prima/inventario/conteomp";
			}
			User user = us.findBySSO(AppController.getPrincipal());
			java.util.Date date = new java.util.Date();
			Conteo.setUsuario_insert(user.getId());
			Conteo.setFecha_insert(date);
			cmps.Actualizar(Conteo);
			model.addAttribute("mensaje", "Conteo grabado correctamente...");
		}
		catch(Exception e)
		{
			model.addAttribute("error",e.getMessage());
			logger.error(AppController.getPrincipal() + " - inventario/conteo. - ", e);
		}
		return "/materia_prima/inventario/conteomp";
	}
	
	@RequestMapping(value = { "/genrentemp/descargar_excel" },method=RequestMethod.GET)
	public ModelAndView desempeniomesxcteexcel(HttpServletRequest req, HttpServletResponse res, Integer id_inv)
	{
		List<Conteo_inventario_mp> ConteoLista = new ArrayList<Conteo_inventario_mp>();
		
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"id_inv",id_inv,"EQ"));
		Map<String,String> mOrd =  new HashMap<String, String>();
		ConteoLista = cmps.BuscarxParamsList(Params, mOrd);
		return new ModelAndView(new ExcelInventarioMPConteo(), "listaexcel", ConteoLista);
	}
	
}
