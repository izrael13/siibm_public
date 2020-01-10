package com.websystique.springmvc.dao.programas;

import java.util.List;
import java.util.Map;

import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.programas.Catalogo_pedidos_sap;

public interface Catalogo_pedidos_sapDAO {
	Catalogo_pedidos_sap BuscarxId(Integer id);
	Catalogo_pedidos_sap BuscarxParamas(List<ParamsGeneral> Params);
	List<Catalogo_pedidos_sap> BuscarxParamas(List<ParamsGeneral> Params, Map<String,String> mOrd);
}
