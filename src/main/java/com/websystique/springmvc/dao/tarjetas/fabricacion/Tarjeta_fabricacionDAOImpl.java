package com.websystique.springmvc.dao.tarjetas.fabricacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.tarjetas.fabricacion.Tarjeta_fabricacion;
import com.websystique.springmvc.model.tarjetas.fabricacion.Tarjeta_fabricacion_Busqueda;

@Repository("tarjeta_fabricacionDAO")
public class Tarjeta_fabricacionDAOImpl extends AbstractDao<Integer,Tarjeta_fabricacion> implements Tarjeta_fabricacionDAO{

	@Override
	public Tarjeta_fabricacion BuscarxFolio(String Folio) {
		// TODO Auto-generated method stub
		Map<String,String> mRes =  new HashMap<String, String>();
		
		mRes.put("folio_tarjeta", Folio);

		Tarjeta_fabricacion Tarjeta = (Tarjeta_fabricacion) criteriaQueryEqObj(mRes);
		
		return Tarjeta;
	}

	@Override
	public List<Tarjeta_fabricacion> BuscarXIdCot(Integer IdCot) {
		// TODO Auto-generated method stub
		Map<String,Integer> mRes =  new HashMap<String, Integer>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		mRes.put("idcotizacion", IdCot);
		mOrd.put("1", "folio_tarjeta");
		
		List<Tarjeta_fabricacion> Lista = criteriaQueryEqInt(mRes, mOrd);
		
		return Lista;
	}

	@Override
	public void Guardar(Tarjeta_fabricacion Tarjeta) {
		// TODO Auto-generated method stub
		persist(Tarjeta);
	}

	@Override
	public void Actualizar(Tarjeta_fabricacion Tarjeta) {
		// TODO Auto-generated method stub
		update(Tarjeta);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Tarjeta_fabricacion_Busqueda> TarjetaBusqueda(Integer IdCot, String Folio) {
		// TODO Auto-generated method stub
		Map<Integer,Integer> paramsInt = new HashMap<Integer, Integer>();
		Map<Integer,String> paramStr = new HashMap<Integer, String>();
		int posicion = 0;
		
		String query  ="select ROW_NUMBER() OVER(ORDER BY A.FOLIO_TARJETA ASC) AS count, A.IDCOTIZACION,A.FOLIO_TARJETA,B.SIMBOLO\r\n" + 
				"from TARJETA_FABRICACION A\r\n" + 
				"inner join COTIZADOR_DETALLES B on A.IDCOTIZACION = B.IDCOTIZACION ";
		if(!Folio.trim().equals(""))
		{
			posicion ++;
			query = query + " where A.FOLIO_TARJETA = ?"+posicion;
			paramStr.put(posicion, Folio);
		}
		else
		{
			posicion ++;
			query = query + " where A.IDCOTIZACION =  ?"+posicion;
			paramsInt.put(posicion, IdCot);
		}
		List<Tarjeta_fabricacion_Busqueda> Lista = new ArrayList<Tarjeta_fabricacion_Busqueda>();
		List<Object> result = criteriaQueryStr(query,paramsInt,paramStr);
		
		Iterator itr = result.iterator();
		
		while(itr.hasNext()){
			   Object[] obj = (Object[]) itr.next();
			   Tarjeta_fabricacion_Busqueda tf = new Tarjeta_fabricacion_Busqueda();
			   tf.setCount(Integer.valueOf(String.valueOf(obj[0])));
			   tf.setIdcotizacion(Integer.valueOf(String.valueOf(obj[1])));
			   tf.setFolio_tarjeta(String.valueOf(obj[2]));
			   tf.setSimbolo(String.valueOf(obj[3]));
			   
			   Lista.add(tf); 
			}
		
		return Lista;
	}

}
