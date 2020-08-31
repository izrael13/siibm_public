package com.websystique.springmvc.dao.certificados;

import com.websystique.springmvc.model.certificados.Liberacioncm;

public interface LiberacioncmDAO {
	Liberacioncm BuscarxId(String tf);
	Liberacioncm BuscarxId(Integer id);
	void Guardar(Liberacioncm lcm);
	void Actualizar(Liberacioncm lcm);
	void Eliminar(Liberacioncm lcm);
}
