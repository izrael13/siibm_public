package com.websystique.springmvc.dao.tarjetas.prospectos;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.tarjetas.prospectos.Prospectos_ventas;
import com.websystique.springmvc.model.tarjetas.prospectos.Prospectos_ventas_busqueda;

@Repository("prospectos_ventasDAO")
public class Prospectos_ventasDAOImpl extends AbstractDao<Integer,Prospectos_ventas> implements Prospectos_ventasDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public Prospectos_ventas buscarPorId(Integer id) {
		// FIXME Auto-generated method stub
		return getByKey(id);
	}



	@Override
	public Integer Guardar(Prospectos_ventas pv) {
		// FIXME Auto-generated method stub
		Integer id = save_entity(pv);
		return id;
	}

	@Override
	public void Actualizar(Prospectos_ventas pv) {
		// FIXME Auto-generated method stub
		update(pv);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Prospectos_ventas_busqueda> buscarPorIdCardCode(Integer id_user,Integer id, String CardCode,Integer CveVendedor, Double PorcAnace,
																Integer Prioridad,Double OporTon,Integer Status,Date Fecha1, Date Fecha2) {
		// FIXME Auto-generated method stub
		String query = "select a.ID id,b.cardname,b.groupname,a.CONTACTO contacto,a.OPORTUNIDAD_TONELADAS opor_ton,a.FECHA_CIERRE fecha_cierre,a.TELEFONO tel,d.NOMBRE edo,e.NOMBRE ciudad,\r\n" + 
				"g.Nombre nom_vendedor,a.PRIORIDAD prioridad,a.PORCENTAJE_AVANCE porc_avance,a.ESTATUS estatus,a.OBSERVACIONES observaciones,a.FECHA_INSERT\r\n" + 
				"from PROSPECTOS_VENTAS a\r\n" + 
				"left join CATALOGO_CLIENTES_SAP b on a.CARDCODE = b.cardcode\r\n" + 
				"left join CATALOGO_ESTADOS d on a.CVE_ESTADO = d.ID\r\n" + 
				"left join CATALOGO_MUNICIPIOS e on a.CVE_ESTADO = e.ID_ESTADO and a.CVE_CIUDAD = e.ID_MUNICIPIO\r\n" + 
				"left join APP_USER f on a.USUARIO_INSERT = f.ID\r\n" + 
				"left join CATALOGO_VENDEDORES_SAP g on f.CVEVENDEDOR_SAP = g.ClaveVendedor\r\n" +
				"where  a.ID > 0 ";
		if(id_user > 0)
			query = query + "and a.USUARIO_INSERT = "+id_user+" ";
		if(id > 0)
			query = query + " and a.ID = "+id+" ";
		if (!CardCode.equals("0"))
			query = query + " and a.CARDCODE = '"+CardCode+"' ";
		if(CveVendedor > 0)
			query = query + " and g.ClaveVendedor = '"+CveVendedor+"' ";
		if(PorcAnace > 0)
			query = query + " and a.PORCENTAJE_AVANCE = '"+PorcAnace+"' ";
		if(Prioridad > 0)
			query = query + " and a.PRIORIDAD = '"+Prioridad+"' ";
		if(OporTon > 0)
			query = query + " and a.OPORTUNIDAD_TONELADAS >= '"+OporTon+"' ";
		if(Status > 0)
			query = query + " and a.ESTATUS = '"+Status+"' ";
		if(Fecha1 != null)
			query = query + " and a.FECHA_CIERRE >= '"+Fecha1+"' ";
		if(Fecha2 != null)
			query = query + " and a.FECHA_CIERRE <= '"+Fecha2+"' ";
		
		query = query + " order by a.FECHA_CIERRE ";
		
		//System.out.println(query);
		
		List<Prospectos_ventas_busqueda> Lista = new ArrayList<Prospectos_ventas_busqueda>(); ;
		List<Object> result = (List<Object>) getSession().createNativeQuery(query).getResultList();
		
		@SuppressWarnings("rawtypes")
		Iterator itr = result.iterator();
		while(itr.hasNext()){
		   Object[] obj = (Object[]) itr.next();
		   Prospectos_ventas_busqueda pvb = new Prospectos_ventas_busqueda();
		   /*System.out.println(String.valueOf(obj[0]));
		   System.out.println(String.valueOf(obj[1]));
		   System.out.println(String.valueOf(obj[2]));
		   System.out.println(String.valueOf(obj[3]));
		   System.out.println(String.valueOf(obj[4]));
		   System.out.println(String.valueOf(obj[5]));
		   System.out.println(String.valueOf(obj[6]));
		   System.out.println(String.valueOf(obj[7]));
		   System.out.println(String.valueOf(obj[8]));*/
		   pvb.setId(Integer.valueOf(String.valueOf(obj[0])));
		   pvb.setCardname(String.valueOf(obj[1]));
		   pvb.setGroupname(String.valueOf(obj[2]));
		   pvb.setContacto(String.valueOf(obj[3]));
		   pvb.setOpor_ton(Double.parseDouble(String.valueOf(obj[4])));
		   pvb.setFecha_cierre(String.valueOf(obj[5]));
		   pvb.setTel(String.valueOf(obj[6]));
		   pvb.setEdo(String.valueOf(obj[7]));
		   pvb.setCiudad(String.valueOf(obj[8]));
		   pvb.setNom_vendedor(String.valueOf(obj[9]));
		   pvb.setPrioridad(Integer.valueOf(String.valueOf(obj[10])));
		   pvb.setPorc_avance(Double.valueOf(String.valueOf(obj[11])));
		   pvb.setEstatus(Integer.valueOf(String.valueOf(obj[12])));
		   pvb.setObservaciones(String.valueOf(obj[13]));
		   pvb.setFecha_insert(String.valueOf(obj[14]));
		   Lista.add(pvb); 
		}
		//System.out.println(Lista.get(0).getId());
		//List<Prospectos_ventas_busqueda> Lista = getSession().createNativeQuery(query).getResultList();
		return Lista;
	}

}
