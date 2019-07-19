package com.websystique.springmvc.dao.reportes;

import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.reportes.Desempenio_mensual_xcliente;

@Repository("desempenio_mensual_xclienteDAO")
public class Desempenio_mensual_xclienteDAOImpl extends AbstractDao<Integer, Desempenio_mensual_xcliente> implements Desempenio_mensual_xclienteDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<Desempenio_mensual_xcliente> Buscar(Integer Anio, String CardCode, Integer SlpCode) {
		// TODO Auto-generated method stub
		List<Desempenio_mensual_xcliente> results = null;
		ProcedureCall criteria = createStoredProcedureCriteria("spDesempenioPorCliente");
		criteria.registerParameter("pAnio", Integer.class, ParameterMode.IN);
		criteria.getParameterRegistration("pAnio").bindValue(Anio);
		
		criteria.registerParameter("pCardCode", String.class, ParameterMode.IN);
		criteria.getParameterRegistration("pCardCode").bindValue(CardCode);
		
		criteria.registerParameter("pSlpCode", Integer.class, ParameterMode.IN);
		criteria.getParameterRegistration("pSlpCode").bindValue(SlpCode);
	
		Output output = criteria.getOutputs().getCurrent();
		if (output.isResultSet()) {
			results = ((ResultSetOutput)output).getResultList();
        }
		return results;
	}

}
