package com.websystique.springmvc.dao.tarjetas.fabricacion;

import java.util.List;

import com.websystique.springmvc.model.tarjetas.fabricacion.Tarjetas_fabricacion_imagenes;

public interface Tarjetas_fabricacion_imagenesDAO {
	List<Tarjetas_fabricacion_imagenes> BuscarxIdCotidDert(Integer idCot, Integer idDet);
	List<Tarjetas_fabricacion_imagenes> BuscarxId(Integer idCot, Integer idDet, String nombre);
	void Guardar(Tarjetas_fabricacion_imagenes tar_img);
	void Borrar(Tarjetas_fabricacion_imagenes tar_img);
	void Actualizar(Tarjetas_fabricacion_imagenes tar_img);
}
