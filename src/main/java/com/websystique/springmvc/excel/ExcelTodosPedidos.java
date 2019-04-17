package com.websystique.springmvc.excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.websystique.springmvc.model.reportes.Todos_pedidos;

public class ExcelTodosPedidos extends AbstractXlsView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
            Workbook workbook,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		
		response.setHeader("Content-Disposition", "attachment; filename=\"todos_pedidos.xls\"");
		
		@SuppressWarnings("unchecked")
		List<Todos_pedidos> listaexcel = (List<Todos_pedidos>) model.get("listaexcel");
		
		Sheet sheet = workbook.createSheet("Detail");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("docentry");
        header.createCell(1).setCellValue("u_tf");
        header.createCell(2).setCellValue("clavesap");
        header.createCell(3).setCellValue("pedido");
        header.createCell(4).setCellValue("cardname");
        header.createCell(5).setCellValue("orden_compra");
        header.createCell(6).setCellValue("elaboracion");
        header.createCell(7).setCellValue("entrega");
        //header.createCell(8).setCellValue("clavesap");
        header.createCell(8).setCellValue("simbolo");
        header.createCell(9).setCellValue("impresion");
        header.createCell(10).setCellValue("cierre");
        header.createCell(11).setCellValue("piezasxatado");
        header.createCell(12).setCellValue("resistencia");
        header.createCell(13).setCellValue("u_l");
        header.createCell(14).setCellValue("u_a");
        header.createCell(15).setCellValue("u_f");
        header.createCell(16).setCellValue("areajuego");
        header.createCell(17).setCellValue("cantidad");
        header.createCell(18).setCellValue("peso");
        header.createCell(19).setCellValue("totalmetros");
        header.createCell(20).setCellValue("combinacion");
        header.createCell(21).setCellValue("totalkilos");
        header.createCell(22).setCellValue("totaldocumento");
        header.createCell(23).setCellValue("precio");
        //header.createCell(25).setCellValue("shiptocode");
        //header.createCell(26).setCellValue("entrega");
        header.createCell(24).setCellValue("preciom2");
        header.createCell(25).setCellValue("docstatus");
        header.createCell(26).setCellValue("precioneto");
        header.createCell(27).setCellValue("descuento");
        header.createCell(28).setCellValue("canceled");
        header.createCell(29).setCellValue("pk");
        header.createCell(30).setCellValue("comision_porc");
        header.createCell(31).setCellValue("importecomision");
        header.createCell(32).setCellValue("pkr");
        header.createCell(33).setCellValue("tipo");
        header.createCell(34).setCellValue("importetotal");
        header.createCell(35).setCellValue("comments");
        header.createCell(36).setCellValue("especialidad");
        header.createCell(37).setCellValue("u_ctopapel");
        header.createCell(38).setCellValue("u_costo_flete");
        header.createCell(39).setCellValue("u_costesp");
        header.createCell(40).setCellValue("u_ctopsc");
        header.createCell(41).setCellValue("u_ctopcc");
		
        for(int i = 0; i < listaexcel.size(); i++)
        {
        	Row dataRow =  sheet.createRow(i+1);
        	dataRow.createCell(0).setCellValue(listaexcel.get(i).getDocentry());
        	dataRow.createCell(1).setCellValue(listaexcel.get(i).getU_tf());
        	dataRow.createCell(2).setCellValue(listaexcel.get(i).getClavesap());
        	dataRow.createCell(3).setCellValue(listaexcel.get(i).getPedido());
        	dataRow.createCell(4).setCellValue(listaexcel.get(i).getCardname());
        	dataRow.createCell(5).setCellValue(listaexcel.get(i).getOrden_compra());
        	dataRow.createCell(6).setCellValue(listaexcel.get(i).getElaboracion());
        	dataRow.createCell(7).setCellValue(listaexcel.get(i).getEntrega());
        	//dataRow.createCell(8).setCellValue(listaexcel.get(i).getClavesap());
        	dataRow.createCell(8).setCellValue(listaexcel.get(i).getSimbolo());
        	dataRow.createCell(9).setCellValue(listaexcel.get(i).getImpresion());
        	dataRow.createCell(10).setCellValue(listaexcel.get(i).getCierre());
        	dataRow.createCell(11).setCellValue(listaexcel.get(i).getPiezasxatado());
        	dataRow.createCell(12).setCellValue(listaexcel.get(i).getResistencia());
        	dataRow.createCell(13).setCellValue(listaexcel.get(i).getU_l());
        	dataRow.createCell(14).setCellValue(listaexcel.get(i).getU_a());
        	dataRow.createCell(15).setCellValue(listaexcel.get(i).getU_f());
        	dataRow.createCell(16).setCellValue(listaexcel.get(i).getAreajuego());
        	dataRow.createCell(17).setCellValue(listaexcel.get(i).getCantidad());
        	dataRow.createCell(18).setCellValue(listaexcel.get(i).getPeso());
        	dataRow.createCell(19).setCellValue(listaexcel.get(i).getTotalmetros());
        	dataRow.createCell(20).setCellValue(listaexcel.get(i).getCombinacion());
        	dataRow.createCell(21).setCellValue(listaexcel.get(i).getTotalkilos());
        	dataRow.createCell(22).setCellValue(listaexcel.get(i).getTotaldocumento());
        	dataRow.createCell(23).setCellValue(listaexcel.get(i).getPrecio());
        	//dataRow.createCell(25).setCellValue(listaexcel.get(i).getShiptocode());
        	//dataRow.createCell(26).setCellValue(listaexcel.get(i).getEntrega());
        	dataRow.createCell(24).setCellValue(listaexcel.get(i).getPreciom2());
        	dataRow.createCell(25).setCellValue(listaexcel.get(i).getDocstatus());
        	dataRow.createCell(26).setCellValue(listaexcel.get(i).getPrecioneto());
        	dataRow.createCell(27).setCellValue(listaexcel.get(i).getDescuento());
        	dataRow.createCell(28).setCellValue(listaexcel.get(i).getCanceled());
        	dataRow.createCell(29).setCellValue(listaexcel.get(i).getPk());
        	dataRow.createCell(30).setCellValue(listaexcel.get(i).getComision_porc());
        	dataRow.createCell(31).setCellValue(listaexcel.get(i).getImportecomision());
        	dataRow.createCell(32).setCellValue(listaexcel.get(i).getPkr());
        	dataRow.createCell(33).setCellValue(listaexcel.get(i).getTipo());
        	dataRow.createCell(34).setCellValue(listaexcel.get(i).getImportetotal());
        	dataRow.createCell(35).setCellValue(listaexcel.get(i).getComments());
        	dataRow.createCell(36).setCellValue(listaexcel.get(i).getEspecialidad());
        	dataRow.createCell(37).setCellValue(listaexcel.get(i).getU_ctopapel());
        	dataRow.createCell(38).setCellValue(listaexcel.get(i).getU_costo_flete());
        	dataRow.createCell(39).setCellValue(listaexcel.get(i).getU_costesp());
        	dataRow.createCell(40).setCellValue(listaexcel.get(i).getU_ctopsc());
        	dataRow.createCell(41).setCellValue(listaexcel.get(i).getU_ctopcc());
        }
	}

}
