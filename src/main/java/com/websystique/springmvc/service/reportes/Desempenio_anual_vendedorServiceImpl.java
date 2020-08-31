package com.websystique.springmvc.service.reportes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.reportes.Desempenio_anual_vendedorDAO;
import com.websystique.springmvc.model.reportes.Desempenio_anual_vendedor;


@Service("Desempenio_anual_vendedorService")
@Transactional
public class Desempenio_anual_vendedorServiceImpl implements Desempenio_anual_vendedorService{
	
	@Autowired
	Desempenio_anual_vendedorDAO dao;

	@Override
	public List<Desempenio_anual_vendedor> BuscarxAnio(Integer Anio, Integer SlpCode) {
		return dao.BuscarxAnios(Anio, SlpCode);
	}

	@Override
	public List<Object> DesempenioComparativo(String AnioAnt, String AnioAct, Integer CteVen) {
		return dao.DesempenioComparativos(AnioAnt, AnioAct, CteVen);
	}


	

}
