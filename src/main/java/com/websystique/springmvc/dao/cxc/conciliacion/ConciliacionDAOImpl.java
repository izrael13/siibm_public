package com.websystique.springmvc.dao.cxc.conciliacion;

import javax.persistence.ParameterMode;

import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;

@Repository("conciliacionDAO")
public class ConciliacionDAOImpl extends AbstractDao<Integer, Integer> implements ConciliacionDAO{

	@SuppressWarnings("unchecked")
	@Override
	public void GrabarArchivo(String cfdi, String fecha_Fac, String fecha_conci, Double importe) {
		
		ProcedureCall criteria = createStoredProcedureCriteriaStr("spAlseaConciliacion");
		criteria.registerParameter("pFechaFac", String.class, ParameterMode.IN);
		criteria.registerParameter("pFechaConci", String.class, ParameterMode.IN);
		criteria.registerParameter("pImporte", Double.class, ParameterMode.IN);
		criteria.registerParameter("pCFDI", String.class, ParameterMode.IN);
		
		criteria.getParameterRegistration("pFechaFac").bindValue(fecha_Fac);
		criteria.getParameterRegistration("pFechaConci").bindValue(fecha_conci);
		criteria.getParameterRegistration("pImporte").bindValue(importe);
		criteria.getParameterRegistration("pCFDI").bindValue(cfdi);
		
		Output output = criteria.getOutputs().getCurrent();
		if (output.isResultSet()) {
			((ResultSetOutput)output).getSingleResult();
        }
	}

}
