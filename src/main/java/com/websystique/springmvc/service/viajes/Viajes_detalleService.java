package com.websystique.springmvc.service.viajes;

import java.util.List;

import com.websystique.springmvc.model.viajes.Viajes_detalle;

public interface Viajes_detalleService {
	List<Viajes_detalle> findDetalleByViaje(String num_viaje);
}
