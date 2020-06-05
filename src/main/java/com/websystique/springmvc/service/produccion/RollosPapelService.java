package com.websystique.springmvc.service.produccion;

import java.util.List;
import com.websystique.springmvc.model.produccion.RollosPapel;

public interface RollosPapelService {
	RollosPapel RollosPapel(String numeroRolloID);
	List<RollosPapel>ListRollos(String numeroRolloID);

}
