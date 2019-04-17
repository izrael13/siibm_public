package com.websystique.springmvc.dao.reportes;

import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.reportes.Todos_pedidos;

@Repository("todos_pedidosDAO")
public class Todos_pedidosDAOImpl extends AbstractDao<Integer,Todos_pedidos> implements Todos_pedidosDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<Todos_pedidos> findPedidosByAnio(Integer anio) {
		List<Todos_pedidos> result = null;
		ProcedureCall criteria = createStoredProcedureCriteria("spTodosPedidos");
		criteria.registerParameter("pAnio", Integer.class, ParameterMode.IN);
		criteria.getParameterRegistration("pAnio").bindValue(anio);
		Output output = criteria.getOutputs().getCurrent();
		if (output.isResultSet()) {
			result = ((ResultSetOutput)output).getResultList();
        }
		
		return result;
	}
	
}
