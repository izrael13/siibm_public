package com.websystique.springmvc.dao.reportes;

import java.util.List;

import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.reportes.Cobranza_detalle;

@Repository("cobranza_detalleDAO")
public class Cobranza_detalleDAOImpl extends AbstractDao<Integer,Cobranza_detalle> implements Cobranza_detalleDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<Cobranza_detalle> findByCteVen() {
		List<Cobranza_detalle> result = null;
		ProcedureCall criteria = createStoredProcedureCriteria("spCobranzadetalle_cte_ven");
		//criteria.registerParameter("pcteven", Integer.class, ParameterMode.IN);
		//criteria.getParameterRegistration("pcteven").bindValue(cveven);
		Output output = criteria.getOutputs().getCurrent();
		if (output.isResultSet()) {
			result = ((ResultSetOutput)output).getResultList();
        }
		return result;
	}

}
