package com.websystique.springmvc.dao.tarjetas.fabricacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.tarjetas.fabricacion.Tarjeta_fabricacion;

@Repository("tarjeta_fabricacionDAO")
public class Tarjeta_fabricacionDAOImpl extends AbstractDao<Integer,Tarjeta_fabricacion> implements Tarjeta_fabricacionDAO{

	@Override
	public Tarjeta_fabricacion BuscarxFolio(String Folio, Integer IdDis) {
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add( new ParamsGeneral(1,"folio_tarjeta",Folio,"EQ"));
		Params.add( new ParamsGeneral(1,"iddiseniador",IdDis,"EQ"));
		Tarjeta_fabricacion Tarjeta = (Tarjeta_fabricacion) criteriaGeneralObj(Params);
		return Tarjeta;
	}

	@Override
	public List<Tarjeta_fabricacion> BuscarXIdCot(Integer IdCot, Integer IdDis) {	
		Map<String,String> mOrd =  new HashMap<String, String>();
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		
		Params.add( new ParamsGeneral(1,"idcotizacion",IdCot,"EQ"));
		if(IdDis > 0)
			Params.add( new ParamsGeneral(1,"iddiseniador",IdDis,"EQ"));
		
		mOrd.put("1", "folio_tarjeta");
		
		List<Tarjeta_fabricacion> Lista = criteriaGeneralList(Params, mOrd);
		
		return Lista;
	}

	@Override
	public void Guardar(Tarjeta_fabricacion Tarjeta) {
		persist(Tarjeta);
	}

	@Override
	public void Actualizar(Tarjeta_fabricacion Tarjeta) {
		update(Tarjeta);
	}

	@Override
	public List<Tarjeta_fabricacion> TarjetaBusqueda(Integer IdCot, String Folio, String cardcode, Integer IdDis) {
		// TODO Auto-generated method stub
		Map<Integer,Integer> paramsInt = new HashMap<Integer, Integer>();
		Map<Integer,String> paramStr = new HashMap<Integer, String>();
		int posicion = 0;
		
		String query  ="select A.* \r\n" + 
				"from TARJETA_FABRICACION A\r\n" + 
				//"inner join COTIZADOR_DETALLES B on A.IDCOTIZACION = B.IDCOTIZACION \r\n" +
				"inner join COTIZADOR C on A.IDCOTIZACION = C.ID ";
		if(!Folio.trim().equals(""))
		{
			posicion ++;
			query = query + " where A.FOLIO_TARJETA = ?"+posicion;
			paramStr.put(posicion, Folio);
			
			posicion ++;
			query = query + " AND A.IDDISENIADOR = ?"+posicion;
			paramsInt.put(posicion, IdDis);
		}
		else
		{
			if(IdCot > 0 )
			{
				posicion ++;
				query = query + " where A.IDCOTIZACION =  ?"+posicion;
				paramsInt.put(posicion, IdCot);
				
				posicion ++;
				query = query + " AND A.IDDISENIADOR = ?"+posicion;
				paramsInt.put(posicion, IdDis);
			}
			else
			{
				if(!cardcode.equals(""))
				{
					posicion ++;
					query = query + " where C.CARDCODE =  ?"+posicion;
					paramStr.put(posicion, cardcode);
					
					posicion ++;
					query = query + " AND A.IDDISENIADOR = ?"+posicion;
					paramsInt.put(posicion, IdDis);
				}
			}
		}
		List<Tarjeta_fabricacion> result = criteriaQueryNamedStr(query,paramsInt,paramStr);
		return result;
	}

	@Override
	public void Borrar(Tarjeta_fabricacion Tarjeta) {
		delete(Tarjeta);
	}

	@Override
	public List<Tarjeta_fabricacion> BuscarXAut(List<ParamsGeneral> Params) {
		Map<String,String> mOrd =  new HashMap<String, String>();
		List<Tarjeta_fabricacion> Lista = criteriaGeneralList(Params, mOrd);
		return Lista;
	}

