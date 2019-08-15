package com.websystique.springmvc.dao.ventas;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ventas.Pronosticoscte;

@SuppressWarnings("all")
@Repository("pronosticoscteDAO")
public class PronosticoscteDAOImpl extends AbstractDao<Integer,Pronosticoscte> implements PronosticoscteDAO{

	@Override
	public void Guardar(Integer idven, String idcte, String anio, String mes, Double pron, Integer accion, Integer code) {
		// TODO Auto-generated method stub
		ProcedureCall criteria = createStoredProcedureCriteriaStr("spSubirPronosticosQV");
		criteria.registerParameter("pCveVen", Integer.class, ParameterMode.IN);
		criteria.registerParameter("pCveCte", String.class, ParameterMode.IN);
		criteria.registerParameter("pAnio", String.class, ParameterMode.IN);
		criteria.registerParameter("pMes", String.class, ParameterMode.IN);
		criteria.registerParameter("pPron", Double.class, ParameterMode.IN);
		criteria.registerParameter("pAccion", Integer.class, ParameterMode.IN);
		criteria.registerParameter("pCode", Integer.class, ParameterMode.IN);
		criteria.registerParameter("pBanCte", Integer.class, ParameterMode.IN);
		
		criteria.getParameterRegistration("pCveVen").bindValue(idven);
		criteria.getParameterRegistration("pCveCte").bindValue(idcte);
		criteria.getParameterRegistration("pAnio").bindValue(anio);
		criteria.getParameterRegistration("pMes").bindValue(mes);
		criteria.getParameterRegistration("pPron").bindValue(pron);
		criteria.getParameterRegistration("pAccion").bindValue(accion);
		criteria.getParameterRegistration("pCode").bindValue(code);
		criteria.getParameterRegistration("pBanCte").bindValue(0);
		
		Output output = criteria.getOutputs().getCurrent();
		
		if (output.isResultSet()) {
			String result = (String) ((ResultSetOutput)output).getSingleResult();
	}

	/*@Override
	public List<Pronosticoscte> readFile(List<Pronosticoscte> list) {
		// FIXME Auto-generated method stub
		persist(list);
		return null;
	} */
	}

	@Override
	public List<Pronosticoscte> Lista(String anio, String mes, Integer cveven, String cvecte, Integer cteban, Integer opcion) {
		// TODO Auto-generated method stub
		List<Pronosticoscte> Lista = new ArrayList<Pronosticoscte>();
		ProcedureCall criteria = createStoredProcedureCriteria("spSubirPronosticosQV");
		criteria.registerParameter("pCveVen", Integer.class, ParameterMode.IN);
		criteria.registerParameter("pCveCte", String.class, ParameterMode.IN);
		criteria.registerParameter("pAnio", String.class, ParameterMode.IN);
		criteria.registerParameter("pMes", String.class, ParameterMode.IN);
		criteria.registerParameter("pPron", Double.class, ParameterMode.IN);
		criteria.registerParameter("pAccion", Integer.class, ParameterMode.IN);
		criteria.registerParameter("pCode", Integer.class, ParameterMode.IN);
		criteria.registerParameter("pBanCte", Integer.class, ParameterMode.IN);
		
		
		criteria.getParameterRegistration("pCveVen").bindValue(cveven);
		criteria.getParameterRegistration("pCveCte").bindValue(cvecte);
		criteria.getParameterRegistration("pAnio").bindValue(anio);
		criteria.getParameterRegistration("pMes").bindValue(mes);
		criteria.getParameterRegistration("pPron").bindValue(0.0);
		criteria.getParameterRegistration("pAccion").bindValue(opcion);
		criteria.getParameterRegistration("pCode").bindValue(0);
		criteria.getParameterRegistration("pBanCte").bindValue(cteban);
		
		Output output = criteria.getOutputs().getCurrent();
		
		if (output.isResultSet()) {
			Lista =  ((ResultSetOutput)output).getResultList();
		}
		return Lista;
	}
}
