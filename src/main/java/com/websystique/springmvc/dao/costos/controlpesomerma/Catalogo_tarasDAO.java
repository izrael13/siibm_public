package com.websystique.springmvc.dao.costos.controlpesomerma;

import java.util.List;

import com.websystique.springmvc.model.costos.controlpesomerma.Catalogo_taras;

public interface Catalogo_tarasDAO {
	List<Catalogo_taras> ListaTaras();
	Catalogo_taras BuscarxId(Integer Id);
}
