package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Menus;

public interface MenusService {
	List<Menus> ListaMenusxpadre(Integer padre);
	List<Menus> ListaMenusxpadre();
}
