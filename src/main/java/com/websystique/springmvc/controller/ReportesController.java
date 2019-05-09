package com.websystique.springmvc.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.websystique.springmvc.excel.ConsKilosExcel;
import com.websystique.springmvc.excel.ExcelAmortHerra;
import com.websystique.springmvc.excel.ExcelGolpesPend2;
import com.websystique.springmvc.excel.ExcelTodosPedidos;
import com.websystique.springmvc.excel.ExcelView;
import com.websystique.springmvc.excel.ExcelViewGolpesKilosMes;
import com.websystique.springmvc.excel.ExcelViewGolpesMaqMes;
import com.websystique.springmvc.excel.ExcelViewUltSem;
import com.websystique.springmvc.excel.MediaPedidosCte;
import com.websystique.springmvc.model.reportes.Amortiza_herramentales;
import com.websystique.springmvc.model.reportes.ConsumoKilos;
import com.websystique.springmvc.model.reportes.Golpes_maquina_mes;
import com.websystique.springmvc.model.reportes.Golpes_pendientes_fab;
import com.websystique.springmvc.model.reportes.Golpeskilosmaquinas;
import com.websystique.springmvc.model.reportes.Media_pedidos_cte;
import com.websystique.springmvc.model.reportes.Reporte_consumo_papel;
import com.websystique.springmvc.model.reportes.Reportes_consumo_papel_utl_sem;
import com.websystique.springmvc.model.reportes.Todos_pedidos;
import com.websystique.springmvc.service.reportes.Amortiza_herramentalesService;
import com.websystique.springmvc.service.reportes.Cobranza_acumService;
import com.websystique.springmvc.service.reportes.Cobranza_detalleService;
import com.websystique.springmvc.service.reportes.ConsumoKilosService;
import com.websystique.springmvc.service.reportes.Flautas_prom_semService;
import com.websystique.springmvc.service.reportes.Golpes_maquina_mesService;
import com.websystique.springmvc.service.reportes.Golpes_pendientes_fabService;
import com.websystique.springmvc.service.reportes.GolpeskilosmaquinasService;
import com.websystique.springmvc.service.reportes.Inventario_almacenService;
import com.websystique.springmvc.service.reportes.Media_pedidos_cteService;
import com.websystique.springmvc.service.reportes.Meses_anioService;
import com.websystique.springmvc.service.reportes.Paros_concepto_diaService;
import com.websystique.springmvc.service.reportes.Paros_maquina_diaService;
import com.websystique.springmvc.service.reportes.Peso_diaService;
import com.websystique.springmvc.service.reportes.Reporte_consumo_papelService;
import com.websystique.springmvc.service.reportes.Reportes_consumo_papel_acum_mesService;
import com.websystique.springmvc.service.reportes.Reportes_consumo_papel_utl_semService;
import com.websystique.springmvc.service.reportes.Semanas_anioService;
import com.websystique.springmvc.service.reportes.Todos_pedidosService;
import com.websystique.springmvc.service.reportes.Viajes_mes_ciudadService;

//import net.sf.jasperreports.engine.JRDataSource;
//import net.sf.jasperreports.engine.JREmptyDataSource;
/*import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap; */

@Controller
@RequestMapping("/reportes")
public class ReportesController {
	
	private Logger logger = Logger.getLogger(ReportesController.class);
	
	@Autowired
	Reporte_consumo_papelService rep;	
	@Autowired
	Semanas_anioService semanioService;	
	@Autowired
	Reportes_consumo_papel_utl_semService rep_ult_sem;	
	@Autowired
	Reportes_consumo_papel_acum_mesService rep_acum_mes;
	@Autowired
	Golpes_maquina_mesService reptroqprod;
	@Autowired
	Meses_anioService mesesanio;
	@Autowired
	GolpeskilosmaquinasService gkms;
	@Autowired
	Flautas_prom_semService fpms;
	@Autowired
	Paros_maquina_diaService pmds;
	@Autowired
	Paros_concepto_diaService pcds;
	@Autowired
	Peso_diaService pds;
	@Autowired
	Inventario_almacenService ias;
	@Autowired
	Golpes_pendientes_fabService gpf;
	@Autowired
	Amortiza_herramentalesService ahs;
	@Autowired
	Todos_pedidosService tps;
	@Autowired
	Cobranza_detalleService cds;
	@Autowired
	Cobranza_acumService cai;
	@Autowired
	ConsumoKilosService cks;
	@Autowired
	Media_pedidos_cteService mpc;
	@Autowired
	Viajes_mes_ciudadService vmc;
	
	Calendar calendar = Calendar.getInstance();
	
