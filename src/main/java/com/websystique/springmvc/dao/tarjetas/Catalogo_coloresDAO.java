package com.websystique.springmvc.dao.tarjetas;

import java.util.List;

import com.websystique.springmvc.model.tarjetas.Catalogo_colores;

public interface Catalogo_coloresDAO {
	List<Catalogo_colores> ListaColores();
	Catalogo_colores BuscarxId(Integer id);
}
