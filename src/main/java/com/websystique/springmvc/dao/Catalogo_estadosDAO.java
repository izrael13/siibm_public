package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.Catalogo_estados;

public interface Catalogo_estadosDAO {
	List<Catalogo_estados> ListEstados();
	Catalogo_estados BuscarEdoId(Integer id);
}
