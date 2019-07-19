package com.websystique.springmvc.dao.tarjetas;

import java.util.List;

import com.websystique.springmvc.model.tarjetas.Catalogo_resistencias_sap_vw;

public interface Catalogo_resistencias_sap_vwDAO {
	List<Catalogo_resistencias_sap_vw> ListaResis();
	Catalogo_resistencias_sap_vw BuscarxId(Integer id);
	List<Catalogo_resistencias_sap_vw> ListaResis(String corrugado);
}
