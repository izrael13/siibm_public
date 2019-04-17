package com.websystique.springmvc.service.viajes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.viajes.Viajes_detalleDAO;
import com.websystique.springmvc.model.viajes.Viajes_detalle;

@Service("viajes_detalleService")
@Transactional
public class Viajes_detalleServiceImpl implements Viajes_detalleService{
	
	@Autowired
	Viajes_detalleDAO dao;

	@Override
	public List<Viajes_detalle> findDetalleByViaje(String num_viaje) {
		// FIXME Auto-generated method stub
		return dao.findDetalleByViaje(num_viaje);
	}
}
