package com.websystique.springmvc.service.tarjetas;

import java.util.List;

import com.websystique.springmvc.model.tarjetas.Catalogo_clientes_sap_vw;

public interface Catalogo_clientes_sap_vwService {
	List<Catalogo_clientes_sap_vw> ListaCtes();
	List<Catalogo_clientes_sap_vw> ListaCtes(Integer SlpCode);
	Catalogo_clientes_sap_vw cat_cte_sap(String CardCode);
}
