package com.websystique.springmvc.service.reportes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.reportes.Desempenio_mensual_vendedorDAO;
import com.websystique.springmvc.model.reportes.Desempenio_mensual_vendedor;

@Service("desempenio_mensual_vendedorService")
@Transactional
public class Desempenio_mensual_vendedorServiceImpl implements Desempenio_mensual_vendedorService{
	
	@Autowired
	Desempenio_mensual_vendedorDAO dao;
	
	
	@Override
	public List<Desempenio_mensual_vendedor> BuscarxAnio(Integer Anio) {
		// TODO Auto-generated method stub
		return dao.BuscarxAnio(Anio);
	}

}
