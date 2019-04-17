package com.websystique.springmvc.dao.reportes;

import java.util.Date;
import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.reportes.ConsumoKilos;

@SuppressWarnings("all")
@Repository("consumoKilosDAO")
public class ConsumoKilosDAOImpl extends AbstractDao<Integer,ConsumoKilos>implements ConsumoKilosDAO{

	@Override
	public List<ConsumoKilos> findByAll(String fecha_ini, String fecha_fin, Integer per_ini, Integer per_fin) {
		// FIXME Auto-generated method stub
		List<ConsumoKilos> result = null;
		
		ProcedureCall criteria = createStoredProcedureCriteria("spResporteConsumoKilos");
		
		criteria.registerParameter("pFechaIni", String.class, ParameterMode.IN);
		criteria.getParameterRegistration("pFechaIni").bindValue(fecha_ini);
		
		criteria.registerParameter("pFechaFin", String.class, ParameterMode.IN);
		criteria.getParameterRegistration("pFechaFin").bindValue(fecha_fin);
		
		criteria.registerParameter("pProgramaIni", Integer.class, ParameterMode.IN);
		criteria.getParameterRegistration("pProgramaIni").bindValue(per_ini);
		
		criteria.registerParameter("pProgramaFin", Integer.class, ParameterMode.IN);
		criteria.getParameterRegistration("pProgramaFin").bindValue(per_fin);
		
		Output output = criteria.getOutputs().getCurrent();
		if (output.isResultSet()) {
			result = ((ResultSetOutput)output).getResultList();
        }
		
		return result;
	}

}
