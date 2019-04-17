package com.websystique.springmvc.service.reportes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.reportes.Media_pedidos_cteDAO;
import com.websystique.springmvc.model.reportes.Media_pedidos_cte;

@Service("media_pedidos_cteService")
@Transactional
public class Media_pedidos_cteServiceImpl implements Media_pedidos_cteService{
	
	@Autowired
	Media_pedidos_cteDAO dao;
	
	@Override
	public List<Media_pedidos_cte> findbyFlag() {
		// FIXME Auto-generated method stub
		return dao.findbyFlag();
	}

}
