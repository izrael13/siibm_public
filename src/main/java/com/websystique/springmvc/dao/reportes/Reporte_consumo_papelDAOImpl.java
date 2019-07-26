package com.websystique.springmvc.dao.reportes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.dao.reportes.Reporte_consumo_papelDAO;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.reportes.Reporte_consumo_papel;

@SuppressWarnings("all")
@Repository("reporte_consumo_papelDAO")
public class Reporte_consumo_papelDAOImpl extends AbstractDao<Integer, Reporte_consumo_papel> implements Reporte_consumo_papelDAO{

	@Override
	public List<Reporte_consumo_papel> findByAnioSem(int anio, int semana) {
		
		//Map<String,String> mRes =  new HashMap<String, String>();
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"anio",String.valueOf(anio),"EQ"));
		Params.add(new ParamsGeneral(1,"semana",String.valueOf(semana),"EQ"));
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		//mRes.put("anio",String.valueOf(anio));
		//mRes.put("semana",String.valueOf(semana));
		
		mOrd.put("1", "ancho");
		mOrd.put("2", "anio");
		mOrd.put("3", "semana");
		
		List<Reporte_consumo_papel> consumo = criteriaGeneralList(Params, mOrd);
		
		return consumo;
	}

	@Override
	public List<Reporte_consumo_papel> findByAnioSemInt(int anio_ini, int semana_ini, int anio_fin, int semana_fin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reporte_consumo_papel> findAllRegistros() {
		
		//Map<String,String> mRes =  new HashMap<String, String>();
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		mOrd.put("1", "ancho");
		mOrd.put("2", "anio");
		mOrd.put("3", "semana");
		
		List<Reporte_consumo_papel> consumo = criteriaGeneralList(Params, mOrd);
		
		return consumo;
		 
	}
	
	
}
