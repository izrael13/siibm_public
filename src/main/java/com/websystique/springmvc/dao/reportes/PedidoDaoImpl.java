package com.websystique.springmvc.dao.reportes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.ParameterMode;

import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.reportes.Pedido;
import com.websystique.springmvc.model.reportes.PedidoPk;

@Repository("PedidoDao")
public class PedidoDaoImpl extends AbstractDao<PedidoPk, Pedido> implements PedidoDao{

	@SuppressWarnings("unchecked")
	public List<Pedido> findPedido(int pId) {
		List<Pedido> pedidos = null;
		
		Integer id = Integer.valueOf(pId);
		ProcedureCall sp = createStoredProcedureCriteria("spPedidosConRetraso");
		sp.registerParameter("pId", Integer.class, ParameterMode.IN);
		sp.getParameterRegistration("pId").bindValue(id);
		
		Output salida = sp.getOutputs().getCurrent();
		
		if (salida.isResultSet()) {
			pedidos = ((ResultSetOutput)salida).getResultList();
		}
		return pedidos;
	}
	
	
	@Override
	public List<Pedido> findAll() {
		List<Pedido> pedidos = null;
		
		pedidos = creaPedidos(); 
					
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		mOrd.put("1", "id");
		mOrd.put("2", "cliente");

		pedidos = criteriaGeneralList(Params, mOrd);
		
		return pedidos;
	}

	@Override
	public Pedido findByPk(PedidoPk pPk) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Pedido> creaPedidos() {
		Pedido ped = new Pedido();
		ped.setId(116798);
		ped.setCliente("DISTRIBUIDORA E IMPORTADORA ALSEA SA DE CV");
		ped.setSimbolo("CAJA CORRUGADA 14\" KRAFT PAQ/50 (MONTERREY) MAYO2019");
		ped.setFecha("01/10/2019");
		ped.setKilos("6910.99");
		ped.setGolpes("10.40");
		ped.setMaquina("TROQUELADORA-3");
				
		List<Pedido> pedidos;
		pedidos = new ArrayList<Pedido>();
		pedidos.add(ped);
        return pedidos;
	}
	
	
	
	
}
