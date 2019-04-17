package com.websystique.springmvc.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

public class CustomFilter extends GenericFilterBean
{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// FIXME Auto-generated method stub
		
		HttpServletRequest req = (HttpServletRequest) request;
		req.getSession().setMaxInactiveInterval(10);//10 minutos

		chain.doFilter(request, response);
	}
	
}
