package com.websystique.springmvc.dao.reportes;

import java.util.List;

import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.reportes.Media_pedidos_cte;


@Repository("media_pedidos_cteDAO")
public class Media_pedidos_cteDAOImpl extends AbstractDao<Integer,Media_pedidos_cte> implements Media_pedidos_cteDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<Media_pedidos_cte> findbyFlag() {
		// FIXME Auto-generated method stub
		List<Media_pedidos_cte> result = null;
		ProcedureCall criteria = createStoredProcedureCriteria("spMediaPedidosCte");
		Output output = criteria.getOutputs().getCurrent();
		if (output.isResultSet()) {
			result = ((ResultSetOutput)output).getResultList();
        }
		
		return result;
	}

}
