package com.websystique.springmvc.service.programas;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.programas.Programas_reg_barcaDAO;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.programas.Programas_reg_barca;

@Service("programas_reg_barcaService")
@Transactional
public class Programas_reg_barcaServiceImpl implements Programas_reg_barcaService{

	@Autowired
	Programas_reg_barcaDAO dao;
	
	@Override
	public Programas_reg_barca BuscarxId(Integer id) {
		return dao.BuscarxId(id);
	}

	@Override
	public List<Programas_reg_barca> BuscarxParams(List<ParamsGeneral> Params, Map<String, String> mOrd) {
		return dao.BuscarxParams(Params, mOrd);
	}

	@Override
	public Programas_reg_barca BuscarxParams(List<ParamsGeneral> Params) {
		return dao.BuscarxParams(Params);
	}

	@Override
	public void Guardar(Programas_reg_barca Programa) {
		dao.Guardar(Programa);
	}

	@Override
	public void Actualizar(Programas_reg_barca Programa) {
		dao.Actualizar(Programa);
	}

	@Override
	public void Eliminar(Programas_reg_barca Programa) {
		dao.Eliminar(Programa);
	}

}
