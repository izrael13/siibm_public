package com.websystique.springmvc.service.produccion;

import java.util.List;


import com.websystique.springmvc.model.produccion.Validacion_ped_rollo;

public interface Validacion_ped_rolloService {
	List<Validacion_ped_rollo>ListValid(String prog_corru);//same for the service
	//Validacion_ped_rollo Validacion_ped_rollo(Integer validado_ID);
	void Save(Validacion_ped_rollo prog);//
	Validacion_ped_rollo BuscarxPedRollo(String progCorru_ID, String numerorolloid);
	
	//Validacion_ped_rollo Search(Integer id);
	//List<Customer> ListSearch(String sear);

}
