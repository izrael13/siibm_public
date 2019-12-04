package com.websystique.springmvc.service.tarjetas.commons;

import java.util.List;

import org.json.JSONObject;

public interface CotizadorTarjetasService {
	JSONObject DataSourceJasperCot(Integer id, Integer addDetalles);//addDetalle: 1: Sí, 0: No
	JSONObject addSpecificDetalle(Integer id, Integer iddet);
	//JSONObject DataSourceJasperReq(Integer id);
	JSONObject DataSourceJasperTF(Integer id, Integer iddet, Integer addCotCotDet);//addCotCotDet: 1: Sí, 0: No
	List<JSONObject> addEspecialidades(Integer id, Integer iddet);
}
