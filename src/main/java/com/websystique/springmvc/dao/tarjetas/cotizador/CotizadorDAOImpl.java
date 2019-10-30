package com.websystique.springmvc.dao.tarjetas.cotizador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.tarjetas.cotizador.Cotizador;
//import com.websystique.springmvc.model.tarjetas.cotizador.Cotizador_busqueda;

@Repository("cotizadorDAO")
public class CotizadorDAOImpl extends AbstractDao<Integer,Cotizador> implements CotizadorDAO{

	@Override
	public Cotizador BuscarxId(Integer id) {
		// FIXME Auto-generated method stub
		/*Map<String,Integer> mRes =  new HashMap<String, Integer>();
		mRes.put("id", id);*/
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"id",id,"EQ"));
		return (Cotizador) criteriaGeneralObj(Params);
	}
	
	@Override
	public Cotizador BuscarxId(Integer id, Integer userInsert) {
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"id",id,"EQ"));
		Params.add(new ParamsGeneral(1,"idtiporequerimiento",3,"NE"));
		Params.add(new ParamsGeneral(1,"usuario_insert",userInsert,"EQ"));
		return (Cotizador) criteriaGeneralObj(Params);
	}
	
	@Override
	public Cotizador BuscarxIdArr(Integer id, Integer userInsert) {
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"id",id,"EQ"));
		Params.add(new ParamsGeneral(1,"idtiporequerimiento",3,"EQ"));
		Params.add(new ParamsGeneral(1,"usuario_insert",userInsert,"EQ"));
		return (Cotizador) criteriaGeneralObj(Params);
	}
	
	@Override
	public List<Cotizador> BuscarxUser(Integer idUser) {
		// FIXME Auto-generated method stub
		//Map<String,Integer> mRes =  new HashMap<String, Integer>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		//mRes.put("usuario_insert", idUser);
		mOrd.put("1", "id");
		
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"usuario_insert",idUser,"EQ"));
		
		List<Cotizador> Lista = criteriaGeneralList(Params, mOrd);
		
		return Lista;
	}

	@Override
	public Integer Guardar(Cotizador cot) {
		// FIXME Auto-generated method stub
		Integer id = save_entity(cot);
		return id;
	}

	@Override
	public void Actualizar(Cotizador cot) {
		// FIXME Auto-generated method stub
		update(cot);
	}

	/*@Override
	public List<Cotizador_busqueda> ListaBusquedaxIdCardCode(Integer id, String cardCode,Integer idUser) {
		// FIXME Auto-generated method stub
		Map<Integer,Integer> paramsInt = new HashMap<Integer, Integer>();
		Map<Integer,String> paramStr = new HashMap<Integer, String>();
		int posicion = 0;
		
		String query = "select ROW_NUMBER() OVER(ORDER BY a.ID ASC) AS count, a.ID,b.cardname,c.address +' '+c.direccion direccion,a.FECHA_INSERT fecha_insert\r\n" + 
				"from COTIZADOR a\r\n" + 
				"inner join CATALOGO_CLIENTES_SAP b on a.CARDCODE = b.cardcode\r\n" + 
				"inner join CATALOGO_DIRECCIONES_SAP c on a.CARDCODE = c.cardcode and a.LINENUM_DIR_ENTREGA = c.linenum\r\n" + 
				"where a.ID > 0";
		if(id > 0)
		{
			posicion ++;
			query = query + " and a.ID = ?"+posicion;
			paramsInt.put(posicion, id);
		}
		if (!cardCode.equals("0"))
		{
			posicion ++;
			query = query + " and a.CARDCODE = ?"+posicion;
			paramStr.put(posicion, cardCode);
		}
		if(idUser > 0)
		{
			posicion ++;
			query = query + " and a.USUARIO_INSERT = ?"+posicion;
			paramsInt.put(posicion, idUser);
		}
		
		query = query + " order by a.FECHA_INSERT ";
		
		List<Cotizador_busqueda> Lista = new ArrayList<Cotizador_busqueda>();
		
		List<Object> result = criteriaQueryStr(query,paramsInt,paramStr);//getSession().createNativeQuery(query).getResultList();
		
		@SuppressWarnings("rawtypes")
		Iterator itr = result.iterator();
		
		while(itr.hasNext()){
			   Object[] obj = (Object[]) itr.next();
			   Cotizador_busqueda cb = new Cotizador_busqueda();
			   cb.setCount(Integer.valueOf(String.valueOf(obj[0])));
			   cb.setId(Integer.valueOf(String.valueOf(obj[1])));
			   cb.setCardname(String.valueOf(obj[2]));
			   cb.setDireccion(String.valueOf(obj[3]));
			   cb.setFecha_insert(String.valueOf(obj[4]));
			   
			   Lista.add(cb); 
			}
		
		return Lista;
	} */
