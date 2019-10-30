package com.websystique.springmvc.dao.costos.controlpesomerma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.costos.controlpesomerma.Control_peso_merma;

@Repository("control_peso_mermaDAO")
public class Control_peso_mermaDAOImpl extends AbstractDao<Integer, Control_peso_merma> implements Control_peso_mermaDAO{

	@Override
	public List<Control_peso_merma> ListaControlPeso() {
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		mOrd.put("100", "fecha_registro");
		
		List<Control_peso_merma> ListaControl = criteriaGeneralList(Params, mOrd);
		return ListaControl;
	}

	@Override
	public Control_peso_merma BuscarxId(Integer Id) {
		return getByKey(Id);
	}

	@Override
	public void Guardar(Control_peso_merma cpm) {
		save_entity(cpm);		
	}

	@Override
	public List<Control_peso_merma> ListaControlPeso(List<ParamsGeneral> Params) {
		Map<String,String> mOrd =  new HashMap<String, String>();
		mOrd.put("100", "fecha_registro");
		
		List<Control_peso_merma> ListaControl = criteriaGeneralList(Params, mOrd);
		return ListaControl;
	}

}
