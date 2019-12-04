package com.websystique.springmvc.service.tarjetas.commons;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.tarjetas.Catalogo_colores;
import com.websystique.springmvc.model.tarjetas.Catalogo_direcciones_sap_vw;
import com.websystique.springmvc.model.tarjetas.Catalogo_especialidades_sap_vw;
import com.websystique.springmvc.model.tarjetas.Catalogo_resistencias_sap_vw;
import com.websystique.springmvc.model.tarjetas.cotizador.Cotizador;
import com.websystique.springmvc.model.tarjetas.cotizador.Cotizador_detalles;
import com.websystique.springmvc.model.tarjetas.fabricacion.Tarjeta_fabricacion;
import com.websystique.springmvc.model.tarjetas.fabricacion.Tarjetas_fabricacion_imagenes;
import com.websystique.springmvc.service.UserService;
import com.websystique.springmvc.service.tarjetas.Catalogo_cajas_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Catalogo_clientes_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Catalogo_coloresService;
import com.websystique.springmvc.service.tarjetas.Catalogo_direcciones_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Catalogo_especialidades_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Catalogo_herramentalesService;
import com.websystique.springmvc.service.tarjetas.Catalogo_resistencias_sap_vwService;
import com.websystique.springmvc.service.tarjetas.Catalogo_sellosService;
import com.websystique.springmvc.service.tarjetas.Codigo_barras_cotizadorService;
import com.websystique.springmvc.service.tarjetas.Especialidades_cotizacionService;
import com.websystique.springmvc.service.tarjetas.cotizador.CotizadorService;
import com.websystique.springmvc.service.tarjetas.cotizador.Cotizador_detallesService;
import com.websystique.springmvc.service.tarjetas.fabricacion.Tarjeta_fabricacionService;
import com.websystique.springmvc.service.tarjetas.fabricacion.Tarjetas_fabricacion_imagenesService;

@Service("cotizadorTarjetasService")
@Transactional
public class CotizadorTarjetasServiceImpl implements CotizadorTarjetasService{
	
	@Autowired
	UserService us;
	@Autowired
	CotizadorService cs;
	@Autowired
	Catalogo_clientes_sap_vwService ccavs;
	@Autowired
	Catalogo_direcciones_sap_vwService cdsv;
	@Autowired
	Cotizador_detallesService cds;
	@Autowired
	Codigo_barras_cotizadorService cbsc;
	@Autowired
	Especialidades_cotizacionService ecs;
	@Autowired
	Catalogo_especialidades_sap_vwService ces;
	@Autowired
	Catalogo_cajas_sap_vwService ccss;
	@Autowired
	Catalogo_resistencias_sap_vwService crss;
	@Autowired
	Catalogo_sellosService css;
	@Autowired
	Catalogo_coloresService ccos;
	@Autowired
	Catalogo_herramentalesService chs;
	@Autowired
	Tarjeta_fabricacionService tfs;
	@Autowired
	Tarjetas_fabricacion_imagenesService tfis;
	
	@Override
	public JSONObject DataSourceJasperCot(Integer id, Integer addDetalles) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().setDateFormat("yyyy-MM-dd HH:mm").create();
		Cotizador cot = new Cotizador();
		cot = cs.BuscarxId(id);
		JSONObject JsonCot = new JSONObject(gson.toJson(cot));
		JsonCot.put("cliente", ccavs.cat_cte_sap(cot.getCardcode()).getCardname());
		JsonCot.put("cliente_factura", (cot.getCardcode_factura() != null ? ccavs.cat_cte_sap(cot.getCardcode_factura()).getCardname() : ""));
		Catalogo_direcciones_sap_vw dir = new Catalogo_direcciones_sap_vw();
		dir = cdsv.DirCardCodeNumLine(cot.getCardcode(), cot.getLinenum_dir_entrega()); 
		JsonCot.put("lab",dir.getDireccion()+ " " +dir.getCiudad()+ " "+dir.getEstado());
		User user = new User();
		user = us.findById(cot.getUsuario_aut_ventas() == null ? 0 : cot.getUsuario_aut_ventas());
		JsonCot.put("autorizador", user != null ? user.getFirstName() + " " + user.getLastName() : "");
		user = null;
		user = us.findById(cot.getUsuario_insert());
		JsonCot.put("representante", user != null ? user.getFirstName() + " " + user.getLastName() : "");
		user = null;
		user = us.findById(cot.getUsuario_asigna_arrastre() == null ? 0 : cot.getUsuario_asigna_arrastre());
			JsonCot.put("arrmuestrista", user == null ? "" : user.getFirstName() + " " + user.getLastName());
		user = null;
		user = us.findById(cot.getUsuario_diseniador() == null ? 0 : cot.getUsuario_diseniador());
			JsonCot.put("diseniador", user == null ? "" : user.getFirstName() + " " + user.getLastName());
		user = null;
		user = us.findById(cot.getUsuario_rech_diseniador() == null ? 0 : cot.getUsuario_rech_diseniador());
		JsonCot.put("diseniador_rech", user == null ? "" : user.getFirstName() + " " + user.getLastName());
		user = null;
		user = us.findById(cot.getUsuario_cancel() == null ? 0 : cot.getUsuario_cancel());
		JsonCot.put("usuario_cancel", user == null ? "" : user.getFirstName() + " " + user.getLastName());
		
