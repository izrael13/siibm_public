package com.websystique.springmvc.dao.reportes;

import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.reportes.Golpes_maquina_mes;

@SuppressWarnings("all")
@Repository("Golpes_maquina_mesDAO")
public class Golpes_maquina_mesDAOImpl extends AbstractDao<Integer, Golpes_maquina_mes> implements Golpes_maquina_mesDAO {

	@Override
	public List<Golpes_maquina_mes> findAllRegistros() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Golpes_maquina_mes> findAllRegistrosByMes(Integer anio, Integer mes) {
		// TODO Auto-generated method stub
		List<Golpes_maquina_mes> results = null;
		ProcedureCall criteria = createStoredProcedureCriteria("spTroqueladoraProd");
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
