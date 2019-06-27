package com.websystique.springmvc.service.tarjetas;

import java.util.List;

import com.websystique.springmvc.model.tarjetas.Codigo_barras_cotizador;

public interface Codigo_barras_cotizadorService {
	Codigo_barras_cotizador BuscarXCotDetCod(Integer idcot, Integer idcotdet, Integer idcod);
	List<Codigo_barras_cotizador> BuscarXCotDet(Integer idcot, Integer idcotdet);
	List<Codigo_barras_cotizador> BuscarXCot(Integer idcot);
	
	void Guardar(Codigo_barras_cotizador objesp);
	void Borrar(Codigo_barras_cotizador obj);
	void Borrar(List<Codigo_barras_cotizador> lista);
	void Guardar(List<Codigo_barras_cotizador> lista);
}
