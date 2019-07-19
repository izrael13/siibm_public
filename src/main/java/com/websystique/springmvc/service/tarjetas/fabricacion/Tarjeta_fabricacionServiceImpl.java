package com.websystique.springmvc.service.tarjetas.fabricacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.tarjetas.fabricacion.Tarjeta_fabricacionDAO;
import com.websystique.springmvc.model.tarjetas.fabricacion.Tarjeta_fabricacion;
import com.websystique.springmvc.model.tarjetas.fabricacion.Tarjeta_fabricacion_Busqueda;

@Service("tarjeta_fabricacionService")
@Transactional
public class Tarjeta_fabricacionServiceImpl implements Tarjeta_fabricacionService{
	
	@Autowired
	Tarjeta_fabricacionDAO dao;
	
	@Override
	public Tarjeta_fabricacion BuscarxFolio(String Folio) {
		// TODO Auto-generated method stub
		return dao.BuscarxFolio(Folio);
	}

	@Override
	public List<Tarjeta_fabricacion> BuscarXIdCot(Integer IdCot) {
		// TODO Auto-generated method stub
		return dao.BuscarXIdCot(IdCot);
	}

	@Override
	public void Guardar(Tarjeta_fabricacion Tarjeta) {
		// TODO Auto-generated method stub
		dao.Guardar(Tarjeta);
	}

	@Override
	public void Actualizar(Tarjeta_fabricacion Tarjeta) {
		// TODO Auto-generated method stub
		dao.Actualizar(Tarjeta);
	}

	@Override
	public List<Tarjeta_fabricacion_Busqueda> TarjetaBusqueda(Integer IdCot, String Folio) {
		// TODO Auto-generated method stub
		return dao.TarjetaBusqueda(IdCot, Folio);
	}

	@Override
	public void Borrar(Tarjeta_fabricacion Tarjeta) {
		// TODO Auto-generated method stub
		dao.Borrar(Tarjeta);
	}

}
