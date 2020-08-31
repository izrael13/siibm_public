package com.websystique.springmvc.service.tarjetas;

import java.util.List;

import com.websystique.springmvc.model.tarjetas.Catalogo_identificadores_sap_vw;

public interface Catalogo_identificadores_sap_vwService {
	Catalogo_identificadores_sap_vw BuscaxId(Integer id);
	List<Catalogo_identificadores_sap_vw> ListaIdentificadores();
}
