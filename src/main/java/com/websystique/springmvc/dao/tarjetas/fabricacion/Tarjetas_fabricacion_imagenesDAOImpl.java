package com.websystique.springmvc.dao.tarjetas.fabricacion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.tarjetas.fabricacion.Tarjetas_fabricacion_imagenes;

@Repository("tarjetas_fabricacion_imagenesDAO")
public class Tarjetas_fabricacion_imagenesDAOImpl extends AbstractDao<Integer,Tarjetas_fabricacion_imagenes> implements Tarjetas_fabricacion_imagenesDAO{

	@Override
	public List<Tarjetas_fabricacion_imagenes> BuscarxIdCotidDert(Integer idCot, Integer idDet) {
		// TODO Auto-generated method stub
		Map<String,Integer> mRes =  new HashMap<String, Integer>();
		Map<String,String> mResStr =  new HashMap<String, String>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		mRes.put("idcotizacion", idCot);
		mRes.put("iddetalle", idDet);
		mOrd.put("1", "fecha_insert");
		
		List<Tarjetas_fabricacion_imagenes> Lista = criteriaQueryEqStrInt(mResStr,mRes, mOrd);
		
		return Lista;
	}

	@Override
	public List<Tarjetas_fabricacion_imagenes> BuscarxId(Integer idCot, Integer idDet, String nombre) {
		// TODO Auto-generated method stub
		Map<String,Integer> mResInt =  new HashMap<String, Integer>();
		Map<String,String> mResStr =  new HashMap<String, String>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		mResInt.put("idcotizacion", idCot);
		mResInt.put("iddetalle", idDet);
		
		mResStr.put("nombre", nombre);
		
		mOrd.put("1", "nombre");
		
		List<Tarjetas_fabricacion_imagenes> Lista = criteriaQueryEqStrInt(mResStr,mResInt,mOrd);
		return Lista;
	}

	@Override
	public void Guardar(Tarjetas_fabricacion_imagenes tar_img) {
		// TODO Auto-generated method stub
		persist(tar_img);
	}

	@Override
	public void Borrar(Tarjetas_fabricacion_imagenes tar_img) {
		// TODO Auto-generated method stub
		delete(tar_img);
	}

	@Override
	public void Actualizar(Tarjetas_fabricacion_imagenes tar_img) {
		// TODO Auto-generated method stub
		update(tar_img);
	}

}
