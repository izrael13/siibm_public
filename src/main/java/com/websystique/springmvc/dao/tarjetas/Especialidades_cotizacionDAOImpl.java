package com.websystique.springmvc.dao.tarjetas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.tarjetas.Especialidades_cotizacion;

@Repository("especialidades_cotizacionDAO")
public class Especialidades_cotizacionDAOImpl extends AbstractDao<Integer,Especialidades_cotizacion> implements Especialidades_cotizacionDAO{

	@Override
	public List<Especialidades_cotizacion> ListaEspDet(Integer idCot, Integer idDet) {
		// FIXME Auto-generated method stub
		Map<String,String> mOrd =  new HashMap<String, String>();
		Map<String,Integer> mRes =  new HashMap<String, Integer>();
		mRes.put("iddetalle", idDet);
		mRes.put("idcotizacion", idCot);
		return criteriaQueryEqInt(mRes,mOrd);
	}

	@Override
	public Especialidades_cotizacion EspDet(Integer idCot, Integer idDet, Integer idEsp) {
		// FIXME Auto-generated method stub
		Map<String,Integer> mRes =  new HashMap<String, Integer>();
		mRes.put("iddetalle", idDet);
		mRes.put("idcotizacion", idCot);
		mRes.put("idespecialidad", idEsp);
		return (Especialidades_cotizacion) criteriaQueryIntEqObj(mRes);
	}

	@Override
	public void Guardar(Especialidades_cotizacion esp) {
		// FIXME Auto-generated method stub
		persist(esp);
	}

	@Override
	public void Borrar(Especialidades_cotizacion obj) {
		// FIXME Auto-generated method stub
		delete(obj);
	}

}
