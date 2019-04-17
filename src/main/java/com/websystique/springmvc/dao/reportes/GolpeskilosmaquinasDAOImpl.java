package com.websystique.springmvc.dao.reportes;

import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.reportes.Golpeskilosmaquinas;

@SuppressWarnings("all")
@Repository("GolpeskilosmaquinasDAO")
public class GolpeskilosmaquinasDAOImpl extends AbstractDao<Integer, Golpeskilosmaquinas> implements GolpeskilosmaquinasDAO {

	@Override
	public List<Golpeskilosmaquinas> findAllRegistros() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Golpeskilosmaquinas> findAllRegistrosByMes(Integer anio, Integer mes) {
		// TODO Auto-generated method stub
		List<Golpeskilosmaquinas> ls = null;
		ProcedureCall criteria = createStoredProcedureCriteria("spGolpesKilosMaquinas");
		criteria.registerParameter("vAnio", Integer.class, ParameterMode.IN);
		criteria.registerParameter("vMes", Integer.class, ParameterMode.IN);
		criteria.getParameterRegistration("vAnio").bindValue(anio);
		criteria.getParameterRegistration("vMes").bindValue(mes);
		Output output = criteria.getOutputs().getCurrent();
		if (output.isResultSet()) {
			ls = ((ResultSetOutput)output).getResultList();
        }
		return ls;
	}
	
}
