package com.websystique.springmvc.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.programas.Catalogo_pedidos_sap;
import com.websystique.springmvc.model.programas.Programas_cti;
import com.websystique.springmvc.model.programas.Programas_reg_barca;
import com.websystique.springmvc.model.tarjetas.fabricacion.Tarjeta_fabricacion;
import com.websystique.springmvc.service.UserService;
import com.websystique.springmvc.service.programas.Catalogo_pedidos_sapService;
import com.websystique.springmvc.service.programas.Programas_ctiService;
import com.websystique.springmvc.service.programas.Programas_reg_barcaService;
import com.websystique.springmvc.service.tarjetas.commons.CotizadorTarjetasService;
import com.websystique.springmvc.service.tarjetas.fabricacion.Tarjeta_fabricacionService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.util.JRLoader;


@Controller
@RequestMapping("/programas")
public class ProgramasController {
	private Logger logger = Logger.getLogger(ProgramasController.class);
	@Autowired Catalogo_pedidos_sapService cps;
	@Autowired Programas_reg_barcaService prbs;
	@Autowired UserService us;
	@Autowired Programas_ctiService pcs;
	@Autowired Tarjeta_fabricacionService tfs;
	@Autowired CotizadorTarjetasService ctsc;
	
	@RequestMapping(value = {"/programacion/programacionabc" }, method = RequestMethod.GET)
	public String tarjeta_fabricacion(ModelMap model, @RequestParam(value = "id", defaultValue = "0", required = false) Integer id) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().setDateFormat("yyyy-MM-dd HH:mm").create();
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		logger.info(AppController.getPrincipal() + " tarjeta_fabricacion");
		
		Programas_reg_barca Programa = new Programas_reg_barca();

