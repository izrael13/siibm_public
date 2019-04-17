package com.websystique.springmvc.dao.reportes;

import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.reportes.Amortiza_herramentales;

@SuppressWarnings("all")
@Repository("amortiza_herramentalesDAO")
public class Amortiza_herramentalesDAOImpl extends AbstractDao<Integer,Amortiza_herramentales>implements Amortiza_herramentalesDAO{

	@Override
	public List<Amortiza_herramentales> findAmortHerram(Integer select) {
		// FIXME Auto-generated method stub
		List<Amortiza_herramentales> result = null;
		
		ProcedureCall criteria = createStoredProcedureCriteria("amortizacion_herramentales");
		criteria.registerParameter("pSelect", Integer.class, ParameterMode.IN);
		criteria.getParameterRegistration("pSelect").bindValue(select);
		Output output = criteria.getOutputs().getCurrent();
		if (output.isResultSet()) {
			result = ((ResultSetOutput)output).getResultList();
        }
		
		return result;
	}

}
