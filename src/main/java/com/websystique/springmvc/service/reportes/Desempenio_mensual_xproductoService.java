package com.websystique.springmvc.service.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.Desempenio_mensual_xproducto;

public interface Desempenio_mensual_xproductoService {
	List<Desempenio_mensual_xproducto> Buscar(Integer anio,Integer slpcode, Integer xcte, Integer xitem);
}
