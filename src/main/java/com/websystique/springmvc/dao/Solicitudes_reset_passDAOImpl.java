package com.websystique.springmvc.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.Solicitudes_reset_pass;

@Repository("solicitudes_reset_passDAO")
public class Solicitudes_reset_passDAOImpl extends AbstractDao<Integer,Solicitudes_reset_pass> implements Solicitudes_reset_passDAO{

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
		Map<String,Date> mResD =  new HashMap<String, Date>();
		mResD.put("fecha_caducidad", fc);
		
		Map<String,String> mResS =  new HashMap<String, String>();
		mResS.put("sso_id", sso);
		
		Solicitudes_reset_pass sol = null;
		sol = (Solicitudes_reset_pass) criteriaQueryStrDate(mResS,mResD);
		
		return sol;
	}

	@Override
	public Solicitudes_reset_pass findByFechaRegSoo(Date fr, String sso) {
		// FIXME Auto-generated method stub
		return null;
	}

	@Override
	public void save(Solicitudes_reset_pass s) {
		persist(s);
	}

	@Override
	public Solicitudes_reset_pass findByAll(String sso, String email, Date fr, Date fc) {
		// FIXME Auto-generated method stub
		Map<String,Date> mResD =  new HashMap<String, Date>();
		mResD.put("fecha_caducidad", fc);
		mResD.put("fecha_sol", fr);
		
		Map<String,String> mResS =  new HashMap<String, String>();
		mResS.put("sso_id", sso);
		mResS.put("email", email);
		
		Solicitudes_reset_pass sol = null;
		sol = (Solicitudes_reset_pass) criteriaQueryStrDate(mResS,mResD);
		
		return sol;
	}

}
