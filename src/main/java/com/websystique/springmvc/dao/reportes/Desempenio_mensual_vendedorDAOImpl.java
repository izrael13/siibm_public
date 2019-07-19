package com.websystique.springmvc.dao.reportes;

import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.reportes.Desempenio_mensual_vendedor;

@Repository("desempenio_mensual_vendedorDAO")
public class Desempenio_mensual_vendedorDAOImpl extends AbstractDao<Integer,Desempenio_mensual_vendedor> implements Desempenio_mensual_vendedorDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<Desempenio_mensual_vendedor> BuscarxAnio(Integer Anio) {
		// TODO Auto-generated method stub
		List<Desempenio_mensual_vendedor> results = null;
		ProcedureCall criteria = createStoredProcedureCriteria("spDesempenio_mensual_vendedor");
		criteria.registerParameter("pAnio", Integer.class, ParameterMode.IN);
		criteria.getParameterRegistration("pAnio").bindValue(Anio);
	
		Output output = criteria.getOutputs().getCurrent();
		if (output.isResultSet()) {
			results = ((ResultSetOutput)output).getResultList();
        }
		return results;
	}

}
