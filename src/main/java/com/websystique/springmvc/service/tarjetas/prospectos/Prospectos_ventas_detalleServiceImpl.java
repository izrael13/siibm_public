package com.websystique.springmvc.service.tarjetas.prospectos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.tarjetas.prospectos.Prospectos_ventas_detalleDAO;
import com.websystique.springmvc.model.tarjetas.prospectos.Prospectos_ventas_detalle;

@Service("prospectos_ventas_detalleService")
@Transactional
public class Prospectos_ventas_detalleServiceImpl implements Prospectos_ventas_detalleService{
	
	@Autowired
	Prospectos_ventas_detalleDAO dao;
	
	@Override
	public List<Prospectos_ventas_detalle> BuscarPorIdPV(Integer id) {
		// FIXME Auto-generated method stub
		return dao.BuscarPorIdPV(id);
	}

	@Override
	public void Guardar(Prospectos_ventas_detalle pvd) {
		// FIXME Auto-generated method stub
		dao.Guardar(pvd);
	}

}
