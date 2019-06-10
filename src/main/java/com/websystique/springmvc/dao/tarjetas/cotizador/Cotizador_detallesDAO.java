package com.websystique.springmvc.dao.tarjetas.cotizador;

import java.util.List;

import com.websystique.springmvc.model.tarjetas.cotizador.Cotizador_detalles;


public interface Cotizador_detallesDAO {
	Cotizador_detalles BuscarxId(Integer id, Integer iddet, Integer userInsert);
	List<Cotizador_detalles> BuscarxCotId(Integer idCot);
	Integer Guardar(Cotizador_detalles cot);
	void Actualizar(Cotizador_detalles cot);
}
