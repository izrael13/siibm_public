package com.websystique.springmvc.dao.tarjetas.fabricacion;

import java.util.List;

import com.websystique.springmvc.model.tarjetas.fabricacion.Tarjeta_fabricacion;
import com.websystique.springmvc.model.tarjetas.fabricacion.Tarjeta_fabricacion_Busqueda;

public interface Tarjeta_fabricacionDAO {
	Tarjeta_fabricacion BuscarxFolio(String Folio);
	List<Tarjeta_fabricacion> BuscarXIdCot(Integer IdCot);
	void Guardar(Tarjeta_fabricacion Tarjeta);
	void Actualizar(Tarjeta_fabricacion Tarjeta);
	List<Tarjeta_fabricacion_Busqueda> TarjetaBusqueda(Integer IdCot, String Folio);
}