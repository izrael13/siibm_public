package com.websystique.springmvc.service.tarjetas.cotizador;

import java.util.List;

import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.tarjetas.cotizador.Bocetos_cotizador;

public interface Bocetos_cotizadorService {
	Bocetos_cotizador BuscarXId(Integer id);
	List<Bocetos_cotizador> BuscarxIdCot(Integer idcot);
	List<Bocetos_cotizador> BuscarxIdCot(List<ParamsGeneral> params);
	void Guardar(Bocetos_cotizador Boceto);
	void Actualizar(Bocetos_cotizador Boceto);
	void Eliminar(Bocetos_cotizador Boceto);
	Integer Maximo(String atributo);
}
