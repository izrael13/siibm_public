package com.websystique.springmvc.service.tarjetas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.tarjetas.Catalogo_maquinas_sap_vwDAO;
import com.websystique.springmvc.model.tarjetas.Catalogo_maquinas_sap_vw;

@Service("catalogo_maquinas_sap_vwService")
@Transactional
public class Catalogo_maquinas_sap_vwServiceImpl implements Catalogo_maquinas_sap_vwService{
	
	@Autowired
	Catalogo_maquinas_sap_vwDAO dao;
	
	@Override
	public List<Catalogo_maquinas_sap_vw> ListaMaquinas() {
		return dao.ListaMaquinas();
	}

	@Override
	public Catalogo_maquinas_sap_vw BuscarxId(Integer id) {
		return dao.BuscarxId(id);
	}

	@Override
	public List<Catalogo_maquinas_sap_vw> ListaMaquinas(Integer grupo) {
		return dao.ListaMaquinas(grupo);
	}

	@Override
	public List<Catalogo_maquinas_sap_vw> ListaMaquinas(Integer grupo, Integer numTintas) {
		return dao.ListaMaquinas(grupo, numTintas);
	}

	@Override
	public List<Catalogo_maquinas_sap_vw> ListaMaquinas(Boolean u_grapado) {
		return dao.ListaMaquinas(u_grapado);
	}

}
