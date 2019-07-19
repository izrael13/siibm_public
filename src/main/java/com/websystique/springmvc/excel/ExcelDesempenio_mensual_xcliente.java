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

import com.websystique.springmvc.model.reportes.Desempenio_mensual_xcliente;

public class ExcelDesempenio_mensual_xcliente extends AbstractXlsView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
            Workbook workbook,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		response.setHeader("Content-Disposition", "attachment; filename=\"desempenioMensualXcliente.xls\"");
		
		@SuppressWarnings("unchecked")
		List<Desempenio_mensual_xcliente> listaexcel = (List<Desempenio_mensual_xcliente>) model.get("listaexcel");
		
DecimalFormat df = new DecimalFormat("####################");
		
		Sheet sheet = workbook.createSheet("Detail");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Cliente");
        header.createCell(1).setCellValue("Vendedor");
        header.createCell(2).setCellValue("Enero");
        header.createCell(3).setCellValue("Febrero");
        header.createCell(4).setCellValue("Marzo");
        header.createCell(5).setCellValue("Abril");
        header.createCell(6).setCellValue("Mayo");
        header.createCell(7).setCellValue("Junio");
        header.createCell(8).setCellValue("Julio");
        header.createCell(9).setCellValue("Agosto");
        header.createCell(10).setCellValue("Septiembre");
        header.createCell(11).setCellValue("Octubre");
        header.createCell(12).setCellValue("Noviembre");
        header.createCell(13).setCellValue("Diciembre");
        header.createCell(14).setCellValue("Total");
        
        for(int i = 0; i < listaexcel.size(); i++)
        {
        	Row dataRow =  sheet.createRow(i+1);
        	dataRow.createCell(0).setCellValue(listaexcel.get(i).getId() == 1 ? listaexcel.get(i).getCardname().substring(2, 7) : listaexcel.get(i).getCardname());
        	dataRow.createCell(1).setCellValue(listaexcel.get(i).getSlpname());
        	dataRow.createCell(2).setCellValue(df.format(listaexcel.get(i).getEnero()));
        	dataRow.createCell(3).setCellValue(df.format(listaexcel.get(i).getFebrero()));
        	dataRow.createCell(4).setCellValue(df.format(listaexcel.get(i).getMarzo()));
        	dataRow.createCell(5).setCellValue(df.format(listaexcel.get(i).getAbril()));
        	dataRow.createCell(6).setCellValue(df.format(listaexcel.get(i).getMayo()));
        	dataRow.createCell(7).setCellValue(df.format(listaexcel.get(i).getJunio()));
        	dataRow.createCell(8).setCellValue(df.format(listaexcel.get(i).getJulio()));
        	dataRow.createCell(9).setCellValue(df.format(listaexcel.get(i).getAgosto()));
        	dataRow.createCell(10).setCellValue(df.format(listaexcel.get(i).getSeptiembre()));
        	dataRow.createCell(11).setCellValue(df.format(listaexcel.get(i).getOctubre()));
        	dataRow.createCell(12).setCellValue(df.format(listaexcel.get(i).getNoviembre()));
        	dataRow.createCell(13).setCellValue(df.format(listaexcel.get(i).getDiciembre()));
        	dataRow.createCell(14).setCellValue(df.format(listaexcel.get(i).getTotaltenanio()));
        }
	}

}
