package com.websystique.springmvc.service.reportes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.reportes.Amortiza_herramentalesDAO;
import com.websystique.springmvc.model.reportes.Amortiza_herramentales;

@Service("amortiza_herramentalesService")
@Transactional
public class Amortiza_herramentalesServiceImpl implements Amortiza_herramentalesService{
	
	@Autowired
	Amortiza_herramentalesDAO dao;
	
	@Override
	public List<Amortiza_herramentales> findAmortHerram(Integer select) {
		// FIXME Auto-generated method stub
		return dao.findAmortHerram(select);
	}

}
