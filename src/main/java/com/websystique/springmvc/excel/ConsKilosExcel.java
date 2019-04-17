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

import com.websystique.springmvc.model.reportes.ConsumoKilos;

public class ConsKilosExcel extends AbstractXlsView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
            Workbook workbook,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		
		response.setHeader("Content-Disposition", "attachment; filename=\"consumo_kilos.xls\"");
		String fecha_ini = request.getParameter("fecha_ini");
		String fecha_fin = request.getParameter("fecha_fin");
		
		@SuppressWarnings("unchecked")
		List<ConsumoKilos> listaexcel = (List<ConsumoKilos>) model.get("listaexcel");
		
		Sheet sheet = workbook.createSheet("Detail");
		
		Row header1 = sheet.createRow(0);
		header1.createCell(0).setCellValue("Consumo en kilos " + fecha_ini + " - " + fecha_fin);
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,9));
		
        Row header2 = sheet.createRow(1);
        header2.createCell(0).setCellValue("Tipo - Ancho");
        header2.createCell(1).setCellValue("Tipo");
        header2.createCell(2).setCellValue("Ancho");
        header2.createCell(3).setCellValue("Inventario inicial");
        header2.createCell(4).setCellValue("Entradas");
        header2.createCell(5).setCellValue("Metros lineales");
        header2.createCell(6).setCellValue("Programado");
        header2.createCell(7).setCellValue("Salidas");
        header2.createCell(8).setCellValue("Diferencia");
        header2.createCell(9).setCellValue("Inventario final");
		
        for(int i = 0; i < listaexcel.size(); i++)
        {
        	Row dataRow =  sheet.createRow(i+2);
        	dataRow.createCell(0).setCellValue(listaexcel.get(i).getTipo_papel());
        	dataRow.createCell(1).setCellValue(listaexcel.get(i).getTipo());
        	dataRow.createCell(2).setCellValue(listaexcel.get(i).getAncho());
        	dataRow.createCell(3).setCellValue(listaexcel.get(i).getInv_ini());
        	dataRow.createCell(4).setCellValue(listaexcel.get(i).getEntradas());
        	dataRow.createCell(5).setCellValue(listaexcel.get(i).getMtrslineales());
        	dataRow.createCell(6).setCellValue(listaexcel.get(i).getProgramado());
        	dataRow.createCell(7).setCellValue(listaexcel.get(i).getSalidas());
        	dataRow.createCell(8).setCellValue(listaexcel.get(i).getDiferencia());
        	dataRow.createCell(9).setCellValue(listaexcel.get(i).getInv_fin());
        }
	}

}
