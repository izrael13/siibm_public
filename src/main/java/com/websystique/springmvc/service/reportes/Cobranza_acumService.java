package com.websystique.springmvc.service.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.Cobranza_acum;

public interface Cobranza_acumService {
	List<Cobranza_acum> findByIntervalo(Integer SlpCode);
}
