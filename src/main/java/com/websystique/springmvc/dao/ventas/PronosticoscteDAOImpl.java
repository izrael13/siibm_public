package com.websystique.springmvc.dao.ventas;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ventas.Pronosticoscte;

@SuppressWarnings("all")
@Repository("pronosticoscteDAO")
public class PronosticoscteDAOImpl extends AbstractDao<Integer,Pronosticoscte> implements PronosticoscteDAO{

	@Override
	public List<Pronosticoscte> readFile(List<Pronosticoscte> list) {
		// FIXME Auto-generated method stub
		persist(list);
		return null;
	}

}