	/*@Override
	public List<Object> BuscarEsp(Integer idcot, Integer Iddet) {
		Map<Integer,Integer> paramsInt = new HashMap<Integer, Integer>();
		Map<Integer,String> paramStr = new HashMap<Integer, String>();
		
		String query  ="select b.name, a.costo,a.ajuste, b.code, a.cm, b.ruta \r\n" + 
				"from especialidades_cotizacion a\r\n" + 
				"inner join catalogo_especialidades_sap b on a.idespecialidad = b.code\r\n" + 
				"where a.idcotizacion = ?1 and a.iddetalle = ?2";
		paramsInt.put(1, idcot);
		paramsInt.put(2, Iddet);
		List<Object> result = criteriaQueryStr(query,paramsInt,paramStr);
		
		return result;

	} */

	@Override
	public List<Tarjeta_fabricacion> ListaSeguimiento(String Folio, Integer IdCot, Integer Status, String CardCode) {
		Map<Integer,Integer> paramsInt = new HashMap<Integer, Integer>();
		Map<Integer,String> paramStr = new HashMap<Integer, String>();
		Integer posicion = 0;

		String query = "select * from TARJETA_FABRICACION a \r\n" + 
				"where a.FOLIO_TARJETA != '' ";
		if(!Folio.equals(""))
		{
			posicion++;
			query = query + " and a.FOLIO_TARJETA = ?"+posicion;
			paramStr.put(posicion, Folio);		
		}
		
		if(IdCot > 0)
		{
			posicion++;
			query = query + " and a.IDCOTIZACION = ?"+posicion;
			paramsInt.put(posicion, IdCot);
		}
		
		if(!CardCode.equals(""))
		{
			posicion++;
			query = query + " and a.CARDCODE = ?"+posicion;
			paramStr.put(posicion, CardCode);		
		}
		
		if(Status == 1)//rechazadas
		{
			query = query + " and (a.FECHA_RECH_CALIDAD is not null or \r\n" + 
								"a.FECHA_RECH_PRODUCCION is not null or\r\n" + 
								"a.FECHA_RECH_ING is not null or\r\n" + 
								"a.FECHA_RECH_CLIENTE is not null ) and a.FECHA_CANCELA is null";
		}
		
		if(Status == 2)//canceladas
		{
			query = query + " and a.FECHA_CANCELA is not null ";
		}
		
		if(Status == 3)//Diseño
		{
			query = query + " and a.FECHA_AUT_DISENIADOR is null and a.FECHA_CANCELA is null ";
		}
		
		if(Status == 4)//Calidad
		{
			query = query + " and a.FECHA_AUT_DISENIADOR is not null and a.FECHA_AUT_CALIDAD is null  and a.FECHA_CANCELA is null ";
		}
		
		if(Status == 5)//Produccion
		{
			query = query + " and a.FECHA_AUT_CALIDAD is not null and a.FECHA_AUT_PRODUCCION is null  and a.FECHA_CANCELA is null ";
		}
		
		if(Status == 6)//Ingenieria
		{
			query = query + " and a.FECHA_AUT_PRODUCCION is not null and a.FECHA_AUT_ING is null  and a.FECHA_CANCELA is null ";
		}
		
		if(Status == 7)//Cliente
		{
			query = query + " and a.FECHA_AUT_ING is not null and a.FECHA_AUT_CLIENTE is null  and a.FECHA_CANCELA is null ";
		}
		
		if(Status == 8)//aut cliente
		{
			query = query + " and a.FECHA_AUT_CLIENTE is not null  and a.FECHA_CANCELA is null ";
		}
		List<Tarjeta_fabricacion> Lista = criteriaQueryNamedStr(query,paramsInt,paramStr);
		return Lista;
	}

	@Override
	public Tarjeta_fabricacion BuscarxFolio(String Folio) {
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add( new ParamsGeneral(1,"folio_tarjeta",Folio,"EQ"));
		Tarjeta_fabricacion Tarjeta = (Tarjeta_fabricacion) criteriaGeneralObj(Params);
		return Tarjeta;
	}

