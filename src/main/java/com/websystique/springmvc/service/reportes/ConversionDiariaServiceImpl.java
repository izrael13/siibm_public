package com.websystique.springmvc.service.reportes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.reportes.ConversionDiariaDAO;
import com.websystique.springmvc.dao.reportes.EntradaAlmacenDAO;
import com.websystique.springmvc.model.reportes.ConversionDiaria;
import com.websystique.springmvc.model.reportes.EntradaAlmacen;
import com.websystique.springmvc.utilities.DateUtils;

@Service("conversionDiariaService")
@Transactional
public class ConversionDiariaServiceImpl implements ConversionDiariaService{
	
	@Autowired
	EntradaAlmacenDAO  entradaAlmacenDAO;
	@Autowired
	ConversionDiariaDAO conversionDiariaDAO;

	
	@Override
	public List<ConversionDiaria> getAllByDate(String fecha_ini) {
	    String fechaIni = (fecha_ini + " 07:00:00").replace("-","/");
        String fechaFin = (DateUtils.addDayToDate(fecha_ini,"yyyy-MM-dd",1)  + " 07:00:00").replace("-", "/");
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
