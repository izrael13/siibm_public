package com.websystique.springmvc.dao.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.Amortiza_herramentales;

public interface Amortiza_herramentalesDAO {
	List<Amortiza_herramentales> findAmortHerram(Integer select);
}
