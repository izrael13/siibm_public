package com.websystique.springmvc.service.tarjetas.fabricacion;

import java.util.List;

import com.websystique.springmvc.model.tarjetas.fabricacion.Tarjeta_fabricacion;
import com.websystique.springmvc.model.tarjetas.fabricacion.Tarjeta_fabricacion_Busqueda;

public interface Tarjeta_fabricacionService {
	Tarjeta_fabricacion BuscarxFolio(String Folio);
	List<Tarjeta_fabricacion> BuscarXIdCot(Integer IdCot);
	void Guardar(Tarjeta_fabricacion Tarjeta);
	void Actualizar(Tarjeta_fabricacion Tarjeta);
	List<Tarjeta_fabricacion_Busqueda> TarjetaBusqueda(Integer IdCot, String Folio);
	void Borrar(Tarjeta_fabricacion Tarjeta);
	List<Tarjeta_fabricacion> BuscarXAut(String usuario_aut, String fecha_aut, String usuario_aut_act, String fecha_aut_act);
}
