package com.websystique.springmvc.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.websystique.springmvc.model.tarjetas.Catalogo_maquinas_sap_vw;
import com.websystique.springmvc.service.tarjetas.Catalogo_maquinas_sap_vwService;

@Component
public class RutaToCatalogoMaquinasSapCoverter implements Converter<Object, Catalogo_maquinas_sap_vw>{
	
	@Autowired
	Catalogo_maquinas_sap_vwService cms;
	
	@Override
	public Catalogo_maquinas_sap_vw convert(Object element) {
		Integer id = Integer.parseInt((String)element);
		Catalogo_maquinas_sap_vw maquina = cms.BuscarxId(id);
		return maquina;
	}

}
