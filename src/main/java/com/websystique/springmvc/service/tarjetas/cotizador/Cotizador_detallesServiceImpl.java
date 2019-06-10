package com.websystique.springmvc.service.tarjetas.cotizador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.tarjetas.cotizador.Cotizador_detallesDAO;
import com.websystique.springmvc.model.tarjetas.cotizador.Cotizador_detalles;

@Service("cotizador_detallesService")
@Transactional
public class Cotizador_detallesServiceImpl implements Cotizador_detallesService{
	
	@Autowired
	Cotizador_detallesDAO dao;
	
	@Override
	public Cotizador_detalles BuscarxId(Integer id, Integer iddet, Integer userInsert) {
		// FIXME Auto-generated method stub
		return dao.BuscarxId(id, iddet, userInsert);
	}

	@Override
	public List<Cotizador_detalles> BuscarxCotId(Integer idCot) {
		// FIXME Auto-generated method stub
		return dao.BuscarxCotId(idCot);
	}

	@Override
	public Integer Guardar(Cotizador_detalles cot) {
		// FIXME Auto-generated method stub
		return dao.Guardar(cot);
	}

	@Override
	public void Actualizar(Cotizador_detalles cot) {
		// FIXME Auto-generated method stub
		dao.Actualizar(cot);
	}

}
