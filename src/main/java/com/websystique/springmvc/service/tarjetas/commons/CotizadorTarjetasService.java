package com.websystique.springmvc.service.tarjetas.commons;

import java.util.List;

import org.json.JSONObject;

import com.websystique.springmvc.model.tarjetas.cotizador.Cotizador;
import com.websystique.springmvc.model.tarjetas.cotizador.Cotizador_detalles;
import com.websystique.springmvc.model.tarjetas.fabricacion.Tarjeta_fabricacion;

public interface CotizadorTarjetasService {
	JSONObject DataSourceJasperCot(Integer id, Integer addDetalles);//addDetalle: 1: Sí, 0: No
	JSONObject addSpecificDetalle(Integer id, Integer iddet);
	//JSONObject DataSourceJasperReq(Integer id);
	JSONObject DataSourceJasperTF(Integer id, Integer iddet, Integer addCotCotDet, Integer idprog);//addCotCotDet: 1: Sí, 0: No
	List<JSONObject> addEspecialidades(Integer id, Integer iddet);	
	JSONObject DataSourceJasperCot(Cotizador cot, Integer addDetalles);//addDetalle: 1: Sí, 0: No
	JSONObject addSpecificDetalle(Cotizador_detalles a);
	JSONObject DataSourceJasperTF(Tarjeta_fabricacion tar, Integer addCotCotDet, Integer idprog);//addCotCotDet: 1: Sí, 0: No
}
