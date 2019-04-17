package com.websystique.springmvc.dao.reportes;

import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.reportes.Paros_concepto_dia;

@Repository("paros_concepto_diaDAO")
public class Paros_concepto_diaDAOImpl extends AbstractDao<Integer, Paros_concepto_dia> implements Paros_concepto_diaDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<Paros_concepto_dia> findAllParosConceptodia(String fecha_ini) {
		// TODO Auto-generated method stub
		List<Paros_concepto_dia> results = null;
		ProcedureCall criteria = createStoredProcedureCriteria("spParos_concepto_dia");
		criteria.registerParameter("pFecha_ini", String.class, ParameterMode.IN);
		criteria.getParameterRegistration("pFecha_ini").bindValue(fecha_ini);
		Output output = criteria.getOutputs().getCurrent();
		if (output.isResultSet()) {
			results = ((ResultSetOutput)output).getResultList();
        }
		return results;
	}

}
