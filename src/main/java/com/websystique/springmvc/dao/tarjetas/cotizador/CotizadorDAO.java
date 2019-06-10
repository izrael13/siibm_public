package com.websystique.springmvc.dao.tarjetas.cotizador;

import java.util.List;

import com.websystique.springmvc.model.tarjetas.cotizador.Cotizador;
import com.websystique.springmvc.model.tarjetas.cotizador.Cotizador_busqueda;

public interface CotizadorDAO {
	Cotizador BuscarxId(Integer id, Integer userInsert);
	List<Cotizador> BuscarxUser(Integer idUser);
	Integer Guardar(Cotizador cot);
	void Actualizar(Cotizador cot);
	List<Cotizador_busqueda> ListaBusquedaxIdCardCode(Integer id, String cardCode,Integer idUser);
	List<Cotizador_busqueda> ListaBusquedaxIdCardCodeDet(Integer id, String cardCode,Integer idUser);
}
