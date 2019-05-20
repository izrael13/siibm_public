package com.websystique.springmvc.service.reportes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.reportes.Inventario_almacenDAO;
import com.websystique.springmvc.model.reportes.Inventario_almacen;

@Service("inventario_almacenService")
@Transactional
public class Inventario_almacenServiceImpl implements Inventario_almacenService{

	@Autowired
	private Inventario_almacenDAO dao;
	
	@Override
	public List<Inventario_almacen> findByAlmacen(String almacen, Integer SlpCode) {
		return dao.findByAlmacen(almacen, SlpCode);
	}
	
}
