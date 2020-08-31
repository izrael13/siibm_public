package com.websystique.springmvc.dao.reportes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.reportes.Desempenio_anual_vendedor;


@Repository("desempenio_anual_vendedorDAO")
public class Desempenio_anual_vendedorDAOImpl extends AbstractDao<Integer,Desempenio_anual_vendedor> implements Desempenio_anual_vendedorDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<Desempenio_anual_vendedor>BuscarxAnios(Integer Anio, Integer SlpCode) {
		List<Desempenio_anual_vendedor> results = new ArrayList<Desempenio_anual_vendedor>();
		ProcedureCall criteria = createStoredProcedureCriteria("spDesempenio_anual_vendedor");
		criteria.registerParameter("pAnio", Integer.class, ParameterMode.IN);
		criteria.getParameterRegistration("pAnio").bindValue(Anio);
		
		criteria.registerParameter("pSlpCode", Integer.class, ParameterMode.IN);
		criteria.getParameterRegistration("pSlpCode").bindValue(SlpCode);
	
		Output output = criteria.getOutputs().getCurrent();
		if (output.isResultSet()) {
			results = ((ResultSetOutput)output).getResultList();
        }
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> DesempenioComparativos(String AnioAnt, String AnioAct, Integer CteVen) {
		List<Object> results = null;
		ProcedureCall criteria = createStoredProcedureCriteriaStr("spDesempenioComparativo");
		
		criteria.registerParameter("pAnioAnt", String.class, ParameterMode.IN);
		criteria.getParameterRegistration("pAnioAnt").bindValue(AnioAnt);
		
		criteria.registerParameter("pAnioAct", String.class, ParameterMode.IN);
		criteria.getParameterRegistration("pAnioAct").bindValue(AnioAct);
		
		criteria.registerParameter("pCveVen", Integer.class, ParameterMode.IN);
		criteria.getParameterRegistration("pCveVen").bindValue(CteVen);
		
		Output output = criteria.getOutputs().getCurrent();
		if (output.isResultSet()) {
			results = ((ResultSetOutput)output).getResultList();
        }
		return results;
	}
	
	

}
