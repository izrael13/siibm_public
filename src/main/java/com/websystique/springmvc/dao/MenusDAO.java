package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.Menus;

public interface MenusDAO {
	List<Menus> ListaMenusxpadre(Integer padre);
	List<Menus> ListaMenusxpadre();
}
