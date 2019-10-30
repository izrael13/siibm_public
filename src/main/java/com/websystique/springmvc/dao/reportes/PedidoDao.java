package com.websystique.springmvc.dao.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.Pedido;
import com.websystique.springmvc.model.reportes.PedidoPk;

public interface PedidoDao {
	Pedido findByPk(PedidoPk pPk);
	List<Pedido> findAll();
	List<Pedido> findPedido(int pId);
}