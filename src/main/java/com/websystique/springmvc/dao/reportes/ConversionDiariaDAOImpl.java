package com.websystique.springmvc.dao.reportes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.reportes.ConversionDiaria;
import com.websystique.springmvc.utilities.DateUtils;

@SuppressWarnings("all")
@Repository("conversionDiariaDAO")
public class ConversionDiariaDAOImpl extends AbstractDao<Integer,ConversionDiaria>implements ConversionDiariaDAO{

	@Override
	public List<ConversionDiaria> findByDateRange(String fechaIni, String fechaFin) {
		List<ConversionDiaria> result = null;
        	

        ProcedureCall criteria = createStoredProcedureCriteria("spReporteConversionDiaria");
		criteria.registerParameter("pFechaIni", String.class, ParameterMode.IN);
		criteria.getParameterRegistration("pFechaIni").bindValue(fechaIni);
		criteria.registerParameter("pFechaFin", String.class, ParameterMode.IN);
        criteria.getParameterRegistration("pFechaFin").bindValue(fechaFin);
		
		Output output = criteria.getOutputs().getCurrent();
		if (output.isResultSet()) {
			result = ((ResultSetOutput)output).getResultList();
        }
		
		return result;
	}

    @Override
    public List<ConversionDiaria> findByPedidos(String listaPedidos) {
        List<ConversionDiaria> result = null;
        ProcedureCall criteria = createStoredProcedureCriteria("spReporteConversionDiaria3");
        criteria.registerParameter("pListaPedidos", String.class, ParameterMode.IN);
        criteria.getParameterRegistration("pListaPedidos").bindValue(listaPedidos);
        
        Output output = criteria.getOutputs().getCurrent();
        if (output.isResultSet()) {
            result = ((ResultSetOutput)output).getResultList();
        }
        
        return result;
    }
	
	
}
