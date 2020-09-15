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
	ConversionDiariaDAO dao;
    
	
	
	@Override
	public List<ConversionDiaria> findByAll(String fecha_ini) {
		return addDataToReport(fecha_ini);
	}
	
	private List<ConversionDiaria> addDataToReport(String fecha_ini){
	    String fechaIni = (fecha_ini + " 07:00:00").replace("-","/");
        String fechaFin = (DateUtils.addDayToDate(fecha_ini,"yyyy-MM-dd",1)  + " 07:00:00").replace("-", "/");
        
	    List<ConversionDiaria> conversionDiaria = dao.findByAll(fechaIni, fechaFin);
	    if (conversionDiaria.size() > 0) {
	        List<EntradaAlmacen> listEntradaAlmacen = entradaAlmacenDAO.findAllEntradaAlmacen(getListaDePedidos(conversionDiaria));
	        if (listEntradaAlmacen.size() > 0) {
	           for (int i=0; i < listEntradaAlmacen.size(); i++) {
	               EntradaAlmacen entradaAlmacen = new EntradaAlmacen();
	               entradaAlmacen = listEntradaAlmacen.get(i);
	               System.out.println(entradaAlmacen.getNumeroPedido() + " " + entradaAlmacen.getFechaEntAlm() + " " + entradaAlmacen.getHoraEntrega());
	           }
	        }
	    }
	    return conversionDiaria;
	}
	
	private String getListaDePedidos(List<ConversionDiaria> listaPedidos) {
	    String pedidos = "";
	    for (int i=0; i < listaPedidos.size(); i++) {
	        if (!pedidos.contains(listaPedidos.get(i).getPedido())){
	            pedidos = pedidos + listaPedidos.get(i).getPedido();
	            if (i< (listaPedidos.size() - 1)) {
	                pedidos = pedidos + ",";
	            }
	        }
	        
	    }
	    return pedidos;
	}
	
	
}
