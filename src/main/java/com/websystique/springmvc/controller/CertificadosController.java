package com.websystique.springmvc.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.json.JSONObject;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.certificados.Certificados;
import com.websystique.springmvc.model.certificados.Liberacioncm;
import com.websystique.springmvc.model.certificados.Remisiones_para_certificado;
import com.websystique.springmvc.model.certificados.Tf_para_certificado;
import com.websystique.springmvc.service.UserService;
import com.websystique.springmvc.service.certificados.CertificadosAllDataService;
import com.websystique.springmvc.service.certificados.CertificadosService;
import com.websystique.springmvc.service.certificados.LiberacioncmService;
import com.websystique.springmvc.service.certificados.Remisiones_para_certificadoService;
import com.websystique.springmvc.service.certificados.Tf_para_certificadoService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
@RequestMapping("/certificados")
public class CertificadosController {
	
	private Logger logger = Logger.getLogger(CertificadosController.class);
	
	@Autowired CertificadosService cs;
	@Autowired Remisiones_para_certificadoService rpcs;
	@Autowired UserService us;
	@Autowired CertificadosAllDataService certalldatas;
	@Autowired Tf_para_certificadoService tfpc;
	@Autowired LiberacioncmService lcms;
	
	@RequestMapping(value = {"/calidad/certificadosabc" }, method = RequestMethod.GET)
	public String cartificadosabc(ModelMap model, @RequestParam(value = "remi", defaultValue = "", required = false) String remi,
			 									  @RequestParam(value = "itemcode", defaultValue = "", required = false) String itemcode)
	{		
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		logger.info(AppController.getPrincipal() + " - embarques/certificadosabc.");
		Certificados cer = new Certificados();
		Remisiones_para_certificado rem = new Remisiones_para_certificado();
		Tf_para_certificado tfs = new Tf_para_certificado();
		
		if(!remi.equals("") && !itemcode.equals("") )
		{
			List<ParamsGeneral> ParamsR = new ArrayList<ParamsGeneral>();
			ParamsR.add(new ParamsGeneral(1,"numatcard",remi,"EQ"));
			ParamsR.add(new ParamsGeneral(1,"itemcodesi",itemcode,"EQ"));
			rem = rpcs.BuscarXParamsObj(ParamsR);
			
			List<ParamsGeneral> ParamsC = new ArrayList<ParamsGeneral>();
			ParamsC.add(new ParamsGeneral(1,"remision",remi,"EQ"));
			ParamsC.add(new ParamsGeneral(1,"itemcodecert",itemcode,"EQ"));
			ParamsC.add(new ParamsGeneral(1,"estatus",1,"EQ"));
			cer = cs.BuscarxParamsObj(ParamsC);
			
			if(rem != null)
			{
				List<ParamsGeneral> ParamsT = new ArrayList<ParamsGeneral>();
				ParamsT.add(new ParamsGeneral(1,"itemcode",rem.getItemcodesi(),"EQ"));
				ParamsT.add(new ParamsGeneral(1,"tf",rem.getTfi(),"EQ"));
				tfs = tfpc.BuscarxParams(ParamsT);
			}	
			
		}
		
		model.addAttribute("Certificado",(cer == null ? new Certificados() : cer));
		model.addAttribute("Remision",rem == null ? new Remisiones_para_certificado() : rem);
		model.addAttribute("Tarjeta",tfs == null ? new Tf_para_certificado() : tfs);
		
		return "/certificados/certificadosabc";
	}
	
