package com.websystique.springmvc.service.tarjetas;

import java.util.List;

import com.websystique.springmvc.model.tarjetas.Catalogo_especialidades_sap_vw;

public interface Catalogo_especialidades_sap_vwService {
	List<Catalogo_especialidades_sap_vw> ListaEsp(Integer vigente);
	Catalogo_especialidades_sap_vw BuscaxId(Integer id);
}
