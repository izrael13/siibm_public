package com.websystique.springmvc.service.tarjetas.cotizador;

import java.util.List;

import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.tarjetas.cotizador.Cotizador;
//import com.websystique.springmvc.model.tarjetas.cotizador.Cotizador_busqueda;

public interface CotizadorService {
	Cotizador BuscarxId(Integer id);
	Cotizador BuscarxId(Integer id, Integer userInsert);
	Cotizador BuscarxIdArr(Integer id, Integer userInsert);
	List<Cotizador> BuscarxUser(Integer idUser);
	void Guardar(Cotizador cot);
	void Actualizar(Cotizador cot);
	List<Cotizador> ListasCotAut(List<ParamsGeneral> params);
	Integer Maximo(String atributo);
}
