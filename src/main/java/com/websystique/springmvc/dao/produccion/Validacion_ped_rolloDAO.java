package com.websystique.springmvc.dao.produccion;


import java.util.List;
import com.websystique.springmvc.model.produccion.Validacion_ped_rollo;

public interface Validacion_ped_rolloDAO {
	
	List<Validacion_ped_rollo>ListValid(String prog_corru);//this is the method that returns everything
	void Save(Validacion_ped_rollo prog);//
	Validacion_ped_rollo BuscarxPedRollo(String progCorru_ID, String numerorolloid);
	//Validacion_ped_rollo Search(Integer id);
	//List<Validacion_ped_rollo> ListSearch(Integer sear);
	

}
