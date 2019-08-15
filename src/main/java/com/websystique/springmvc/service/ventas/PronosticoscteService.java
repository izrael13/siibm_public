package com.websystique.springmvc.service.ventas;

import java.util.List;

import com.websystique.springmvc.model.ventas.Pronosticoscte;

public interface PronosticoscteService {
	
	void Guardar(Integer idven, String idcte, String anio, String mes, Double pron, Integer accion, Integer code);
	List<Pronosticoscte> Lista(String anio, String mes,Integer cveven, String cvecte, Integer cteban, Integer opcion);
	//List<Pronosticoscte> readFile(MultipartFile file);
	
}
