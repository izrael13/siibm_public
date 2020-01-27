package com.websystique.springmvc.dao.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.ListaEmbarques;

public interface ListaEmbarquesDAO {
	List<ListaEmbarques> ListaEmbarques(String fechaIni, String fechaFin);
}
