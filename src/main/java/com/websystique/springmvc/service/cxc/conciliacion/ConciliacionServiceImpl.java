package com.websystique.springmvc.service.cxc.conciliacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.cxc.conciliacion.ConciliacionDAO;

@Service("conciliacionService")
@Transactional
public class ConciliacionServiceImpl implements ConciliacionService{

	@Autowired
	ConciliacionDAO dao;
	
	@Override
	public void GrabarArchivo(String cfdi, String fecha_Fac, String fecha_conci, Double importe) {
		dao.GrabarArchivo(cfdi, fecha_Fac, fecha_conci, importe);
	}

}
