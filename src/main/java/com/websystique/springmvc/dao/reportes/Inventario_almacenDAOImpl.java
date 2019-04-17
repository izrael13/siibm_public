package com.websystique.springmvc.dao.reportes;

import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.reportes.Inventario_almacen;


@Repository("inventario_almacenDAO")
public class Inventario_almacenDAOImpl extends AbstractDao<Integer, Inventario_almacen> implements Inventario_almacenDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<Inventario_almacen> findByAlmacen(String almacen) {
		List<Inventario_almacen> results = null;
		ProcedureCall criteria = createStoredProcedureCriteria("spInventarioAlmacenes");
		criteria.registerParameter("pCveAlmacen", String.class, ParameterMode.IN);
		criteria.getParameterRegistration("pCveAlmacen").bindValue(almacen);
		Output output = criteria.getOutputs().getCurrent();
		if (output.isResultSet()) {
			results = ((ResultSetOutput)output).getResultList();
        }
		return results;
	}
	
}
