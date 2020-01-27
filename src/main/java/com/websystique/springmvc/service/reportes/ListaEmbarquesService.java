package com.websystique.springmvc.service.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.ListaEmbarques;

public interface ListaEmbarquesService {
	List<ListaEmbarques> ListaEmbarques(String fechaIni, String fechaFin);
}
