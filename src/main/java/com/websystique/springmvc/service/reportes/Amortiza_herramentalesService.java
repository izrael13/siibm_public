package com.websystique.springmvc.service.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.Amortiza_herramentales;

public interface Amortiza_herramentalesService {
	List<Amortiza_herramentales> findAmortHerram(Integer select, String herramental);
}
