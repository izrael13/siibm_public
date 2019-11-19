package com.websystique.springmvc.dao.tarjetas.fabricacion;

import java.util.List;

import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.tarjetas.fabricacion.Tarjeta_fabricacion;

public interface Tarjeta_fabricacionDAO {
	Tarjeta_fabricacion BuscarxFolio(String Folio, Integer IdDis);
	Tarjeta_fabricacion BuscarxFolio(String Folio);
	List<Tarjeta_fabricacion> BuscarXIdCot(Integer IdCot, Integer IdDis);
	void Guardar(Tarjeta_fabricacion Tarjeta);
	void Actualizar(Tarjeta_fabricacion Tarjeta);
	List<Tarjeta_fabricacion> TarjetaBusqueda(Integer IdCot, String Folio, String cardcode, Integer IdDis);
	void Borrar(Tarjeta_fabricacion Tarjeta);
	List<Tarjeta_fabricacion> BuscarXAut(List<ParamsGeneral> Params);
	//List<Object> BuscarEsp(Integer idcot, Integer Iddet);
	List<Tarjeta_fabricacion> ListaSeguimiento(String Folio, Integer IdCot, Integer Status, String CardCode);
	Tarjeta_fabricacion BuscarxCot_Cotdet(Integer idcot, Integer iddet);
}
