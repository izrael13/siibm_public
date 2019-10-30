package com.websystique.springmvc.service.costos.controlpesomerma;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.costos.controlpesomerma.Control_peso_mermaDAO;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.costos.controlpesomerma.Control_peso_merma;

@Service("control_peso_mermaService")
@Transactional
public class Control_peso_mermaServiceImpl implements Control_peso_mermaService{
	
	@Autowired
	Control_peso_mermaDAO dao;
	
	@Override
	public List<Control_peso_merma> ListaControlPeso() {
		// TODO Auto-generated method stub
		return dao.ListaControlPeso();
	}

	@Override
	public Control_peso_merma BuscarxId(Integer Id) {
		// TODO Auto-generated method stub
		return dao.BuscarxId(Id);
	}

	@Override
	public void Guardar(Control_peso_merma cpm) {
		dao.Guardar(cpm);		
	}

	@Override
	public List<Control_peso_merma> ListaControlPeso(List<ParamsGeneral> Params) {
		// TODO Auto-generated method stub
		return dao.ListaControlPeso(Params);
	}

}
