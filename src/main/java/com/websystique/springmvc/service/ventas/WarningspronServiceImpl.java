package com.websystique.springmvc.service.ventas;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.ventas.WarningspronDAO;

@Service("warningspronService")
@Transactional
public class WarningspronServiceImpl implements WarningspronService{
	
	@Autowired
	WarningspronDAO dao;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ArrayList map() {
		// FIXME Auto-generated method stub
		return dao.map();
	}

	@Override
	public void truncateTable() {
		// FIXME Auto-generated method stub
		dao.truncateTable();
	}

	@Override
	public String subirQV(Integer mes, Integer anio) {
		// FIXME Auto-generated method stub
		return dao.subirQV(mes, anio);
	}

}
