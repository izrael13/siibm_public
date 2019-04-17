package com.websystique.springmvc.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.Solicitudes_reset_passDAO;
import com.websystique.springmvc.model.Solicitudes_reset_pass;

@Service("solicitudes_reset_passService")
@Transactional
public class Solicitudes_reset_passServiceImpl implements Solicitudes_reset_passService{
	
	@Autowired
	Solicitudes_reset_passDAO dao;
	@Override
	public Solicitudes_reset_pass findByFechaCad(Date fc) {
		// FIXME Auto-generated method stub
		return null;
	}

	@Override
	public Solicitudes_reset_pass findByFechaReg(Date fr) {
		// FIXME Auto-generated method stub
		return null;
	}

	@Override
	public Solicitudes_reset_pass findByFechaCad(String sso) {
		// FIXME Auto-generated method stub
		return null;
	}

	@Override
	public Solicitudes_reset_pass findByFechaCadSoo(Date fc, String sso) {
		// FIXME Auto-generated method stub
		return dao.findByFechaCadSoo(fc, sso);
	}

	@Override
	public Solicitudes_reset_pass findByFechaRegSoo(Date fr, String sso) {
		// FIXME Auto-generated method stub
		return null;
	}

	@Override
	public void save(Solicitudes_reset_pass s) {
		// FIXME Auto-generated method stub
		dao.save(s);
	}

	@Override
	public Solicitudes_reset_pass findByAll(String sso, String email, Date fr, Date fc) {
		// FIXME Auto-generated method stub
		return dao.findByAll(sso, email, fr, fc);
	}

}
