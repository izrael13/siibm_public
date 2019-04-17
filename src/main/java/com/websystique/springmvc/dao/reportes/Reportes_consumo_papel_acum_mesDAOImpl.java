package com.websystique.springmvc.dao.reportes;

import java.util.List;

import javax.jws.WebParam.Mode;
import javax.persistence.ParameterMode;

import org.hibernate.procedure.ParameterRegistration;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.springframework.asm.Type;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.reportes.Reportes_consumo_papel_acum_mes;

@SuppressWarnings("all")
@Repository("reportes_consumo_papel_acum_mesDAO")
public class Reportes_consumo_papel_acum_mesDAOImpl extends AbstractDao<Integer, Reportes_consumo_papel_acum_mes> implements Reportes_consumo_papel_acum_mesDAO{

	@Override
	public List<Reportes_consumo_papel_acum_mes> findAllRegistros() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reportes_consumo_papel_acum_mes> findAllRegistrosByMes(Integer anio, Integer mes) {
		// TODO Auto-generated method stub
		List<Reportes_consumo_papel_acum_mes> results = null;
		ProcedureCall criteria = createStoredProcedureCriteria("spGenerarConsPapelAcumMes");
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
