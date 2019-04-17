package com.websystique.springmvc.dao.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.Cobranza_acum;

public interface Cobranza_acumDAO {
	List<Cobranza_acum> findByIntervalo();
}
