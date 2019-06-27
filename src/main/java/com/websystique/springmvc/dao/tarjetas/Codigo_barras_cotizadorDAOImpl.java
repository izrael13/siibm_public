package com.websystique.springmvc.dao.tarjetas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.tarjetas.Codigo_barras_cotizador;

@Repository("codigo_barras_cotizadorDAO")
public class Codigo_barras_cotizadorDAOImpl extends AbstractDao<Integer,Codigo_barras_cotizador> implements Codigo_barras_cotizadorDAO{

	@Override
	public Codigo_barras_cotizador BuscarXCotDetCod(Integer idcot, Integer idcotdet, Integer idcod) {
		// FIXME Auto-generated method stub
		return null;
	}

	@Override
	public List<Codigo_barras_cotizador> BuscarXCotDet(Integer idcot, Integer idcotdet) {
		// FIXME Auto-generated method stub
		Map<String,String> mOrd =  new HashMap<String, String>();
		Map<String,Integer> mRes =  new HashMap<String, Integer>();
		mRes.put("idcotizacion", idcot);
		mRes.put("iddetalle", idcotdet);
		return criteriaQueryEqInt(mRes,mOrd);
	}

	@Override
	public List<Codigo_barras_cotizador> BuscarXCot(Integer idcot) {
		// FIXME Auto-generated method stub
		Map<String,String> mOrd =  new HashMap<String, String>();
		Map<String,Integer> mRes =  new HashMap<String, Integer>();
		mRes.put("idcotizacion", idcot);
		return criteriaQueryEqInt(mRes,mOrd);
	}

	@Override
	public void Guardar(Codigo_barras_cotizador objesp) {
		// FIXME Auto-generated method stub
		persist(objesp);
	}

	@Override
	public void Borrar(Codigo_barras_cotizador obj) {
		// FIXME Auto-generated method stub
		delete(obj);
	}

	@Override
	public void Borrar(List<Codigo_barras_cotizador> lista) {
		// FIXME Auto-generated method stub
		delete(lista);
	}

	@Override
	public void Guardar(List<Codigo_barras_cotizador> lista) {
		// FIXME Auto-generated method stub
		persist(lista);
	}

}
