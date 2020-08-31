package com.websystique.springmvc.service.certificados;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.certificados.LiberacioncmDAO;
import com.websystique.springmvc.model.certificados.Liberacioncm;

@Service("liberacioncmService")
@Transactional
public class LiberacioncmServiceImpl implements LiberacioncmService{
	
	@Autowired LiberacioncmDAO dao;
	
	@Override
	public Liberacioncm BuscarxId(String itemcode) {
		return dao.BuscarxId(itemcode);
	}

	@Override
	public void Guardar(Liberacioncm lcm) {
		dao.Guardar(lcm);
	}

	@Override
	public void Actualizar(Liberacioncm lcm) {
		dao.Actualizar(lcm);
	}

	@Override
	public void Eliminar(Liberacioncm lcm) {
		dao.Eliminar(lcm);
	}

	@Override
	public Liberacioncm BuscarxId(Integer id) {
		return dao.BuscarxId(id);
	}

}
