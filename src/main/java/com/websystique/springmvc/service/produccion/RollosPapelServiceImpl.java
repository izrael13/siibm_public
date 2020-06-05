package com.websystique.springmvc.service.produccion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.produccion.RollosPapelDAO;
import com.websystique.springmvc.model.produccion.RollosPapel;

@Service("RollosPapelService")
@Transactional
public class RollosPapelServiceImpl implements RollosPapelService{
	
	@Autowired RollosPapelDAO rollo_pa;
	@Override
	public RollosPapel RollosPapel(String numeroRolloID) {
		// TODO Auto-generated method stub
		return rollo_pa.RollosPapel(numeroRolloID);
	}
	@Override
	public List<RollosPapel> ListRollos(String numeroRolloID) {
		// TODO Auto-generated method stub
		return rollo_pa.ListRollos(numeroRolloID);
	}
	

}
