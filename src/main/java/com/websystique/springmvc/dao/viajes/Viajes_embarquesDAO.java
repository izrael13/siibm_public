package com.websystique.springmvc.dao.viajes;

import java.util.List;

import com.websystique.springmvc.model.viajes.Viajes_embarques;

public interface Viajes_embarquesDAO {
	List<Viajes_embarques> findAllViajesByUserSap(Integer userSap);
	List<Viajes_embarques> updateViajes(String nviaje,Integer demora, Integer devolucion, Integer cbo_estado);
	String updateAutEmbarques(String nviaje);
	String updateAutLogistica(String nviaje);
	List<Viajes_embarques> findAllViajesByUserSapLog(Integer userSap);
	List<Viajes_embarques> findAllViajesHistorial();
	String updateRegAemb(String nviaje);
}
