package com.websystique.springmvc.dao.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.Todos_pedidos;

public interface Todos_pedidosDAO {
	List<Todos_pedidos> findPedidosByAnio(Integer anio, Integer SlpCode);
}
