package com.websystique.springmvc.excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.websystique.springmvc.model.reportes.Golpes_maquina_mes;

public class ExcelViewGolpesMaqMes extends AbstractXlsView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		response.setHeader("Content-Disposition", "attachment; filename=\"golpes_maquina_mes.xls\"");
		
		@SuppressWarnings("unchecked")
		List<Golpes_maquina_mes> reporte = (List<Golpes_maquina_mes>) model.get("listaexcel");
		
		Double Flexografica = 0.00;
		Double Flexotroqueladora = 0.00;
		Double Troqueladora1 = 0.00;
		Double Troqueladora2 = 0.00;
		Double Troqueladora3 = 0.00;
		Double Flexos = 0.00;
		Double Troque = 0.00;
		
		Double Flexograficapz = 0.00;
		Double Flexotroqueladorapz = 0.00;
		Double Troqueladora1pz = 0.00;
		Double Troqueladora2pz = 0.00;
		Double Troqueladora3pz = 0.00;
		Double Flexospz = 0.00;
		Double Troquepz = 0.00;
		
		Double Flexograficakl = 0.00;
		Double Flexotroqueladorakl = 0.00;
		Double Troqueladora1kl = 0.00;
		Double Troqueladora2kl = 0.00;
		Double Troqueladora3kl = 0.00;
		Double Flexoskl = 0.00;
		Double Troquekl = 0.00;
		
		int i = 0;
		
		Sheet sheet = workbook.createSheet("Detail");
        Row header = sheet.createRow(0);
        
        header.createCell(0).setCellValue("Fecha");
        header.createCell(2).setCellValue("Flexografica");
        header.createCell(6).setCellValue("Flexotroqueladora");
        header.createCell(10).setCellValue("Troqueladora 1");
        header.createCell(14).setCellValue("Troqueladora 2");
        header.createCell(18).setCellValue("Troqueladora 3");
        header.createCell(22).setCellValue("Suma Flexo");
        header.createCell(26).setCellValue("Suma Troqueladoras");
        Row header2 = sheet.createRow(1);
        header2.createCell(1).setCellValue("Golpes");
        header2.createCell(2).setCellValue("Piezas");
        header2.createCell(3).setCellValue("Kilos");
        header2.createCell(5).setCellValue("Golpes");
        header2.createCell(6).setCellValue("Piezas");
        header2.createCell(7).setCellValue("Kilos");
        header2.createCell(9).setCellValue("Golpes");
        header2.createCell(10).setCellValue("Piezas");
        header2.createCell(11).setCellValue("Kilos");
        header2.createCell(13).setCellValue("Golpes");
        header2.createCell(14).setCellValue("Piezas");
        header2.createCell(15).setCellValue("Kilos");
        header2.createCell(17).setCellValue("Golpes");
        header2.createCell(18).setCellValue("Piezas");
        header2.createCell(19).setCellValue("Kilos");
        header2.createCell(21).setCellValue("Golpes");
        header2.createCell(22).setCellValue("Piezas");
        header2.createCell(23).setCellValue("Kilos");
        header2.createCell(25).setCellValue("Golpes");
        header2.createCell(26).setCellValue("Piezas");
        header2.createCell(27).setCellValue("Kilos");
		
        for(i = 0; i < reporte.size(); i++)
        {
        	 Row dataRow =  sheet.createRow(i+2);
        	 dataRow.createCell(0).setCellType(CellType.STRING);
			 dataRow.createCell(0).setCellValue(String.valueOf(reporte.get(i).getOprunstartdatetime()));
			 dataRow.createCell(1).setCellValue(reporte.get(i).getFlexograficagp());
			 dataRow.createCell(5).setCellValue(reporte.get(i).getFlexotroqueladoragp());
			 dataRow.createCell(9).setCellValue(reporte.get(i).getTroqueladora1gp());
			 dataRow.createCell(13).setCellValue(reporte.get(i).getTroqueladora2gp());
			 dataRow.createCell(17).setCellValue(reporte.get(i).getTroqueladora3gp());
			 dataRow.createCell(21).setCellValue(reporte.get(i).getFlexograficagp() + reporte.get(i).getFlexotroqueladoragp());
			 dataRow.createCell(25).setCellValue(reporte.get(i).getTroqueladora3gp() + reporte.get(i).getTroqueladora2gp() + reporte.get(i).getTroqueladora1gp());
			 
			 dataRow.createCell(2).setCellValue(reporte.get(i).getFlexograficapz());
			 dataRow.createCell(6).setCellValue(reporte.get(i).getFlexotroqueladorapz());
			 dataRow.createCell(10).setCellValue(reporte.get(i).getTroqueladora1pz());
			 dataRow.createCell(14).setCellValue(reporte.get(i).getTroqueladora2pz());
			 dataRow.createCell(18).setCellValue(reporte.get(i).getTroqueladora3pz());
			 dataRow.createCell(22).setCellValue(reporte.get(i).getFlexograficapz() + reporte.get(i).getFlexotroqueladorapz());
			 dataRow.createCell(26).setCellValue(reporte.get(i).getTroqueladora3pz() + reporte.get(i).getTroqueladora2pz() + reporte.get(i).getTroqueladora1pz());
			 
			 dataRow.createCell(3).setCellValue(reporte.get(i).getFlexograficakl());
			 dataRow.createCell(7).setCellValue(reporte.get(i).getFlexotroqueladorakl());
			 dataRow.createCell(11).setCellValue(reporte.get(i).getTroqueladora1kl());
			 dataRow.createCell(15).setCellValue(reporte.get(i).getTroqueladora2kl());
			 dataRow.createCell(19).setCellValue(reporte.get(i).getTroqueladora3kl());
			 dataRow.createCell(23).setCellValue(reporte.get(i).getFlexograficakl() + reporte.get(i).getFlexotroqueladorakl());
			 dataRow.createCell(27).setCellValue(reporte.get(i).getTroqueladora3kl() + reporte.get(i).getTroqueladora2kl() + reporte.get(i).getTroqueladora1kl());
			 
			 Flexografica = Flexografica + reporte.get(i).getFlexograficagp();
			 Flexotroqueladora = Flexotroqueladora + reporte.get(i).getFlexotroqueladoragp();
			 Troqueladora1 = Troqueladora1 + reporte.get(i).getTroqueladora1gp();
			 Troqueladora2 = Troqueladora2 + reporte.get(i).getTroqueladora2gp();
			 Troqueladora3 = Troqueladora3 + reporte.get(i).getTroqueladora3gp();
			 Flexos = Flexos + reporte.get(i).getFlexograficagp() + reporte.get(i).getFlexotroqueladoragp();
			 Troque = Troque + reporte.get(i).getTroqueladora3gp() + reporte.get(i).getTroqueladora2gp() + reporte.get(i).getTroqueladora1gp();
			 
			 Flexograficapz = Flexograficapz + reporte.get(i).getFlexograficapz();
			 Flexotroqueladorapz = Flexotroqueladorapz + reporte.get(i).getFlexotroqueladorapz();
			 Troqueladora1pz = Troqueladora1pz + reporte.get(i).getTroqueladora1pz();
			 Troqueladora2pz = Troqueladora2pz + reporte.get(i).getTroqueladora2pz();
			 Troqueladora3pz = Troqueladora3pz + reporte.get(i).getTroqueladora3pz();
			 Flexospz = Flexospz + reporte.get(i).getFlexograficapz() + reporte.get(i).getFlexotroqueladorapz();
			 Troquepz = Troquepz + reporte.get(i).getTroqueladora3pz() + reporte.get(i).getTroqueladora2pz() + reporte.get(i).getTroqueladora1pz();
			 
			 Flexograficakl = Flexograficakl + reporte.get(i).getFlexograficakl();
			 Flexotroqueladorakl = Flexotroqueladorakl + reporte.get(i).getFlexotroqueladorakl();
			 Troqueladora1kl = Troqueladora1kl + reporte.get(i).getTroqueladora1kl();
			 Troqueladora2kl = Troqueladora2kl + reporte.get(i).getTroqueladora2kl();
			 Troqueladora3kl = Troqueladora3kl + reporte.get(i).getTroqueladora3kl();
			 Flexoskl = Flexoskl + reporte.get(i).getFlexograficakl() + reporte.get(i).getFlexotroqueladorakl();
			 Troquekl = Troquekl + reporte.get(i).getTroqueladora3kl() + reporte.get(i).getTroqueladora2kl() + reporte.get(i).getTroqueladora1kl();
        }
        Row dataRow =  sheet.createRow(i+3);
        dataRow.createCell(0).setCellValue("Total");
        dataRow.createCell(1).setCellValue(Flexografica);
        dataRow.createCell(5).setCellValue(Flexotroqueladora);
        dataRow.createCell(9).setCellValue(Troqueladora1);
        dataRow.createCell(13).setCellValue(Troqueladora2);
        dataRow.createCell(17).setCellValue(Troqueladora3);
        dataRow.createCell(21).setCellValue(Flexos);
        dataRow.createCell(25).setCellValue(Troque);
        
        dataRow.createCell(2).setCellValue(Flexograficapz);
        dataRow.createCell(6).setCellValue(Flexotroqueladorapz);
        dataRow.createCell(10).setCellValue(Troqueladora1pz);
        dataRow.createCell(14).setCellValue(Troqueladora2pz);
        dataRow.createCell(18).setCellValue(Troqueladora3pz);
        dataRow.createCell(22).setCellValue(Flexospz);
        dataRow.createCell(26).setCellValue(Troquepz);
        
        dataRow.createCell(3).setCellValue(Flexograficakl);
        dataRow.createCell(7).setCellValue(Flexotroqueladorakl);
        dataRow.createCell(11).setCellValue(Troqueladora1kl);
        dataRow.createCell(15).setCellValue(Troqueladora2kl);
        dataRow.createCell(19).setCellValue(Troqueladora3kl);
        dataRow.createCell(23).setCellValue(Flexoskl);
        dataRow.createCell(27).setCellValue(Troquekl);
	}

}
