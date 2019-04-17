package com.websystique.springmvc.dao.reportes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.reportes.Reportes_consumo_papel_utl_sem;

import org.hibernate.result.ResultSetOutput;

@SuppressWarnings("all")
@Repository("reportes_consumo_papel_utl_semDAO")
public class Reportes_consumo_papel_utl_semDAOImpl extends AbstractDao<Integer, Reportes_consumo_papel_utl_sem> implements Reportes_consumo_papel_utl_semDAO{

	@Override
	public List<Reportes_consumo_papel_utl_sem> findByAnioSem(int anio, int semana) {
		List<Reportes_consumo_papel_utl_sem> results = null;
		ProcedureCall criteria = createStoredProcedureCriteria("spGenerarConsPapelUltSem");
		Output output = criteria.getOutputs().getCurrent();
		if (output.isResultSet()) {
			results = ((ResultSetOutput)output).getResultList();
        }
		return results;
	}

	@Override
	public List<Reportes_consumo_papel_utl_sem> findByAnioSemInt(int anio_ini, int semana_ini, int anio_fin,
			int semana_fin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reportes_consumo_papel_utl_sem> findAllRegistros() {
		// TODO Auto-generated method stub
		return null;
	}



}
