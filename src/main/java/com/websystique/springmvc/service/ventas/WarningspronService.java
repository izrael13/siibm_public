package com.websystique.springmvc.service.ventas;

import java.util.ArrayList;

public interface WarningspronService {
	ArrayList<?> map ();
	void truncateTable();
	String subirQV(Integer mes, Integer anio);
}
