package com.websystique.springmvc.dao.reportes;
import java.util.List;

import javax.persistence.ParameterMode;

import com.websystique.springmvc.model.reportes.Embarque_diario_detalle;

import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.springframework.stereotype.Repository;
import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.dao.reportes.Embarque_diario_detalleDAO;

@Repository("embarque_diario_detalleDAO")
public class Embarque_diario_detalleDAOImpl extends AbstractDao<Integer,Embarque_diario_detalle> implements Embarque_diario_detalleDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<Embarque_diario_detalle> Lista(String fecha, Integer SlpCode) {
		List<Embarque_diario_detalle> results = null;
		ProcedureCall criteria = createStoredProcedureCriteria("spDetalleEmbarqueDiario");
		
		criteria.registerParameter("pFecha", String.class, ParameterMode.IN);
		criteria.getParameterRegistration("pFecha").bindValue(fecha);
		
		criteria.registerParameter("pSlpCode", Integer.class, ParameterMode.IN);
		criteria.getParameterRegistration("pSlpCode").bindValue(SlpCode);
		
		Output output = criteria.getOutputs().getCurrent();
		if (output.isResultSet()) {
			results = ((ResultSetOutput)output).getResultList();
        }
		return results;
	}

}
