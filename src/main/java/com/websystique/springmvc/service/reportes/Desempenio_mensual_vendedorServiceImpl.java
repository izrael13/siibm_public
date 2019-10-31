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
	public List<Desempenio_mensual_vendedor> BuscarxAnio(Integer Anio, Integer SlpCode) {
		return dao.BuscarxAnio(Anio, SlpCode);
	}


	@Override
	public List<Object> DesempenioComparativo(String AnioAnt, String AnioAct, Integer CteVen, String Meses) {
		return dao.DesempenioComparativo(AnioAnt, AnioAct, CteVen, Meses);
	}

}