	@RequestMapping(value = {"/papel/consumo_papel_mes" }, method = RequestMethod.GET)
	public String consumo_papel_mes(ModelMap model) {
		try {
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			model.addAttribute("mesesanio", mesesanio.findallRegistros());
			model.addAttribute("selectedValue", String.valueOf(calendar.get(Calendar.YEAR)) + String.valueOf(calendar.get(Calendar.MONTH)+1));
			model.addAttribute("reporte",rep_acum_mes.findAllRegistrosByMes(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1));
			logger.info(AppController.getPrincipal() + " - consumo_papel_mes.");
		}
		catch(Exception e)
		{
			logger.error(AppController.getPrincipal() + " - consumo_papel_mes. - " + e.getMessage());
		}
		return "/reportes/consumo_papel_mes";
	}
	
	@RequestMapping(value = {"/papel/buscarMes" }, method = RequestMethod.GET)
	public String consumo_papel_buscar_mes(ModelMap model,@RequestParam("aniomes") String aniomes) {
		try {
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			model.addAttribute("mesesanio", mesesanio.findallRegistros());
			model.addAttribute("selectedValue", String.valueOf(aniomes.substring(0,4) + aniomes.substring(4,aniomes.length())));
			model.addAttribute("reporte",rep_acum_mes.findAllRegistrosByMes(Integer.parseInt(aniomes.substring(0,4)), Integer.parseInt(aniomes.substring(4,aniomes.length()))));
			logger.info(AppController.getPrincipal() + " - buscarMes.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - buscarMes. - " + e.getMessage());
		}
		return "/reportes/consumo_papel_mes";
	}
	
	@RequestMapping(value = {"/papel/consumo_papel_ult_sem" }, method = RequestMethod.GET)
	public String consumo_papel_ult_sem(ModelMap model) {
		try {
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			model.addAttribute("reporte",rep_ult_sem.findByAnioSem(0, 0));
			logger.info(AppController.getPrincipal() + " - consumo_papel_ult_sem.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - consumo_papel_ult_sem. - " + e.getMessage());
		}
		return "/reportes/reportes_consumo_papel_utl_sem";
	}
	
