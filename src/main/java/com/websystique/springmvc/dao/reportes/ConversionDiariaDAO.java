package com.websystique.springmvc.dao.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.ConversionDiaria;

public interface ConversionDiariaDAO {
	List<ConversionDiaria> findByAll(String fechaIni, String fechaFin);
}
