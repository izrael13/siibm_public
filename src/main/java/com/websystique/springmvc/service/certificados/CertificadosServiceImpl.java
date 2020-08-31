package com.websystique.springmvc.service.certificados;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.certificados.CertificadosDAO;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.certificados.Certificados;

@Service("certificadosService")
@Transactional
public class CertificadosServiceImpl implements CertificadosService{
	
	@Autowired CertificadosDAO dao;
	
	@Override
	public Certificados BuscarxId(Integer id) {
		return dao.BuscarxId(id);
	}

	@Override
	public Certificados BuscarxParamsObj(List<ParamsGeneral> params) {
		return dao.BuscarxParamsObj(params);
	}

	@Override
	public List<Certificados> BuscarxParamsList(List<ParamsGeneral> params, Map<String, String> mOrd) {
		return dao.BuscarxParamsList(params,mOrd);
	}

	@Override
	public void Guardar(Certificados c) {
		dao.Guardar(c);
	}

	@Override
	public void Actualizar(Certificados c) {
		dao.Actualizar(c);
	}

}
