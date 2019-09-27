package com.websystique.springmvc.dao.tarjetas;

import java.util.List;

import com.websystique.springmvc.model.tarjetas.Catalogo_herramentales;

public interface Catalogo_herramentalesDAO {
	Catalogo_herramentales BuscarxId(Integer id);
	List<Catalogo_herramentales> BuscarxTipo(Integer tipo);
	void Guardar(Catalogo_herramentales CatHerra);
	void Actualziar(Catalogo_herramentales CatHerra);
}
