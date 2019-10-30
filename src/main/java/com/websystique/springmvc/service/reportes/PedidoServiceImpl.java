package com.websystique.springmvc.service.reportes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.reportes.PedidoDao;
import com.websystique.springmvc.model.reportes.Pedido;
import com.websystique.springmvc.model.reportes.PedidoPk;

@Service("PedidoService")
@Transactional

public class PedidoServiceImpl implements PedidoService {
	@Autowired
	private PedidoDao dao;
	
	@Override
	public List<Pedido> findAll() {
//		Alumno alu = new Alumno();
//		alu.setExp(61011);
//		alu.setId(1);
//		alu.setNombre("David");
//		alu.setFecNan("13/11/1971");
//		
//		List<Alumno> alumnos;
//		alumnos = new ArrayList<Alumno>();
//		alumnos.add(alu);
		
	    List<Pedido> pedidos = dao.findAll();	
		return pedidos;
	}

	@Override
	public Pedido findByPk(PedidoPk pPk) {
		Pedido pedido = new Pedido();

//		Alumno pedido = dao.findByPk(pPk);
		return pedido;
	}

	@Override
	public List<Pedido> findPedido(int pId) {
		List<Pedido> pedidoa = dao.findPedido(pId);
		return pedidoa;
	}

}