package com.websystique.springmvc.service.reportes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.reportes.Cobranza_acumDAO;
import com.websystique.springmvc.model.reportes.Cobranza_acum;

@Service("cobranza_acumService")
@Transactional
public class Cobranza_acumServiceImpl implements Cobranza_acumService{
	
	@Autowired
	Cobranza_acumDAO dao;
	
	@Override
	public List<Cobranza_acum> findByIntervalo() {
		// FIXME Auto-generated method stub
		return dao.findByIntervalo();
	}
	
}
