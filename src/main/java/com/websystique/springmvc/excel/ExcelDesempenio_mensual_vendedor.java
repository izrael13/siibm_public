package com.websystique.springmvc.excel;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.websystique.springmvc.model.reportes.Desempenio_mensual_vendedor;

public class ExcelDesempenio_mensual_vendedor extends AbstractXlsView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
            Workbook workbook,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		// FIXME Auto-generated method stub
		response.setHeader("Content-Disposition", "attachment; filename=\"desempenioMensualXvendedor.xls\"");
		
		@SuppressWarnings("unchecked")
		List<Desempenio_mensual_vendedor> listaexcel = (List<Desempenio_mensual_vendedor>) model.get("listaexcel");
		
		DecimalFormat df = new DecimalFormat("####################");
		
		Sheet sheet = workbook.createSheet("Detail");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Vendedor");
        header.createCell(1).setCellValue("Enero");
        header.createCell(2).setCellValue("Febrero");
        header.createCell(3).setCellValue("Marzo");
        header.createCell(4).setCellValue("Abril");
        header.createCell(5).setCellValue("Mayo");
        header.createCell(6).setCellValue("Junio");
        header.createCell(7).setCellValue("Julio");
        header.createCell(8).setCellValue("Agosto");
        header.createCell(9).setCellValue("Septiembre");
        header.createCell(10).setCellValue("Octubre");
        header.createCell(11).setCellValue("Noviembre");
        header.createCell(12).setCellValue("Diciembre");
        header.createCell(13).setCellValue("Total");
        
        for(int i = 0; i < listaexcel.size(); i++)
        {
        	Row dataRow =  sheet.createRow(i+1);
        	dataRow.createCell(0).setCellValue(listaexcel.get(i).getId() == 1 ? listaexcel.get(i).getSlpname().substring(2, 7) : listaexcel.get(i).getSlpname());
        	dataRow.createCell(1).setCellValue(df.format(listaexcel.get(i).getEnero()));
        	dataRow.createCell(2).setCellValue(df.format(listaexcel.get(i).getFebrero()));
        	dataRow.createCell(3).setCellValue(df.format(listaexcel.get(i).getMarzo()));
        	dataRow.createCell(4).setCellValue(df.format(listaexcel.get(i).getAbril()));
        	dataRow.createCell(5).setCellValue(df.format(listaexcel.get(i).getMayo()));
        	dataRow.createCell(6).setCellValue(df.format(listaexcel.get(i).getJunio()));
        	dataRow.createCell(7).setCellValue(df.format(listaexcel.get(i).getJulio()));
        	dataRow.createCell(8).setCellValue(df.format(listaexcel.get(i).getAgosto()));
        	dataRow.createCell(9).setCellValue(df.format(listaexcel.get(i).getSeptiembre()));
        	dataRow.createCell(10).setCellValue(df.format(listaexcel.get(i).getOctubre()));
        	dataRow.createCell(11).setCellValue(df.format(listaexcel.get(i).getNoviembre()));
        	dataRow.createCell(12).setCellValue(df.format(listaexcel.get(i).getDiciembre()));
        	dataRow.createCell(13).setCellValue(df.format(listaexcel.get(i).getTotaltenanio()));
        }
		
	}

}