		if(id > 0)
		{
			Programa = BuscarPrograma(id);
			
			JSONObject pedido = gson.fromJson(Pedidos(Programa.getPedido(), Programa.getTf_sap(), Programa.getCant_programada(), null), JSONObject.class);
			
			model.addAttribute("simbolo", pedido.get("codigoarticulo"));
			model.addAttribute("desc_simbolo", pedido.get("descripcion"));
			model.addAttribute("cant_sol", Double.valueOf(pedido.get("cantidad").toString()));
			model.addAttribute("Tarjetas", gson.fromJson(ListaTarjetaPedidos(Programa.getPedido()),Object.class));
			Programa.setCant_pendiente(CalcularCantPend(Programa.getCant_acumulada(), Double.valueOf(pedido.get("cantidad").toString())));
			model.addAttribute("ListaProgramas", BuscarProgrmas(Programa.getPedido(),Programa.getTf_sap()));
		}
		model.addAttribute("Programa", Programa);
		return "/tarjetas/programas/programacionabc";
	}
	
	private Programas_reg_barca BuscarPrograma(Integer id)
	{
		Programas_reg_barca Programa = new Programas_reg_barca();
		Programa = prbs.BuscarxId(id);
		Programa.setCant_acumulada(CalcularCantAcum(Programa.getPedido(), Programa.getTf_sap(), null));
		return Programa;
	}
	
	@RequestMapping(value = {"/programacion/programacionabc" }, method = RequestMethod.POST)
	public String tarjeta_fabricacionpost(@Valid @ModelAttribute("Programa") Programas_reg_barca Programa, BindingResult result,ModelMap model) {
		try
		{
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.serializeNulls().setDateFormat("yyyy-MM-dd HH:mm").create();
			
			logger.info(AppController.getPrincipal() + " tarjeta_fabricacionpost");
			model.addAttribute("Tarjetas", gson.fromJson(Programa.getPedido() != null ? ListaTarjetaPedidos(Programa.getPedido()) : "",Object.class));
			
			if (result.hasErrors())
				return "/tarjetas/programas/programacionabc";
			
			User user = us.findBySSO(AppController.getPrincipal());
			java.util.Date date = new java.util.Date();
			Programa.setUsuario_insert(user.getId());
			Programa.setFecha_insert(date);
			Programa.setUsuario_update(user.getId());
			Programa.setFecha_update(date);
			
			JSONObject pedido = gson.fromJson(Pedidos(Programa.getPedido(), Programa.getTf_sap(), Programa.getCant_programada(),null), JSONObject.class);
			
			if(Programa.getCant_acumulada() > Double.valueOf(pedido.get("cantidad").toString()))
				model.addAttribute("error", "Cantidad acumulada mayor a cantidad solicitada.");
			else
			{
				if(Programa.getId() == null) 
					prbs.Guardar(Programa);
				else
					prbs.Actualizar(Programa);
			
				model.addAttribute("mensaje", "Programa grabado correctamente.");
			}
			model.addAttribute("simbolo", pedido.get("codigoarticulo"));
			model.addAttribute("desc_simbolo", pedido.get("descripcion"));
			model.addAttribute("cant_sol", pedido.get("cantidad"));
			model.addAttribute("ListaProgramas", BuscarProgrmas(Programa.getPedido(),Programa.getTf_sap()));
		}
		catch(Exception e)
		{
			logger.info(AppController.getPrincipal() + " tarjeta_fabricacionpost", e);
			model.addAttribute("error", e.getMessage() + e.getCause() + e.getStackTrace() + e.getLocalizedMessage());
		}
		return "/tarjetas/programas/programacionabc";
	}
	private List<Programas_cti> BuscarProgrmas(Integer Pedido, String TFSAP)
	{
		//Map<String, String> mOrd = new TreeMap<String, String>();
		//mOrd.put("1", "programa");
		List<Programas_cti> ListProg = new ArrayList<Programas_cti>();
		//List<ParamsGeneral> ParamsP = new ArrayList<ParamsGeneral>();
		//ParamsP.add(new ParamsGeneral(1,"pedido",Pedido,"EQ"));
		//ParamsP.add(new ParamsGeneral(1,"tarjeta",TFSAP,"EQ"));
		ListProg = pcs.BuscarxParams(Pedido,TFSAP);
		return ListProg;
	}
	private String ListaTarjetaPedidos(Integer Pedido)
	{
		List<Catalogo_pedidos_sap> ListaPedidos = new ArrayList<Catalogo_pedidos_sap>();
		List<String> Lista = new ArrayList<String>();
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"pedido",Pedido,"EQ"));
		Map<String, String> mOrd = new TreeMap<String, String>();
		ListaPedidos = cps.BuscarxParamas(Params, mOrd);
		ListaPedidos.forEach(a -> {
			Lista.add(a.getTf());
		});
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().create();
		return gson.toJson(Lista);
	}
	
	@RequestMapping(value = "/programacion/buscartarjetas", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> ListaTFs(HttpServletResponse response,HttpServletRequest request,ModelMap model,@RequestParam("pedido") Integer Pedido){
		try
		{
			logger.info(AppController.getPrincipal() + " - imprimirrep ");
			return new ResponseEntity<Object>(ListaTarjetaPedidos(Pedido),HttpStatus.OK);
		}
		catch(Exception e)
		{
			logger.info(AppController.getPrincipal() + " - imprimirrep :", e);
			return new ResponseEntity<Object>(e.getMessage()+ "-" +e.getCause()+ "-"+ e.getStackTrace(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private String Pedidos(Integer Pedido, String TFSAP, Double CantProgCap, Integer id)
	{
		Catalogo_pedidos_sap ObjPedido = new Catalogo_pedidos_sap();
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"pedido",Pedido,"EQ"));
		Params.add(new ParamsGeneral(1,"tf",TFSAP,"EQ"));
		ObjPedido = cps.BuscarxParamas(Params);
		
		/*List<Programas_reg_barca> ListProgramaReg = new ArrayList<Programas_reg_barca>();
		Params.clear();
		Params.add(new ParamsGeneral(1,"pedido",Pedido,"EQ"));
		Params.add(new ParamsGeneral(1,"tf_sap",TFSAP,"EQ"));
		Map<String, String> mOrd = new TreeMap<String, String>();
		ListProgramaReg = prbs.BuscarxParams(Params,mOrd);
		*/
		Double cant_acum = CalcularCantAcum(Pedido, TFSAP, id) + CantProgCap;
		Double cant_pend = CalcularCantPend(cant_acum, Double.valueOf(ObjPedido.getCantidad().toString()));
		
		/*for(Programas_reg_barca a : ListProgramaReg)
			cant_acum = cant_acum + a.getCant_programada();*/
			
		//cant_pend = ObjPedido.getCantidad() - cant_acum;
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().setDateFormat("yyyy-MM-dd HH:mm").create();
		JSONObject JsonPedido = new JSONObject(gson.toJson(ObjPedido));
		JsonPedido.put("cant_acum", cant_acum);
		JsonPedido.put("cant_pend", cant_pend);
		
		/*Map<String, String> mOrd = new TreeMap<String, String>();
		mOrd.put("1", "programa");
		List<Programas_cti> ListProg = new ArrayList<Programas_cti>();
		List<ParamsGeneral> ParamsP = new ArrayList<ParamsGeneral>();
		ParamsP.add(new ParamsGeneral(1,"pedido",Pedido,"EQ"));
		ParamsP.add(new ParamsGeneral(1,"tarjeta",TFSAP,"EQ"));
		ListProg = pcs.BuscarxParams(ParamsP,mOrd); */
		JsonPedido.put("Programas", gson.toJson(BuscarProgrmas(Pedido, TFSAP)));
		return gson.toJson(JsonPedido);
	}
	
	private Double CalcularCantAcum(Integer Pedido, String TFSAP, Integer id)
	{
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		List<Programas_reg_barca> ListProgramaReg = new ArrayList<Programas_reg_barca>();
		Params.add(new ParamsGeneral(1,"pedido",Pedido,"EQ"));
		Params.add(new ParamsGeneral(1,"tf_sap",TFSAP,"EQ"));
		Map<String, String> mOrd = new TreeMap<String, String>();
		ListProgramaReg = prbs.BuscarxParams(Params,mOrd);
		Double cant_acum = 0.0;
		for(Programas_reg_barca a : ListProgramaReg)
		{
			if(a.getId() != id)
				cant_acum = cant_acum + a.getCant_programada();
		}
		return cant_acum;
	}
	
	private Double CalcularCantPend(Double CantAcum, Double Cantidad)
	{
		return Cantidad - CantAcum;
	}
	
	@RequestMapping(value = "/programacion/buscarpedido", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> Pedido(HttpServletResponse response,HttpServletRequest request,ModelMap model,
									@RequestParam("pedido") Integer Pedido, @RequestParam("tfsap") String TFSAP, 
									@RequestParam("cant_prog_cap") Double CantProgCap, @RequestParam("id") Integer id){
		try
		{
			logger.info(AppController.getPrincipal() + " - buscarpedido ");
			return new ResponseEntity<Object>(Pedidos(Pedido, TFSAP, CantProgCap, id),HttpStatus.OK);
		}
		catch(Exception e)
		{
			logger.info(AppController.getPrincipal() + " - buscarpedido :", e);
			return new ResponseEntity<Object>(e.getMessage()+ "-" +e.getCause()+ "-"+ e.getStackTrace(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/programacion/buscarprogramasreg", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> buscarprogramasreg(HttpServletResponse response,HttpServletRequest request,ModelMap model,
									@RequestParam(value = "pedido", defaultValue = "0", required = false) Integer Pedido, 
									@RequestParam(value = "tfsap", defaultValue = "", required = false) String Tfsap, 
									@RequestParam(value = "tfprog", defaultValue = "", required = false) String Tfprog,
									@RequestParam(value = "programa", defaultValue = "", required = false) String Programa){
		try
		{
			logger.info(AppController.getPrincipal() + " - imprimirrep ");
			return new ResponseEntity<Object>(BuscarProgramas(Pedido, Tfsap, Tfprog, Programa),HttpStatus.OK);
		}
		catch(Exception e)
		{
			logger.info(AppController.getPrincipal() + " - buscarprogramasreg :", e);
			return new ResponseEntity<Object>(e.getMessage()+ "-" +e.getCause()+ "-"+ e.getStackTrace(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private String BuscarProgramas(Integer pedido, String tfsap, String tfprog, String programa) {
		List<Programas_reg_barca> ListProgramaReg = new ArrayList<Programas_reg_barca>();		
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		if(pedido > 0)
			Params.add(new ParamsGeneral(1,"pedido",pedido,"EQ"));
		if(!tfsap.equals(""))
			Params.add(new ParamsGeneral(1,"tf_sap",tfsap,"EQ"));
		if(!tfprog.equals(""))
			Params.add(new ParamsGeneral(1,"tf_programa",tfprog,"EQ"));
		if(!programa.equals(""))
			Params.add(new ParamsGeneral(1,"programa",programa,"EQ"));
		
		
		Map<String, String> mOrd = new TreeMap<String, String>();
		if(Params.size() > 0)
			ListProgramaReg = prbs.BuscarxParams(Params,mOrd);
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().setDateFormat("yyyy-MM-dd HH:mm").create();
		List<JSONObject> JsonProgramas = new ArrayList<JSONObject>();
		ListProgramaReg.forEach(a -> {
			JSONObject JsonProg = new JSONObject(gson.toJson(a));
			JsonProgramas.add(JsonProg);
		});
		
		return gson.toJson(JsonProgramas);
	}
	
	@RequestMapping(value = "/programacion/eliminar", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> eliminar(HttpServletResponse response,HttpServletRequest request,ModelMap model,
									@RequestParam(value = "id", defaultValue = "0", required = false) Integer id){
		try
		{
			logger.info(AppController.getPrincipal() + " - programacion/eliminar ");
			Programas_reg_barca p = new Programas_reg_barca();
			p = prbs.BuscarxId(id);
			if(p == null)
				return new ResponseEntity<Object>("No existe el programa, favor de verificar.",HttpStatus.OK);
			else
			{
				
				prbs.Eliminar(p);
				List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
				Params.add(new ParamsGeneral(1,"pedido",p.getPedido(),"EQ"));
				Params.add(new ParamsGeneral(1,"tf_sap",p.getTf_sap(),"EQ"));
				Params.add(new ParamsGeneral(1,"id",p.getId(),"GT"));
				Map<String, String> mOrd = new TreeMap<String, String>();
				mOrd.put("1", "id");
				
				for(Programas_reg_barca a : prbs.BuscarxParams(Params, mOrd))
				{
					a.setCant_acumulada(a.getCant_acumulada() - p.getCant_programada());
					a.setCant_pendiente(p.getCant_pendiente() + p.getCant_programada());
					prbs.Actualizar(a);
				}
				return new ResponseEntity<Object>("Programa eliminado correctamente",HttpStatus.OK);
			}
			
		}
		catch(Exception e)
		{
			logger.info(AppController.getPrincipal() + " - programacion/eliminar :", e);
			return new ResponseEntity<Object>(e.getMessage()+ "-" +e.getCause()+ "-"+ e.getStackTrace(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/programacion/imprimirtfprog", method = RequestMethod.GET)
    @ResponseBody
    public void getRpt1(HttpServletResponse response,HttpServletRequest request,ModelMap model,
    				    @RequestParam("idtf") String idtf,
    				    @RequestParam("idprog") Integer idprog) throws JRException, IOException {
		String msj = "";
		try
		{
			Tarjeta_fabricacion t = new Tarjeta_fabricacion();
			t = tfs.BuscarxFolio(idtf);
			InputStream jasperStream = this.getClass().getResourceAsStream("/jasperreports/cotizador/Tarjeta_fabricacion.jasper");
			Map<String,Object> params = new HashMap<>();
			
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
			ByteArrayInputStream jsonDataStream = new ByteArrayInputStream(stripAccents(ctsc.DataSourceJasperTF(t, 1, idprog).toString()).getBytes("UTF-8"));
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
	
	private String stripAccents(String s) 
	{
	    s = Normalizer.normalize(s, Normalizer.Form.NFD);
	    s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
	    return s;
	}
	
	@RequestMapping(value = {"/programacion/programaconsulta" }, method = RequestMethod.GET)
	public String programaconsulta(ModelMap model, 
									@RequestParam(value = "pedido", defaultValue = "0", required = false) Integer pedido,
									@RequestParam(value = "id1", defaultValue = "0", required = false) Integer id1,
									@RequestParam(value = "id2", defaultValue = "0", required = false) Integer id2) {
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		
		if((pedido > 0) || (id1 > 0 && id2 > 0))
		{
			Map<String, String> mOrd = new TreeMap<String, String>();
			mOrd.put("1", "id");
			List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
			if(pedido > 0)
				Params.add(new ParamsGeneral(1,"pedido",pedido,"EQ"));
			
			if(id1 > 0 && id2 > 0) {
				Params.add(new ParamsGeneral(1,"id",id1,"GTE"));
				Params.add(new ParamsGeneral(1,"id",id2,"LTE"));
			}
			
			model.addAttribute("pedido",pedido);
			model.addAttribute("id1", id1);
			model.addAttribute("id2", id2);
			model.addAttribute("lista", prbs.BuscarxParams(Params, mOrd));
		}
		
		
		return "/tarjetas/programas/programasconsulta";
	}

}
