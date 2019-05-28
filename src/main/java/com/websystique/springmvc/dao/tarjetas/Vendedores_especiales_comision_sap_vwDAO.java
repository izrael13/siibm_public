package com.websystique.springmvc.dao.tarjetas;

import java.util.List;

import com.websystique.springmvc.model.tarjetas.Vendedores_especiales_comision_sap_vw;

public interface Vendedores_especiales_comision_sap_vwDAO {
	List<Vendedores_especiales_comision_sap_vw> VenEsp(Integer SlpCode, String CardCode);
}
