package com.websystique.springmvc.excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.websystique.springmvc.model.mp.inventario.Conteo_inventario_mp;

public class ExcelInventarioMPConteo extends AbstractXlsView{

	protected void buildExcelDocument(Map<String, Object> model,
            Workbook workbook,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		response.setHeader("Content-Disposition", "attachment; filename=\"InventarioMPConteo.xls\"");
		@SuppressWarnings("unchecked")
		List<Conteo_inventario_mp> ConteoLista = (List<Conteo_inventario_mp>) model.get("listaexcel");
		
		Sheet sheet = workbook.createSheet("Detail");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("N");
        header.createCell(1).setCellValue("Rollo id");
        header.createCell(2).setCellValue("Tipo");
        header.createCell(3).setCellValue("Ancho");
        header.createCell(4).setCellValue("Peso");
        header.createCell(5).setCellValue("Ubicacion");
        header.createCell(6).setCellValue("Conteo");
		
        for(int i = 0; i < ConteoLista.size(); i++)
        {
        	Row dataRow =  sheet.createRow(i+1);
        	dataRow.createCell(0).setCellValue(i+1);
        	dataRow.createCell(1).setCellValue(ConteoLista.get(i).getRollo_id() == null ? "" : ConteoLista.get(i).getRollo_id());
        	dataRow.createCell(2).setCellValue(ConteoLista.get(i).getTipo() == null ? "" : ConteoLista.get(i).getTipo());
        	dataRow.createCell(3).setCellValue(ConteoLista.get(i).getAncho() == null ? 0.0 : ConteoLista.get(i).getAncho());
        	dataRow.createCell(4).setCellValue(ConteoLista.get(i).getPeso() == null ? 0.0 : ConteoLista.get(i).getPeso());
        	dataRow.createCell(5).setCellValue(ConteoLista.get(i).getUbicacion() == null ? "" : ConteoLista.get(i).getUbicacion());
        	dataRow.createCell(6).setCellValue(ConteoLista.get(i).getConteo() == null ? 0.0 : ConteoLista.get(i).getConteo());
        }
        
	}

}
