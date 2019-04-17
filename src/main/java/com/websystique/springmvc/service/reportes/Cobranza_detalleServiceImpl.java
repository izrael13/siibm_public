package com.websystique.springmvc.service.reportes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.reportes.Cobranza_detalleDAO;
import com.websystique.springmvc.model.reportes.Cobranza_detalle;

@Service("cobranza_detalleService")
@Transactional
public class Cobranza_detalleServiceImpl implements Cobranza_detalleService{
	
	@Autowired
	Cobranza_detalleDAO dao;
	
	@Override
	public List<Cobranza_detalle> findByCteVen() {
		// FIXME Auto-generated method stub
		return dao.findByCteVen();
	}
		
}
