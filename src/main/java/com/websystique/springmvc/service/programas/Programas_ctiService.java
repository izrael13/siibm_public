package com.websystique.springmvc.service.programas;

import java.util.List;
import java.util.Map;

import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.programas.Programas_cti;

public interface Programas_ctiService {
	Programas_cti BuscarxId(Integer id);
	Programas_cti BuscarxParams(List<ParamsGeneral> Params);
	List<Programas_cti> BuscarxParams(List<ParamsGeneral> Params, Map<String, String> mOrd);
	List<Programas_cti> BuscarxParams(Integer Pedido, String TFSAP);
}
