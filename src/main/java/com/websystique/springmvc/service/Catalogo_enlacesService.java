package com.websystique.springmvc.service;

import java.util.List;
import java.util.Map;

import com.websystique.springmvc.model.Catalogo_enlaces;
import com.websystique.springmvc.model.ParamsGeneral;

public interface Catalogo_enlacesService {
	void Guardar(Catalogo_enlaces Enlace);
	void Actualizar(Catalogo_enlaces Enlace);
	void Eliminar(Catalogo_enlaces Enlace);
	Catalogo_enlaces BuscarxId(Integer id);
	List<Catalogo_enlaces> BuscarxParams(List<ParamsGeneral> Params, Map<String,String> mOrd);
	List<Catalogo_enlaces> BuscarTodos();
}