	@RequestMapping(value = {"/papel/Excel_ult_sem" },method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView excel_ult_sem() {
		List<Reportes_consumo_papel_utl_sem> reporte = null;
		try
		{
			reporte = rep_ult_sem.findByAnioSem(0, 0);
			logger.info(AppController.getPrincipal() + " - excel_ult_sem.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - excel_ult_sem. - " + e.getMessage());
		}
		return new ModelAndView(new ExcelViewUltSem(), "reporte", reporte);
	}

	@RequestMapping(value = {"/papel/consumo_papel" }, method = RequestMethod.GET)
	public String consumo_papel(ModelMap model) {
		Map<Integer, String> seman = null;
		try {
			seman = semanioService.findAllSemanas();
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			model.addAttribute("semanas_anio", seman);
			calendar.setFirstDayOfWeek( Calendar.MONDAY);
			calendar.setMinimalDaysInFirstWeek( 4 );
			java.util.Date date = new Date();
			calendar.setTime(date);
			calendar.add(Calendar.WEEK_OF_YEAR , - 12);
			int numberWeekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
			model.addAttribute("selectedValue", String.valueOf(calendar.get(Calendar.YEAR)) + String.valueOf(numberWeekOfYear));
			model.addAttribute("reporte", rep.findByAnioSem(calendar.get(Calendar.YEAR), numberWeekOfYear));
			logger.info(AppController.getPrincipal() + " - consumo_papel.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - consumo_papel. - " + e.getMessage());
		}
		return "/reportes/consumo_papel";
	}
	
	@RequestMapping(value = { "/papel/buscarSem" })
	public String buscarSem(ModelMap model,@RequestParam("aniosem") String aniosem) {
		Map<Integer, String> seman = null;
		try {
		seman = semanioService.findAllSemanas();
		model.addAttribute("semanas_anio", seman);
		model.addAttribute("selectedValue", aniosem);
		model.addAttribute("reporte", rep.findByAnioSem(Integer.parseInt(aniosem.substring(0,4)), Integer.parseInt(aniosem.substring(4,aniosem.length()))));
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		logger.info(AppController.getPrincipal() + " - buscarSem.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - buscarSem. - " + e.getMessage());
		}
		return "/reportes/consumo_papel";
	}
	
	@RequestMapping(value = { "/papel/Excel" },method=RequestMethod.GET)
	public ModelAndView excel(HttpServletRequest req, HttpServletResponse res) {
		String aniosem = "";
		List<Reporte_consumo_papel> listaexcel = null;
		try {
			aniosem = req.getParameter("aniosem");
			listaexcel = rep.findByAnioSem(Integer.parseInt(aniosem.substring(0,4)), Integer.parseInt(aniosem.substring(4,aniosem.length())));
			logger.info(AppController.getPrincipal() + " - Excel.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - Excel. - " + e.getMessage());
		}
		return new ModelAndView(new ExcelView(), "listaexcel", listaexcel);
	}
	
	@RequestMapping(value = {"/produccion/golpes_maquina_mes__" }, method = RequestMethod.GET)
	public String troqueladoras_prod(ModelMap model) {
		try {
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			model.addAttribute("mesesanio", mesesanio.findallRegistros());
			model.addAttribute("selectedValue", String.valueOf(calendar.get(Calendar.YEAR)) + String.valueOf(calendar.get(Calendar.MONTH)+1));
			model.addAttribute("reporte",reptroqprod.findAllRegistrosByMes(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1));
		logger.info(AppController.getPrincipal() + " - golpes_maquina_mes__.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - golpes_maquina_mes__. - " + e.getMessage());
		}
		return "/reportes/golpes_maquina_mes";
	}
	
	@RequestMapping(value = {"/produccion/buscartroqmes" }, method = RequestMethod.GET)
	public String buscartroqueladoras_prod(ModelMap model,@RequestParam("aniomes") String aniomes) {
		try {
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			model.addAttribute("mesesanio", mesesanio.findallRegistros());
			model.addAttribute("selectedValue", aniomes);
			model.addAttribute("reporte",reptroqprod.findAllRegistrosByMes(Integer.parseInt(aniomes.substring(0,4)), Integer.parseInt(aniomes.substring(4,aniomes.length()))));
			logger.info(AppController.getPrincipal() + " - buscartroqmes.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - buscartroqmes. - " + e.getMessage());
		}
		return "/reportes/golpes_maquina_mes";
	}
	
	@RequestMapping(value = { "/produccion/Excel_golpes_maq_mes" },method=RequestMethod.GET)
	public ModelAndView Excel_golpes_maq_mes(HttpServletRequest req, HttpServletResponse res) {
		String aniomes = "";
		List<Golpes_maquina_mes> listaexcel = null;
		try {
			aniomes = req.getParameter("aniomes");
			listaexcel = reptroqprod.findAllRegistrosByMes(Integer.parseInt(aniomes.substring(0,4)), Integer.parseInt(aniomes.substring(4,aniomes.length())));
			logger.info(AppController.getPrincipal() + " - Excel_golpes_maq_mes.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - Excel_golpes_maq_mes. - " + e.getMessage());
		}
		return new ModelAndView(new ExcelViewGolpesMaqMes(), "listaexcel", listaexcel);
	}
	
	@RequestMapping(value = {"/ventas/golpes_kilos_maquina__" }, method = RequestMethod.GET)
	public String golpes_kilos_maquina(ModelMap model) {
		try {
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			model.addAttribute("mesesanio", mesesanio.findallRegistros());
			model.addAttribute("selectedValue", String.valueOf(calendar.get(Calendar.YEAR)) + String.valueOf(calendar.get(Calendar.MONTH)+1));
			model.addAttribute("reporte",gkms.findAllRegistrosByMes(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1));
			logger.info(AppController.getPrincipal() + " - golpes_kilos_maquina__.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - golpes_kilos_maquina__. - " + e.getMessage());
		}
		return "/reportes/golpeskilos_maquina";
	}
	
	@RequestMapping(value = {"/ventas/buscarGKmes" }, method = RequestMethod.GET)
	public String buscarGKmes__(ModelMap model,@RequestParam("aniomes") String aniomes) {
		try {
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			model.addAttribute("mesesanio", mesesanio.findallRegistros());
			model.addAttribute("selectedValue", aniomes);
			model.addAttribute("reporte",gkms.findAllRegistrosByMes(Integer.parseInt(aniomes.substring(0,4)), Integer.parseInt(aniomes.substring(4,aniomes.length()))));
			logger.info(AppController.getPrincipal() + " - buscarGKmes.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - buscarGKmes. - " + e.getMessage());
		}
		return "/reportes/golpeskilos_maquina";
	}
	
	@RequestMapping(value = { "/ventas/Excel_GKmes" },method=RequestMethod.GET)
	public ModelAndView Excel_GKmes(HttpServletRequest req, HttpServletResponse res) {
		String aniomes = "";
		List<Golpeskilosmaquinas> listaexcel = null;
		try {
		aniomes = req.getParameter("aniomes");
		listaexcel = gkms.findAllRegistrosByMes(Integer.parseInt(aniomes.substring(0,4)), Integer.parseInt(aniomes.substring(4,aniomes.length())));
		logger.info(AppController.getPrincipal() + " - Excel_GKmes.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - Excel_GKmes. - " + e.getMessage());
		}
		return new ModelAndView(new ExcelViewGolpesKilosMes(), "listaexcel", listaexcel);
	}
	
	@RequestMapping(value = {"/produccion/flautaPromSem__" }, method = RequestMethod.GET)
	public String corrugado_maquina(ModelMap model) {
		try {
			Map<Integer, String> seman = semanioService.findAllSemanas();
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			model.addAttribute("semanas_anio", seman);
			calendar.setFirstDayOfWeek( Calendar.MONDAY);
			calendar.setMinimalDaysInFirstWeek( 4 );
			java.util.Date date = new Date();
			calendar.setTime(date);
			int numberWeekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
			model.addAttribute("selectedValue", String.valueOf(calendar.get(Calendar.YEAR)) + String.valueOf(numberWeekOfYear));
			model.addAttribute("reporte", fpms.findbySemAnio(calendar.get(Calendar.YEAR), numberWeekOfYear));
			logger.info(AppController.getPrincipal() + " - flautaPromSem__.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - flautaPromSem__. - " + e.getMessage());
		}
		return "/reportes/flauta_prom_sem";
	}
	
	@RequestMapping(value = { "/produccion/buscarFlautaPromSem" })
	public String buscarCorrSem(ModelMap model,@RequestParam("aniosem") String aniosem) {
		try {
			Map<Integer, String> seman = semanioService.findAllSemanas();
			model.addAttribute("semanas_anio", seman);
			model.addAttribute("selectedValue", aniosem);
			model.addAttribute("reporte", fpms.findbySemAnio(Integer.parseInt(aniosem.substring(0,4)), Integer.parseInt(aniosem.substring(4,aniosem.length()))));
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			logger.info(AppController.getPrincipal() + " - buscarFlautaPromSem.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - buscarFlautaPromSem. - " + e.getMessage());
		}
		return "/reportes/flauta_prom_sem";
	}
	
	@RequestMapping(value = {"/produccion/paros_maq_dia__" }, method = RequestMethod.GET)
	public String paros_maquina(ModelMap model) {
		try {
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			model.addAttribute("mesesanio", mesesanio.findallRegistros());
			model.addAttribute("selectedValue", String.valueOf(calendar.get(Calendar.YEAR)) + String.valueOf(calendar.get(Calendar.MONTH)+1));
			model.addAttribute("reporte",pmds.findbyAnioMes(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1));
			logger.info(AppController.getPrincipal() + " - paros_maq_dia__.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - paros_maq_dia__. - " + e.getMessage());
		}
		return "/reportes/paros_maquina_dia";	
	}
	
	@RequestMapping(value = {"/produccion/buscarparosmaqdia" }, method = RequestMethod.GET)
	public String buscarparosmaqdia__(ModelMap model,@RequestParam("aniomes") String aniomes) {
		try {
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			model.addAttribute("mesesanio", mesesanio.findallRegistros());
			model.addAttribute("selectedValue", aniomes);
			model.addAttribute("reporte",pmds.findbyAnioMes(Integer.parseInt(aniomes.substring(0,4)), Integer.parseInt(aniomes.substring(4,aniomes.length()))));
			logger.info(AppController.getPrincipal() + " - buscarparosmaqdia.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - buscarparosmaqdia. - " + e.getMessage());
		}
		return "/reportes/paros_maquina_dia";
	}
	
	@RequestMapping(value = {"/produccion/paros_concepto_d__" }, method = RequestMethod.GET)
	public String paros_concepto_d(ModelMap model) {
		try {
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		LocalDateTime ldt = LocalDateTime.now();
		String fecha_ini = DateTimeFormatter.ofPattern("yyyy/MM/dd", Locale.getDefault()).format(ldt);
		model.addAttribute("fecha_ini", fecha_ini);
		model.addAttribute("reporte",pcds.findAllParosConceptodia(fecha_ini));
		logger.info(AppController.getPrincipal() + " - paros_concepto_d__.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - paros_concepto_d__. - " + e.getMessage());
		}
		return "/reportes/paros_concepto_dia";
	}
	
	@RequestMapping(value = {"/produccion/buscarparoscondia" }, method = RequestMethod.GET)
	public String buscarparoscondia_(ModelMap model,@RequestParam("fecha_ini") String fecha_ini) {
		try {
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		model.addAttribute("fecha_ini", fecha_ini);
		model.addAttribute("reporte",pcds.findAllParosConceptodia(fecha_ini));
		logger.info(AppController.getPrincipal() + " - buscarparoscondia.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - buscarparoscondia. - " + e.getMessage());
		}
		return "/reportes/paros_concepto_dia";
	}
	
	@RequestMapping(value = {"/ventas/peso_dia_d__" })
	public String peso_dia_d__(ModelMap model) {
		try {
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		model.addAttribute("mesesanio", mesesanio.findallRegistros());
		model.addAttribute("selectedValue", String.valueOf(calendar.get(Calendar.YEAR)) + String.valueOf(calendar.get(Calendar.MONTH)+1));
		model.addAttribute("reporte",pds.findByAnioMes(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1));
		logger.info(AppController.getPrincipal() + " - peso_dia_d__.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - peso_dia_d__. - " + e.getMessage());
		}
		return "/reportes/peso_dia";
	}
	
	@RequestMapping(value = {"/ventas/buscarPeso_dia_" }, method = RequestMethod.GET)
	public String buscarPeso_dia_(ModelMap model,@RequestParam("aniomes") String aniomes) {
		try {
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		model.addAttribute("mesesanio", mesesanio.findallRegistros());
		model.addAttribute("selectedValue", aniomes);
		model.addAttribute("reporte",pds.findByAnioMes(Integer.parseInt(aniomes.substring(0,4)), Integer.parseInt(aniomes.substring(4,aniomes.length()))));
		logger.info(AppController.getPrincipal() + " - buscarPeso_dia_.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - buscarPeso_dia_. - " + e.getMessage());
		}
		return "/reportes/peso_dia";
	}
	
	@RequestMapping(value = {"/ventas/inven_alm____"})
	public String inventario_almacen(ModelMap model)
	{
		try {
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		logger.info(AppController.getPrincipal() + " - inven_alm____.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - inven_alm____. - " + e.getMessage());
		}
		return "/reportes/inventario_almacen";
	}
	
	@RequestMapping(value = {"/ventas/buscaInvenalm_"}, method = RequestMethod.GET)
	public String busca_inventario_almacen(ModelMap model,@RequestParam("cve_almacen") String cve_almacen)
	{
		try {
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		model.addAttribute("reporte", ias.findByAlmacen(cve_almacen));
		model.addAttribute("selectedValue", cve_almacen);
		logger.info(AppController.getPrincipal() + " - buscaInvenalm_.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - buscaInvenalm_. - " + e.getMessage());
		}
		return "/reportes/inventario_almacen";
	}
	
	@RequestMapping(value = {"/ventas/golpes_pend_fab_" }, method = RequestMethod.GET)
	public String golpes_pend_fab_(ModelMap model) {
		try {
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		model.addAttribute("mesesanio", mesesanio.findallRegistros());
		model.addAttribute("selectedValue", String.valueOf(calendar.get(Calendar.YEAR)) + String.valueOf(calendar.get(Calendar.MONTH)+1));
		model.addAttribute("reporte",gpf.findByAnioMes(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1));
		logger.info(AppController.getPrincipal() + " - golpes_pend_fab_.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - golpes_pend_fab_. - " + e.getMessage());
		}
		return "/reportes/golpes_pendientes_fab";
	}
	
	@RequestMapping(value = {"/ventas/buscargplpenfab" }, method = RequestMethod.GET)
	public String buscargplpenfab(ModelMap model,@RequestParam("aniomes") String aniomes) {
		try {
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		model.addAttribute("mesesanio", mesesanio.findallRegistros());
		model.addAttribute("selectedValue", aniomes);
		model.addAttribute("reporte", gpf.findByAnioMes(Integer.parseInt(aniomes.substring(0,4)), Integer.parseInt(aniomes.substring(4,aniomes.length()))));
		logger.info(AppController.getPrincipal() + " - buscargplpenfab.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - buscargplpenfab. - " + e.getMessage());
		}
		return "/reportes/golpes_pendientes_fab";
	}
	
	@RequestMapping(value = { "/ventas/Excel_golpesPend" },method=RequestMethod.GET)
	public ModelAndView excelGPend(HttpServletRequest req, HttpServletResponse res) {
		String aniomes = "";
		List<Golpes_pendientes_fab> listaexcel = null;
		try {
		aniomes = req.getParameter("aniomes");
		listaexcel = gpf.findByAnioMes(Integer.parseInt(aniomes.substring(0,4)), Integer.parseInt(aniomes.substring(4,aniomes.length())));
		logger.info(AppController.getPrincipal() + " - Excel_golpesPend.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - Excel_golpesPend. - " + e.getMessage());
		}
		return new ModelAndView(new ExcelGolpesPend2(), "listaexcel", listaexcel);
	}
	
	@RequestMapping(value = {"/ventas/golpes_pend_fab_2" }, method = RequestMethod.GET)
	public String golpes_pend_fab_2(ModelMap model) {
		try {
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		model.addAttribute("mesesanio", mesesanio.findallRegistros());
		model.addAttribute("selectedValue", String.valueOf(calendar.get(Calendar.YEAR)) + String.valueOf(calendar.get(Calendar.MONTH)+1));
		model.addAttribute("reporte",gpf.findByAnioMes(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1));
		logger.info(AppController.getPrincipal() + " - golpes_pend_fab_2.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - golpes_pend_fab_2. - " + e.getMessage());
		}
		return "/reportes/golpes_pendientes_fab2";
	}
	
	@RequestMapping(value = {"/ventas/buscargplpenfab2" }, method = RequestMethod.GET)
	public String buscargplpenfab2(ModelMap model,@RequestParam("aniomes") String aniomes) {
		try {
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		model.addAttribute("mesesanio", mesesanio.findallRegistros());
		model.addAttribute("selectedValue", aniomes);
		model.addAttribute("reporte", gpf.findByAnioMes(Integer.parseInt(aniomes.substring(0,4)), Integer.parseInt(aniomes.substring(4,aniomes.length()))));
		logger.info(AppController.getPrincipal() + " - buscargplpenfab2.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - buscargplpenfab2. - " + e.getMessage());
		}
		return "/reportes/golpes_pendientes_fab2";
	}
	
	@RequestMapping(value = { "/ventas/Excel_golpesPend2" },method=RequestMethod.GET)
	public ModelAndView excelGPend2(HttpServletRequest req, HttpServletResponse res) {
		String aniomes = "";
		List<Golpes_pendientes_fab> listaexcel = null;
		try {
		aniomes = req.getParameter("aniomes");
		listaexcel = gpf.findByAnioMes(Integer.parseInt(aniomes.substring(0,4)), Integer.parseInt(aniomes.substring(4,aniomes.length())));
		logger.info(AppController.getPrincipal() + " - Excel_golpesPend.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - Excel_golpesPend2. - " + e.getMessage());
		}
		return new ModelAndView(new ExcelGolpesPend2(), "listaexcel", listaexcel);
	}
	
	@RequestMapping(value = {"/ingenieria/amortizacion_herram___" }, method = RequestMethod.GET)
	public String amortizacion_herram___(ModelMap model) {
		try {
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		logger.info(AppController.getPrincipal() + " - amortizacion_herram___.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - amortizacion_herram___. - " + e.getMessage());
		}
		return "/reportes/amortiza_herramentales";
	}
	
	@RequestMapping(value = {"/ingenieria/buscarTpoHerr" }, method = RequestMethod.GET)
	public String buscarTpoHerr(ModelMap model,@RequestParam("select") String select,@RequestParam("herramental") String herramental) {
		try {
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		model.addAttribute("select", select);
		model.addAttribute("herramental", herramental);
		model.addAttribute("reporte",ahs.findAmortHerram(Integer.parseInt(select),herramental));
		logger.info(AppController.getPrincipal() + " - buscarTpoHerr.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - buscarTpoHerr. - " + e.getMessage());
		}
		return "/reportes/amortiza_herramentales";
	}
	
	@RequestMapping(value = { "/ingenieria/excelamortherr" },method=RequestMethod.GET)
	public ModelAndView excelamortherr(HttpServletRequest req, HttpServletResponse res) {
		String select = "";
		String herramental = "";
		List<Amortiza_herramentales> listaexcel = null;
		try {
			
		select = req.getParameter("select");
		herramental = req.getParameter("herramental");
		
		listaexcel = ahs.findAmortHerram(Integer.parseInt(select),herramental);
		logger.info(AppController.getPrincipal() + " - excelamortherr.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - excelamortherr. - " + e.getMessage());
		}
		return new ModelAndView(new ExcelAmortHerra(), "listaexcel", listaexcel);
	}
	
	@RequestMapping(value = {"/ventas/todos_pedidos___" }, method = RequestMethod.GET)
	public String todos_pedidos___(ModelMap model) {
		try {
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		int anio = calendar.get(Calendar.YEAR);
		model.addAttribute("select", anio);
		model.addAttribute("reporte", tps.findPedidosByAnio(anio));
		logger.info(AppController.getPrincipal() + " - todos_pedidos___.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - todos_pedidos___. - " + e.getMessage());
		}
		return "/reportes/todos_pedidos_ventas";
	}
	
	@RequestMapping(value = {"/ventas/buscarpedido" }, method = RequestMethod.GET)
	public String buscarpedido(ModelMap model,@RequestParam("anio") String anio) {
		try {
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		model.addAttribute("select", anio);
		model.addAttribute("reporte",tps.findPedidosByAnio(Integer.parseInt(anio)));
		logger.info(AppController.getPrincipal() + " - buscarpedido.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - buscarpedido. - " + e.getMessage());
		}
		return "/reportes/todos_pedidos_ventas";
	}
	
	@RequestMapping(value = { "/ventas/excelpedido" },method=RequestMethod.GET)
	public ModelAndView excelpedido(HttpServletRequest req, HttpServletResponse res) {
		String anio = "";
		List<Todos_pedidos> listaexcel = null;
		try {
		anio = req.getParameter("anio");
		listaexcel = tps.findPedidosByAnio(Integer.parseInt(anio));
		logger.info(AppController.getPrincipal() + " - excelpedido.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - excelpedido. - " + e.getMessage());
		}
		return new ModelAndView(new ExcelTodosPedidos(), "listaexcel", listaexcel);
	}
	
	@RequestMapping(value = {"/ingenieria/todos_pedidos_ing_" }, method = RequestMethod.GET)
	public String todos_pedidos_ing_(ModelMap model) {
		try {
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		int anio = calendar.get(Calendar.YEAR);
		model.addAttribute("select", anio);
		model.addAttribute("reporte", tps.findPedidosByAnio(anio));
		logger.info(AppController.getPrincipal() + " - todos_pedidos_ing_.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - todos_pedidos_ing_. - " + e.getMessage());
		}
		return "/reportes/todos_pedidos_ing";
	}
	
	@RequestMapping(value = {"/ingenieria/buscarpedidoing" }, method = RequestMethod.GET)
	public String buscarpedidoing(ModelMap model,@RequestParam("anio") String anio) {
		try {
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		model.addAttribute("select", anio);
		model.addAttribute("reporte",tps.findPedidosByAnio(Integer.parseInt(anio)));
		logger.info(AppController.getPrincipal() + " - buscarpedidoing.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - buscarpedidoing. - " + e.getMessage());
		}
		return "/reportes/todos_pedidos_ing";
	}
	
	@RequestMapping(value = {"/cobranza/detalle_cobranza" }, method = RequestMethod.GET)
	public String detalle_cob(ModelMap model) {
		try {
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		model.addAttribute("reporte",cds.findByCteVen());
		model.addAttribute("acumulado",cai.findByIntervalo());
		logger.info(AppController.getPrincipal() + " - /cobranza/detalle_cobranza.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - /cobranza/detalle_cobranza. - " + e.getMessage());
		}
		return "/reportes/detalle_cobranza";
	}
	
	@RequestMapping(value = {"/papel/consumo_kilos" }, method = RequestMethod.GET)
	public String consumo_kilos(ModelMap model) {
		try {
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		logger.info(AppController.getPrincipal() + " - consumo_kilos.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - consumo_kilos. - " + e.getMessage());
		}
		return "/reportes/consumo_kilos";
	}
	
	@RequestMapping(value = {"/papel/buscarconsumokilos" }, method = RequestMethod.GET)
	public String buscarconsumokilos(ModelMap model,@RequestParam("fecha_ini") String fi,@RequestParam("fecha_fin") String ff) {
		try {
			model.addAttribute("loggedinuser", AppController.getPrincipal());
			logger.info(AppController.getPrincipal() + " - buscarconsumokilos.");
			model.addAttribute("fecha_ini",fi);
			model.addAttribute("fecha_fin",ff);
			//Date fechai = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z").parse(fi);
			//Date fechaf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z").parse(ff);
			model.addAttribute("reporte",cks.findByAll(fi, ff, 0, 0));
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - buscarconsumokilos. - " + e.getMessage());
		}
		return "/reportes/consumo_kilos";
	}
	
	@RequestMapping(value = { "/papel/consumo_kilos_excel" },method=RequestMethod.GET)
	public ModelAndView consumo_kilos_excel(HttpServletRequest req, HttpServletResponse res) {
		String fecha_ini = "";
		String fecha_fin = "";
		List<ConsumoKilos> listaexcel = null;
		try {
			fecha_ini = req.getParameter("fecha_ini");
			fecha_fin = req.getParameter("fecha_fin");
			
			listaexcel = cks.findByAll(fecha_ini, fecha_fin, 0, 0);
			logger.info(AppController.getPrincipal() + " - consumo_kilos_excel.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - consumo_kilos_excel. - " + e.getMessage());
		}
		return new ModelAndView(new ConsKilosExcel(), "listaexcel", listaexcel);
	}
	
	@RequestMapping(value = {"/papel/inv_papl____" }, method = RequestMethod.GET)
	public String invetario_papel(ModelMap model) {
		try {
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		logger.info(AppController.getPrincipal() + " - inv_papl____.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - inv_papl____. - " + e.getMessage());
		}
		return "/reportes/invetario_papel";
	}
	
	@RequestMapping(value = {"/ventas/media_pedidos_cte1" }, method = RequestMethod.GET)
	public String media_pedidos_cte1(ModelMap model) {
		try {
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		logger.info(AppController.getPrincipal() + " - media_pedidos_cte1.");
		model.addAttribute("reporte", mpc.findbyFlag());
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - media_pedidos_cte1. - " + e.getMessage());
		}
		return "/reportes/mediapedidoscte1";
	}
	
	/*@RequestMapping(value = {"/ventas/media_pedidos_cte2" }, method = RequestMethod.GET)
	public String media_pedidos_cte2(ModelMap model) {
		try {
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		logger.info(AppController.getPrincipal() + " - media_pedidos_cte2.");
		model.addAttribute("reporte", mpc.findbyFlag(1));
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - media_pedidos_cte2. - " + e.getMessage());
		}
		return "/reportes/mediapedidoscte2";
	} */
	
	
	@RequestMapping(value = { "/ventas/mediapedidosExcel" },method=RequestMethod.GET)
	public ModelAndView mediapedidosExcel(HttpServletRequest req, HttpServletResponse res) {

		List<Media_pedidos_cte> listaexcel = null;
		try {
			//int ban = Integer.parseInt(req.getParameter("ban"));
			//System.out.println(ban);
			listaexcel = mpc.findbyFlag();
			logger.info(AppController.getPrincipal() + " - mediapedidosExcel.");
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - mediapedidosExcel. - " + e.getMessage());
		}
		return new ModelAndView(new MediaPedidosCte(), "listaexcel", listaexcel);
	} 
	
	@RequestMapping(value = {"/ventas/viajes_mes_ciudad" }, method = RequestMethod.GET)
	public String viajes_mes_ciudad(ModelMap model) {
		try {
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		logger.info(AppController.getPrincipal() + " - ventas/viajes_mes_ciudad.");
		model.addAttribute("mesesanio", mesesanio.findallRegistros());
		model.addAttribute("selectedValue", String.valueOf(calendar.get(Calendar.YEAR)) + String.valueOf(calendar.get(Calendar.MONTH)+1));
		model.addAttribute("reporte", vmc.findByAnioMes(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1));
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - ventas/viajes_mes_ciudad. - " + e.getMessage());
		}
		return "/reportes/viajes_mes_ciudad";
	}
	@RequestMapping(value = {"/ventas/buscarporMes" }, method = RequestMethod.GET)
	public String buscarporMes(ModelMap model,@RequestParam("aniomes") String aniomes) {
		try {
		model.addAttribute("loggedinuser", AppController.getPrincipal());
		logger.info(AppController.getPrincipal() + " - ventas/buscarporMes.");		
		model.addAttribute("mesesanio", mesesanio.findallRegistros());
		model.addAttribute("selectedValue", aniomes);
		model.addAttribute("reporte", vmc.findByAnioMes(Integer.parseInt(aniomes.substring(0,4)), Integer.parseInt(aniomes.substring(4,aniomes.length()))));
		}
		catch(Exception e) {
			logger.error(AppController.getPrincipal() + " - ventas/buscarporMes. - " + e.getMessage());
		}
		return "/reportes/viajes_mes_ciudad";
	}
	
	
	/*@RequestMapping(value = "helloReport1", method = RequestMethod.GET)
	  @ResponseBody
	  public void getRpt1(HttpServletResponse response) throws JRException, IOException {
		//System.out.println(this.getClass().getResourceAsStream("/jasperreports/Blank_A4.jasper"));
	    InputStream jasperStream = this.getClass().getResourceAsStream("/jasperreports/Blank_A4.jasper");
	    Map<String,Object> params = new HashMap<>();
	    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
	    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(cai.findByIntervalo());
	    //JRDataSource dataSource = getDataSource();
	    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

	    response.setContentType("application/pdf");
	    response.setHeader("Content-disposition", "inline");

	    final OutputStream outStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	  } */
	//cks
	//todos_pedidos_ing_
	/*@RequestMapping(value = "/profile", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String processAJAXRequest() {
	        String response = "respuesta";
	        // Process the request
	        // Prepare the response string
	        return response;
	  FUNCIONA OK CON AJAX  
	}*/
		
}
