package com.websystique.springmvc.dao.reportes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.reportes.EntradaAlmacen;

@SuppressWarnings("all")
@Repository("entradaAlmacenDAO")
public class EntradaAlmacenDAOImpl extends AbstractDao<Integer,EntradaAlmacen>implements EntradaAlmacenDAO{


    @Override
    public List<EntradaAlmacen> findAllEntradaAlmacen(String listaPedidos) {
        List<EntradaAlmacen> result = new ArrayList<EntradaAlmacen>();
        ProcedureCall criteria = null;
        criteria = createStoredProcedureCriteria("spReporteConversionDiaria2");
        criteria.registerParameter("plistaPedidos", String.class, ParameterMode.IN);
        criteria.getParameterRegistration("plistaPedidos").bindValue(listaPedidos);
        Output output = criteria.getOutputs().getCurrent();
        if (output.isResultSet()) {
            result = ((ResultSetOutput)output).getResultList();
        }
        
        return result;
    }
    
    
	
}
