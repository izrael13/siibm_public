package com.websystique.springmvc.service.tarjetas.prospectos;

import java.util.List;

import com.websystique.springmvc.model.tarjetas.prospectos.Prospectos_ventas_detalle;

public interface Prospectos_ventas_detalleService {
	List<Prospectos_ventas_detalle> BuscarPorIdPV(Integer id);
	void Guardar(Prospectos_ventas_detalle pvd);
}
