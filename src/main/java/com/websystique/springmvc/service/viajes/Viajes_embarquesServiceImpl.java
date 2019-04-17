package com.websystique.springmvc.service.viajes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.viajes.Viajes_embarquesDAO;
import com.websystique.springmvc.model.viajes.Viajes_embarques;

@Service("viajes_embarquesService")
@Transactional
public class Viajes_embarquesServiceImpl implements Viajes_embarquesService{
	
	@Autowired
	Viajes_embarquesDAO dao;

	@Override
	public List<Viajes_embarques> findAllViajesByUserSap(Integer userSap) {
		// FIXME Auto-generated method stub
		return dao.findAllViajesByUserSap(userSap);
	}
	
	@Override
	public List<Viajes_embarques> updateViajes(String nviaje, Integer demora, Integer devolucion, Integer cbo_estado) {
		// FIXME Auto-generated method stub
		return dao.updateViajes(nviaje, demora, devolucion, cbo_estado);
	}

	@Override
	public String updateAutEmbarques(String nviaje) {
		// FIXME Auto-generated method stub
		return dao.updateAutEmbarques(nviaje);
	}

	@Override
	public String updateAutLogistica(String nviaje) {
		// FIXME Auto-generated method stub
		return dao.updateAutLogistica(nviaje);
	}

	@Override
	public List<Viajes_embarques> findAllViajesByUserSapLog(Integer userSap) {
		// FIXME Auto-generated method stub
		return dao.findAllViajesByUserSapLog(userSap);
	}

	@Override
	public String updateRegAemb(String nviaje) {
		// FIXME Auto-generated method stub
		return dao.updateRegAemb(nviaje);
	}

	@Override
	public List<Viajes_embarques> findAllViajesHistorial() {
		// FIXME Auto-generated method stub
		return dao.findAllViajesHistorial();
	}
	
	
}
