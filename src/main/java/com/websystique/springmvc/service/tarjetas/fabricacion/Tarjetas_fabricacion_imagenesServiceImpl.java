package com.websystique.springmvc.service.tarjetas.fabricacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.tarjetas.fabricacion.Tarjetas_fabricacion_imagenesDAO;
import com.websystique.springmvc.model.tarjetas.fabricacion.Tarjetas_fabricacion_imagenes;

@Service("tarjetas_fabricacion_imagenesService")
@Transactional
public class Tarjetas_fabricacion_imagenesServiceImpl implements Tarjetas_fabricacion_imagenesService{
	
	@Autowired
	Tarjetas_fabricacion_imagenesDAO dao;
	
	@Override
	public List<Tarjetas_fabricacion_imagenes> BuscarxIdCotidDert(Integer idCot, Integer idDet) {
		// TODO Auto-generated method stub
		return dao.BuscarxIdCotidDert(idCot, idDet);
	}

	@Override
	public List<Tarjetas_fabricacion_imagenes> BuscarxId(Integer idCot, Integer idDet, String nombre) {
		// TODO Auto-generated method stub
		return dao.BuscarxId(idCot, idDet, nombre);
	}

	@Override
	public void Guardar(Tarjetas_fabricacion_imagenes tar_img) {
		// TODO Auto-generated method stub
		dao.Guardar(tar_img);
	}

	@Override
	public void Borrar(Tarjetas_fabricacion_imagenes tar_img) {
		// TODO Auto-generated method stub
		dao.Borrar(tar_img);
	}

	@Override
	public void Actualizar(Tarjetas_fabricacion_imagenes tar_img) {
		// TODO Auto-generated method stub
		dao.Actualizar(tar_img);
	}

}
