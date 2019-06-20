package com.websystique.springmvc.dao.tarjetas.cotizador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.tarjetas.cotizador.Cotizador;
import com.websystique.springmvc.model.tarjetas.cotizador.Cotizador_busqueda;

@Repository("cotizadorDAO")
public class CotizadorDAOImpl extends AbstractDao<Integer,Cotizador> implements CotizadorDAO{

	@Override
	public Cotizador BuscarxId(Integer id) {
		// FIXME Auto-generated method stub
		Map<String,Integer> mRes =  new HashMap<String, Integer>();
		mRes.put("id", id);
		return (Cotizador) criteriaQueryIntEqObj(mRes);
	}
	
	@Override
	public Cotizador BuscarxId(Integer id, Integer userInsert) {
		// FIXME Auto-generated method stub
		Map<String,Integer> mRes =  new HashMap<String, Integer>();
		mRes.put("id", id);
		mRes.put("usuario_insert", userInsert);
		return (Cotizador) criteriaQueryIntEqObj(mRes);
	}

	@Override
	public List<Cotizador> BuscarxUser(Integer idUser) {
		// FIXME Auto-generated method stub
		Map<String,Integer> mRes =  new HashMap<String, Integer>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		
		mRes.put("usuario_insert", idUser);
		mOrd.put("1", "id");
		
		List<Cotizador> Lista = criteriaQueryEqInt(mRes, mOrd);
		
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

	@Override
	public List<Cotizador_busqueda> ListaBusquedaxIdCardCode(Integer id, String cardCode,Integer idUser) {
		// FIXME Auto-generated method stub
		String query = "select ROW_NUMBER() OVER(ORDER BY a.ID ASC) AS count, a.ID,b.cardname,c.address +' '+c.direccion direccion,a.FECHA_INSERT fecha_insert\r\n" + 
				"from COTIZADOR a\r\n" + 
				"inner join CATALOGO_CLIENTES_SAP b on a.CARDCODE = b.cardcode\r\n" + 
				"inner join CATALOGO_DIRECCIONES_SAP c on a.CARDCODE = c.cardcode and a.LINENUM_DIR_ENTREGA = c.linenum\r\n" + 
				"where a.ID > 0";
		if(id > 0)
			query = query + " and a.ID = "+id+" ";
		if (!cardCode.equals("0"))
			query = query + " and a.CARDCODE = '"+cardCode+"' ";
		if(idUser > 0)
			query = query + " and a.USUARIO_INSERT = "+idUser+" ";
		
		query = query + " order by a.FECHA_INSERT ";
		
		List<Cotizador_busqueda> Lista = new ArrayList<Cotizador_busqueda>();
		@SuppressWarnings({ "unchecked" })
		List<Object> result = (List<Object>) getSession().createNativeQuery(query).getResultList();
		
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
	}

	@Override
	public List<Cotizador_busqueda> ListaBusquedaxIdCardCodeDet(Integer id, String cardCode, Integer idUser,Integer idDet,Boolean autVtas,Boolean autProg) {
		// FIXME Auto-generated method stub
		String query = "select ROW_NUMBER() OVER(ORDER BY a.ID ASC) AS count,a.ID id,b.cardname,c.address +' '+c.direccion direccion,a.FECHA_INSERT fecha_insert,d.SIMBOLO simbolo,e.nombrelargo,d.iddetalle iddet, \r\n"+
				"d.comision_directo,d.precio_objetivo,d.precio_sugerido,d.precio_neto,d.descuento_vendedor,e.nombrecorto \r\n" + 
				"from COTIZADOR a\r\n" + 
				"inner join CATALOGO_CLIENTES_SAP b on a.CARDCODE = b.cardcode\r\n" + 
				"inner join CATALOGO_DIRECCIONES_SAP c on a.CARDCODE = c.cardcode and a.LINENUM_DIR_ENTREGA = c.linenum\r\n" + 
				"left join COTIZADOR_DETALLES d on a.ID = d.IDCOTIZACION\r\n" + 
				"inner join CATALOGO_CAJAS_SAP e on d.IDCAJA_SAP = e.idtipocaja \r\n" + 
				"where a.ID > 0";
		if(id > 0)
			query = query + " and a.ID = "+id+" ";
		if (!cardCode.equals("0"))
			query = query + " and a.CARDCODE = '"+cardCode+"' ";
		if(idUser > 0)
			query = query + " and a.USUARIO_INSERT = "+idUser+" ";
		if(idDet > 0)
			query = query + " and d.iddetalle = "+idDet+" ";
		if(autVtas)
		{
			query = query + "and a.fecha_envia_ventas is not null and a.usuario_envia_ventas is not null \r\n" + 
					"and a.fecha_aut_ventas is null and a.usuario_aut_ventas is null and a.fecha_rech_ventas is null and a.usuario_rech_ventas is null  \r\n" +
					"and a.usuario_cancel is null and a.fecha_cancel is null ";
		}
		if(autProg)
		{
			query = query + "and a.fecha_envia_a_prog is not null and a.usuario_envia_a_prog is not null \r\n" + 
					"and a.fecha_aut_prog is null and a.usuario_aut_prog is null and a.fecha_rech_prog is null and a.usuario_rech_prog is null  \r\n" +
					"and a.usuario_cancel is null and a.fecha_cancel is null ";
		}
		
		query = query + " order by a.FECHA_INSERT ";
		//System.out.println(query);
		List<Cotizador_busqueda> Lista = new ArrayList<Cotizador_busqueda>();
		@SuppressWarnings({ "unchecked" })
		List<Object> result = (List<Object>) getSession().createNativeQuery(query).getResultList();
		
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
			   Lista.add(cb); 
			}
		
		return Lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> ListaCotizacionesJasper(Integer id,Boolean autProg) {
		// FIXME Auto-generated method stub
		String query = "select a.cardcode,d.cardname,c.first_name +' '+ c.last_name as 'representante',b.simbolo,a.fecha_insert,a.id,b.largo,b.ancho,b.fondo,b.medida_lamina,b.area_unitaria,\r\n" + 
				"peso_pieza,piezasxjuego,area_total,peso_juego,e.tipocajabarca,f.resistencia,f.corrugado,f.color,b.peso_resis,b.preciom2resistencia,b.precio_neto,\r\n" + 
				"b.precio_objetivo,b.pk_teorico,b.kg,b.descuento_vendedor,piezasxtarima,porcentaje_comision,comisionxmillar,costo_papel,b.ref_para_comision,\r\n" + 
				"b.cpcc,cierre,cierre_detalle,b.num_tintas,(select  isnull(costo,0) \r\n" + 
				"from especialidades_cotizacion g where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=1) as 'michelman int' ,\r\n" + 
				"(select   isnull(costo,0) from especialidades_cotizacion g where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=2) as 'michelman ext',\r\n" + 
				"(select   isnull(costo,0) from especialidades_cotizacion g where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=3) as 'open sesame',\r\n" + 
				"(select   isnull(costo,0) from especialidades_cotizacion g where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=4) as 'pegado',\r\n" + 
				"(select   isnull(costo,0) from especialidades_cotizacion g where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=8) as 'grapado',\r\n" + 
				"(select   isnull(costo,0) from especialidades_cotizacion g where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=9) as 'tarima estandar',\r\n" + 
				"(select   isnull(costo,0) from especialidades_cotizacion g where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=10) as 'water paper interior',\r\n" + 
				"(select   isnull(costo,0) from especialidades_cotizacion g where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=11) as 'water paper exterior',\r\n" + 
				"(select   isnull(costo,0) from especialidades_cotizacion g where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=12) as 'water paper ambas caras',\r\n" + 
				"(select   isnull(costo,0) from especialidades_cotizacion g where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=13) as 'emulsion acrilica',\r\n" + 
				"(select   isnull(costo,0) from especialidades_cotizacion g where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=14) as 'michelman ambas caras',\r\n" + 
				"(select   isnull(costo,0) from especialidades_cotizacion g where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=15) as 'string king',\r\n" + 
				"(select   isnull(costo,0) from especialidades_cotizacion g where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=16) as 'cera',\r\n" + 
				"(select   isnull(costo,0) from especialidades_cotizacion g where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=17) as 'maquila desvarbe',\r\n" + 
				"(select   isnull(costo,0) from especialidades_cotizacion g where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=18) as 'almidon especial',\r\n" + 
				"(select  isnull(costo,0) from especialidades_cotizacion g where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=19) as 'tarima especial',\r\n" + 
				"(select   isnull(costo,0) from especialidades_cotizacion g where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=20) as 'maquila pegado',\r\n" + 
				"(select   isnull(costo,0) from especialidades_cotizacion g where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=22) as 'bolsa',\r\n" + 
				"(select   isnull(costo,0) from especialidades_cotizacion g where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=23) as 'desvarbe',\r\n" + 
				"(select   isnull(costo,0) from especialidades_cotizacion g where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=24) as 'tarima viajer',\r\n" + 
				"(select   isnull(costo,0) from especialidades_cotizacion g where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=35) as 'maquila de ensamble',\r\n" + 
				"(select   isnull(costo,0) from especialidades_cotizacion g where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=36) as 'trim adicional',\r\n" + 
				"(select   isnull(costo,0) from especialidades_cotizacion g where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=37) as 'doble paso'\r\n" + 
				",total_especialidades,b.costo_flete,porc_flete,g.direccion,g.ciudad,g.estado,(select  h.first_name +' '+ h.last_name from app_user h where c.id=a.usuario_aut_ventas) as 'autorizador',  \r\n" +
				"(select  isnull(h.name,'')  from especialidades_cotizacion g inner join catalogo_especialidades_sap h on g.idespecialidad = h.code where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=1) as 'michelman intn' ,\r\n" + 
				"(select   isnull(h.name,'') from especialidades_cotizacion g inner join catalogo_especialidades_sap h on g.idespecialidad = h.code where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=2) as 'michelman extn',\r\n" + 
				"(select   isnull(h.name,'') from especialidades_cotizacion g inner join catalogo_especialidades_sap h on g.idespecialidad = h.code where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=3) as 'open sesamen',\r\n" + 
				"(select   isnull(h.name,'') from especialidades_cotizacion g inner join catalogo_especialidades_sap h on g.idespecialidad = h.code where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=4) as 'pegadon',\r\n" + 
				"(select   isnull(h.name,'') from especialidades_cotizacion g inner join catalogo_especialidades_sap h on g.idespecialidad = h.code where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=8) as 'grapadon',\r\n" + 
				"(select   isnull(h.name,'') from especialidades_cotizacion g inner join catalogo_especialidades_sap h on g.idespecialidad = h.code where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=9) as 'tarima estandarn',\r\n" + 
				"(select   isnull(h.name,'') from especialidades_cotizacion g inner join catalogo_especialidades_sap h on g.idespecialidad = h.code where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=10) as 'water paper interiorn',\r\n" + 
				"(select   isnull(h.name,'') from especialidades_cotizacion g inner join catalogo_especialidades_sap h on g.idespecialidad = h.code where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=11) as 'water paper exteriorn',\r\n" + 
				"(select   isnull(h.name,'') from especialidades_cotizacion g inner join catalogo_especialidades_sap h on g.idespecialidad = h.code where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=12) as 'water paper ambas carasn',\r\n" + 
				"(select   isnull(h.name,'') from especialidades_cotizacion g inner join catalogo_especialidades_sap h on g.idespecialidad = h.code where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=13) as 'emulsion acrilican',\r\n" + 
				"(select   isnull(h.name,'') from especialidades_cotizacion g inner join catalogo_especialidades_sap h on g.idespecialidad = h.code where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=14) as 'michelman ambas carasn',\r\n" + 
				"(select   isnull(h.name,'') from especialidades_cotizacion g inner join catalogo_especialidades_sap h on g.idespecialidad = h.code where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=15) as 'string kingn',\r\n" + 
				"(select   isnull(h.name,'') from especialidades_cotizacion g inner join catalogo_especialidades_sap h on g.idespecialidad = h.code where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=16) as 'ceran',\r\n" + 
				"(select   isnull(h.name,'') from especialidades_cotizacion g inner join catalogo_especialidades_sap h on g.idespecialidad = h.code where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=17) as 'maquila desvarben',\r\n" + 
				"(select   isnull(h.name,'') from especialidades_cotizacion g inner join catalogo_especialidades_sap h on g.idespecialidad = h.code where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=18) as 'almidon especialn',\r\n" + 
				"(select  isnull(h.name,'')  from especialidades_cotizacion g inner join catalogo_especialidades_sap h on g.idespecialidad = h.code where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=19) as 'tarima especialn',\r\n" + 
				"(select   isnull(h.name,'') from especialidades_cotizacion g inner join catalogo_especialidades_sap h on g.idespecialidad = h.code where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=20) as 'maquila pegadon',\r\n" + 
				"(select   isnull(h.name,'') from especialidades_cotizacion g inner join catalogo_especialidades_sap h on g.idespecialidad = h.code where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=22) as 'bolsan',\r\n" + 
				"(select   isnull(h.name,'') from especialidades_cotizacion g inner join catalogo_especialidades_sap h on g.idespecialidad = h.code where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=23) as 'desvarben',\r\n" + 
				"(select   isnull(h.name,'') from especialidades_cotizacion g inner join catalogo_especialidades_sap h on g.idespecialidad = h.code where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=24) as 'tarima viajern',\r\n" + 
				"(select   isnull(h.name,'') from especialidades_cotizacion g inner join catalogo_especialidades_sap h on g.idespecialidad = h.code where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=35) as 'maquila de ensamblen',\r\n" + 
				"(select   isnull(h.name,'') from especialidades_cotizacion g inner join catalogo_especialidades_sap h on g.idespecialidad = h.code where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=36) as 'trim adicionaln',\r\n" + 
				"(select   isnull(h.name,'') from especialidades_cotizacion g inner join catalogo_especialidades_sap h on g.idespecialidad = h.code where g.idcotizacion=b.idcotizacion and g.iddetalle=b.iddetalle and g.idespecialidad=37) as 'doble pason' \r\n" +
				"from cotizador a \r\n" + 
				"join cotizador_detalles b on a.id=b.idcotizacion\r\n" + 
				"join app_user c on a.usuario_insert=c.id\r\n" + 
				"join catalogo_clientes_sap d on d.cardcode=a.cardcode\r\n" + 
				"join catalogo_cajas_sap e on e.idtipocaja=b.idcaja_sap\r\n" + 
				"join catalogo_resistencias_sap f on f.idresistencia=b.idresistencia_barca\r\n" + 
				"join catalogo_direcciones_sap g on g.cardcode=a.cardcode and g.linenum=a.linenum_dir_entrega " ;
				if(id > 0)
					query = query + "where idcotizacion = "+ id +" order by b.iddetalle ";
				else
				{
					if(autProg)
					{
						query = query + "where a.fecha_envia_a_prog is not null and a.usuario_envia_a_prog is not null \r\n" + 
								"and a.fecha_aut_prog is null and a.usuario_aut_prog is null and a.fecha_rech_prog is null and a.usuario_rech_prog is null  \r\n" +
								"and a.usuario_cancel is null and a.fecha_cancel is null ";
					}
				}	
		
		List<Object> result = (List<Object>) getSession().createNativeQuery(query).getResultList();
		
		return result;
	}

}
