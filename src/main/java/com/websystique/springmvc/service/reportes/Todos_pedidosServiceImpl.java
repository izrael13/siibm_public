package com.websystique.springmvc.service.reportes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.reportes.Todos_pedidosDAO;
import com.websystique.springmvc.model.reportes.Todos_pedidos;

@Service("todos_pedidosService")
@Transactional
public class Todos_pedidosServiceImpl implements Todos_pedidosService{
	
	@Autowired
	Todos_pedidosDAO dao;

	@Override
	public List<Todos_pedidos> findPedidosByAnio(Integer anio, Integer SlpCode) {
		// FIXME Auto-generated method stub
		return dao.findPedidosByAnio(anio,SlpCode);
	}
}
