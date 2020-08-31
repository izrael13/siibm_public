package com.websystique.springmvc.service.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.Desempenio_anual_vendedor;

public interface Desempenio_anual_vendedorService {
	List<Desempenio_anual_vendedor> BuscarxAnio(Integer Anio, Integer SlpCode);
	List<Object> DesempenioComparativo(String AnioAnt, String AnioAct, Integer CteVen);

}
