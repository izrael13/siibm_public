package com.websystique.springmvc.service.reportes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.reportes.Embarque_diario_detalleDAO;
import com.websystique.springmvc.model.reportes.Embarque_diario_detalle;

@Service("embarque_diario_detalleService")
@Transactional
public class Embarque_diario_detalleServiceImpl implements Embarque_diario_detalleService{
	
	@Autowired
	Embarque_diario_detalleDAO dao;
	
	@Override
	public List<Embarque_diario_detalle> Lista(String fecha, Integer SlpCode) {
		// TODO Auto-generated method stub
		return dao.Lista(fecha, SlpCode);
	}

}
