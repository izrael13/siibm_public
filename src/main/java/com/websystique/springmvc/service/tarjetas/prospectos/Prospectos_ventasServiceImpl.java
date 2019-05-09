package com.websystique.springmvc.service.tarjetas.prospectos;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.tarjetas.prospectos.Prospectos_ventasDAO;
import com.websystique.springmvc.model.tarjetas.prospectos.Prospectos_ventas;
import com.websystique.springmvc.model.tarjetas.prospectos.Prospectos_ventas_busqueda;

@Service("prospectos_ventasService")
@Transactional
public class Prospectos_ventasServiceImpl implements Prospectos_ventasService{
	
	@Autowired
	Prospectos_ventasDAO dao;
	
	@Override
	public Prospectos_ventas buscarPorId(Integer id) {
		// FIXME Auto-generated method stub
		return dao.buscarPorId(id);
	}

	@Override
	public List<Prospectos_ventas> buscarPorCardCode(String CardCode) {
		// FIXME Auto-generated method stub
		return dao.buscarPorCardCode(CardCode);
	}

	@Override
	public Integer Guardar(Prospectos_ventas pv) {
		// FIXME Auto-generated method stub
		return dao.Guardar(pv);
	}

	@Override
	public void Actualizar(Prospectos_ventas pv) {
		dao.Actualizar(pv);
		
	}

	@Override
	public List<Prospectos_ventas_busqueda> buscarPorIdCardCode(Integer id_user,Integer id, String CardCode,Integer CveVendedor, Double PorcAnace,
																Integer Prioridad,Double OporTon,Integer Status,Date Fecha1, Date Fecha2) {
		// FIXME Auto-generated method stub
		return dao.buscarPorIdCardCode(id_user, id, CardCode, CveVendedor, PorcAnace, Prioridad, OporTon, Status,Fecha1,Fecha2);
	}

}
