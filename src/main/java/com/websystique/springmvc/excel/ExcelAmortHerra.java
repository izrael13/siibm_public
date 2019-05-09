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
		response.setHeader("Content-Disposition", "attachment; filename=\"amorizacionherramentales.xls\"");
		
		@SuppressWarnings("unchecked")
		List<Amortiza_herramentales> listaexcel = (List<Amortiza_herramentales>) model.get("listaexcel");
		
		Sheet sheet = workbook.createSheet("Detail");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Herramental");
        header.createCell(1).setCellValue("Vendedor");
        header.createCell(2).setCellValue("Cliente");
        header.createCell(3).setCellValue("Grabados/Suajes nombre");
        header.createCell(4).setCellValue("Grabados y/o Suajes");
        header.createCell(5).setCellValue("Fecha recepción");
        header.createCell(6).setCellValue("Total Facturado");
        header.createCell(7).setCellValue("Total Nota de Crédito");
        header.createCell(8).setCellValue("Total (Facturado - NC");
        header.createCell(9).setCellValue("Total Herramental");
        header.createCell(10).setCellValue("Amortizado");
        header.createCell(11).setCellValue("Porcentaje");
                
        for(int i = 0; i < listaexcel.size(); i++)
        {
        	Row dataRow =  sheet.createRow(i+1);
        	dataRow.createCell(0).setCellValue(listaexcel.get(i).getHerramental());
        	dataRow.createCell(1).setCellValue(listaexcel.get(i).getVendedor());
        	dataRow.createCell(2).setCellValue(listaexcel.get(i).getClientes());
        	dataRow.createCell(3).setCellValue(listaexcel.get(i).getGrab_suaj_nom());
        	dataRow.createCell(4).setCellValue(listaexcel.get(i).getGrabados_suajes());
        	dataRow.createCell(5).setCellValue(listaexcel.get(i).getFecha_recepcion());
        	dataRow.createCell(6).setCellValue(listaexcel.get(i).getTotalFacturado());
        	dataRow.createCell(7).setCellValue(listaexcel.get(i).getTotalNotaCredito());
        	dataRow.createCell(8).setCellValue(listaexcel.get(i).getTOTAL());
        	dataRow.createCell(9).setCellValue(listaexcel.get(i).getTotalHerramental());
        	dataRow.createCell(10).setCellValue(listaexcel.get(i).getAMORTIZADO());
        	dataRow.createCell(11).setCellValue(listaexcel.get(i).getPORCENTAJE());
        }
		
	}

}
