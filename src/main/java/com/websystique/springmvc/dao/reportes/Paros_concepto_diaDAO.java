package com.websystique.springmvc.dao.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.Paros_concepto_dia;;

public interface Paros_concepto_diaDAO {
	List<Paros_concepto_dia> findAllParosConceptodia(String fecha_ini);
}
