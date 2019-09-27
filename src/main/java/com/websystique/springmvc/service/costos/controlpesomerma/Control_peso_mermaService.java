package com.websystique.springmvc.service.costos.controlpesomerma;

import java.util.Date;
import java.util.List;

import com.websystique.springmvc.model.costos.controlpesomerma.Control_peso_merma;

public interface Control_peso_mermaService {
	List<Control_peso_merma> ListaControlPeso();
	Control_peso_merma BuscarxId(Integer Id);
	void Guardar(Control_peso_merma cpm);
	List<Control_peso_merma> ListaControlPeso(Date fecha);
}
