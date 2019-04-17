package com.websystique.springmvc.excel;

import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.websystique.springmvc.model.reportes.Reporte_consumo_papel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class ExcelView extends AbstractXlsView{

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        // change the file name
        response.setHeader("Content-Disposition", "attachment; filename=\"consumo_papel.xls\"");
        Double	m90u=0.0;
        Double	m90=0.0;
        Double	m195u=0.0;
        Double	m190e=0.0;
        Double	m170rmsgr=0.0;
        Double	m170msgr=0.0;
        Double	m155e=0.0;
        Double	m150u=0.0;
        Double	m150rmsgr=0.0;
        Double	m150msgr=0.0;
        Double	m130msgr=0.0;
        Double	m110u=0.0;
        Double	m110p=0.0;
        Double	m110=0.0;
        Double	lb38a=0.0;
        Double	lb185c=0.0;
        Double	lb150c=0.0;
        Double	lb130c=0.0;
        Double	l56a=0.0;
        Double	l42a=0.0;
        Double	l35a=0.0;
        Double	l33a=0.0;
        Double	l305a=0.0;
        Double	l275t=0.0;
        Double	l275a=0.0;
        Double	l26a=0.0;
        Double	l260rlsgr=0.0;
        Double	l260nlsgn=0.0;
        Double	l200t=0.0;
        Double	l200rlsgr=0.0;
        Double	l200a=0.0;
        Double	l170u=0.0;
        Double	l170c=0.0;
        Double	l150lsgs=0.0;
        Double	l150lnm=0.0;
        Double	l140t=0.0;
        Double	l140c=0.0;
        Double	l130lsgs=0.0;
        Double	l125t=0.0;
        Double	l125c=0.0;
        Double	l120lsgs=0.0;
        Double totalpapel = 0.0;
        Double total_sem = 0.0;

        @SuppressWarnings("unchecked")
        List<Reporte_consumo_papel> lista = (List<Reporte_consumo_papel>) model.get("listaexcel");
        
        String b = "";
        Sheet sheet = workbook.createSheet("Detail");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("año");
		header.createCell(1).setCellValue("semana");
		header.createCell(2).setCellValue("ancho");
		header.createCell(3).setCellValue("M90U");
		header.createCell(4).setCellValue("M90");
		header.createCell(5).setCellValue("M195U");
		header.createCell(6).setCellValue("M190E");
		header.createCell(7).setCellValue("M170RMSGR");
		header.createCell(8).setCellValue("M170MSGR");
		header.createCell(9).setCellValue("M155E");
		header.createCell(10).setCellValue("M150U");
		header.createCell(11).setCellValue("M150RMSGR");
		header.createCell(12).setCellValue("M150MSGR");
		header.createCell(13).setCellValue("M130MSGR");
		header.createCell(14).setCellValue("M110U");
		header.createCell(15).setCellValue("M110P");
		header.createCell(16).setCellValue("M110");
		header.createCell(17).setCellValue("LB38A");
		header.createCell(18).setCellValue("LB185C");
		header.createCell(19).setCellValue("LB150C");
		header.createCell(20).setCellValue("LB130C");
		header.createCell(21).setCellValue("L56A");
		header.createCell(22).setCellValue("L42A");
		header.createCell(23).setCellValue("L35A");
		header.createCell(24).setCellValue("L33A");
		header.createCell(25).setCellValue("L305A");
		header.createCell(26).setCellValue("L275T");
		header.createCell(27).setCellValue("L275A");
		header.createCell(28).setCellValue("L26A");
		header.createCell(29).setCellValue("L260RLSGR");
		header.createCell(30).setCellValue("L260NLSGN");
		header.createCell(31).setCellValue("L200T");
		header.createCell(32).setCellValue("L200RLSGR");
		header.createCell(33).setCellValue("L200A");
		header.createCell(34).setCellValue("L170U");
		header.createCell(35).setCellValue("L170C");
		header.createCell(36).setCellValue("L150LSGS");
		header.createCell(37).setCellValue("L150LNM");
		header.createCell(38).setCellValue("L140T");
		header.createCell(39).setCellValue("L140C");
		header.createCell(40).setCellValue("L130LSGS");
		header.createCell(41).setCellValue("L125T");
		header.createCell(42).setCellValue("L125C");
		header.createCell(43).setCellValue("L120LSGS");
		header.createCell(44).setCellValue("Total semana");

		Integer numrow = 1;
		Integer numsem = 0;
		try 
		{
			for(Integer i = 0; i <= lista.size(); i ++)
			{
				//System.out.println(i);
				if(i == lista.size() || (!b.equals(String.valueOf(lista.get(i).getAncho())) && !b.equals("")))
				{
					//System.out.println(lista.size());
					Row dataRow =  sheet.createRow(numrow);
					dataRow.createCell(1).setCellValue("Total");
					dataRow.createCell(3).setCellValue(m90u);
					dataRow.createCell(4).setCellValue(m90);
					dataRow.createCell(5).setCellValue(m195u);
					dataRow.createCell(6).setCellValue(m190e);
					dataRow.createCell(7).setCellValue(m170rmsgr);
					dataRow.createCell(8).setCellValue(m170msgr);
					dataRow.createCell(9).setCellValue(m155e);
					dataRow.createCell(10).setCellValue(m150u);
					dataRow.createCell(11).setCellValue(m150rmsgr);
					dataRow.createCell(12).setCellValue(m150msgr);
					dataRow.createCell(13).setCellValue(m130msgr);
					dataRow.createCell(14).setCellValue(m110u);
					dataRow.createCell(15).setCellValue(m110p);
					dataRow.createCell(16).setCellValue(m110);
					dataRow.createCell(17).setCellValue(lb38a);
					dataRow.createCell(18).setCellValue(lb185c);
					dataRow.createCell(19).setCellValue(lb150c);
					dataRow.createCell(20).setCellValue(lb130c);
					dataRow.createCell(21).setCellValue(l56a);
					dataRow.createCell(22).setCellValue(l42a);
					dataRow.createCell(23).setCellValue(l35a);
					dataRow.createCell(24).setCellValue(l33a);
					dataRow.createCell(25).setCellValue(l305a);
					dataRow.createCell(26).setCellValue(l275t);
					dataRow.createCell(27).setCellValue(l275a);
					dataRow.createCell(28).setCellValue(l26a);
					dataRow.createCell(29).setCellValue(l260rlsgr);
					dataRow.createCell(30).setCellValue(l260nlsgn);
					dataRow.createCell(31).setCellValue(l200t);
					dataRow.createCell(32).setCellValue(l200rlsgr);
					dataRow.createCell(33).setCellValue(l200a);
					dataRow.createCell(34).setCellValue(l170u);
					dataRow.createCell(35).setCellValue(l170c);
					dataRow.createCell(36).setCellValue(l150lsgs);
					dataRow.createCell(37).setCellValue(l150lnm);
					dataRow.createCell(38).setCellValue(l140t);
					dataRow.createCell(39).setCellValue(l140c);
					dataRow.createCell(40).setCellValue(l130lsgs);
					dataRow.createCell(41).setCellValue(l125t);
					dataRow.createCell(42).setCellValue(l125c);
					dataRow.createCell(43).setCellValue(l120lsgs);
					dataRow.createCell(44).setCellValue(total_sem);
					numrow++;
					dataRow =  sheet.createRow(numrow);
					dataRow.createCell(1).setCellValue("Promedio");
					dataRow.createCell(3).setCellValue(m90u/numsem);m90u=0.0;
					dataRow.createCell(4).setCellValue(m90/numsem);m90=0.0;
					dataRow.createCell(5).setCellValue(m195u/numsem);m195u=0.0;
					dataRow.createCell(6).setCellValue(m190e/numsem);m190e=0.0;
					dataRow.createCell(7).setCellValue(m170rmsgr/numsem);m170rmsgr=0.0;
					dataRow.createCell(8).setCellValue(m170msgr/numsem);m170msgr=0.0;
					dataRow.createCell(9).setCellValue(m155e/numsem);m155e=0.0;
					dataRow.createCell(10).setCellValue(m150u/numsem);m150u=0.0;
					dataRow.createCell(11).setCellValue(m150rmsgr/numsem);m150rmsgr=0.0;
					dataRow.createCell(12).setCellValue(m150msgr/numsem);m150msgr=0.0;
					dataRow.createCell(13).setCellValue(m130msgr/numsem);m130msgr=0.0;
					dataRow.createCell(14).setCellValue(m110u/numsem);m110u=0.0;
					dataRow.createCell(15).setCellValue(m110p/numsem);m110p=0.0;
					dataRow.createCell(16).setCellValue(m110/numsem);m110=0.0;
					dataRow.createCell(17).setCellValue(lb38a/numsem);lb38a=0.0;
					dataRow.createCell(18).setCellValue(lb185c/numsem);lb185c=0.0;
					dataRow.createCell(19).setCellValue(lb150c/numsem);lb150c=0.0;
					dataRow.createCell(20).setCellValue(lb130c/numsem);lb130c=0.0;
					dataRow.createCell(21).setCellValue(l56a/numsem);l56a=0.0;
					dataRow.createCell(22).setCellValue(l42a/numsem);l42a=0.0;
					dataRow.createCell(23).setCellValue(l35a/numsem);l35a=0.0;
					dataRow.createCell(24).setCellValue(l33a/numsem);l33a=0.0;
					dataRow.createCell(25).setCellValue(l305a/numsem);l305a=0.0;
					dataRow.createCell(26).setCellValue(l275t/numsem);l275t=0.0;
					dataRow.createCell(27).setCellValue(l275a/numsem);l275a=0.0;
					dataRow.createCell(28).setCellValue(l26a/numsem);l26a=0.0;
					dataRow.createCell(29).setCellValue(l260rlsgr/numsem);l260rlsgr=0.0;
					dataRow.createCell(30).setCellValue(l260nlsgn/numsem);l260nlsgn=0.0;
					dataRow.createCell(31).setCellValue(l200t/numsem);l200t=0.0;
					dataRow.createCell(32).setCellValue(l200rlsgr/numsem);l200rlsgr=0.0;
					dataRow.createCell(33).setCellValue(l200a/numsem);l200a=0.0;
					dataRow.createCell(34).setCellValue(l170u/numsem);l170u=0.0;
					dataRow.createCell(35).setCellValue(l170c/numsem);l170c=0.0;
					dataRow.createCell(36).setCellValue(l150lsgs/numsem);l150lsgs=0.0;
					dataRow.createCell(37).setCellValue(l150lnm/numsem);l150lnm=0.0;
					dataRow.createCell(38).setCellValue(l140t/numsem);l140t=0.0;
					dataRow.createCell(39).setCellValue(l140c/numsem);l140c=0.0;
					dataRow.createCell(40).setCellValue(l130lsgs/numsem);l130lsgs=0.0;
					dataRow.createCell(41).setCellValue(l125t/numsem);l125t=0.0;
					dataRow.createCell(42).setCellValue(l125c/numsem);l125c=0.0;
					dataRow.createCell(43).setCellValue(l120lsgs/numsem);l120lsgs=0.0;
					dataRow.createCell(44).setCellValue(total_sem/numsem);
					numrow = numrow +2;
					numsem = 0;
					total_sem = 0.0;
					
					if(i < lista.size())
					{
						header = sheet.createRow(numrow);
				        header.createCell(0).setCellValue("año");
						header.createCell(1).setCellValue("semana");
						header.createCell(2).setCellValue("ancho");
						header.createCell(3).setCellValue("M90U");
						header.createCell(4).setCellValue("M90");
						header.createCell(5).setCellValue("M195U");
						header.createCell(6).setCellValue("M190E");
						header.createCell(7).setCellValue("M170RMSGR");
						header.createCell(8).setCellValue("M170MSGR");
						header.createCell(9).setCellValue("M155E");
						header.createCell(10).setCellValue("M150U");
						header.createCell(11).setCellValue("M150RMSGR");
						header.createCell(12).setCellValue("M150MSGR");
						header.createCell(13).setCellValue("M130MSGR");
						header.createCell(14).setCellValue("M110U");
						header.createCell(15).setCellValue("M110P");
						header.createCell(16).setCellValue("M110");
						header.createCell(17).setCellValue("LB38A");
						header.createCell(18).setCellValue("LB185C");
						header.createCell(19).setCellValue("LB150C");
						header.createCell(20).setCellValue("LB130C");
						header.createCell(21).setCellValue("L56A");
						header.createCell(22).setCellValue("L42A");
						header.createCell(23).setCellValue("L35A");
						header.createCell(24).setCellValue("L33A");
						header.createCell(25).setCellValue("L305A");
						header.createCell(26).setCellValue("L275T");
						header.createCell(27).setCellValue("L275A");
						header.createCell(28).setCellValue("L26A");
						header.createCell(29).setCellValue("L260RLSGR");
						header.createCell(30).setCellValue("L260NLSGN");
						header.createCell(31).setCellValue("L200T");
						header.createCell(32).setCellValue("L200RLSGR");
						header.createCell(33).setCellValue("L200A");
						header.createCell(34).setCellValue("L170U");
						header.createCell(35).setCellValue("L170C");
						header.createCell(36).setCellValue("L150LSGS");
						header.createCell(37).setCellValue("L150LNM");
						header.createCell(38).setCellValue("L140T");
						header.createCell(39).setCellValue("L140C");
						header.createCell(40).setCellValue("L130LSGS");
						header.createCell(41).setCellValue("L125T");
						header.createCell(42).setCellValue("L125C");
						header.createCell(43).setCellValue("L120LSGS");
						header.createCell(44).setCellValue("Total semana");
						numrow++;
					}
				}
		        Row dataRow =  sheet.createRow(numrow);
		        dataRow.createCell(0).setCellValue(lista.get(i).getAnio());
				dataRow.createCell(1).setCellValue(lista.get(i).getSemana());
				dataRow.createCell(2).setCellValue(lista.get(i).getAncho());
				dataRow.createCell(3).setCellValue(lista.get(i).getM90u());
				dataRow.createCell(4).setCellValue(lista.get(i).getM90());
				dataRow.createCell(5).setCellValue(lista.get(i).getM195u());
				dataRow.createCell(6).setCellValue(lista.get(i).getM190e());
				dataRow.createCell(7).setCellValue(lista.get(i).getM170rmsgr());
				dataRow.createCell(8).setCellValue(lista.get(i).getM170msgr());
				dataRow.createCell(9).setCellValue(lista.get(i).getM155e());
				dataRow.createCell(10).setCellValue(lista.get(i).getM150u());
				dataRow.createCell(11).setCellValue(lista.get(i).getM150rmsgr());
				dataRow.createCell(12).setCellValue(lista.get(i).getM150msgr());
				dataRow.createCell(13).setCellValue(lista.get(i).getM130msgr());
				dataRow.createCell(14).setCellValue(lista.get(i).getM110u());
				dataRow.createCell(15).setCellValue(lista.get(i).getM110p());
				dataRow.createCell(16).setCellValue(lista.get(i).getM110());
				dataRow.createCell(17).setCellValue(lista.get(i).getLb38a());
				dataRow.createCell(18).setCellValue(lista.get(i).getLb185c());
				dataRow.createCell(19).setCellValue(lista.get(i).getLb150c());
				dataRow.createCell(20).setCellValue(lista.get(i).getLb130c());
				dataRow.createCell(21).setCellValue(lista.get(i).getL56a());
				dataRow.createCell(22).setCellValue(lista.get(i).getL42a());
				dataRow.createCell(23).setCellValue(lista.get(i).getL35a());
				dataRow.createCell(24).setCellValue(lista.get(i).getL33a());
				dataRow.createCell(25).setCellValue(lista.get(i).getL305a());
				dataRow.createCell(26).setCellValue(lista.get(i).getL275t());
				dataRow.createCell(27).setCellValue(lista.get(i).getL275a());
				dataRow.createCell(28).setCellValue(lista.get(i).getL26a());
				dataRow.createCell(29).setCellValue(lista.get(i).getL260rlsgr());
				dataRow.createCell(30).setCellValue(lista.get(i).getL260nlsgn());
				dataRow.createCell(31).setCellValue(lista.get(i).getL200t());
				dataRow.createCell(32).setCellValue(lista.get(i).getL200rlsgr());
				dataRow.createCell(33).setCellValue(lista.get(i).getL200a());
				dataRow.createCell(34).setCellValue(lista.get(i).getL170u());
				dataRow.createCell(35).setCellValue(lista.get(i).getL170c());
				dataRow.createCell(36).setCellValue(lista.get(i).getL150lsgs());
				dataRow.createCell(37).setCellValue(lista.get(i).getL150lnm());
				dataRow.createCell(38).setCellValue(lista.get(i).getL140t());
				dataRow.createCell(39).setCellValue(lista.get(i).getL140c());
				dataRow.createCell(40).setCellValue(lista.get(i).getL130lsgs());
				dataRow.createCell(41).setCellValue(lista.get(i).getL125t());
				dataRow.createCell(42).setCellValue(lista.get(i).getL125c());
				dataRow.createCell(43).setCellValue(lista.get(i).getL120lsgs());
				
				totalpapel = lista.get(i).getM90u()+lista.get(i).getM90()+lista.get(i).getM195u()+lista.get(i).getM190e()+lista.get(i).getM170rmsgr()+lista.get(i).getM170msgr()+
						lista.get(i).getM155e()+lista.get(i).getM150u()+lista.get(i).getM150rmsgr()+lista.get(i).getM150msgr()+lista.get(i).getM130msgr()+lista.get(i).getM110u()+
						lista.get(i).getM110p()+lista.get(i).getM110()+lista.get(i).getLb38a()+lista.get(i).getLb185c()+lista.get(i).getLb150c()+lista.get(i).getLb130c()+
						lista.get(i).getL56a()+lista.get(i).getL42a()+lista.get(i).getL35a()+lista.get(i).getL33a()+lista.get(i).getL305a()+lista.get(i).getL275t()+
						lista.get(i).getL275a()+lista.get(i).getL26a()+lista.get(i).getL260rlsgr()+lista.get(i).getL260nlsgn()+lista.get(i).getL200t()+lista.get(i).getL200rlsgr()+
						lista.get(i).getL200a()+lista.get(i).getL170u()+lista.get(i).getL170c()+lista.get(i).getL150lsgs()+lista.get(i).getL150lnm()+lista.get(i).getL140t()+
						lista.get(i).getL140c()+lista.get(i).getL130lsgs()+lista.get(i).getL125t()+lista.get(i).getL125c()+lista.get(i).getL120lsgs();
				total_sem = total_sem + totalpapel;
				dataRow.createCell(44).setCellValue(totalpapel);
				b = String.valueOf(lista.get(i).getAncho());
				
				m90u=m90u+lista.get(i).getM90u();
				m90=m90+lista.get(i).getM90();
				m195u=m195u+lista.get(i).getM195u();
				m190e=m190e+lista.get(i).getM190e();
				m170rmsgr=m170rmsgr+lista.get(i).getM170rmsgr();
				m170msgr=m170msgr+lista.get(i).getM170msgr();
				m155e=m155e+lista.get(i).getM155e();
				m150u=m150u+lista.get(i).getM150u();
				m150rmsgr=m150rmsgr+lista.get(i).getM150rmsgr();
				m150msgr=m150msgr+lista.get(i).getM150msgr();
				m130msgr=m130msgr+lista.get(i).getM130msgr();
				m110u=m110u+lista.get(i).getM110u();
				m110p=m110p+lista.get(i).getM110p();
				m110=m110+lista.get(i).getM110();
				lb38a=lb38a+lista.get(i).getLb38a();
				lb185c=lb185c+lista.get(i).getLb185c();
				lb150c=lb150c+lista.get(i).getLb150c();
				lb130c=lb130c+lista.get(i).getLb130c();
				l56a=l56a+lista.get(i).getL56a();
				l42a=l42a+lista.get(i).getL42a();
				l35a=l35a+lista.get(i).getL35a();
				l33a=l33a+lista.get(i).getL33a();
				l305a=l305a+lista.get(i).getL305a();
				l275t=l275t+lista.get(i).getL275t();
				l275a=l275a+lista.get(i).getL275a();
				l26a=l26a+lista.get(i).getL26a();
				l260rlsgr=l260rlsgr+lista.get(i).getL260rlsgr();
				l260nlsgn=l260nlsgn+lista.get(i).getL260nlsgn();
				l200t=l200t+lista.get(i).getL200t();
				l200rlsgr=l200rlsgr+lista.get(i).getL200rlsgr();
				l200a=l200a+lista.get(i).getL200a();
				l170u=l170u+lista.get(i).getL170u();
				l170c=l170c+lista.get(i).getL170c();
				l150lsgs=l150lsgs+lista.get(i).getL150lsgs();
				l150lnm=l150lnm+lista.get(i).getL150lnm();
				l140t=l140t+lista.get(i).getL140t();
				l140c=l140c+lista.get(i).getL140c();
				l130lsgs=l130lsgs+lista.get(i).getL130lsgs();
				l125t=l125t+lista.get(i).getL125t();
				l125c=l125c+lista.get(i).getL125c();
				l120lsgs=l120lsgs+lista.get(i).getL120lsgs();
				
				numrow ++;
				numsem ++;
			}
		} catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}

    }

}
