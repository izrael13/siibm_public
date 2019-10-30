package com.websystique.springmvc.dao.costos.controlpesomerma;

import java.util.List;

import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.costos.controlpesomerma.Control_peso_merma;

public interface Control_peso_mermaDAO {
	List<Control_peso_merma> ListaControlPeso();
	Control_peso_merma BuscarxId(Integer Id);
	void Guardar(Control_peso_merma cpm);
	List<Control_peso_merma> ListaControlPeso(List<ParamsGeneral> Params);
}
