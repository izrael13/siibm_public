package com.websystique.springmvc.dao.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.ConversionDiaria;

public interface ConversionDiariaDAO {
	List<ConversionDiaria> findByDateRange(String fechaIni, String fechaFin);
	List<ConversionDiaria> findByPedidos(String listaPedidos);
}
