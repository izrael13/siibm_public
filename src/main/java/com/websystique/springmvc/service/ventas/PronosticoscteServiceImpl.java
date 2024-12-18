package com.websystique.springmvc.service.ventas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.ventas.PronosticoscteDAO;
import com.websystique.springmvc.model.ventas.Pronosticoscte;

@Service("pronosticoscteService")
@Transactional
public class PronosticoscteServiceImpl implements PronosticoscteService{
	
	@Autowired
	PronosticoscteDAO dao;

	@Override
	public void Guardar(Integer idven, String idcte, String anio, String mes, Double pron, Integer accion, Integer code) {
		// TODO Auto-generated method stub
		dao.Guardar(idven, idcte, anio, mes, pron, accion, code);
	}

	@Override
	public List<Pronosticoscte> Lista(String anio, String mes, Integer cveven, String cvecte, Integer cteban, Integer opcion) {
		// TODO Auto-generated method stub
		return dao.Lista(anio, mes, cveven, cvecte, cteban, opcion);
	}
	
	/*@Override
	public List<Pronosticoscte> readFile(MultipartFile file) {
		List<Pronosticoscte> fileObj = new ArrayList<Pronosticoscte>();
		
		try {			
				
			File convFile = new File( file.getOriginalFilename());
		    file.transferTo(convFile);
		    Workbook workbook = WorkbookFactory.create(convFile);
		    Sheet sheet = workbook.getSheetAt(0);
		    DataFormatter dataFormatter = new DataFormatter();
		    Iterator<Row> rowIterator = sheet.rowIterator();
		    String objRow = "";
		    String parts[] = null;
		    
		    Integer i = 0;
		    while (rowIterator.hasNext()) {
		    	 Row row = rowIterator.next();
		    	 i++;
		    	 Pronosticoscte pron = new Pronosticoscte();
		            // Now let's iterate over the columns of the current row
		            Iterator<Cell> cellIterator = row.cellIterator();
		            while (cellIterator.hasNext()) {
		                Cell cell = cellIterator.next();
		                String cellValue = dataFormatter.formatCellValue(cell);
		                cellValue = (cellValue.trim() == "" ? " " : cellValue);
		                objRow = objRow + cellValue + "/";
		                //System.out.print(cellValue + "\t");
		            }
		            //System.out.println(objRow);
		            parts = objRow.split("/");
		            //System.out.println(parts.length);
		            //System.out.println("0: "+parts[0]);
		            //System.out.println("1: "+parts[1]);
		            //System.out.println("2: "+parts[2]);
		            //System.out.println("2: "+parts[3]);
		            pron.setID(i);
		            pron.setCVE_CLIENTE(parts[0]);
		            pron.setCLIENTE(parts[1]);
		            pron.setVENDEDOR(parts[2]);
		            pron.setPRONOSTICO(parts[3].trim().matches("-?\\d+(\\.\\d+)?") == false ? 0 : Integer.parseInt(parts[3]));
		            fileObj.add(pron);
		            //System.out.println(fileObj.get(0).getCliente());
		            objRow = "";
		            pron = null;
		            parts = null;
		    }
			
		} catch (IOException e) {
			// FIXME Auto-generated catch block
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			// FIXME Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// FIXME Auto-generated catch block
			e.printStackTrace();
		}
		dao.readFile(fileObj);
		return fileObj;
	} */

}
