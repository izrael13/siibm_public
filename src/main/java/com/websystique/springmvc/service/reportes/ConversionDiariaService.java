package com.websystique.springmvc.service.reportes;

import java.util.List;

import com.websystique.springmvc.model.reportes.ConversionDiaria;
import com.websystique.springmvc.model.reportes.EntradaAlmacen;

public interface ConversionDiariaService {
	List<ConversionDiaria> getAllByDate(String fechaIni, String fecha_Fin);
	List<ConversionDiaria> getAllByNumeroPedido(String pedidos);
	List<EntradaAlmacen> getAllEntradaAlmacen(String pedidos); 
}
