package com.websystique.springmvc.service.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.ConversionDiaria;

public interface ConversionDiariaService {
	List<ConversionDiaria> findByAll(String fecha_ini);
}
