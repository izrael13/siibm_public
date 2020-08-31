package com.websystique.springmvc.service.certificados;

import java.util.List;
import java.util.Map;

import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.certificados.Remisiones_para_certificado;

public interface Remisiones_para_certificadoService {
	Remisiones_para_certificado BuscarXParamsObj(List<ParamsGeneral> params);
	List<Remisiones_para_certificado> BuscarXParamsList(List<ParamsGeneral> params, Map<String,String> mOrd);
}
