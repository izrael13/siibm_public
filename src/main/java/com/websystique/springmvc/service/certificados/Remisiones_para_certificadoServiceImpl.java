package com.websystique.springmvc.service.certificados;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.certificados.Remisiones_para_certificadoDAO;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.certificados.Remisiones_para_certificado;

@Service("remisiones_para_certificadoService")
@Transactional
public class Remisiones_para_certificadoServiceImpl implements Remisiones_para_certificadoService{
	
	@Autowired Remisiones_para_certificadoDAO dao;

	@Override
	public Remisiones_para_certificado BuscarXParamsObj(List<ParamsGeneral> params) {
		return dao.BuscarXParamsObj(params);
	}

	@Override
	public List<Remisiones_para_certificado> BuscarXParamsList(List<ParamsGeneral> params, Map<String, String> mOrd) {
		return dao.BuscarXParamsList(params, mOrd);
	}

}
