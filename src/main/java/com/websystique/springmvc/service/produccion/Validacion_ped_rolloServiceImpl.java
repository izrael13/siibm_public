package com.websystique.springmvc.service.produccion;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websystique.springmvc.dao.produccion.Validacion_ped_rolloDAO;
import com.websystique.springmvc.model.produccion.Validacion_ped_rollo;

@Service("Validacion_ped_rolloService")
@Transactional 

public class Validacion_ped_rolloServiceImpl implements Validacion_ped_rolloService {

	@Autowired Validacion_ped_rolloDAO valid_ped_dao;
	
	@Override
	public void Save(Validacion_ped_rollo prog) {
		valid_ped_dao.Save(prog);
		
	}

	@Override
	public List<Validacion_ped_rollo>ListValid(String prog_corru) {
		// TODO Auto-generated method stub
		return valid_ped_dao.ListValid(prog_corru);
	}

	@Override
	public Validacion_ped_rollo BuscarxPedRollo(String progCorru_ID,
			String numerorolloid) {
		// TODO Auto-generated method stub
		return valid_ped_dao.BuscarxPedRollo(progCorru_ID, numerorolloid);
	}
	
}




