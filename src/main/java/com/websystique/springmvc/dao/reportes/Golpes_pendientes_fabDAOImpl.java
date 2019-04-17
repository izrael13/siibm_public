package com.websystique.springmvc.dao.reportes;

import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.reportes.Golpes_pendientes_fab;

@Repository("Golpes_pendientes_fabDAO")
public class Golpes_pendientes_fabDAOImpl extends AbstractDao<Integer,Golpes_pendientes_fab> implements Golpes_pendientes_fabDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<Golpes_pendientes_fab> findByAnioMes(Integer anio, Integer mes) {
		// FIXME Auto-generated method stub
		List<Golpes_pendientes_fab> result = null;
		
		ProcedureCall criteria = createStoredProcedureCriteria("spGolpesPendientesFabricar");
		criteria.registerParameter("vAnio", Integer.class, ParameterMode.IN);
		criteria.registerParameter("vMes", Integer.class, ParameterMode.IN);
		criteria.getParameterRegistration("vAnio").bindValue(anio);
		criteria.getParameterRegistration("vMes").bindValue(mes);
		Output output = criteria.getOutputs().getCurrent();
		if (output.isResultSet()) {
			result = ((ResultSetOutput)output).getResultList();
        }
		
		return result;
	}

}
