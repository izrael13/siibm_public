package com.websystique.springmvc.dao.tarjetas.cotizador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.tarjetas.cotizador.Cotizador_detalles;

@Repository("cotizador_detallesDAO")
public class Cotizador_detallesDAOImpl  extends AbstractDao<Integer,Cotizador_detalles> implements Cotizador_detallesDAO{

	@Override
	public Cotizador_detalles BuscarxId(Integer id, Integer iddet, Integer userInsert) {
		// FIXME Auto-generated method stub
		/*Map<String,Integer> mRes =  new HashMap<String, Integer>();
		mRes.put("idcotizacion", id);
		mRes.put("iddetalle", iddet);
		mRes.put("usuario_insert", userInsert);*/
		
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"idcotizacion",id,"EQ"));
		Params.add(new ParamsGeneral(1,"iddetalle",iddet,"EQ"));
		Params.add(new ParamsGeneral(1,"usuario_insert",userInsert,"EQ"));
		
		return (Cotizador_detalles) criteriaGeneralObj(Params);
	}

	@Override
	public List<Cotizador_detalles> BuscarxCotId(Integer idCot) {
		// FIXME Auto-generated method stub
		//Map<String,Integer> mRes =  new HashMap<String, Integer>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		//mRes.put("idcotizacion", idCot);
		mOrd.put("1", "idcotizacion");
		
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"idcotizacion",idCot,"EQ"));
		
		List<Cotizador_detalles> Lista = criteriaGeneralList(Params, mOrd);
		
		return Lista;
	}

	@Override
	public Integer Guardar(Cotizador_detalles cot) {
		// FIXME Auto-generated method stub
		Cotizador_detalles id = save_entityObj(cot);
		Integer i = id.getIddetalle();
		return i;
	}

	@Override
	public void Actualizar(Cotizador_detalles cot) {
		// FIXME Auto-generated method stub
		update(cot);
	}

	@Override
	public Cotizador_detalles BuscarxIdDet(Integer id, Integer iddet) {
		// FIXME Auto-generated method stub
		/*Map<String,Integer> mRes =  new HashMap<String, Integer>();
		mRes.put("idcotizacion", id);
		mRes.put("iddetalle", iddet);*/
		
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"idcotizacion",id,"EQ"));
		Params.add(new ParamsGeneral(1,"iddetalle",iddet,"EQ"));
		
		return (Cotizador_detalles) criteriaGeneralObj(Params);
	}

}
