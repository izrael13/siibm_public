package com.websystique.springmvc.service.reportes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.reportes.ListaEmbarquesDAO;
import com.websystique.springmvc.model.reportes.ListaEmbarques;

@Service("listaEmbarquesService")
@Transactional
public class ListaEmbarquesServiceImpl implements ListaEmbarquesService{

	@Autowired ListaEmbarquesDAO dao;
	
	@Override
	public List<ListaEmbarques> ListaEmbarques(String fechaIni, String fechaFin) {
		return dao.ListaEmbarques(fechaIni, fechaFin);
	}

}
