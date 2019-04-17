package com.websystique.springmvc.dao.ventas;

import java.util.ArrayList;
import java.util.Map;

import javax.persistence.ParameterMode;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ventas.Pronosticoscte;
import com.websystique.springmvc.model.viajes.Viajes_embarques;

@SuppressWarnings("all")
@Repository("warningspronDAO")
public class WarningspronDAOImpl extends AbstractDao<Integer,Pronosticoscte> implements WarningspronDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public ArrayList map() {
		ArrayList map = (ArrayList) getSession().createNativeQuery("select ROW_NUMBER() OVER(ORDER BY CVE_CLIENTE) n,CVE_CLIENTE from PRONOSTICOS_TEMP where PRONOSTICO > 0 and CVE_CLIENTE != '' group by CVE_CLIENTE having count(0) > 1").getResultList();
		return map;
	}

	@Override
	public void truncateTable() {
		// FIXME Auto-generated method stub
		getSession().createSQLQuery("truncate table PRONOSTICOS_TEMP").executeUpdate();
	}

	@Override
	public String subirQV(Integer mes, Integer anio) {
		// FIXME Auto-generated method stub
		String result = "";
		ProcedureCall criteria = createStoredProcedureCriteriaStr("spSubirPronosticosQV");
		criteria.registerParameter("pMes", Integer.class, ParameterMode.IN);
		criteria.registerParameter("pAnio", Integer.class, ParameterMode.IN);
		criteria.getParameterRegistration("pMes").bindValue(mes);
		criteria.getParameterRegistration("pAnio").bindValue(anio);
		
		Output output = criteria.getOutputs().getCurrent();
		
		if (output.isResultSet()) {
			result = (String) ((ResultSetOutput)output).getSingleResult();
        }
		
		return result;
	}
	
}
