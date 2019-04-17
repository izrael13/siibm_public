package com.websystique.springmvc.excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.websystique.springmvc.model.reportes.Media_pedidos_cte;


public class MediaPedidosCte extends AbstractXlsView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		 // change the file name
        response.setHeader("Content-Disposition", "attachment; filename=\"Media_pedidos_cte.xls\"");
        
        @SuppressWarnings("unchecked")
        List<Media_pedidos_cte> listaexcel = (List<Media_pedidos_cte>) model.get("listaexcel");
		
        Sheet sheet = workbook.createSheet("Detail");
        Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("Cliente");
		header.createCell(1).setCellValue("Nombre Cliente");
		header.createCell(2).setCellValue("[2017-01-01/2017-12-31]");
		header.createCell(3).setCellValue("[2018-01-01/2018-07-31]");
		header.createCell(4).setCellValue("[2018-08-01/2019-04-30]");
		header.createCell(5).setCellValue("[2019-05-01/2019-12-31]");
		
		for(int i = 0; i < listaexcel.size(); i++)
        {
        	Row dataRow =  sheet.createRow(i+1);
        	dataRow.createCell(0).setCellValue(listaexcel.get(i).getCardcode());
        	dataRow.createCell(1).setCellValue(listaexcel.get(i).getCardname());
        	dataRow.createCell(2).setCellValue(listaexcel.get(i).getPeriodo1() == null ? 0 : listaexcel.get(i).getPeriodo1());
        	dataRow.createCell(3).setCellValue(listaexcel.get(i).getPeriodo2() == null ? 0 : listaexcel.get(i).getPeriodo2());
        	dataRow.createCell(4).setCellValue(listaexcel.get(i).getPeriodo3() == null ? 0 : listaexcel.get(i).getPeriodo3());
        	dataRow.createCell(5).setCellValue(listaexcel.get(i).getPeriodo4() == null ? 0 : listaexcel.get(i).getPeriodo4());
        }
		
	}

}
