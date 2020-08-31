package com.websystique.springmvc.service.certificados;

import java.util.List;
import java.util.Map;

import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.certificados.Tf_para_certificado;

public interface Tf_para_certificadoService {
	Tf_para_certificado BuscarxParams(List<ParamsGeneral> params);
	List<Tf_para_certificado> BuscarListxParams(List<ParamsGeneral> params, Map<String,String> mOrd);
}
