package com.websystique.springmvc.dao.tarjetas;

import java.util.List;

import com.websystique.springmvc.model.tarjetas.Catalogo_direcciones_sap_vw;

public interface Catalogo_direcciones_sap_vwDAO {
	List<Catalogo_direcciones_sap_vw> ListaDir();
	List<Catalogo_direcciones_sap_vw> ListaDirCardCode(String CardCode);
	List<Catalogo_direcciones_sap_vw> ListaDirCardCodeNumLine(String CardCode,Integer NumLine);
}
