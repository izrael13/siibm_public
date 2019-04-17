package com.websystique.springmvc.service.ventas;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.websystique.springmvc.model.ventas.Pronosticoscte;

public interface PronosticoscteService {
	
	List<Pronosticoscte> readFile(MultipartFile file);
	
}
