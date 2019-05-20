package com.websystique.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.MenusDAO;
import com.websystique.springmvc.model.Menus;

@Service("menusService")
@Transactional
public class MenusServiceImpl implements MenusService{

	@Autowired
	MenusDAO dao;
	
	@Override
	public List<Menus> ListaMenusxpadre(Integer padre) {
		// FIXME Auto-generated method stub
		return dao.ListaMenusxpadre(padre);
	}

}
