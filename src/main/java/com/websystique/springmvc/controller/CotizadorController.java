package com.websystique.springmvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.tarjetas.Catalogo_direcciones_sap_vw;
import com.websystique.springmvc.model.tarjetas.cotizador.CotizadorDataBean;
import com.websystique.springmvc.service.UserService;
import com.websystique.springmvc.service.tarjetas.Catalogo_clientes_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Catalogo_direcciones_sap_vwService;

@Controller
@RequestMapping("/ventas/tarjetas/cotizador")
public class CotizadorController {
	
	private Logger logger = Logger.getLogger(CotizadorController.class);
	
	@Autowired
	UserService us;
	@Autowired
	Catalogo_clientes_sap_vwService ccavs;
	@Autowired
	Catalogo_direcciones_sap_vwService cdsv;
	
	@RequestMapping(value = {"/cotizadorabc" }, method = RequestMethod.GET)
	public String cotizadotget(ModelMap model) {
		try {
			User user = us.findBySSO(AppController.getPrincipal());
			
			model.addAttribute("clientes", ccavs.ListaCtes(user.getCvevendedor_sap()));
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			model.addAttribute("cotizadordatabean", new CotizadorDataBean());
			model.addAttribute("direcciones", cdsv.ListaDirCardCode("0"));
			logger.info(AppController.getPrincipal() + " - cotizadorabc.");
		}
		catch(Exception e)
		{
			logger.error(AppController.getPrincipal() + " - cotizadorabc. - " + e.getMessage());
		}
		return "/tarjetas/cotizador/cotizador";
	}
	
	@RequestMapping(value = {"/buscardirecciones"}, method = RequestMethod.GET)
	public @ResponseBody String buscardirecciones(HttpServletRequest req, HttpServletResponse res)
	   throws Exception {
		String cardcode = req.getParameter("cardcode");
		List<Catalogo_direcciones_sap_vw> ListaDir = cdsv.ListaDirCardCode(cardcode);
		Gson g=new Gson();
		return g.toJson(ListaDir);
	
	}
	
	@RequestMapping(value = {"/buscarinfodir"}, method = RequestMethod.GET)
	public @ResponseBody String buscarinfodir(HttpServletRequest req, HttpServletResponse res)
	   throws Exception {
		String cardcode = req.getParameter("cardcode");
		String linenum = req.getParameter("linenum");
		
		List<Catalogo_direcciones_sap_vw> ListaDir = cdsv.ListaDirCardCodeNumLine(cardcode, Integer.parseInt(linenum));
		Gson g=new Gson();
		return g.toJson(ListaDir);
	
	}
	
}
