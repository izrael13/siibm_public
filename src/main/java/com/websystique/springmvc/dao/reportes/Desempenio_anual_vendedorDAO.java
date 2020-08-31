package com.websystique.springmvc.dao.reportes;

import java.util.List;
import com.websystique.springmvc.model.reportes.Desempenio_anual_vendedor;

public interface Desempenio_anual_vendedorDAO {
	
	List<Desempenio_anual_vendedor>BuscarxAnios(Integer Anios, Integer SlpCodes);
	List<Object> DesempenioComparativos(String AnioAnt, String AnioAct, Integer CteVen);

}
