package com.websystique.springmvc.service.tarjetas;

import java.util.List;

import com.websystique.springmvc.model.tarjetas.Especialidades_cotizacion;

public interface Especialidades_cotizacionService {
	List<Especialidades_cotizacion> ListaEspDet(Integer idCot, Integer idDet);
	Especialidades_cotizacion EspDet(Integer idCot, Integer idDet,Integer idEsp);
	void Guardar(Especialidades_cotizacion esp);
	void Borrar(Especialidades_cotizacion obj);
	void Borrar(List<Especialidades_cotizacion> lista);
	void Guardar(List<Especialidades_cotizacion> lista);
}
