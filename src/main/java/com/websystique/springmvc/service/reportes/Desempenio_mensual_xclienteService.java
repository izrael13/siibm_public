package com.websystique.springmvc.service.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.Desempenio_mensual_xcliente;

public interface Desempenio_mensual_xclienteService {
	List<Desempenio_mensual_xcliente> Buscar(Integer Anio, String CardCode, Integer SlpCode);
}