	@RequestMapping(value = {"/calidad/buscarremisiones" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> buscarremisiones(ModelMap model, @RequestParam("rem") String rem)
	{
		try
		{
			logger.info(AppController.getPrincipal() + " - calidad/buscarremisiones.");		
			List<ParamsGeneral> ParamsR = new ArrayList<ParamsGeneral>();
			ParamsR.add(new ParamsGeneral(1,"numatcard",rem,"EQ"));
			Map<String,String> mOrd =  new HashMap<String, String>();
			List<Remisiones_para_certificado> RemList = rpcs.BuscarXParamsList(ParamsR, mOrd);
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.serializeNulls().setDateFormat("yyyy-MM-dd HH:mm").create();
			return new ResponseEntity<Object>(gson.toJson(RemList), HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = {"/calidad/certificadosabc" }, method = RequestMethod.POST)
	public String certificadosabc(@Valid @ModelAttribute("Certificado") Certificados cer, BindingResult result, ModelMap model, HttpServletRequest request)
	{
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		logger.info(AppController.getPrincipal() + " - certificadosabcpost.");
		model.addAttribute("Certificado",cer);
		
		Remisiones_para_certificado rem = new Remisiones_para_certificado();
		Tf_para_certificado tfs = new Tf_para_certificado();
		
		List<ParamsGeneral> ParamsR = new ArrayList<ParamsGeneral>();
		ParamsR.add(new ParamsGeneral(1,"numatcard",cer.getRemision(),"EQ"));
		ParamsR.add(new ParamsGeneral(1,"tfi",cer.getFolio_tf(),"EQ"));
		rem = rpcs.BuscarXParamsObj(ParamsR);
		model.addAttribute("Remision", rem);
		
		if(rem != null)
		{
			List<ParamsGeneral> ParamsT = new ArrayList<ParamsGeneral>();
			ParamsT.add(new ParamsGeneral(1,"itemcode",rem.getItemcodesi(),"EQ"));
			ParamsT.add(new ParamsGeneral(1,"tf",rem.getTfi(),"EQ"));
			tfs = tfpc.BuscarxParams(ParamsT);
		}
		model.addAttribute("Tarjeta",tfs == null ? new Tf_para_certificado() : tfs);
		
		User user = us.findBySSO(AppController.getPrincipal());
		java.util.Date date = new java.util.Date();
		cer.setEstatus(true);
		cer.setFecha_update(date);
		cer.setUsuario_update(user.getId());
		
		if (result.hasErrors())
			return "/certificados/certificadosabc";	
		
		if(cer.getId() == null)
		{
			cer.setFecha_certificado(date);
			cer.setUsuario_insert(user.getId());
			cs.Guardar(cer);
		}
		else
			cs.Actualizar(cer);	
		
		model.addAttribute("mensaje", "Certificado guardado exitosamente.");
		return "/certificados/certificadosabc";
	}
	
	@RequestMapping(value = {"/calidad/cancelarcer" }, method = RequestMethod.POST)
	public @ResponseBody String enviarboceto(ModelMap model, @RequestParam("id") Integer id)
	{
		try
		{
			User user = us.findBySSO(AppController.getPrincipal());
			java.util.Date date = new java.util.Date();		
			Certificados cer = new Certificados();
			cer = cs.BuscarxId(id);
			cer.setEstatus(false);
			cer.setFecha_cancel(date);
			cer.setUsuario_cancel(user.getId());
			cs.Actualizar(cer);
			return "OK";
		}
		catch(Exception e)
		{
			return e.getMessage()+""+e.getLocalizedMessage()+""+e.getCause()+""+e.getStackTrace();
		}
	}
	
	@RequestMapping(value = "/calidad/certificadosimpr", method = RequestMethod.GET)
    @ResponseBody
    public void getRpt1(HttpServletResponse response,HttpServletRequest request,ModelMap model,@RequestParam("id") Integer id)
    {
		try
		{
			InputStream jasperStream = this.getClass().getResourceAsStream("/jasperreports/certificados/Certificado_calidad.jasper");

			//System.out.println(stripAccents(certalldatas.BuscarXIDCert(id).toString()));
			Map<String,Object> params = new HashMap<>();
			
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
			
			ByteArrayInputStream jsonDataStream = new ByteArrayInputStream(stripAccents(certalldatas.BuscarXIDCert(id).toString()).getBytes("UTF-8"));
			JsonDataSource dataSource = new JsonDataSource(jsonDataStream);
			params.put("Imagen",request.getServletContext().getRealPath("/"));
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
			
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "inline");
			
			    final OutputStream outStream = response.getOutputStream();
			    JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
			logger.info(AppController.getPrincipal() + " - certificadosimpr :");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.info(AppController.getPrincipal() + " - certificadosimpr :"+ e.getMessage()+ " " + e.getStackTrace() + " "+ e.getCause() + " " + e.getLocalizedMessage());
		}
    }
	
	private String stripAccents(String s) 
	{
	    s = Normalizer.normalize(s, Normalizer.Form.NFD);
	    s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
	    return s;
	}
	
	@RequestMapping(value = {"/ingenieria/liberacioncm" }, method = RequestMethod.GET)
	public String liberacioncm(ModelMap model, @RequestParam(value = "itemcode", defaultValue = "", required = false) String itemcode)
	{		
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		logger.info(AppController.getPrincipal() + " - ingenieria/liberacioncmget.");
		Liberacioncm lcm = new Liberacioncm();
		//Remisiones_para_certificado rem = new Remisiones_para_certificado();
		Tf_para_certificado tfs = new Tf_para_certificado();
		
		//List<ParamsGeneral> ParamsR = new ArrayList<ParamsGeneral>();

		//if(!itemcode.equals("")) {
			//ParamsR.add(new ParamsGeneral(1,"itemcodesi",itemcode,"EQ"));
			//rem = rpcs.BuscarXParamsObj(ParamsR);

			//if(rem != null)
			//{				
				lcm = lcms.BuscarxId(itemcode);				
				
				List<ParamsGeneral> ParamsT = new ArrayList<ParamsGeneral>();
				ParamsT.add(new ParamsGeneral(1,"itemcode",itemcode,"EQ"));
				tfs = tfpc.BuscarxParams(ParamsT);
			//}			
		//}	
		
		model.addAttribute("Liberacioncm",(lcm == null ? new Liberacioncm() : lcm));
		//model.addAttribute("Remision",rem == null ? new Remisiones_para_certificado() : rem);
		model.addAttribute("Tarjeta",tfs == null ? new Tf_para_certificado() : tfs);
		
		return "/certificados/liberacioncm";
	}
	
	@RequestMapping(value = {"/ingenieria/liberacioncm" }, method = RequestMethod.POST)
	public String liberacioncm(@Valid @ModelAttribute("Liberacioncm") Liberacioncm lcm, 
								BindingResult result, ModelMap model, HttpServletRequest request,
								@RequestParam CommonsMultipartFile file)
	{
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		logger.info(AppController.getPrincipal() + " - ingenieria/liberacioncmpost.");
		
		//Remisiones_para_certificado rem = new Remisiones_para_certificado();
		Tf_para_certificado tfs = new Tf_para_certificado();
		//List<ParamsGeneral> ParamsR = new ArrayList<ParamsGeneral>();

		try
		{
			//ParamsR.add(new ParamsGeneral(1,"itemcodesi",lcm.getItemcode_lcm(),"EQ"));
			//rem = rpcs.BuscarXParamsObj(ParamsR);
			//if(rem != null)
			//{						
				
				List<ParamsGeneral> ParamsT = new ArrayList<ParamsGeneral>();
				ParamsT.add(new ParamsGeneral(1,"itemcode",lcm.getItemcode_lcm(),"EQ"));
				tfs = tfpc.BuscarxParams(ParamsT);
			//}			
			
			//model.addAttribute("Remision",rem == null ? new Remisiones_para_certificado() : rem);
			model.addAttribute("Tarjeta",tfs == null ? new Tf_para_certificado() : tfs);

			if (result.hasErrors())
				return "/certificados/liberacioncm";	
			
			if(!file.isEmpty())
			{
				String folderPath = request.getServletContext().getRealPath("/")+"static\\img_certificados\\";
	            String fileName = lcm.getTf_lcm()+".jpg";
	            String filePath = folderPath + fileName;
	            lcm.setDir_file(filePath);
	            
	            byte barr[]=file.getBytes(); 
		        Path path = Paths.get(filePath);
	            try {
					Files.write(path, barr);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			java.util.Date date = new java.util.Date();
			User user = us.findBySSO(AppController.getPrincipal());
			lcm.setFecha_update(date);
			lcm.setUsuario_update(user.getId());
			lcm.setActivo(true);
			if(lcm.getId() == null)
			{
				lcm.setFecha_insert(date);
				lcm.setUsuario_insert(user.getId());
				lcms.Guardar(lcm);
			}
			else
				lcms.Actualizar(lcm);
			
			model.addAttribute("Liberacioncm", lcm);
			model.addAttribute("mensaje", "Guardado exitosamente.");
		}
		catch(Exception e)
		{
			model.addAttribute("errors", e);
		}
		
		return "/certificados/liberacioncm";
	}
	
	@RequestMapping(value = {"/ingenieria/liberacioncmdelete" }, method = RequestMethod.POST)
	public String liberacioncmdelete(@RequestParam("TID") Integer id, ModelMap model)
	{	
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		logger.info(AppController.getPrincipal() + " - ingenieria/liberacioncmdelete.");
		try 
		{
			Liberacioncm lcm =new Liberacioncm();
			lcm = lcms.BuscarxId(id);
			
			java.util.Date date = new java.util.Date();
			User user = us.findBySSO(AppController.getPrincipal());
			lcm.setFecha_update(date);
			lcm.setUsuario_update(user.getId());;
			lcm.setActivo(false);
			
			lcms.Actualizar(lcm);
			
			model.addAttribute("mensaje", "Eliminado exitosamente.");
		}
		catch(Exception e)
		{
			model.addAttribute("errors", e);
		}
		
		model.addAttribute("Liberacioncm", new Liberacioncm());
		return "/certificados/liberacioncm";
	}
	
	@RequestMapping(value = {"/ingenieria/buscarsimbolocert" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> buscarsimbolocert(ModelMap model, @RequestParam("tf") String tf)
	{
		try
		{
			logger.info(AppController.getPrincipal() + " - ingenieria/buscarsimbolocert.");		
			List<ParamsGeneral> ParamsT = new ArrayList<ParamsGeneral>();
			ParamsT.add(new ParamsGeneral(1,"tf",tf,"EQ"));
			Map<String,String> mOrd =  new HashMap<String, String>();
			List<Tf_para_certificado> tfs = tfpc.BuscarListxParams(ParamsT, mOrd);
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.serializeNulls().setDateFormat("yyyy-MM-dd HH:mm").create();
			return new ResponseEntity<Object>(gson.toJson(tfs), HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<Object>(e, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = {"/calidad/seguimiento_certificadoslista" }, method = RequestMethod.GET)
	public ResponseEntity<?> seguimiento_certificadoslista(ModelMap model, @RequestParam(value = "remi", defaultValue = "", required = false) String remi,
														   @RequestParam(value = "tf", defaultValue = "", required = false) String tf)
	{
		logger.info(AppController.getPrincipal() + " - /calidad/seguimiento_certificadoslista.");
		try
		{
			List<Certificados> ListaCert = new ArrayList<Certificados>();
			List<ParamsGeneral> ParamsC = new ArrayList<ParamsGeneral>();
			if(!remi.equals(""))
				ParamsC.add(new ParamsGeneral(1,"remision",remi,"EQ"));
			if(!tf.equals(""))
				ParamsC.add(new ParamsGeneral(1,"folio_tf",tf,"EQ"));
			
			Map<String,String> mOrd =  new HashMap<String, String>();
			ListaCert = cs.BuscarxParamsList(ParamsC, mOrd);
			
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.serializeNulls().setDateFormat("yyyy-MM-dd HH:mm").create();
			return new ResponseEntity<Object>(gson.toJson(ListaCert), HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<Object>(e, HttpStatus.OK);
		}
	
	}
	
	@RequestMapping(value = {"/calidad/seguimiento_certificados" }, method = RequestMethod.GET)
	public String seguimiento_certificados(ModelMap model, @RequestParam(value = "id", defaultValue = "0", required = false) Integer id)
	{
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		logger.info(AppController.getPrincipal() + " - /calidad/seguimiento_certificadoslista.");
		
		if(id > 0)
		{
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.serializeNulls().setDateFormat("yyyy-MM-dd HH:mm").create();
			JSONObject jsonCert = certalldatas.BuscarXIDCert(id);
			//System.out.println(jsonCert.toString());
			model.addAttribute("CertInfo", gson.fromJson(jsonCert.toString(), Object.class));
		}
		return "/certificados/seguimiento_certificados";
	}
}
