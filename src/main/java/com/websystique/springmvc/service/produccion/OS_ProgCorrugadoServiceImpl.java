package com.websystique.springmvc.service.produccion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.produccion.OS_ProgCorrugadoDAO;
import com.websystique.springmvc.model.produccion.OS_ProgCorrugado;

@Service("OS_ProgCorrugadoService")
@Transactional
public class OS_ProgCorrugadoServiceImpl implements OS_ProgCorrugadoService {

	@Autowired OS_ProgCorrugadoDAO ospro_dao;
	
	@Override
	public List<OS_ProgCorrugado> OS_ProgCorrugado(String Programa) {
		return ospro_dao.OS_ProgCorrugado(Programa);
	}

}
