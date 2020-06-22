package com.websystique.springmvc.service.tarjetas.cotizador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.tarjetas.cotizador.Bocetos_cotizadorDAO;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.tarjetas.cotizador.Bocetos_cotizador;

@Service("bocetos_cotizadorService")
@Transactional
public class Bocetos_cotizadorServiceImpl implements Bocetos_cotizadorService{

	@Autowired Bocetos_cotizadorDAO dao;
	
	@Override
	public Bocetos_cotizador BuscarXId(Integer id) {
		return dao.BuscarXId(id);
	}

	@Override
	public List<Bocetos_cotizador> BuscarxIdCot(Integer idcot) {
		return dao.BuscarxIdCot(idcot);
	}

	@Override
	public void Guardar(Bocetos_cotizador Boceto) {
		dao.Guardar(Boceto);
	}

	@Override
	public void Actualizar(Bocetos_cotizador Boceto) {
		dao.Actualizar(Boceto);
	}

	@Override
	public void Eliminar(Bocetos_cotizador Boceto) {
		dao.Eliminar(Boceto);
	}

	@Override
	public Integer Maximo(String atributo) {
		return dao.Maximo(atributo);
	}

	@Override
	public List<Bocetos_cotizador> BuscarxIdCot(List<ParamsGeneral> params) {
		return dao.BuscarxIdCot(params);
	}

}
