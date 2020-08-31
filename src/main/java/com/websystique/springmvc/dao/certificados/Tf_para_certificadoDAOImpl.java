package com.websystique.springmvc.dao.certificados;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.certificados.Tf_para_certificado;

@Repository("tf_para_certificadoDAO")
public class Tf_para_certificadoDAOImpl extends AbstractDao<Integer, Tf_para_certificado> implements Tf_para_certificadoDAO{

	@Override
	public Tf_para_certificado BuscarxParams(List<ParamsGeneral> params) {
		return (Tf_para_certificado) criteriaGeneralObj(params);
	}

	@Override
	public List<Tf_para_certificado> BuscarListxParams(List<ParamsGeneral> params, Map<String, String> mOrd) {
		return criteriaGeneralList(params, mOrd);
	}

}
