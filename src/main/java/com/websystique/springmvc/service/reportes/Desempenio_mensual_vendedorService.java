package com.websystique.springmvc.service.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.Desempenio_mensual_vendedor;

public interface Desempenio_mensual_vendedorService {
	List<Desempenio_mensual_vendedor> BuscarxAnio(Integer Anio, Integer SlpCode);
}
