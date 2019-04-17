package com.websystique.springmvc.dao.viajes;

import java.util.List;

import com.websystique.springmvc.model.viajes.Viajes_detalle;

public interface Viajes_detalleDAO {
	List<Viajes_detalle> findDetalleByViaje(String num_viaje);
}
