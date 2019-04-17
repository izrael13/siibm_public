package com.websystique.springmvc.dao.reportes;

import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.reportes.Paros_maquina_dia;

@Repository("paros_maquina_diaDAO")
public class Paros_maquina_diaDAOImpl extends AbstractDao<Integer, Paros_maquina_dia> implements Paros_maquina_diaDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<Paros_maquina_dia> findbyAnioMes(Integer anio, Integer mes) {
		List<Paros_maquina_dia> results = null;
		ProcedureCall criteria = createStoredProcedureCriteria("spParosMaqDia");
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
