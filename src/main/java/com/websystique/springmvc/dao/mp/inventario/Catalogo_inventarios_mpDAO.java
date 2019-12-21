package com.websystique.springmvc.dao.mp.inventario;

import java.util.List;
import java.util.Map;

import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.mp.inventario.Catalogo_inventarios_mp;

public interface Catalogo_inventarios_mpDAO {
	void Guardar(Catalogo_inventarios_mp obj);
	void Actualizar(Catalogo_inventarios_mp obj);
	void Eliminar(Catalogo_inventarios_mp obj);
	Catalogo_inventarios_mp BuscarxId(Integer id);
	Catalogo_inventarios_mp BuscarxParamsObj(List<ParamsGeneral> Params);
	List<Catalogo_inventarios_mp> BuscarxParamsList(List<ParamsGeneral> Params,Map<String,String> mOrd);
	Integer Maximo(String atributo);
}
