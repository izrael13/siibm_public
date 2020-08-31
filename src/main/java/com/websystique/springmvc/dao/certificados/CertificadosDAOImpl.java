package com.websystique.springmvc.dao.certificados;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.certificados.Certificados;

@Repository("certificadosDAO")
public class CertificadosDAOImpl  extends AbstractDao<Integer, Certificados> implements CertificadosDAO{

	@Override
	public Certificados BuscarxId(Integer id) {
		return getByKey(id);
	}

	@Override
	public Certificados BuscarxParamsObj(List<ParamsGeneral> params) {
		return (Certificados) criteriaGeneralObj(params);
	}

	@Override
	public List<Certificados> BuscarxParamsList(List<ParamsGeneral> params, Map<String, String> mOrd) {
		return criteriaGeneralList(params, mOrd);
	}

	@Override
	public void Guardar(Certificados c) {
		persist(c);
	}

	@Override
	public void Actualizar(Certificados c) {
		update(c);
	}

}
