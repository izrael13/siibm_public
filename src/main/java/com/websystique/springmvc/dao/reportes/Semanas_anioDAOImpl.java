package com.websystique.springmvc.dao.reportes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.dao.reportes.Semanas_anioDAO;
import com.websystique.springmvc.model.Semanas_anio;

@SuppressWarnings("all")
@Repository("semanas_anioDAO")
public class Semanas_anioDAOImpl extends AbstractDao<Integer, Semanas_anio> implements Semanas_anioDAO{

	@Override
	public Semanas_anio findByAnioSem(int anio, int semana) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Semanas_anio> findByAnioSemInt(int anio_ini, int semana_ini, int anio_fin, int semana_fin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer,String> findAllSemanas() {
		
		Map<String,String> mRes =  new HashMap<String, String>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		mOrd.put("1", "fecha_inicial");
		
		List<Semanas_anio> semanasanio = criteriaQuery(mRes, mOrd);
		Map< Integer, String > sem = new TreeMap <Integer, String>();

		for(int i = 0; i < semanasanio.size(); i++)
		{
			//System.out.println(( String.format("%2s", semanasanio.get(i).getSemana()).replace(' ', '0')));
			sem.put(Integer.valueOf(String.valueOf(semanasanio.get(i).getAnio())+String.format("%2s", semanasanio.get(i).getSemana()).replace(' ', '0')), String.valueOf(semanasanio.get(i).getAnio())+ "-" +String.valueOf(semanasanio.get(i).getSemana()));
		}
		return sem;
	}

}
