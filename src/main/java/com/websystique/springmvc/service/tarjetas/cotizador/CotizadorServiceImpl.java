package com.websystique.springmvc.service.tarjetas.cotizador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.tarjetas.cotizador.CotizadorDAO;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.tarjetas.cotizador.Cotizador;
//import com.websystique.springmvc.model.tarjetas.cotizador.Cotizador_busqueda;

@Service("cotizadorService")
@Transactional
public class CotizadorServiceImpl implements CotizadorService{
	
	@Autowired
	CotizadorDAO dao;
	
	@Override
	public Cotizador BuscarxId(Integer id) {
		// FIXME Auto-generated method stub
		return dao.BuscarxId(id);
	}
	
	@Override
	public Cotizador BuscarxId(Integer id, Integer userInsert) {
		// FIXME Auto-generated method stub
		return dao.BuscarxId(id,userInsert);
	}

	@Override
	public List<Cotizador> BuscarxUser(Integer idUser) {
		// FIXME Auto-generated method stub
		return dao.BuscarxUser(idUser);
	}

	@Override
	public void Guardar(Cotizador cot) {
		dao.Guardar(cot);
	}

	@Override
	public void Actualizar(Cotizador cot) {
		// FIXME Auto-generated method stub
		dao.Actualizar(cot);
		
	}

	@Override
	public List<Cotizador> ListasCotAut(List<ParamsGeneral> params) {
		// TODO Auto-generated method stub
		return dao.ListasCotAut(params);
	}

	@Override
	public Cotizador BuscarxIdArr(Integer id, Integer userInsert) {
		// TODO Auto-generated method stub
		return dao.BuscarxIdArr(id, userInsert);
	}

	@Override
	public Integer Maximo(String atributo) {
		// TODO Auto-generated method stub
		return dao.Maximo(atributo);
	}

}
