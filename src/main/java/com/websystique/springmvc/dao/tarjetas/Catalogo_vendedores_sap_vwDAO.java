package com.websystique.springmvc.dao.tarjetas;

import java.util.List;

import com.websystique.springmvc.model.tarjetas.Catalogo_vendedores_sap_vw;

public interface Catalogo_vendedores_sap_vwDAO {
	List<Catalogo_vendedores_sap_vw> ListaVendedores();
	Catalogo_vendedores_sap_vw BuscarXid(Integer id);
}
