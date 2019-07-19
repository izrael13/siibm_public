package com.websystique.springmvc.service.reportes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.reportes.Desempenio_mensual_xclienteDAO;
import com.websystique.springmvc.model.reportes.Desempenio_mensual_xcliente;

@Service("desempenio_mensual_xclienteService")
@Transactional
public class Desempenio_mensual_xclienteServiceImpl implements Desempenio_mensual_xclienteService{
	
	@Autowired
	Desempenio_mensual_xclienteDAO dao;
	
	@Override
	public List<Desempenio_mensual_xcliente> Buscar(Integer Anio, String CardCode, Integer SlpCode) {
		// TODO Auto-generated method stub
		return dao.Buscar(Anio, CardCode, SlpCode);
	}

}
