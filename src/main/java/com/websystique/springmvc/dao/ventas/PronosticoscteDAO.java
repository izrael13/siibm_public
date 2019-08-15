package com.websystique.springmvc.dao.ventas;

import java.util.List;

import com.websystique.springmvc.model.ventas.Pronosticoscte;

public interface PronosticoscteDAO {
	//List<Pronosticoscte> readFile(List<Pronosticoscte> list);
	void Guardar(Integer idven, String idcte, String anio, String mes, Double pron, Integer accion, Integer code);
	List<Pronosticoscte> Lista(String anio, String mes,Integer cveven, String cvecte, Integer cteban, Integer opcion);
}
