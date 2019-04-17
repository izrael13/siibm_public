package com.websystique.springmvc.excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.websystique.springmvc.model.reportes.Amortiza_herramentales;

public class ExcelAmortHerra extends AbstractXlsView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
            Workbook workbook,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		// FIXME Auto-generated method stub
		response.setHeader("Content-Disposition", "attachment; filename=\"golpes_pendientes.xls\"");
		
		@SuppressWarnings("unchecked")
		List<Amortiza_herramentales> listaexcel = (List<Amortiza_herramentales>) model.get("listaexcel");
		
		Sheet sheet = workbook.createSheet("Detail");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Tarjeta");
        header.createCell(1).setCellValue("Vendedor");
        header.createCell(2).setCellValue("Cliente");
        header.createCell(3).setCellValue("Descripción");
        header.createCell(4).setCellValue("Herramental");
        header.createCell(5).setCellValue("Fecha Recepción");
        header.createCell(6).setCellValue("Total Pedidos");
        header.createCell(7).setCellValue("Facturado");
        header.createCell(8).setCellValue("Costo Herramental");
        header.createCell(9).setCellValue("%Herramental");
        
        for(int i = 0; i < listaexcel.size(); i++)
        {
        	Row dataRow =  sheet.createRow(i+1);
        	dataRow.createCell(0).setCellValue(listaexcel.get(i).getU_tf());
        	dataRow.createCell(1).setCellValue(listaexcel.get(i).getVendedor());
        	dataRow.createCell(2).setCellValue(listaexcel.get(i).getCliente());
        	dataRow.createCell(3).setCellValue(listaexcel.get(i).getDescripcion());
        	dataRow.createCell(4).setCellValue(listaexcel.get(i).getHerramental());
        	dataRow.createCell(5).setCellValue(listaexcel.get(i).getFecha_recep());
        	dataRow.createCell(6).setCellValue(listaexcel.get(i).getTotal_pedidos());
        	dataRow.createCell(7).setCellValue(listaexcel.get(i).getFacturado());
        	dataRow.createCell(8).setCellValue(listaexcel.get(i).getLinetotal());
        	dataRow.createCell(9).setCellValue(listaexcel.get(i).getPorcherra());
        }
		
	}

}
