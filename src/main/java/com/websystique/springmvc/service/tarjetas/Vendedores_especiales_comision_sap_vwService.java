package com.websystique.springmvc.service.tarjetas;

import java.util.List;

import com.websystique.springmvc.model.tarjetas.Vendedores_especiales_comision_sap_vw;

public interface Vendedores_especiales_comision_sap_vwService {
	List<Vendedores_especiales_comision_sap_vw> VenEsp(Integer SlpCode, String CardCode);
}
