package com.websystique.springmvc.service.certificados;

import java.util.List;
import java.util.Map;

import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.certificados.Certificados;

public interface CertificadosService {
	Certificados BuscarxId(Integer id);
	Certificados BuscarxParamsObj(List<ParamsGeneral> params);
	List<Certificados> BuscarxParamsList(List<ParamsGeneral> params, Map<String,String> mOrd);
	void Guardar(Certificados c);
	void Actualizar(Certificados c);
}
