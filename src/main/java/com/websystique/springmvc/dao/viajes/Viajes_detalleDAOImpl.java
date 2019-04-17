package com.websystique.springmvc.dao.viajes;

import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.viajes.Viajes_detalle;


@SuppressWarnings("all")
@Repository("viajes_detalleDAO")
public class Viajes_detalleDAOImpl extends AbstractDao<Integer,Viajes_detalle> implements Viajes_detalleDAO{

	@Override
	public List<Viajes_detalle> findDetalleByViaje(String num_viaje) {
		// FIXME Auto-generated method stub
		List<Viajes_detalle> result = null;
		ProcedureCall criteria = createStoredProcedureCriteria("spViajesEmbarques");
		
		criteria.registerParameter("pBan", Integer.class, ParameterMode.IN);
		criteria.registerParameter("pNumViaje", String.class, ParameterMode.IN);
		criteria.getParameterRegistration("pBan").bindValue(2);
		criteria.getParameterRegistration("pNumViaje").bindValue(num_viaje);
		
		criteria.registerParameter("pDemoras", Integer.class, ParameterMode.IN);
		criteria.registerParameter("pDevoluciones", Integer.class, ParameterMode.IN);
		
		criteria.getParameterRegistration("pDemoras").bindValue(0);
		criteria.getParameterRegistration("pDevoluciones").bindValue(0);
		
		criteria.registerParameter("pCboEdo", Integer.class, ParameterMode.IN);
		//criteria.registerParameter("pManiobras", Integer.class, ParameterMode.IN);
		criteria.getParameterRegistration("pCboEdo").bindValue(0);
		//criteria.getParameterRegistration("pManiobras").bindValue(0);
		
		Output output = criteria.getOutputs().getCurrent();
		if (output.isResultSet()) {
			result = ((ResultSetOutput)output).getResultList();
        }
		
		return result;
	}
	
}
