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

import com.websystique.springmvc.model.reportes.Golpes_pendientes_fab;

public class ExcelGolpesPend2 extends AbstractXlsView{

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
        sheet.addMergedRegion(new CellRangeAddress(0,2,0,0));
        header.createCell(1).setCellValue("Fabricados");
        sheet.addMergedRegion(new CellRangeAddress(0,0,1,6));
        header.createCell(7).setCellValue("Entregados");
        sheet.addMergedRegion(new CellRangeAddress(0,0,7,12));
        header.createCell(13).setCellValue("Total GOLPES pendientes");
        sheet.addMergedRegion(new CellRangeAddress(0,1,13,15));
        header.createCell(16).setCellValue("Capacidad");
        sheet.addMergedRegion(new CellRangeAddress(0,1,16,17));
        header.createCell(18).setCellValue("Total KILOS pendientes");
        sheet.addMergedRegion(new CellRangeAddress(0,1,18,20));
        
        Row header2 = sheet.createRow(1);
        header2.createCell(1).setCellValue("Golpes");
        sheet.addMergedRegion(new CellRangeAddress(1,1,1,3));
        header2.createCell(4).setCellValue("Kilos");
        sheet.addMergedRegion(new CellRangeAddress(1,1,4,6));
        header2.createCell(7).setCellValue("Golpes");
        sheet.addMergedRegion(new CellRangeAddress(1,1,7,9));
        header2.createCell(10).setCellValue("Kilos");
        sheet.addMergedRegion(new CellRangeAddress(1,1,10,12));
        /*header2.createCell(13).setCellValue("Golpes");
        sheet.addMergedRegion(new CellRangeAddress(1,1,13,15));
        header2.createCell(18).setCellValue("Kilos");
        sheet.addMergedRegion(new CellRangeAddress(1,1,18,20));*/
        
        Row header3 = sheet.createRow(2);
        header3.createCell(1).setCellValue("Flexos");
        header3.createCell(2).setCellValue("Troq");
        header3.createCell(3).setCellValue("Otros");
        header3.createCell(4).setCellValue("Flexos");
        header3.createCell(5).setCellValue("Troq");
        header3.createCell(6).setCellValue("Otros");
        header3.createCell(7).setCellValue("Flexos");
        header3.createCell(8).setCellValue("Troq");
        header3.createCell(9).setCellValue("Otros");
        header3.createCell(10).setCellValue("Flexos");
        header3.createCell(11).setCellValue("Troq");
        header3.createCell(12).setCellValue("Otros");
        header3.createCell(13).setCellValue("Flexos");
        header3.createCell(14).setCellValue("Troq");
        header3.createCell(15).setCellValue("Otros");
        header3.createCell(16).setCellValue("Flexos");
        header3.createCell(17).setCellValue("Troq");
        header3.createCell(18).setCellValue("Fabric.");
        header3.createCell(19).setCellValue("Entrega.");
        header3.createCell(20).setCellValue("Pendient");
        
        for(int i = 0; i < listaexcel.size(); i++)
        {
        	Row dataRow =  sheet.createRow(i+3);
        	dataRow.createCell(0).setCellValue(listaexcel.get(i).getFecha());
        	dataRow.createCell(1).setCellValue(listaexcel.get(i).getFlexosgp());
        	dataRow.createCell(2).setCellValue(listaexcel.get(i).getTroqgp());
        	dataRow.createCell(3).setCellValue(listaexcel.get(i).getOtrosgp());
        	dataRow.createCell(4).setCellValue(listaexcel.get(i).getFlexoskl());
        	dataRow.createCell(5).setCellValue(listaexcel.get(i).getTroqkl());
        	dataRow.createCell(6).setCellValue(listaexcel.get(i).getOtroskl());
        	dataRow.createCell(7).setCellValue(listaexcel.get(i).getFlexosgpalm());
        	dataRow.createCell(8).setCellValue(listaexcel.get(i).getTroqgpalm());
        	dataRow.createCell(9).setCellValue(listaexcel.get(i).getOtrosgpalm());
        	dataRow.createCell(10).setCellValue(listaexcel.get(i).getFlexosklalm());
        	dataRow.createCell(11).setCellValue(listaexcel.get(i).getTroqklalm());
        	dataRow.createCell(12).setCellValue(listaexcel.get(i).getOtrosklalm());
        	dataRow.createCell(13).setCellValue(listaexcel.get(i).getGppenflx());
        	dataRow.createCell(14).setCellValue(listaexcel.get(i).getGppentroq());
        	dataRow.createCell(15).setCellValue(listaexcel.get(i).getGppenotros());
        	dataRow.createCell(16).setCellValue(listaexcel.get(i).getCap_flexos());
        	dataRow.createCell(17).setCellValue(listaexcel.get(i).getCap_troq());
        	dataRow.createCell(18).setCellValue(listaexcel.get(i).getKilos_tkf());
        	dataRow.createCell(19).setCellValue(listaexcel.get(i).getKilos_tke());
        	dataRow.createCell(20).setCellValue(listaexcel.get(i).getKilos_pend());
        }
        
	}

}
