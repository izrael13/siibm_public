package com.websystique.springmvc.service.tarjetas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.tarjetas.Especialidades_cotizacionDAO;
import com.websystique.springmvc.model.tarjetas.Especialidades_cotizacion;

@Service("especialidades_cotizacionService")
@Transactional
public class Especialidades_cotizacionServiceImpl implements Especialidades_cotizacionService{
	
	@Autowired
	Especialidades_cotizacionDAO dao;
	
	@Override
	public List<Especialidades_cotizacion> ListaEspDet(Integer idCot, Integer idDet) {
		// FIXME Auto-generated method stub
		return dao.ListaEspDet(idCot, idDet);
	}

	@Override
	public Especialidades_cotizacion EspDet(Integer idCot, Integer idDet, Integer idEsp) {
		// FIXME Auto-generated method stub
		return dao.EspDet(idCot, idDet, idEsp);
	}

	@Override
	public void Guardar(Especialidades_cotizacion esp) {
		// FIXME Auto-generated method stub
		dao.Guardar(esp);
	}

	@Override
	public void Borrar(Especialidades_cotizacion obj) {
		// FIXME Auto-generated method stub
		dao.Borrar(obj);
	}

}
