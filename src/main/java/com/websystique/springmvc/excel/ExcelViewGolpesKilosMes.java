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

import com.websystique.springmvc.model.reportes.Golpeskilosmaquinas;

public class ExcelViewGolpesKilosMes extends AbstractXlsView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		response.setHeader("Content-Disposition", "attachment; filename=\"golpes_kilos_maquina_mes.xls\"");
		
		@SuppressWarnings("unchecked")
		List<Golpeskilosmaquinas> reporte = (List<Golpeskilosmaquinas>) model.get("listaexcel");
		
		Double flk = 0.00;
		Double flg = 0.00;
		Double fltk = 0.00;
		Double fltg = 0.00;
		Double trk = 0.00;
		Double trg = 0.00;
		Double otk = 0.00;
		Double otg = 0.00;
		int i = 0;
		
		Sheet sheet = workbook.createSheet("Detail");
        Row header1 = sheet.createRow(0);
        
        header1.createCell(0).setCellValue("Año");
        header1.createCell(1).setCellValue("Mes");
        header1.createCell(2).setCellValue("Dia");
        sheet.addMergedRegion(new CellRangeAddress(
                0, //first row (0-based)
                0, //last row  (0-based)
                3, //first column (0-based)
                4  //last column  (0-based)
        ));
        sheet.addMergedRegion(new CellRangeAddress(0,0,5,6));
        sheet.addMergedRegion(new CellRangeAddress(0,0,7,8));
        sheet.addMergedRegion(new CellRangeAddress(0,0,9,10));
        header1.createCell(3).setCellValue("Flexografica");
        header1.createCell(5).setCellValue("Flexotroqueladora");
        header1.createCell(7).setCellValue("Troqueladoras");
        header1.createCell(9).setCellValue("Otros");
        
        Row header2 = sheet.createRow(1);
        header2.createCell(3).setCellValue("Kilos");
        header2.createCell(4).setCellValue("Golpes");
        header2.createCell(5).setCellValue("Kilos");
        header2.createCell(6).setCellValue("Golpes");
        header2.createCell(7).setCellValue("Kilos");
        header2.createCell(8).setCellValue("Golpes");
        header2.createCell(9).setCellValue("Kilos");
        header2.createCell(10).setCellValue("Golpes");
        
        for(i = 0; i < reporte.size(); i++)
        {
        	Row dataRow =  sheet.createRow(i+2);
        	dataRow.createCell(0).setCellValue(reporte.get(i).getAnio());
        	dataRow.createCell(1).setCellValue(reporte.get(i).getMes());
        	dataRow.createCell(2).setCellValue(reporte.get(i).getDia());
        	dataRow.createCell(3).setCellValue(reporte.get(i).getFlexografica_kilos());
        	dataRow.createCell(4).setCellValue(reporte.get(i).getFlexografica_golpes());
        	dataRow.createCell(5).setCellValue(reporte.get(i).getFlexotroqueladora_kilos());
        	dataRow.createCell(6).setCellValue(reporte.get(i).getFlexotroqueladora_golpes());
        	dataRow.createCell(7).setCellValue(reporte.get(i).getTroqueladora_kilos());
        	dataRow.createCell(8).setCellValue(reporte.get(i).getTroqueladora_golpes());
        	dataRow.createCell(9).setCellValue(reporte.get(i).getOtros_kilos());
        	dataRow.createCell(10).setCellValue(reporte.get(i).getOtros_golpes());
        	
        	flk = flk + reporte.get(i).getFlexografica_kilos();
        	flg = flg + reporte.get(i).getFlexografica_golpes();
        	fltk = fltk + reporte.get(i).getFlexotroqueladora_kilos();
        	fltg = fltg + reporte.get(i).getFlexotroqueladora_golpes();
        	trk = trk + reporte.get(i).getTroqueladora_kilos();
        	trg = trg + reporte.get(i).getTroqueladora_golpes();
        	otk = otk + reporte.get(i).getOtros_kilos();
        	otg = otg + reporte.get(i).getOtros_golpes();
        	
        }
        Row dataRow =  sheet.createRow(i+2);
        sheet.addMergedRegion(new CellRangeAddress(i+2,i+2,0,2));
        dataRow.createCell(0).setCellValue("Total");
        dataRow.createCell(3).setCellValue(flk);
        dataRow.createCell(4).setCellValue(flg);
        dataRow.createCell(5).setCellValue(fltk);
        dataRow.createCell(6).setCellValue(fltg);
        dataRow.createCell(7).setCellValue(trk);
        dataRow.createCell(8).setCellValue(trg);
        dataRow.createCell(9).setCellValue(otk);
        dataRow.createCell(10).setCellValue(otg);
        
	}

}
