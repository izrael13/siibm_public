package com.websystique.springmvc.service.tarjetas.prospectos;

import java.util.Date;
import java.util.List;

import com.websystique.springmvc.model.tarjetas.prospectos.Prospectos_ventas;
import com.websystique.springmvc.model.tarjetas.prospectos.Prospectos_ventas_busqueda;

public interface Prospectos_ventasService {
	Prospectos_ventas buscarPorId(Integer id);
	List<Prospectos_ventas> buscarPorCardCode(String CardCode);
	Integer Guardar(Prospectos_ventas pv);
	void Actualizar(Prospectos_ventas pv);
	List<Prospectos_ventas_busqueda> buscarPorIdCardCode(Integer id_user,Integer id,String CardCode,Integer CveVendedor, Double PorcAnace,
														Integer Prioridad,Double OporTon,Integer Status,Date Fecha1, Date Fecha2);
}
