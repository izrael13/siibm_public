package com.websystique.springmvc.excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.websystique.springmvc.model.reportes.ConversionDiaria;

public class ConversionDiariaExcel extends AbstractXlsView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
            Workbook workbook,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		
		response.setHeader("Content-Disposition", "attachment; filename=\"conversion_diaria.xls\"");
		String fecha_ini = request.getParameter("fecha_ini");
		
		@SuppressWarnings("unchecked")
		List<ConversionDiaria> listaexcel = (List<ConversionDiaria>) model.get("listaexcel");
		
		Sheet sheet = workbook.createSheet("Detail");
		
		Row header1 = sheet.createRow(0);
		header1.createCell(0).setCellValue("Conversion Diaria " + fecha_ini);
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,9));
		
        Row header2 = sheet.createRow(1);
        header2.createCell(0).setCellValue("ID");
        header2.createCell(1).setCellValue("Color");
        header2.createCell(2).setCellValue("Tipo Color");
        header2.createCell(3).setCellValue("Arrastre");
        
        header2.createCell(0).setCellValue("Pedido");
        header2.createCell(1).setCellValue("Programadas");
        header2.createCell(2).setCellValue("Corrugadas");
        header2.createCell(3).setCellValue("Inicio Setup");
        header2.createCell(4).setCellValue("Termino Setup");
        header2.createCell(5).setCellValue("Inicio Conversion");
        header2.createCell(6).setCellValue("Fin Conversion");
        header2.createCell(7).setCellValue("Piezas Contadas");
        header2.createCell(8).setCellValue("Piezas Buenas");
        header2.createCell(9).setCellValue("Lamina Pendiente");
        header2.createCell(10).setCellValue("Malas Setup");
        header2.createCell(11).setCellValue("Malas Proceso");
        header2.createCell(12).setCellValue("Laminas Por Procesar");
        header2.createCell(13).setCellValue("Work Center ID");
        header2.createCell(14).setCellValue("Number Out");
		
        for(int i = 0; i < listaexcel.size(); i++)
        {
        	Row dataRow =  sheet.createRow(i+2);
       	
        	dataRow.createCell(0).setCellValue(listaexcel.get(i).getPedido());
        	dataRow.createCell(0).setCellValue(listaexcel.get(i).getProgramadas());
        	dataRow.createCell(0).setCellValue(listaexcel.get(i).getCorrugadas());
        	dataRow.createCell(0).setCellValue(listaexcel.get(i).getInicioSetup());
        	dataRow.createCell(0).setCellValue(listaexcel.get(i).getTerminoSetup());
        	dataRow.createCell(0).setCellValue(listaexcel.get(i).getInicioConversion());
        	dataRow.createCell(0).setCellValue(listaexcel.get(i).getFinConversion());
        	dataRow.createCell(0).setCellValue(listaexcel.get(i).getPiezasContadas());
        	dataRow.createCell(0).setCellValue(listaexcel.get(i).getPiezasBuenas());
        	dataRow.createCell(0).setCellValue(listaexcel.get(i).getLaminaPendiente());
        	dataRow.createCell(0).setCellValue(listaexcel.get(i).getMalasSetup());
        	dataRow.createCell(0).setCellValue(listaexcel.get(i).getMalasProceso());
        	dataRow.createCell(0).setCellValue(listaexcel.get(i).getLaminasPorProcesar());
        	dataRow.createCell(0).setCellValue(listaexcel.get(i).getWorkCenterID());
        	dataRow.createCell(0).setCellValue(listaexcel.get(i).getNumberOut());
        }
	}

}
