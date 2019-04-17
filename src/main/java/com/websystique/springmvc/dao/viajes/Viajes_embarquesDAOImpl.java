package com.websystique.springmvc.dao.viajes;

import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.viajes.Viajes_embarques;

@SuppressWarnings("all")
@Repository("viajes_embarquesDAO")
public class Viajes_embarquesDAOImpl extends AbstractDao<Integer,Viajes_embarques> implements Viajes_embarquesDAO{

	@Override
	public List<Viajes_embarques> findAllViajesByUserSap(Integer userSap) {
		
		List<Viajes_embarques> result = null;
		ProcedureCall criteria = createStoredProcedureCriteria("spViajesEmbarques");
		
		criteria.registerParameter("pBan", Integer.class, ParameterMode.IN);
		criteria.registerParameter("pNumViaje", String.class, ParameterMode.IN);
		criteria.getParameterRegistration("pBan").bindValue(1);
		criteria.getParameterRegistration("pNumViaje").bindValue("0");
		
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

	@Override
	public List<Viajes_embarques> updateViajes(String nviaje, Integer demora, Integer devolucion, Integer cbo_estado) {
		// FIXME Auto-generated method stub
		List<Viajes_embarques> result = null;
		ProcedureCall criteria = createStoredProcedureCriteriaStr("spViajesEmbarques");
		
		criteria.registerParameter("pBan", Integer.class, ParameterMode.IN);
		criteria.registerParameter("pNumViaje", String.class, ParameterMode.IN);
		criteria.getParameterRegistration("pBan").bindValue(3);
		criteria.getParameterRegistration("pNumViaje").bindValue(nviaje);
		
		criteria.registerParameter("pDemoras", Integer.class, ParameterMode.IN);
		criteria.registerParameter("pDevoluciones", Integer.class, ParameterMode.IN);
		
		criteria.getParameterRegistration("pDemoras").bindValue(demora);
		criteria.getParameterRegistration("pDevoluciones").bindValue(devolucion);
		
		criteria.registerParameter("pCboEdo", Integer.class, ParameterMode.IN);
		//criteria.registerParameter("pManiobras", Integer.class, ParameterMode.IN);
		criteria.getParameterRegistration("pCboEdo").bindValue(cbo_estado);
		//criteria.getParameterRegistration("pManiobras").bindValue(maniobras);
		
		Output output = criteria.getOutputs().getCurrent();
		
		if (output.isResultSet()) {
			result = ((ResultSetOutput)output).getResultList();
        }
		return result;
	}

	@Override
	public String updateAutEmbarques(String nviaje) {
		// FIXME Auto-generated method stub
		String result = "";
		ProcedureCall criteria = createStoredProcedureCriteriaStr("spViajesEmbarques");
		
		criteria.registerParameter("pBan", Integer.class, ParameterMode.IN);
		criteria.registerParameter("pNumViaje", String.class, ParameterMode.IN);
		criteria.getParameterRegistration("pBan").bindValue(4);
		criteria.getParameterRegistration("pNumViaje").bindValue(nviaje);
		
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
			result = ((ResultSetOutput)output).getSingleResult().toString();
        }
		return result;
	}

	@Override
	public String updateAutLogistica(String nviaje) {
		// FIXME Auto-generated method stub
		String result = "";
		ProcedureCall criteria = createStoredProcedureCriteriaStr("spViajesEmbarques");
		
		criteria.registerParameter("pBan", Integer.class, ParameterMode.IN);
		criteria.registerParameter("pNumViaje", String.class, ParameterMode.IN);
		criteria.getParameterRegistration("pBan").bindValue(5);
		criteria.getParameterRegistration("pNumViaje").bindValue(nviaje);
		
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
			result = ((ResultSetOutput)output).getSingleResult().toString();
        }
		return result;
	}

	@Override
	public List<Viajes_embarques> findAllViajesByUserSapLog(Integer userSap) {
		// FIXME Auto-generated method stub
		List<Viajes_embarques> result = null;
		ProcedureCall criteria = createStoredProcedureCriteria("spViajesEmbarques");
		
		criteria.registerParameter("pBan", Integer.class, ParameterMode.IN);
		criteria.registerParameter("pNumViaje", String.class, ParameterMode.IN);
		criteria.getParameterRegistration("pBan").bindValue(6);
		criteria.getParameterRegistration("pNumViaje").bindValue("0");
		
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

	@Override
	public String updateRegAemb(String nviaje) {
		// FIXME Auto-generated method stub
		String result = "";
		ProcedureCall criteria = createStoredProcedureCriteriaStr("spViajesEmbarques");
		
		criteria.registerParameter("pBan", Integer.class, ParameterMode.IN);
		criteria.registerParameter("pNumViaje", String.class, ParameterMode.IN);
		criteria.getParameterRegistration("pBan").bindValue(7);
		criteria.getParameterRegistration("pNumViaje").bindValue(nviaje);
		
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
			result = ((ResultSetOutput)output).getSingleResult().toString();
        }
		return result;
	}

	@Override
	public List<Viajes_embarques> findAllViajesHistorial() {
		// FIXME Auto-generated method stub
		List<Viajes_embarques> result = null;
		ProcedureCall criteria = createStoredProcedureCriteria("spViajesEmbarques");
		
		criteria.registerParameter("pBan", Integer.class, ParameterMode.IN);
		criteria.registerParameter("pNumViaje", String.class, ParameterMode.IN);
		criteria.getParameterRegistration("pBan").bindValue(8);
		criteria.getParameterRegistration("pNumViaje").bindValue("0");
		
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
