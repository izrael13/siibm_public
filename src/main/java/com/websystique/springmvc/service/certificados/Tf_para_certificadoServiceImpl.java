package com.websystique.springmvc.service.certificados;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.certificados.Tf_para_certificadoDAO;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.certificados.Tf_para_certificado;

@Service("tf_para_certificadoService")
@Transactional
public class Tf_para_certificadoServiceImpl implements Tf_para_certificadoService{
	
	@Autowired Tf_para_certificadoDAO dao;
	
	@Override
	public Tf_para_certificado BuscarxParams(List<ParamsGeneral> params) {
		return dao.BuscarxParams(params);
	}

	@Override
	public List<Tf_para_certificado> BuscarListxParams(List<ParamsGeneral> params, Map<String, String> mOrd) {
		return dao.BuscarListxParams(params, mOrd);
	}

}
