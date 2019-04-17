package com.websystique.springmvc.dao.reportes;

import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.reportes.Peso_dia;

@Repository("peso_diaDAO")
public class Peso_diaDAOImpl extends AbstractDao<Integer, Peso_dia> implements Peso_diaDAO{
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Peso_dia> findByAnioMes(Integer anio, Integer mes) {
		// FIXME Auto-generated method stub
		List<Peso_dia> results = null;
		ProcedureCall criteria = createStoredProcedureCriteria("spPesoDia");
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
