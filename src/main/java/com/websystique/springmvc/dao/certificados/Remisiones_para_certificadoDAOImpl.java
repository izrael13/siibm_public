package com.websystique.springmvc.dao.certificados;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.certificados.Remisiones_para_certificado;

@Repository("remisiones_para_certificadoDAO")
public class Remisiones_para_certificadoDAOImpl extends AbstractDao<Integer, Remisiones_para_certificado> implements Remisiones_para_certificadoDAO{

	@Override
	public Remisiones_para_certificado BuscarXParamsObj(List<ParamsGeneral> params) {
		return (Remisiones_para_certificado) criteriaGeneralObj(params);
	}

	@Override
	public List<Remisiones_para_certificado> BuscarXParamsList(List<ParamsGeneral> params, Map<String, String> mOrd) {
		return criteriaGeneralList(params, mOrd);
	}



}
