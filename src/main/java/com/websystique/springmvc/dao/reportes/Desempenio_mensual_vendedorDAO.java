package com.websystique.springmvc.dao.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.Desempenio_mensual_vendedor;

public interface Desempenio_mensual_vendedorDAO {
	List<Desempenio_mensual_vendedor> BuscarxAnio(Integer Anio, Integer SlpCode);
	List<Object> DesempenioComparativo(String AnioAnt, String AnioAct, Integer CteVen, String Meses);
}
