package com.websystique.springmvc.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

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
import com.google.gson.JsonObject;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.costos.controlpesomerma.Catalogo_taras;
import com.websystique.springmvc.model.costos.controlpesomerma.Control_peso_merma;
import com.websystique.springmvc.service.UserService;
import com.websystique.springmvc.service.costos.controlpesomerma.Catalogo_tarasService;
import com.websystique.springmvc.service.costos.controlpesomerma.Control_peso_mermaService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

//import org.springframework.validation.ObjectError;

@Controller
@RequestMapping("/costos")
public class Control_peso_mermaController {

	private Logger logger = Logger.getLogger(Control_peso_mermaController.class);
	
	@Autowired
	Catalogo_tarasService cts;
	@Autowired
	Control_peso_mermaService cpms;
	@Autowired
	UserService us;
	
	@RequestMapping(value = {"/controlpesomerma/controlmermaabc" }, method = RequestMethod.GET)
	public String herramentalesabc(ModelMap model) {
		try 
		{
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			model.addAttribute("ListaTaras", cts.ListaTaras());
			model.addAttribute("control_peso_merma", new Control_peso_merma());
			model.addAttribute("ListaControlPeso", ListaPesosMerma());
			
			logger.info(AppController.getPrincipal() + " - controlmermaabc.");
		}
		catch(Exception e)
		{
			logger.error(AppController.getPrincipal() + " - controlmermaabc. - " + e.getMessage());
		}
		return "/costos/controlpesomerma/controlmermaabc";
	}
	
	@RequestMapping(value = {"/controlpesomerma/calcularpeso"}, method = RequestMethod.GET)
	public @ResponseBody String buscarciudadxestado(HttpServletRequest req, HttpServletResponse res)
	   throws Exception {
		 
		try
		{
			Path origenPath = Paths.get("\\\\192.169.1.194\\Bascula\\Peso.txt");
	        Path destinoPath = Paths.get("\\\\192.169.1.194\\Bascula\\PESO_MERMA.txt");
	        Files.copy(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
	        
			Integer id_tara =Integer.parseInt(req.getParameter("id_tara"));
			Double peso_real = 0.0;
			BufferedReader reader;
			reader = new BufferedReader(new FileReader("\\\\192.169.1.194\\Bascula\\PESO_MERMA.txt"));
			String line = reader.lines().reduce((first, second) -> second).orElse("");
			String ult_peso = "";
			for(int i = 0; i < line.length(); i++) {
				
				if(line.charAt(i) == '.' || String.valueOf(line.charAt(i)).matches("[0-9]"))
					ult_peso = ult_peso + line.charAt(i);
			}
			
			Double pesototal = ult_peso.length() > 0 ? Double.valueOf(ult_peso) : 0.0;
			reader.close();
			
			if(id_tara > 0) {
				Catalogo_taras objTara = cts.BuscarxId(id_tara);
				peso_real = pesototal -  objTara.getPesokg();
			}
			
			DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.getDefault());
			formatSymbols.setDecimalSeparator('.');
			DecimalFormat decimal2 = new DecimalFormat("###########0.##", formatSymbols);
			
			JsonObject object = new JsonObject();
			object.addProperty("peso_real", decimal2.format(peso_real));
			object.addProperty("pesototal", pesototal);
			
			return object.toString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error(AppController.getPrincipal() + " - calcularpeso. - " + e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage());
			return e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage();
		}
	
	}
	
	@RequestMapping(value = {"/controlpesomerma/controlmermaabc" }, method = RequestMethod.POST)
	public String herramentalesabcpost(@Valid @ModelAttribute("control_peso_merma") Control_peso_merma control_peso_merma,BindingResult result,ModelMap model) {
		try 
		{
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			model.addAttribute("ListaTaras", cts.ListaTaras());
			User user = us.findBySSO(AppController.getPrincipal());
			control_peso_merma.setUsuario_empacador(user.getId());
			java.util.Date date = new java.util.Date();
			control_peso_merma.setFecha_registro(date);
			logger.info(AppController.getPrincipal() + " - herramentalesabcpost.");
			
			if (result.hasErrors() )
			{
				return "/costos/controlpesomerma/controlmermaabc";
			}
			
			control_peso_merma.setPesokg_tara(cts.BuscarxId(control_peso_merma.getIdtara()).getPesokg());
			cpms.Guardar(control_peso_merma);
			model.addAttribute("control_peso_merma", control_peso_merma);
			model.addAttribute("ListaControlPeso", ListaPesosMerma());
			return "/costos/controlpesomerma/controlmermaabc";
			
		}
		catch(Exception e)
		{
			logger.error(AppController.getPrincipal() + " - herramentalesabcpost. - " + e.getMessage());
			return "/costos/controlpesomerma/controlmermaabc";
		}
	}
	
	private Object[] ListaPesosMerma() throws ParseException
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd");
		
