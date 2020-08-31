package com.websystique.springmvc.dao.certificados;

import java.util.List;
import java.util.Map;

import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.certificados.Remisiones_para_certificado;

public interface Remisiones_para_certificadoDAO {
	Remisiones_para_certificado BuscarXParamsObj(List<ParamsGeneral> params);
	List<Remisiones_para_certificado> BuscarXParamsList(List<ParamsGeneral> params, Map<String,String> mOrd);
}
