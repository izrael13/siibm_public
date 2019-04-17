package com.websystique.springmvc.service;

import java.util.Date;

import com.websystique.springmvc.model.Solicitudes_reset_pass;

public interface Solicitudes_reset_passService {
	Solicitudes_reset_pass findByFechaCad(Date fc);
	Solicitudes_reset_pass findByFechaReg(Date fr);
	Solicitudes_reset_pass findByFechaCad(String sso);
	Solicitudes_reset_pass findByFechaCadSoo(Date fc,String sso);
	Solicitudes_reset_pass findByFechaRegSoo(Date fr,String sso);
	Solicitudes_reset_pass findByAll(String sso,String email,Date fr,Date fc);
	void save(Solicitudes_reset_pass s);
}
