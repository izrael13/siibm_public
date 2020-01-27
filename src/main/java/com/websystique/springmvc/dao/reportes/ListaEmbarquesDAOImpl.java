package com.websystique.springmvc.dao.reportes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.reportes.ListaEmbarques;

@Repository("listaEmbarquesDAO")
public class ListaEmbarquesDAOImpl extends AbstractDao<Integer, ListaEmbarques> implements ListaEmbarquesDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<ListaEmbarques> ListaEmbarques(String fechaIni, String fechaFin) {
		List<ListaEmbarques> Lista = new ArrayList<ListaEmbarques>();
		ProcedureCall criteria = createStoredProcedureCriteria("spListaEmbarques");
		criteria.registerParameter("pFechaIni", String.class, ParameterMode.IN);
		criteria.getParameterRegistration("pFechaIni").bindValue(fechaIni);
		
		criteria.registerParameter("pFechaFin", String.class, ParameterMode.IN);
		criteria.getParameterRegistration("pFechaFin").bindValue(fechaFin);
		
		Output output = criteria.getOutputs().getCurrent();
		if (output.isResultSet()) {
			Lista = ((ResultSetOutput)output).getResultList();
        }
		
		return Lista;
	}

}
