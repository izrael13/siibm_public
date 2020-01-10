package com.websystique.springmvc.dao.programas;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.programas.Programas_reg_barca;

@Repository("programas_reg_barcaDAO")
public class Programas_reg_barcaDAOImpl extends AbstractDao<Integer, Programas_reg_barca> implements Programas_reg_barcaDAO{

	@Override
	public Programas_reg_barca BuscarxId(Integer id) {
		return getByKey(id);
	}

	@Override
	public List<Programas_reg_barca> BuscarxParams(List<ParamsGeneral> Params, Map<String, String> mOrd) {
		return criteriaGeneralList(Params, mOrd);
	}

	@Override
	public Programas_reg_barca BuscarxParams(List<ParamsGeneral> Params) {
		return (Programas_reg_barca) criteriaGeneralObj(Params);
	}

	@Override
	public void Guardar(Programas_reg_barca Programa) {
		persist(Programa);
	}

	@Override
	public void Actualizar(Programas_reg_barca Programa) {
		update(Programa);
	}

	@Override
	public void Eliminar(Programas_reg_barca Programa) {
		delete(Programa);
	}

}
