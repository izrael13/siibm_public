package com.websystique.springmvc.dao.tarjetas.cotizador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.tarjetas.cotizador.Cotizador_detalles;

@Repository("cotizador_detallesDAO")
public class Cotizador_detallesDAOImpl  extends AbstractDao<Integer,Cotizador_detalles> implements Cotizador_detallesDAO{

	@Override
	public Cotizador_detalles BuscarxId(Integer id) {
		// FIXME Auto-generated method stub
		return getByKey(id);
	}

	@Override
	public List<Cotizador_detalles> BuscarxCotId(Integer idCot) {
		// FIXME Auto-generated method stub
		Map<String,Integer> mRes =  new HashMap<String, Integer>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		mRes.put("idcotizacion", idCot);
		mOrd.put("1", "idcotizacion");
		
		List<Cotizador_detalles> Lista = criteriaQueryEqInt(mRes, mOrd);
		
		return Lista;
	}

	@Override
	public Integer Guardar(Cotizador_detalles cot) {
		// FIXME Auto-generated method stub
		Integer id = save_entity(cot);
		return id;
	}

	@Override
	public void Actualizar(Cotizador_detalles cot) {
		// FIXME Auto-generated method stub
		update(cot);
	}

}
