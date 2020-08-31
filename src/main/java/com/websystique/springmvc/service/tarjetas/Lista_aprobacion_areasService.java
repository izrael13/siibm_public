package com.websystique.springmvc.service.tarjetas;

import java.util.List;
import java.util.Map;

import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.tarjetas.Lista_aprobacion_areas;

public interface Lista_aprobacion_areasService {
	Lista_aprobacion_areas BuscarXKey(Integer id);
	List<Lista_aprobacion_areas> ListaAproba(List<ParamsGeneral> params, Map<String,String> mOrd);
}
