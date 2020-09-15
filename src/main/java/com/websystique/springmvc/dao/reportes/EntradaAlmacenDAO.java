package com.websystique.springmvc.dao.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.EntradaAlmacen;

public interface EntradaAlmacenDAO {
	List<EntradaAlmacen>  findAllEntradaAlmacen(String listaPedidos);
}
