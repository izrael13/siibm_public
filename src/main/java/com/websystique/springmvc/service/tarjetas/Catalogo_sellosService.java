package com.websystique.springmvc.service.tarjetas;

import java.util.List;

import com.websystique.springmvc.model.tarjetas.Catalogo_sellos;

public interface Catalogo_sellosService {
	List<Catalogo_sellos> ListaSellos();
	Catalogo_sellos BuscarxId(Integer id);
	void Guardar(Catalogo_sellos sello);
	void Actualizar(Catalogo_sellos sello);
}
