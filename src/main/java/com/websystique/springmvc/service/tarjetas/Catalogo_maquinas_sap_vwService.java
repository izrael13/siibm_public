package com.websystique.springmvc.service.tarjetas;

import java.util.List;

import com.websystique.springmvc.model.tarjetas.Catalogo_maquinas_sap_vw;

public interface Catalogo_maquinas_sap_vwService {
	List<Catalogo_maquinas_sap_vw> ListaMaquinas();
	Catalogo_maquinas_sap_vw BuscarxId(Integer id);
	List<Catalogo_maquinas_sap_vw> ListaMaquinas(Integer grupo);
	List<Catalogo_maquinas_sap_vw> ListaMaquinas(Integer grupo, Integer numTintas);
	List<Catalogo_maquinas_sap_vw> ListaMaquinas(Boolean u_grapado);
}
