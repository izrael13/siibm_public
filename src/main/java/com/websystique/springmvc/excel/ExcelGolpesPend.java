package com.websystique.springmvc.excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.websystique.springmvc.model.reportes.Golpes_pendientes_fab;

public class ExcelGolpesPend extends AbstractXlsView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
							            Workbook workbook,
							            HttpServletRequest request,
							            HttpServletResponse response) throws Exception {
		// FIXME Auto-generated method stub
		response.setHeader("Content-Disposition", "attachment; filename=\"golpes_pendientes.xls\"");
		
		@SuppressWarnings("unchecked")
		List<Golpes_pendientes_fab> listaexcel = (List<Golpes_pendientes_fab>) model.get("listaexcel");
		
		Sheet sheet = workbook.createSheet("Detail");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Fecha");
        header.createCell(1).setCellValue("Kilos captados");
        header.createCell(2).setCellValue("Golpes captados");
        header.createCell(3).setCellValue("Troquel golpes captados");
        header.createCell(4).setCellValue("Flexo kilos captados");
        header.createCell(5).setCellValue("Troquel kilos captados");
        header.createCell(6).setCellValue("Otros kilos captados");
        header.createCell(7).setCellValue("Kilos entregados PT06");
        header.createCell(8).setCellValue("Flexo golpes entregados");
        header.createCell(9).setCellValue("Troquel golpes entregados");
        header.createCell(10).setCellValue("Otros kilos entregados");

        for(int i = 0; i < listaexcel.size(); i++)
        {
        	Row dataRow =  sheet.createRow(i+1);
        	dataRow.createCell(0).setCellValue(listaexcel.get(i).getFecha());
        	dataRow.createCell(1).setCellValue(listaexcel.get(i).getKilos_tkf());
        	dataRow.createCell(2).setCellValue(listaexcel.get(i).getFlexosgp());
        	dataRow.createCell(3).setCellValue(listaexcel.get(i).getTroqgp());
        	dataRow.createCell(4).setCellValue(listaexcel.get(i).getFlexoskl());
        	dataRow.createCell(5).setCellValue(listaexcel.get(i).getTroqkl());
        	dataRow.createCell(6).setCellValue(listaexcel.get(i).getOtroskl());
        	dataRow.createCell(7).setCellValue(listaexcel.get(i).getKilos_tke());
        	dataRow.createCell(8).setCellValue(listaexcel.get(i).getFlexosgpalm());
        	dataRow.createCell(9).setCellValue(listaexcel.get(i).getTroqgpalm());
        	dataRow.createCell(10).setCellValue(listaexcel.get(i).getOtrosklalm());

        }
        
	}

}
