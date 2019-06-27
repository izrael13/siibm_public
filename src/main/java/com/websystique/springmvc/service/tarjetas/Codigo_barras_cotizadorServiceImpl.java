package com.websystique.springmvc.service.tarjetas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.tarjetas.Codigo_barras_cotizadorDAO;
import com.websystique.springmvc.model.tarjetas.Codigo_barras_cotizador;

@Service("codigo_barras_cotizadorService")
@Transactional
public class Codigo_barras_cotizadorServiceImpl implements Codigo_barras_cotizadorService{

	@Autowired
	Codigo_barras_cotizadorDAO dao;
	
	@Override
	public Codigo_barras_cotizador BuscarXCotDetCod(Integer idcot, Integer idcotdet, Integer idcod) {
		// FIXME Auto-generated method stub
		return dao.BuscarXCotDetCod(idcot, idcotdet, idcod);
	}

	@Override
	public List<Codigo_barras_cotizador> BuscarXCotDet(Integer idcot, Integer idcotdet) {
		// FIXME Auto-generated method stub
		return dao.BuscarXCotDet(idcot, idcotdet);
	}

	@Override
	public List<Codigo_barras_cotizador> BuscarXCot(Integer idcot) {
		// FIXME Auto-generated method stub
		return dao.BuscarXCot(idcot);
	}

	@Override
	public void Guardar(Codigo_barras_cotizador objesp) {
		// FIXME Auto-generated method stub
		dao.Guardar(objesp);
	}

	@Override
	public void Borrar(Codigo_barras_cotizador obj) {
		// FIXME Auto-generated method stub
		dao.Borrar(obj);
	}

	@Override
	public void Borrar(List<Codigo_barras_cotizador> lista) {
		// FIXME Auto-generated method stub
		dao.Borrar(lista);
	}

	@Override
	public void Guardar(List<Codigo_barras_cotizador> lista) {
		// FIXME Auto-generated method stub
		dao.Guardar(lista);
	}

}
