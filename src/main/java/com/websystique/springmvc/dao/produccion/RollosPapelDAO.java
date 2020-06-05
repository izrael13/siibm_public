package com.websystique.springmvc.dao.produccion;

import java.util.List;

import com.websystique.springmvc.model.produccion.RollosPapel;

public interface RollosPapelDAO {

	RollosPapel RollosPapel(String numeroRolloID);
	List<RollosPapel>ListRollos(String numeroRolloID);
//	NAMECLASS TABLE (TYPE_OF_PARAMS VARIABLE)
	
	
}
