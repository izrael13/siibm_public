package com.websystique.springmvc.service.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.Inventario_almacen;

public interface Inventario_almacenService {
	List<Inventario_almacen> findByAlmacen(String almacen);
}
