package com.websystique.springmvc.service.mp.inventario;

import java.util.List;
import java.util.Map;

import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.mp.inventario.Conteo_inventario_mp;

public interface Conteo_inventario_mpService {
	void Guardar(Conteo_inventario_mp obj);
	void Guardar(List<Conteo_inventario_mp> Listaobj);
	void Actualizar(Conteo_inventario_mp obj);
	void Eliminar(Conteo_inventario_mp obj);
	void Eliminar(List<Conteo_inventario_mp> Listaobj);
	Conteo_inventario_mp BuscarxId(Integer id);
	Conteo_inventario_mp BuscarxParamsObj(List<ParamsGeneral> Params);
	List<Conteo_inventario_mp> BuscarxParamsList(List<ParamsGeneral> Params,Map<String,String> mOrd);
	Integer Maximo(String atributo);
}