		if(addDetalles == 1)
		{
			List<JSONObject> ListaJsonDet = new ArrayList<JSONObject>();
			cds.BuscarxCotId(id).forEach(a ->{
				ListaJsonDet.add(addSpecificDetalle(id,a.getIddetalle()));
			});
			
			JsonCot.put("ListaDetalles", ListaJsonDet);
		}
		
		JsonCot.put("SUBREPORT_DIR", "/jasperreports/cotizador/");
		
		return JsonCot;
	}

	@Override
	public JSONObject addSpecificDetalle(Integer id, Integer iddet) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().create();
		Cotizador_detalles a = new Cotizador_detalles(); 
		a = cds.BuscarxIdDet(id, iddet);
		a.setCodigo_barra_cotizador(cbsc.BuscarXCotDet(id, iddet));
		
			JSONObject JsonCotDet = new JSONObject(gson.toJson(a));
			List<JSONObject> ListaJsonEsp = new ArrayList<JSONObject>();
			ListaJsonEsp = addEspecialidades(id, iddet);
			JsonCotDet.put("ListaEsp", ListaJsonEsp);

			JsonCotDet.put("estilo_caja", a.getIdcaja_sap() == null ? "" : ccss.BuscarxId(a.getIdcaja_sap()).getNombrecorto());

			Catalogo_resistencias_sap_vw objResis = new Catalogo_resistencias_sap_vw();
			objResis = a.getIdresistencia_barca() == null ? null : crss.BuscarxId(a.getIdresistencia_barca());

			JsonCotDet.put("resistencia", objResis == null ? "" : objResis.getResistencia());

			JsonCotDet.put("flauta", objResis == null ? "" : objResis.getCorrugado());

			JsonCotDet.put("papel", objResis == null ? "" : objResis.getColor());

			JsonCotDet.put("SUBREPORT_DIR", "/jasperreports/cotizador/");
			JsonCotDet.put("resis_cte", a.getResistencia_cte() == null ? "" : css.BuscarxId(a.getResistencia_cte()).getNombre());
			
			Catalogo_colores color = new Catalogo_colores();
			color = ccos.BuscarxId(a.getColor1() == null ? 0 : a.getColor1());		
			JsonCotDet.put("color1n", a.getColor1() == null ? "" : color.getColor());
			JsonCotDet.put("color1c", a.getColor1() == null ? "" : "#"+color.getColor_est().trim());
			
			color = ccos.BuscarxId(a.getColor2() == null ? 0 : a.getColor2());		
			JsonCotDet.put("color2n", a.getColor2() == null ? "" : color.getColor());
			JsonCotDet.put("color2c", a.getColor2() == null ? "" : "#"+color.getColor_est().trim());
			
			color = ccos.BuscarxId(a.getColor3() == null ? 0 : a.getColor3());		
			JsonCotDet.put("color3n", a.getColor3() == null ? "" : color.getColor());
			JsonCotDet.put("color3c", a.getColor3() == null ? "" : "#"+color.getColor_est().trim());
			
			color = ccos.BuscarxId(a.getColor4() == null ? 0 : a.getColor4());		
			JsonCotDet.put("color4n", a.getColor4() == null ? "" : color.getColor());
			JsonCotDet.put("color4c", a.getColor4() == null ? "" : "#"+color.getColor_est().trim());
			
			color = ccos.BuscarxId(a.getColor5() == null ? 0 : a.getColor5());		
			JsonCotDet.put("color5n", a.getColor5() == null ? "" : color.getColor());
			JsonCotDet.put("color5c", a.getColor5() == null ? "" : "#"+color.getColor_est().trim());
			
			color = ccos.BuscarxId(a.getColor6() == null ? 0 : a.getColor6());		
			JsonCotDet.put("color6n", a.getColor6() == null ? "" : color.getColor());
			JsonCotDet.put("color6c", a.getColor6() == null ? "" : "#"+color.getColor_est().trim());
			
			color = ccos.BuscarxId(a.getColor7() == null ? 0 : a.getColor7());		
			JsonCotDet.put("color7n", a.getColor7() == null ? "" : color.getColor());
			JsonCotDet.put("color7c", a.getColor7() == null ? "" : "#"+color.getColor_est().trim());
					
		return JsonCotDet;
	}

	/*@Override
	public JSONObject DataSourceJasperReq(Integer id) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().setDateFormat("yyyy-MM-dd HH:mm").create();
		Cotizador cot = new Cotizador();
		cot = cs.BuscarxId(id);
		JSONObject JsonCot = new JSONObject(gson.toJson(cot));
		JsonCot.put("cliente", ccavs.cat_cte_sap(cot.getCardcode()).getCardname());
		JsonCot.put("cliente_factura", (cot.getCardcode_factura() != null ? ccavs.cat_cte_sap(cot.getCardcode_factura()).getCardname() : ""));
		Catalogo_direcciones_sap_vw dir = new Catalogo_direcciones_sap_vw();
		dir = cdsv.DirCardCodeNumLine(cot.getCardcode(), cot.getLinenum_dir_entrega()); 
		JsonCot.put("lab",dir.getDireccion()+ " " +dir.getCiudad()+ " "+dir.getEstado());
		JsonCot.put("contacto",dir.getContacto());
		JsonCot.put("correo",dir.getEmail());
		JsonCot.put("telefono",dir.getTelefono());
		User user = new User();
		user = us.findById(cot.getUsuario_aut_ventas() == null ? 0 : cot.getUsuario_aut_ventas());
		if(user != null)
			JsonCot.put("autorizador", user.getFirstName() + " " + user.getLastName());
		user = null;
		user = us.findById(cot.getUsuario_insert());
		if(user != null)
			JsonCot.put("representante", user.getFirstName() + " " + user.getLastName());		
		List<JSONObject> ListaJsonDet = new ArrayList<JSONObject>();	
		cds.BuscarxCotId(id).forEach(a ->{
			a.setCodigo_barra_cotizador(cbsc.BuscarXCotDet(a.getIdcotizacion(), a.getIddetalle()));
			JSONObject JsonCotDet = new JSONObject(gson.toJson(a));
			List<JSONObject> ListaJsonEsp = new ArrayList<JSONObject>();
			ListaJsonEsp = addEspecialidades(id, a.getIddetalle());
			JsonCotDet.put("ListaEsp", ListaJsonEsp);
			JsonCotDet.put("estilo_caja", ccss.BuscarxId(a.getIdcaja_sap()).getNombrecorto());
			Catalogo_resistencias_sap_vw objResis = new Catalogo_resistencias_sap_vw();
			objResis = crss.BuscarxId(a.getIdresistencia_barca());
			JsonCotDet.put("resistencia", objResis.getResistencia());
			JsonCotDet.put("flauta", objResis.getCorrugado());
			JsonCotDet.put("papel", objResis.getColor());
			JsonCotDet.put("SUBREPORT_DIR", "/jasperreports/cotizador/");
			JsonCotDet.put("resis_cte", css.BuscarxId(a.getResistencia_cte()).getNombre());
			
			Catalogo_colores color = new Catalogo_colores();
			color = ccos.BuscarxId(a.getColor1() == null ? 0 : a.getColor1());		
			JsonCotDet.put("color1n", a.getColor1() == null ? "" : color.getColor());
			JsonCotDet.put("color1c", a.getColor1() == null ? "" : "#"+color.getColor_est().trim());
			
			color = ccos.BuscarxId(a.getColor2() == null ? 0 : a.getColor2());		
			JsonCotDet.put("color2n", a.getColor2() == null ? "" : color.getColor());
			JsonCotDet.put("color2c", a.getColor2() == null ? "" : "#"+color.getColor_est().trim());
			
			color = ccos.BuscarxId(a.getColor3() == null ? 0 : a.getColor3());		
			JsonCotDet.put("color3n", a.getColor3() == null ? "" : color.getColor());
			JsonCotDet.put("color3c", a.getColor3() == null ? "" : "#"+color.getColor_est().trim());
			
			color = ccos.BuscarxId(a.getColor4() == null ? 0 : a.getColor4());		
			JsonCotDet.put("color4n", a.getColor4() == null ? "" : color.getColor());
			JsonCotDet.put("color4c", a.getColor4() == null ? "" : "#"+color.getColor_est().trim());
			
			color = ccos.BuscarxId(a.getColor5() == null ? 0 : a.getColor5());		
			JsonCotDet.put("color5n", a.getColor5() == null ? "" : color.getColor());
			JsonCotDet.put("color5c", a.getColor5() == null ? "" : "#"+color.getColor_est().trim());
			
			color = ccos.BuscarxId(a.getColor6() == null ? 0 : a.getColor6());		
			JsonCotDet.put("color6n", a.getColor6() == null ? "" : color.getColor());
			JsonCotDet.put("color6c", a.getColor6() == null ? "" : "#"+color.getColor_est().trim());
			
			color = ccos.BuscarxId(a.getColor7() == null ? 0 : a.getColor7());		
			JsonCotDet.put("color7n", a.getColor7() == null ? "" : color.getColor());
			JsonCotDet.put("color7c", a.getColor7() == null ? "" : "#"+color.getColor_est().trim());
			
			ListaJsonDet.add(JsonCotDet);
		});	
		
		JsonCot.put("ListaDetalles", ListaJsonDet);
		JsonCot.put("SUBREPORT_DIR", "/jasperreports/cotizador/");
		return JsonCot;
	} */

	@SuppressWarnings({ "rawtypes"})
	@Override
	public JSONObject DataSourceJasperTF(Integer id, Integer iddet, Integer addCotCotDet) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().create();
		User user = new User();
		Tarjeta_fabricacion tf= new Tarjeta_fabricacion();
		JSONObject JsonTF = null;
		
		tf = tfs.BuscarxCot_Cotdet(id, iddet);
		if(tf != null)
		{			
			JsonTF = new JSONObject(gson.toJson(tf));
			
			tf.setTarjeta_img(tfis.BuscarxIdCotidDert(id, iddet));
			for(Tarjetas_fabricacion_imagenes tfi : tf.getTarjeta_img())
			{
				if(tfi.getCama()) {
					JsonTF.put("cama_nombre", tfi.getNombre());
					JsonTF.put("cama_path", tfi.getPath());
				}
				else
				{
					if(tfi.getPrincipal())
					{
						JsonTF.put("principal_nombre", tfi.getNombre());
						JsonTF.put("principal_path", tfi.getPath());
					}
				}	
			}
			JsonTF.put("tarjeta_img", tf.getTarjeta_img().stream().filter(a -> !a.getCama() && !a.getPrincipal()).collect(Collectors.toList()));
			user = us.findById(tf.getUsuario_aut_diseniador() == null ? 0 : tf.getUsuario_aut_diseniador());
			JsonTF.put("diseniador", tf.getUsuario_aut_diseniador() == null ? "" : user.getFirstName() + " " +user.getLastName());
			
			user = us.findById(tf.getUsuario_aut_calidad() == null ? 0 : tf.getUsuario_aut_calidad());
			JsonTF.put("aut_calidad", tf.getUsuario_aut_calidad() == null ? "" : user.getFirstName() + " " +user.getLastName());
			
			user = us.findById(tf.getUsuario_aut_cliente() == null ? 0 : tf.getUsuario_aut_cliente());
			JsonTF.put("aut_cliente", tf.getUsuario_aut_cliente() == null ? "" : user.getFirstName() + " " +user.getLastName());
			
			user = us.findById(tf.getUsuario_aut_ing() == null ? 0 : tf.getUsuario_aut_ing());
			JsonTF.put("aut_ingenieria", tf.getUsuario_aut_ing() == null ? "" : user.getFirstName() + " " +user.getLastName());
			
			user = us.findById(tf.getUsuario_aut_produccion() == null ? 0 :tf.getUsuario_aut_produccion());
			JsonTF.put("aut_produccion", tf.getUsuario_aut_produccion() == null ? "" : user.getFirstName() + " " +user.getLastName());
			
			user = us.findById(tf.getUsuario_rech_produccion() == null ? 0 :tf.getUsuario_rech_produccion());
			JsonTF.put("rech_produccion", tf.getUsuario_rech_produccion() == null ? "" : user.getFirstName() + " " +user.getLastName());
			
			user = us.findById(tf.getUsuario_recha_ing() == null ? 0 : tf.getUsuario_recha_ing());
			JsonTF.put("rech_ingenieria", tf.getUsuario_recha_ing() == null ? "" : user.getFirstName() + " " +user.getLastName());
			
			user = us.findById(tf.getUsuario_rech_cliente() == null ? 0 : tf.getUsuario_rech_cliente());
			JsonTF.put("rech_cliente", tf.getUsuario_rech_cliente() == null ? "" : user.getFirstName() + " " +user.getLastName());
			
			JsonTF.put("suaje", tf.getSuaje() == null ? "" : chs.BuscarxId(tf.getSuaje()).getNombre());
			JsonTF.put("grabado", tf.getGrabado() == null ? "" : chs.BuscarxId(tf.getGrabado()).getNombre());
			
			user = us.findById(tf.getUsuario_rech_calidad() == null ? 0 : tf.getUsuario_rech_calidad());
			JsonTF.put("rech_calidad", tf.getUsuario_rech_calidad() == null ? "" : user.getFirstName() + " " +user.getLastName());
			
			user = us.findById(tf.getUsuario_cancela() == null ? 0 : tf.getUsuario_cancela());
			JsonTF.put("usr_cancela", tf.getUsuario_cancela() == null ? "" : user.getFirstName() + " " +user.getLastName());
			
			if(addCotCotDet == 1)
			{
				JSONObject JsonCot = DataSourceJasperCot(id,0);
				JSONObject JsonCotdet = addSpecificDetalle(id,iddet);
						
				Iterator itCot = JsonCot.keys();
				Iterator itCotDet = JsonCotdet.keys();
				
				while(itCot.hasNext()) {
		            String key = (String) itCot.next();
		            JsonTF.put(key, JsonCot.get(key));
		        }
				
				while(itCotDet.hasNext()) {
		            String key = (String) itCotDet.next();
		            JsonTF.put(key, JsonCotdet.get(key));
		        }
			}
		}
		System.out.println(JsonTF.toString());
		return JsonTF;
	}

	@Override
	public List<JSONObject> addEspecialidades(Integer id, Integer iddet) {
		List<JSONObject> ListaJsonEsp = new ArrayList<JSONObject>();
		ecs.ListaEspDet(id, iddet).forEach(b ->{
			JSONObject JsonEsp = new JSONObject();
			Catalogo_especialidades_sap_vw EspSap = ces.BuscaxId(b.getIdespecialidad());
			JsonEsp.put("count", b.getCount());
			JsonEsp.put("iddetalle", b.getIddetalle());
			JsonEsp.put("idcotizacion", b.getIdcotizacion());
			JsonEsp.put("especialidad", EspSap.getName());
			JsonEsp.put("costo", b.getCosto());
			JsonEsp.put("ajuste", b.getAjuste());
			JsonEsp.put("esquema", b.getEsquema());
			JsonEsp.put("cm", b.getCm());
			JsonEsp.put("iddespecialidad", b.getIdespecialidad());
			JsonEsp.put("ruta", EspSap.getRuta());
			ListaJsonEsp.add(JsonEsp);
		});
		
		return ListaJsonEsp;
	}

}
