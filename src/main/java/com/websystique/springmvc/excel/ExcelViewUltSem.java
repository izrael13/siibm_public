package com.websystique.springmvc.excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.websystique.springmvc.model.reportes.Reportes_consumo_papel_utl_sem;

public class ExcelViewUltSem extends AbstractXlsView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		 // change the file name
        response.setHeader("Content-Disposition", "attachment; filename=\"ultima_semana.xls\"");
        
        @SuppressWarnings("unchecked")
        List<Reportes_consumo_papel_utl_sem> reporte = (List<Reportes_consumo_papel_utl_sem>) model.get("reporte");
		
        Sheet sheet = workbook.createSheet("Detail");
        Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("año");
		header.createCell(1).setCellValue("semana");
		header.createCell(2).setCellValue("ancho");
		header.createCell(3).setCellValue("tipo papel");
		header.createCell(4).setCellValue("Promedio 12 semanas");
		header.createCell(5).setCellValue("Inventario inicial");
		header.createCell(6).setCellValue("Programados semana actual");
		header.createCell(7).setCellValue("Diferencia");
		int numrow = 1;
		
		
		 for(int i = 0; i < reporte.size(); i ++)
		{
			 Row dataRow =  sheet.createRow(numrow);
			 dataRow.createCell(0).setCellValue(reporte.get(i).getAnio());
				dataRow.createCell(1).setCellValue(reporte.get(i).getSemana());
				dataRow.createCell(2).setCellValue(reporte.get(i).getAncho());
				dataRow.createCell(3).setCellValue(reporte.get(i).getTipo_papel());
				dataRow.createCell(4).setCellValue(reporte.get(i).getProm_utl_sem());
				dataRow.createCell(5).setCellValue(reporte.get(i).getInventario_inicial());
				dataRow.createCell(6).setCellValue(reporte.get(i).getProm_act_sem());
				dataRow.createCell(7).setCellValue(reporte.get(i).getDiferencia());
				numrow ++;
		}
		
		
	}

}
