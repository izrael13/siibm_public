package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Catalogo_municipios;

public interface Catalogo_municipiosService {
	List<Catalogo_municipios> ListMunicipiosXEstado(Integer cve_estado);
	List<Catalogo_municipios> ListMunicipios();
}