	@Override
	public Tarjeta_fabricacion BuscarxCot_Cotdet(Integer idcot, Integer iddet) {
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add( new ParamsGeneral(1,"idcotizacion",idcot,"EQ"));
		Params.add( new ParamsGeneral(1,"iddetalle",iddet,"EQ"));
		Tarjeta_fabricacion Tarjeta = (Tarjeta_fabricacion) criteriaGeneralObj(Params);
		return Tarjeta;
	}

	@Override
	public Tarjeta_fabricacion BuscarTFPG(List<ParamsGeneral> Params) {
		Tarjeta_fabricacion TF = (Tarjeta_fabricacion) criteriaGeneralObj(Params);
		return TF;
	}

	@Override
	public List<Tarjeta_fabricacion> ListaSeguimiento(String Folio, Integer IdCot, Integer Status, String CardCode,
			Integer usrinsert) {
		Map<Integer,Integer> paramsInt = new HashMap<Integer, Integer>();
		Map<Integer,String> paramStr = new HashMap<Integer, String>();
		Integer posicion = 0;

		String query = "select * from TARJETA_FABRICACION a inner join COTIZADOR b on a.IDCOTIZACION = b.ID\r\n" + 
				"where a.FOLIO_TARJETA != '' ";
		if(!Folio.equals(""))
		{
			posicion++;
			query = query + " and a.FOLIO_TARJETA = ?"+posicion;
			paramStr.put(posicion, Folio);		
		}
		
		if(IdCot > 0)
		{
			posicion++;
			query = query + " and a.IDCOTIZACION = ?"+posicion;
			paramsInt.put(posicion, IdCot);
		}
		
		if(!CardCode.equals(""))
		{
			posicion++;
			query = query + " and a.CARDCODE = ?"+posicion;
			paramStr.put(posicion, CardCode);		
		}
		
		if(Status == 1)//rechazadas
		{
			query = query + " and (a.FECHA_RECH_CALIDAD is not null or \r\n" + 
								"a.FECHA_RECH_PRODUCCION is not null or\r\n" + 
								"a.FECHA_RECH_ING is not null or\r\n" + 
								"a.FECHA_RECH_CLIENTE is not null ) and a.FECHA_CANCELA is null";
		}
		
		if(Status == 2)//canceladas
		{
			query = query + " and a.FECHA_CANCELA is not null ";
		}
		
		if(Status == 3)//Diseño
		{
			query = query + " and a.FECHA_AUT_DISENIADOR is null and a.FECHA_CANCELA is null ";
		}
		
		if(Status == 4)//Calidad
		{
			query = query + " and a.FECHA_AUT_DISENIADOR is not null and a.FECHA_AUT_CALIDAD is null  and a.FECHA_CANCELA is null ";
		}
		
		if(Status == 5)//Produccion
		{
			query = query + " and a.FECHA_AUT_CALIDAD is not null and a.FECHA_AUT_PRODUCCION is null  and a.FECHA_CANCELA is null ";
		}
		
		if(Status == 6)//Ingenieria
		{
			query = query + " and a.FECHA_AUT_PRODUCCION is not null and a.FECHA_AUT_ING is null  and a.FECHA_CANCELA is null ";
		}
		
		if(Status == 7)//Cliente
		{
			query = query + " and a.FECHA_AUT_ING is not null and a.FECHA_AUT_CLIENTE is null  and a.FECHA_CANCELA is null ";
		}
		
		if(Status == 8)//aut cliente
		{
			query = query + " and a.FECHA_AUT_CLIENTE is not null  and a.FECHA_CANCELA is null ";
		}
		
		if(usrinsert > 0)// vendedor, usuario insert
		{
			posicion++;
			query = query + " and b.USUARIO_INSERT = ?"+posicion;
			paramsInt.put(posicion, usrinsert);	
		}
		

		List<Tarjeta_fabricacion> Lista = criteriaQueryNamedStr(query,paramsInt,paramStr);
		return Lista;
	}
	
}
