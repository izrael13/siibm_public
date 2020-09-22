package com.websystique.springmvc.service.reportes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.reportes.ConversionDiariaDAO;
import com.websystique.springmvc.dao.reportes.EntradaAlmacenDAO;
import com.websystique.springmvc.model.reportes.ConversionDiaria;
import com.websystique.springmvc.model.reportes.EntradaAlmacen;

@Service("conversionDiariaService")
@Transactional
public class ConversionDiariaServiceImpl implements ConversionDiariaService{
	
	@Autowired
	EntradaAlmacenDAO  entradaAlmacenDAO;
	@Autowired
	ConversionDiariaDAO conversionDiariaDAO;

	
	@Override
	public List<ConversionDiaria> getAllByDate(String fechaIni, String fechaFin) {
	   
        return conversionDiariaDAO.findByDateRange(fechaIni, fechaFin);
	}
	
	@Override
	public List<ConversionDiaria> getAllByNumeroPedido(String pedidos){
	    return conversionDiariaDAO.findByPedidos(pedidos);
	}
	
	@Override
	public List<EntradaAlmacen> getAllEntradaAlmacen(String pedidos){
	    return entradaAlmacenDAO.findAllEntradaAlmacen(pedidos);
	} 
	
	
}
