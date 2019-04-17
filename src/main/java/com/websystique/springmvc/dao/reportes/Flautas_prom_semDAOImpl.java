package com.websystique.springmvc.dao.reportes;

import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.reportes.Flautas_prom_sem;

@SuppressWarnings("all")
@Repository("flautas_prom_semDAO")
public class Flautas_prom_semDAOImpl extends AbstractDao<Integer, Flautas_prom_sem> implements Flautas_prom_semDAO{

	@Override
	public List<Flautas_prom_sem> findbySemAnio(Integer anio, Integer semana) {
		// TODO Auto-generated method stub
		List<Flautas_prom_sem> results = null;
		ProcedureCall criteria = createStoredProcedureCriteria("spFlautapromsem");
		criteria.registerParameter("pAnio", Integer.class, ParameterMode.IN);
		criteria.registerParameter("pSemana", Integer.class, ParameterMode.IN);
		criteria.getParameterRegistration("pAnio").bindValue(anio);
		criteria.getParameterRegistration("pSemana").bindValue(semana);
		Output output = criteria.getOutputs().getCurrent();
		if (output.isResultSet()) {
			results = ((ResultSetOutput)output).getResultList();
        }
		return results;
	}

}
