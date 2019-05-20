package com.websystique.springmvc.dao.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.Inventario_almacen;

public interface Inventario_almacenDAO {
	List<Inventario_almacen> findByAlmacen(String almacen, Integer SlpCode);
}
