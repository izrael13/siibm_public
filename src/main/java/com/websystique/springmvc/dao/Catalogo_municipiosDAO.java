package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.Catalogo_municipios;

public interface Catalogo_municipiosDAO {
	List<Catalogo_municipios> ListMunicipiosXEstado(Integer cve_estado);
	List<Catalogo_municipios> ListMunicipios();
}