		String dateS = simpleDateFormat.format(new Date());
		Date dateA = simpleDateFormat.parse(dateS);
		
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"fecha_registro", dateA, "GTE"));
		
		List<JsonObject> ListaJson = new ArrayList<JsonObject>();
		List<Control_peso_merma> Lista = cpms.ListaControlPeso(Params);
		if(Lista.size() > 0) {
			Lista.forEach(a -> {
				JsonObject object = new JsonObject();
				object.addProperty("id", a.getId());
				object.addProperty("pedido", a.getPedido());
				User user = us.findById(a.getUsuario_empacador());
				object.addProperty("usuario_empacador", user.getFirstName() + " " + user.getLastName());
				object.addProperty("idtara", cts.BuscarxId( a.getIdtara()).getDescripcion());
				object.addProperty("pesokg_tara", a.getPesokg_tara());
				object.addProperty("pesokg_total", a.getPesokg_total());
				object.addProperty("pesokg_real", a.getPesokg_real());
				object.addProperty("fecha_registro", a.getFecha_registro().toString());
				object.addProperty("comentarios", a.getComentarios());
				ListaJson.add(object);
			});
		}
		
		Gson gson = new Gson();
		return gson.fromJson(gson.toJson(ListaJson), Object[].class);
	}
	
	////////////////////////////////////IMPRIMIR JASPER/////////////////////////////
	@RequestMapping(value = "/controlpesomerma/imprimirrep", method = RequestMethod.GET)
	@ResponseBody
	public void imprimirrep(HttpServletResponse response,HttpServletRequest request,ModelMap model,@RequestParam("id") Integer id) throws JRException, IOException {
		String msj = "";
		try
		{
			InputStream jasperStream = this.getClass().getResourceAsStream("/jasperreports/costos/ControlPesoMerma.jasper");
			Map<String,Object> params = new HashMap<>();			
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
			
			Control_peso_merma objMerma = cpms.BuscarxId(id);
			params.put("fecha_registro", objMerma.getFecha_registro());
			User user = us.findById(objMerma.getUsuario_empacador());
			params.put("nombre", user.getFirstName() + " " + user.getLastName());
			params.put("descripcion", cts.BuscarxId(objMerma.getIdtara()).getDescripcion());
			params.put("pesokg_tara", objMerma.getPesokg_tara());
			params.put("pesokg_total", objMerma.getPesokg_total());
			params.put("pesokg_real", objMerma.getPesokg_real());
			params.put("comentarios", objMerma.getComentarios());
			Collection<Control_peso_merma> coll = new ArrayList<Control_peso_merma>();
			coll.add(objMerma);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(coll ,false));
			
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "inline");
			
			final OutputStream outStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
			logger.info(AppController.getPrincipal() + " - imprimirrep :"+ msj);
		}
		catch(Exception e)
		{
			msj = e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage();
			logger.info(AppController.getPrincipal() + " - imprimirrep :"+ msj);
		}
	}
	
	@RequestMapping(value = {"/controlpesomerma/historialcontrolmermaabc" }, method = RequestMethod.GET)
	public String historialcontrolmermaabc(ModelMap model,
			@RequestParam(value = "pedido", defaultValue = "", required = false) String pedido,
			@RequestParam(value = "idEmp", defaultValue = "0", required = false) Integer idEmp,
			@RequestParam(value = "idTara", defaultValue = "0", required = false) Integer idTara,
			@RequestParam(value = "fecha", defaultValue = "", required = false) String fecha) {
		try 
		{
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			model.addAttribute("ListaTaras", cts.ListaTaras());
			model.addAttribute("ListaControlPeso", BuscaPesosMerma(pedido,idEmp,idTara,fecha));
			
			List<User> users = null;		
			users = us.findAllUsers().stream()
			.filter(a -> a.getUserProfiles().stream()
					.filter(b -> b.getType().equals("EMPACADOR")).count() > 0).collect(Collectors.toList());
			
			model.addAttribute("ListaEmp", users);
			
			logger.info(AppController.getPrincipal() + " - historialcontrolmermaabc.");
		}
		catch(Exception e)
		{
			logger.error(AppController.getPrincipal() + " - historialcontrolmermaabc. - " + e.getMessage());
		}
		return "/costos/controlpesomerma/historialcontrolmermaabc";
	}
	
	private Object[] BuscaPesosMerma(String pedido,Integer idEmp, Integer idTara, String fecha) throws ParseException
	{
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		
		if(fecha.length() > 0) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd");
			Date dateA = simpleDateFormat.parse(fecha);
			Params.add(new ParamsGeneral(1,"fecha_registro", dateA, "GTE"));
		}
		
		if(pedido.length() > 0)
			Params.add(new ParamsGeneral(1,"pedido", pedido, "EQ"));
		
		if(idEmp > 0)
			Params.add(new ParamsGeneral(1,"usuario_empacador", idEmp, "EQ"));
		
		if(idTara > 0)
			Params.add(new ParamsGeneral(1,"idtara", idTara, "EQ"));
			
		List<JsonObject> ListaJson = new ArrayList<JsonObject>();
		if(Params.size() > 0) {
			List<Control_peso_merma> Lista = cpms.ListaControlPeso(Params);
		if(Lista.size() > 0) {
			Lista.forEach(a -> {
				JsonObject object = new JsonObject();
				object.addProperty("id", a.getId());
				object.addProperty("pedido", a.getPedido());
				User user = us.findById(a.getUsuario_empacador());
				object.addProperty("usuario_empacador", user.getFirstName() + " " + user.getLastName());
				object.addProperty("idtara", cts.BuscarxId( a.getIdtara()).getDescripcion());
				object.addProperty("pesokg_tara", a.getPesokg_tara());
				object.addProperty("pesokg_total", a.getPesokg_total());
				object.addProperty("pesokg_real", a.getPesokg_real());
				object.addProperty("fecha_registro", a.getFecha_registro().toString());
				object.addProperty("comentarios", a.getComentarios());
				ListaJson.add(object);
			});
		}
		}
		Gson gson = new Gson();
		return gson.fromJson(gson.toJson(ListaJson), Object[].class);
	}
	
}
