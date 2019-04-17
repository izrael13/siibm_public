package com.websystique.springmvc.dao.reportes;

import java.util.List;

import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.reportes.Cobranza_acum;

@Repository("cobranza_acumDAO")
public class Cobranza_acumDAOImpl extends AbstractDao<Integer, Cobranza_acum> implements Cobranza_acumDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<Cobranza_acum> findByIntervalo() {
		// FIXME Auto-generated method stub
		List<Cobranza_acum> result = null;
		ProcedureCall criteria = createStoredProcedureCriteria("spCobranzadetalle_intervalo");
		Output output = criteria.getOutputs().getCurrent();
		if (output.isResultSet()) {
			result = ((ResultSetOutput)output).getResultList();
        }
		
		return result;
	}

}
