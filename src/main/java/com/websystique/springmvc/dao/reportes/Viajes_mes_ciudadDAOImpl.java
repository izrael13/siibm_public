package com.websystique.springmvc.dao.reportes;

import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.reportes.Viajes_mes_ciudad;

@Repository("viajes_mes_ciudadDAO")
public class Viajes_mes_ciudadDAOImpl extends AbstractDao<Integer, Viajes_mes_ciudad> implements Viajes_mes_ciudadDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<Viajes_mes_ciudad> findByAnioMes(Integer anio, Integer mes) {
		// FIXME Auto-generated method stub
		List<Viajes_mes_ciudad> results = null;
		ProcedureCall criteria = createStoredProcedureCriteria("spViajes_mes_ciudad");
		criteria.registerParameter("pAnio", Integer.class, ParameterMode.IN);
		criteria.registerParameter("pMes", Integer.class, ParameterMode.IN);
		criteria.getParameterRegistration("pAnio").bindValue(anio);
		criteria.getParameterRegistration("pMes").bindValue(mes);
		Output output = criteria.getOutputs().getCurrent();
		if (output.isResultSet()) {
			results = ((ResultSetOutput)output).getResultList();
        }
		return results;
	}

}
