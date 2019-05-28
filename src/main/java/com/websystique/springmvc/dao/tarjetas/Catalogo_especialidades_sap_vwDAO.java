package com.websystique.springmvc.dao.tarjetas;

import java.util.List;

import com.websystique.springmvc.model.tarjetas.Catalogo_especialidades_sap_vw;

public interface Catalogo_especialidades_sap_vwDAO {
	List<Catalogo_especialidades_sap_vw> ListaEsp();
	Catalogo_especialidades_sap_vw BuscaxId(Integer id);
}
