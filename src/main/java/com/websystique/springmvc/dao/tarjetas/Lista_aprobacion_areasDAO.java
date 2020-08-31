package com.websystique.springmvc.dao.tarjetas;

import java.util.List;
import java.util.Map;

import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.tarjetas.Lista_aprobacion_areas;

public interface Lista_aprobacion_areasDAO {
	Lista_aprobacion_areas BuscarXKey(Integer id);
	List<Lista_aprobacion_areas> ListaAproba(List<ParamsGeneral> params, Map<String,String> mOrd);
}
