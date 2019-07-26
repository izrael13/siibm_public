package com.websystique.springmvc.dao.tarjetas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.tarjetas.Especialidades_cotizacion;

@Repository("especialidades_cotizacionDAO")
public class Especialidades_cotizacionDAOImpl extends AbstractDao<Integer,Especialidades_cotizacion> implements Especialidades_cotizacionDAO{

	@Override
	public List<Especialidades_cotizacion> ListaEspDet(Integer idCot, Integer idDet) {
		// FIXME Auto-generated method stub
		Map<String,String> mOrd =  new HashMap<String, String>();
		//Map<String,Integer> mRes =  new HashMap<String, Integer>();
		//mRes.put("iddetalle", idDet);
		//mRes.put("idcotizacion", idCot);
		
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"iddetalle",idDet,"EQ"));
		Params.add(new ParamsGeneral(1,"idcotizacion",idCot,"EQ"));
		
		return criteriaGeneralList(Params, mOrd);
	}

	@Override
	public Especialidades_cotizacion EspDet(Integer idCot, Integer idDet, Integer idEsp) {
		// FIXME Auto-generated method stub
		/*Map<String,Integer> mRes =  new HashMap<String, Integer>();
		mRes.put("iddetalle", idDet);
		mRes.put("idcotizacion", idCot);
		mRes.put("idespecialidad", idEsp);*/
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"iddetalle",idDet,"EQ"));
		Params.add(new ParamsGeneral(1,"idcotizacion",idCot,"EQ"));
		Params.add(new ParamsGeneral(1,"idespecialidad",idEsp,"EQ"));
		return (Especialidades_cotizacion) criteriaGeneralObj(Params);
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

	@Override
	public void Borrar(List<Especialidades_cotizacion> lista) {
		// FIXME Auto-generated method stub
		delete(lista);
	}

	@Override
	public void Guardar(List<Especialidades_cotizacion> lista) {
		// FIXME Auto-generated method stub
		persist(lista);
	}

}
