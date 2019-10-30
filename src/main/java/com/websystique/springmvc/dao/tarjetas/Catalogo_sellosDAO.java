package com.websystique.springmvc.dao.tarjetas;

import java.util.List;

import com.websystique.springmvc.model.tarjetas.Catalogo_sellos;

public interface Catalogo_sellosDAO {
	List<Catalogo_sellos> ListaSellos();
	Catalogo_sellos BuscarxId(Integer id);
	void Guardar(Catalogo_sellos sello);
	void Actualizar(Catalogo_sellos sello);
}
