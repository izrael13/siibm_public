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
import com.websystique.springmvc.model.reportes.Desempenio_anual_vendedor;

public class ExcelDesempenio_anual_vendedor extends AbstractXlsView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
            Workbook workbook,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		// FIXME Auto-generated method stub
		response.setHeader("Content-Disposition", "attachment; filename=\"desempenioAnualXvendedor.xls\"");
		
		@SuppressWarnings("unchecked")
		List<Desempenio_anual_vendedor> listaexcel = (List<Desempenio_anual_vendedor>) model.get("listaexcel");
		
		DecimalFormat df = new DecimalFormat("####################");
		
		Sheet sheet = workbook.createSheet("Detail");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Vendedor");
        header.createCell(1).setCellValue("Año 1");
        header.createCell(2).setCellValue("Porcentaje 1");
        header.createCell(3).setCellValue("Año 2");
        header.createCell(4).setCellValue("Porcentaje 2");
        header.createCell(5).setCellValue("Año 3");
        header.createCell(6).setCellValue("Porcentaje 3");
        header.createCell(7).setCellValue("Año 4");
        header.createCell(8).setCellValue("Porcentaje 4");
        header.createCell(9).setCellValue("Año 5");
        header.createCell(10).setCellValue("Porcentaje 5");
        
        
        for(int i = 0; i < listaexcel.size(); i++)
        {
        	Row dataRow =  sheet.createRow(i+1);
        	dataRow.createCell(0).setCellValue(listaexcel.get(i).getId() == 1 ? listaexcel.get(i).getSlpname().substring(2, 7) : listaexcel.get(i).getSlpname());
        	dataRow.createCell(1).setCellValue(df.format(listaexcel.get(i).getAnio1()));
        	dataRow.createCell(2).setCellValue(df.format(listaexcel.get(i).getPorcen1()));
        	dataRow.createCell(3).setCellValue(df.format(listaexcel.get(i).getAnio2()));
        	dataRow.createCell(4).setCellValue(df.format(listaexcel.get(i).getPorcen2()));
        	dataRow.createCell(5).setCellValue(df.format(listaexcel.get(i).getAnio3()));
        	dataRow.createCell(6).setCellValue(df.format(listaexcel.get(i).getPorcen3()));
        	dataRow.createCell(7).setCellValue(df.format(listaexcel.get(i).getAnio4()));
        	dataRow.createCell(8).setCellValue(df.format(listaexcel.get(i).getPorcen4()));
        	dataRow.createCell(9).setCellValue(df.format(listaexcel.get(i).getAnio5()));
        	dataRow.createCell(10).setCellValue(df.format(listaexcel.get(i).getPorcen5()));
        }
		
	}

}
