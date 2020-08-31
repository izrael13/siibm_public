package com.websystique.springmvc.dao.certificados;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.certificados.Liberacioncm;

@Repository("liberacioncmDAO")
public class LiberacioncmDAOImpl extends AbstractDao<Integer, Liberacioncm> implements LiberacioncmDAO{

	@Override
	public Liberacioncm BuscarxId(String itemcode) {
		List<ParamsGeneral> ParamsR = new ArrayList<ParamsGeneral>();
		ParamsR.add(new ParamsGeneral(1,"itemcode_lcm",itemcode,"EQ"));
		return (Liberacioncm) criteriaGeneralObj(ParamsR);
	}

	@Override
	public void Guardar(Liberacioncm lcm) {
		persist(lcm);
	}

	@Override
	public void Actualizar(Liberacioncm lcm) {
		update(lcm);
	}

	@Override
	public void Eliminar(Liberacioncm lcm) {
		delete(lcm);
	}

	@Override
	public Liberacioncm BuscarxId(Integer id) {
		return getByKey(id);
	}

}
