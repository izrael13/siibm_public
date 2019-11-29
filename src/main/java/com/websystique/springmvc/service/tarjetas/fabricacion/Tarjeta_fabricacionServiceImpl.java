package com.websystique.springmvc.service.tarjetas.fabricacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.tarjetas.fabricacion.Tarjeta_fabricacionDAO;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.tarjetas.fabricacion.Tarjeta_fabricacion;

@Service("tarjeta_fabricacionService")
@Transactional
public class Tarjeta_fabricacionServiceImpl implements Tarjeta_fabricacionService{
	
	@Autowired
	Tarjeta_fabricacionDAO dao;
	
	@Override
	public Tarjeta_fabricacion BuscarxFolio(String Folio, Integer IdDis) {
		// TODO Auto-generated method stub
		return dao.BuscarxFolio(Folio,IdDis);
	}

	@Override
	public List<Tarjeta_fabricacion> BuscarXIdCot(Integer IdCot, Integer IdDis) {
		// TODO Auto-generated method stub
		return dao.BuscarXIdCot(IdCot,IdDis);
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
	public List<Tarjeta_fabricacion> TarjetaBusqueda(Integer IdCot, String Folio, String cardcode, Integer IdDis) {
		// TODO Auto-generated method stub
		return dao.TarjetaBusqueda(IdCot, Folio, cardcode, IdDis);
	}

	@Override
	public void Borrar(Tarjeta_fabricacion Tarjeta) {
		// TODO Auto-generated method stub
		dao.Borrar(Tarjeta);
	}

	@Override
	public List<Tarjeta_fabricacion> BuscarXAut(List<ParamsGeneral> Params) {
		// TODO Auto-generated method stub
		return dao.BuscarXAut(Params);
	}

	/*@Override
	public List<Object> BuscarEsp(Integer idcot, Integer Iddet) {
		// TODO Auto-generated method stub
		return dao.BuscarEsp(idcot, Iddet);
	} */

	@Override
	public List<Tarjeta_fabricacion> ListaSeguimiento(String Folio, Integer IdCot, Integer Status, String CardCode) {
		// TODO Auto-generated method stub
		return dao.ListaSeguimiento(Folio, IdCot, Status, CardCode);
	}

	@Override
	public Tarjeta_fabricacion BuscarxFolio(String Folio) {
		// TODO Auto-generated method stub
		return dao.BuscarxFolio(Folio);
	}

	@Override
	public Tarjeta_fabricacion BuscarxCot_Cotdet(Integer idcot, Integer iddet) {
		// TODO Auto-generated method stub
		return dao.BuscarxCot_Cotdet(idcot, iddet);
	}

	@Override
	public Tarjeta_fabricacion BuscarTFPG(List<ParamsGeneral> Params) {
		// TODO Auto-generated method stub
		return dao.BuscarTFPG(Params);
	}

}
