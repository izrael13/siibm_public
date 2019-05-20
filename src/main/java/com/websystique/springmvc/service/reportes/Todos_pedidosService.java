package com.websystique.springmvc.service.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.Todos_pedidos;

public interface Todos_pedidosService {
	List<Todos_pedidos> findPedidosByAnio(Integer anio, Integer SlpCode);
}
