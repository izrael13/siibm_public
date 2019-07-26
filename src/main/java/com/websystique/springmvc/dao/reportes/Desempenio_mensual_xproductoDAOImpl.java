package com.websystique.springmvc.dao.reportes;

import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.reportes.Desempenio_mensual_xproducto;

@Repository("desempenio_mensual_xproductoDAO")
public class Desempenio_mensual_xproductoDAOImpl extends AbstractDao<Integer, Desempenio_mensual_xproducto> implements Desempenio_mensual_xproductoDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<Desempenio_mensual_xproducto> Buscar(Integer anio, Integer slpcode, Integer xcte, Integer xitem) {
		List<Desempenio_mensual_xproducto> results = null;
		
		ProcedureCall criteria = createStoredProcedureCriteria("spDesempenioxproducto");
		criteria.registerParameter("pAnio", Integer.class, ParameterMode.IN);
		criteria.getParameterRegistration("pAnio").bindValue(anio);
		
		criteria.registerParameter("pSlpcode", Integer.class, ParameterMode.IN);
		criteria.getParameterRegistration("pSlpcode").bindValue(slpcode);
		
		criteria.registerParameter("pxcte", Integer.class, ParameterMode.IN);
		criteria.getParameterRegistration("pxcte").bindValue(xcte);
		
		criteria.registerParameter("pxitem", Integer.class, ParameterMode.IN);
		criteria.getParameterRegistration("pxitem").bindValue(xitem);	
		
		Output output = criteria.getOutputs().getCurrent();
		if (output.isResultSet()) {
			results = ((ResultSetOutput)output).getResultList();
        }
		
		return results;
	}



}
