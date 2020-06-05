package com.websystique.springmvc.dao.programas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.programas.Programas_cti;

@Repository("programas_ctiDAO")
public class Programas_ctiDAOImpl extends AbstractDao<Integer, Programas_cti> implements Programas_ctiDAO{

	@Override
	public Programas_cti BuscarxId(Integer id) {
		return getByKey(id);
	}

	@Override
	public Programas_cti BuscarxParams(List<ParamsGeneral> Params) {
		return (Programas_cti) criteriaGeneralObj(Params);
	}

	@Override
	public List<Programas_cti> BuscarxParams(List<ParamsGeneral> Params, Map<String, String> mOrd) {
		return criteriaGeneralList(Params, mOrd);
	}

	@Override
	public List<Programas_cti> BuscarxParams(Integer Pedido, String TFSAP) {
		Map<Integer,Integer> paramsInt = new HashMap<Integer, Integer>();
		Map<Integer,String> paramStr = new HashMap<Integer, String>();
		String query = "SELECT * \r\n" + 
				"FROM PROGRAMAS_CTI A\r\n" + 
				"WHERE A.pedido = ?1 AND A.tarjeta = ?2\r\n" + 
				"AND A.programa COLLATE SQL_Latin1_General_CP1_CI_AS NOT IN (SELECT B.PROGRAMA \r\n" + 
				"					   FROM PROGRAMAS_REG_BARCA B \r\n" + 
				"					   WHERE B.PEDIDO = A.pedido AND \r\n" + 
				"					   B.TF_SAP COLLATE SQL_Latin1_General_CP1_CI_AS = tarjeta AND \r\n" + 
				"					   B.PROGRAMA COLLATE SQL_Latin1_General_CP1_CI_AS = A.programa AND \r\n" + 
				"					   B.CANT_PROGRAMADA = A.pzasprogramadas\r\n" + 
				"					  );";
		paramsInt.put(1, Pedido);
		paramStr.put(2, TFSAP);
		return criteriaQueryNamedStr(query,paramsInt,paramStr);
	}

}
