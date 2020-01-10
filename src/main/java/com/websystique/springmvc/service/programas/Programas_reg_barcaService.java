package com.websystique.springmvc.service.programas;

import java.util.List;
import java.util.Map;

import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.programas.Programas_reg_barca;

public interface Programas_reg_barcaService {
	Programas_reg_barca BuscarxId(Integer id);
	List<Programas_reg_barca> BuscarxParams(List<ParamsGeneral> Params, Map<String,String> mOrd);
	Programas_reg_barca BuscarxParams(List<ParamsGeneral> Params);
	void Guardar(Programas_reg_barca Programa);
	void Actualizar(Programas_reg_barca Programa);
	void Eliminar(Programas_reg_barca Programa);
}
