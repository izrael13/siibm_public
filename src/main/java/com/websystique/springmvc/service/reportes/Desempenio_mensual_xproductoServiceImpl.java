package com.websystique.springmvc.service.reportes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.reportes.Desempenio_mensual_xproductoDAO;
import com.websystique.springmvc.model.reportes.Desempenio_mensual_xproducto;

@Service("sesempenio_mensual_xproductoService")
@Transactional
public class Desempenio_mensual_xproductoServiceImpl implements Desempenio_mensual_xproductoService{

	@Autowired
	Desempenio_mensual_xproductoDAO dao;
	
	@Override
	public List<Desempenio_mensual_xproducto> Buscar(Integer anio, Integer slpcode, Integer xcte, Integer xitem) {
		// TODO Auto-generated method stub
		return dao.Buscar(anio, slpcode, xcte, xitem);
	}

}
