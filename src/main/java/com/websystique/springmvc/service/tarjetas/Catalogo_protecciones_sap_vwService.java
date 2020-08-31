package com.websystique.springmvc.service.tarjetas;

import java.util.List;

import com.websystique.springmvc.model.tarjetas.Catalogo_protecciones_sap_vw;

public interface Catalogo_protecciones_sap_vwService {
	Catalogo_protecciones_sap_vw BuscaxId(Integer id);
	List<Catalogo_protecciones_sap_vw> ListaProtecciones();
}
