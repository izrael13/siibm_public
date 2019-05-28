package com.websystique.springmvc.dao.tarjetas;

import java.util.List;

import com.websystique.springmvc.model.tarjetas.Catalogo_cajas_sap_vw;

public interface Catalogo_cajas_sap_vwDAO {
	List<Catalogo_cajas_sap_vw> ListaCajas();
	Catalogo_cajas_sap_vw BuscarxId(Integer id);
}