/*
	@Override
	public List<Cotizador_busqueda> ListaBusquedaxIdCardCodeDet(Integer id, String cardCode, Integer idUser,Integer idDet,Boolean autVtas,Boolean autProgAsigDis) {
		// FIXME Auto-generated method stub
		Map<Integer,Integer> paramsInt = new HashMap<Integer, Integer>();
		Map<Integer,String> paramStr = new HashMap<Integer, String>();
		int posicion = 0;
		
		String query = "select ROW_NUMBER() OVER(ORDER BY a.ID ASC) AS count,a.ID id,b.cardname,c.address +' '+c.direccion direccion,a.FECHA_INSERT fecha_insert,d.SIMBOLO simbolo,e.nombrelargo,d.iddetalle iddet, \r\n" + 
				"d.comision_directo,d.precio_objetivo,d.precio_sugerido,d.precio_neto,d.descuento_vendedor,e.nombrecorto,d.cpcc,d.ref_para_comision, a.observaciones_diseniador,f.resistencia,f.corrugado,f.color,g.SELLOS \r\n" + 
				"from COTIZADOR a \r\n" + 
				"inner join CATALOGO_CLIENTES_SAP b on a.CARDCODE = b.cardcode \r\n" + 
				"inner join CATALOGO_DIRECCIONES_SAP c on a.CARDCODE = c.cardcode and a.LINENUM_DIR_ENTREGA = c.linenum \r\n" + 
				"left join COTIZADOR_DETALLES d on a.ID = d.IDCOTIZACION\r\n" + 
				"inner join CATALOGO_CAJAS_SAP e on d.IDCAJA_SAP = e.idtipocaja\r\n" + 
				"left join catalogo_resistencias_sap f on d.IDRESISTENCIA_BARCA = f.idresistencia\r\n" + 
				"left join CATALOGO_SELLOS g on d.RESISTENCIA_CTE = g.ID \r\n" + 
				"where a.ID > 0";
		if(id > 0)
		{
			posicion ++;			
			query = query + " and a.ID =  ?"+posicion;
			paramsInt.put(posicion, id);
		}
		if (!cardCode.equals("0"))
		{
			posicion ++;
			query = query + " and a.CARDCODE =  ?"+posicion;
			paramStr.put(posicion, cardCode);
		}
		if(idUser > 0)
		{
			posicion ++;
			query = query + " and a.USUARIO_INSERT =  ?"+posicion;
			paramsInt.put(posicion, idUser);
			
		}
		if(idDet > 0)
		{
			posicion ++;
			query = query + " and d.iddetalle =  ?"+posicion;
			paramsInt.put(posicion, idDet);
		}
		if(autVtas)
		{
			query = query + " and a.fecha_envia_ventas is not null and a.usuario_envia_ventas is not null \r\n" + 
					"and a.fecha_aut_ventas is null and a.usuario_aut_ventas is null and a.fecha_rech_ventas is null and a.usuario_rech_ventas is null  \r\n" +
					"and a.usuario_cancel is null and a.fecha_cancel is null ";
		}
		if(autProgAsigDis)
		{
			query = query + " and a.fecha_aut_prog is not null and a.usuario_aut_prog is not null and a.fecha_aut_ventas is not null and a.usuario_aut_ventas is not null \r\n" +
					"and a.usuario_cancel is null and a.fecha_cancel is null and a.usuario_diseniador is null and a.fecha_asign_diseniador is null and a.usuario_rech_diseniador is null and a.fecha_rech_diseniador is null";
		}
		
		query = query + " order by a.FECHA_INSERT ";
		//System.out.println(query);
		List<Cotizador_busqueda> Lista = new ArrayList<Cotizador_busqueda>();

		List<Object> result = criteriaQueryStr(query,paramsInt,paramStr);
		
		@SuppressWarnings("rawtypes")
		Iterator itr = result.iterator();
		
		while(itr.hasNext()){
			   Object[] obj = (Object[]) itr.next();
			   Cotizador_busqueda cb = new Cotizador_busqueda();
			   cb.setCount(Integer.valueOf(String.valueOf(obj[0])));
			   cb.setId(Integer.valueOf(String.valueOf(obj[1])));
			   cb.setCardname(String.valueOf(obj[2]));
			   cb.setDireccion(String.valueOf(obj[3]));
			   cb.setFecha_insert(String.valueOf(obj[4]));
			   cb.setSimbolo(String.valueOf(obj[5]));
			   cb.setCaja(String.valueOf(obj[6]));
			   cb.setIddet(Integer.valueOf(String.valueOf(obj[7])));
			   cb.setComision_directo(Double.parseDouble(String.valueOf(obj[8])));
			   cb.setPrecio_objetivo(Double.parseDouble(String.valueOf(obj[9])));
			   cb.setPrecio_sugerido(Double.parseDouble(String.valueOf(obj[10])));
			   cb.setPrecio_neto(Double.parseDouble(String.valueOf(obj[11])));
			   cb.setDescuento_vendedor(Double.parseDouble(String.valueOf(obj[12])));
			   cb.setNombrecorto(String.valueOf(obj[13]));
			   cb.setCpcc(Double.parseDouble(String.valueOf(obj[14])));
			   cb.setRef_para_com(Double.parseDouble(String.valueOf(obj[15])));
			   cb.setObservaciones_diseniador(String.valueOf(obj[16]));
			   cb.setResistencia(String.valueOf(obj[17]));
			   cb.setCorrugado(String.valueOf(obj[18]));
			   cb.setColor(String.valueOf(obj[19]));
			   cb.setSellos(String.valueOf(obj[20]));
			   Lista.add(cb); 
			}
		
		return Lista;
	}  */

	@Override
	public List<Cotizador> ListasCotAut(List<ParamsGeneral> params) {
		List<Cotizador> Lista = new ArrayList<Cotizador>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		Lista = criteriaGeneralList(params, mOrd);
		return Lista;
	}
	

}
