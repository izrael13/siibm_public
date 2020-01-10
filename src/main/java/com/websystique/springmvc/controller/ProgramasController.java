package com.websystique.springmvc.controller;

import java.util.ArrayList;
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
import com.websystique.springmvc.model.programas.Programas_reg_barca;
import com.websystique.springmvc.service.UserService;
import com.websystique.springmvc.service.programas.Catalogo_pedidos_sapService;
import com.websystique.springmvc.service.programas.Programas_reg_barcaService;


@Controller
@RequestMapping("/programas")
public class ProgramasController {
	private Logger logger = Logger.getLogger(ProgramasController.class);
	@Autowired Catalogo_pedidos_sapService cps;
	@Autowired Programas_reg_barcaService prbs;
	@Autowired UserService us;
	
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
			model.addAttribute("cant_sol", pedido.get("cantidad"));
			model.addAttribute("Tarjetas", gson.fromJson(ListaTarjetaPedidos(Programa.getPedido()),Object.class));
			
			Programa.setCant_pendiente(CalcularCantPend(Programa.getCant_acumulada(), Double.valueOf(pedido.get("cantidad").toString())));
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
		}
		catch(Exception e)
		{
			logger.info(AppController.getPrincipal() + " tarjeta_fabricacionpost", e);
			model.addAttribute("error", e.getMessage() + e.getCause() + e.getStackTrace() + e.getLocalizedMessage());
		}
		return "/tarjetas/programas/programacionabc";
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
		Double cant_pend = CalcularCantPend(cant_acum, ObjPedido.getCantidad());
		
		/*for(Programas_reg_barca a : ListProgramaReg)
			cant_acum = cant_acum + a.getCant_programada();*/
			
		//cant_pend = ObjPedido.getCantidad() - cant_acum;
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().setDateFormat("yyyy-MM-dd HH:mm").create();
		JSONObject JsonPedido = new JSONObject(gson.toJson(ObjPedido));
		JsonPedido.put("cant_acum", cant_acum);
		JsonPedido.put("cant_pend", cant_pend);
		
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

}
