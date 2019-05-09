package com.websystique.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.Catalogo_municipiosDAO;
import com.websystique.springmvc.model.Catalogo_municipios;

@Service("catalogo_municipiosService")
@Transactional
public class Catalogo_municipiosServiceImpl implements Catalogo_municipiosService{

	@Autowired
	Catalogo_municipiosDAO dao;
	
	@Override
	public List<Catalogo_municipios> ListMunicipiosXEstado(Integer cve_estado) {
		// FIXME Auto-generated method stub
		return dao.ListMunicipiosXEstado(cve_estado);
	}

	@Override
	public List<Catalogo_municipios> ListMunicipios() {
		// FIXME Auto-generated method stub
		return dao.ListMunicipios();
	}

}
