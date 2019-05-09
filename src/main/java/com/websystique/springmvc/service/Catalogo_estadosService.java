package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Catalogo_estados;

public interface Catalogo_estadosService {
	List<Catalogo_estados> ListEstados();
	Catalogo_estados BuscarEdoId(Integer id);
}
