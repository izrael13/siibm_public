package com.websystique.springmvc.dao.tarjetas.prospectos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.tarjetas.prospectos.Prospectos_ventas_detalle;

@Repository("prospectos_ventas_detalleDAO")
public class Prospectos_ventas_detalleDAOImpl extends AbstractDao<Integer,Prospectos_ventas_detalle> implements Prospectos_ventas_detalleDAO{

	@Override
	public List<Prospectos_ventas_detalle> BuscarPorIdPV(Integer id) {
		
		Map<String,Integer> mRes =  new HashMap<String, Integer>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		mRes.put("id_prospecto_ventas", id);
		mOrd.put("1", "id");
		List<Prospectos_ventas_detalle> Lista = criteriaQueryEqInt(mRes, mOrd);
		
		return Lista;
	}

	@Override
	public void Guardar(Prospectos_ventas_detalle pvd) {
		persist(pvd);
	}

}
