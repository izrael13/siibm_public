package com.websystique.springmvc.dao.programas;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.programas.Catalogo_pedidos_sap;

@Repository("catalogo_pedidos_sapDAO")
public class Catalogo_pedidos_sapDAOImpl  extends AbstractDao<Integer,Catalogo_pedidos_sap> implements Catalogo_pedidos_sapDAO{

	@Override
	public Catalogo_pedidos_sap BuscarxId(Integer id) {
		return getByKey(id);
	}

	@Override
	public Catalogo_pedidos_sap BuscarxParamas(List<ParamsGeneral> Params) {
		return (Catalogo_pedidos_sap) criteriaGeneralObj(Params);
	}

	@Override
	public List<Catalogo_pedidos_sap> BuscarxParamas(List<ParamsGeneral> Params, Map<String, String> mOrd) {
		return criteriaGeneralList(Params, mOrd);
	}

}
