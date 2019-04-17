package com.websystique.springmvc.dao.ventas;

import java.util.ArrayList;

public interface WarningspronDAO {
	@SuppressWarnings("rawtypes")
	ArrayList map ();
	void truncateTable();
	String subirQV(Integer mes, Integer anio);
}
