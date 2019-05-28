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
		return getByKey(id);
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
		String query = "select a.ID,b.cardname,c.address +' '+c.direccion direccion,a.FECHA_INSERT fecha_insert\r\n" + 
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
			   cb.setId(Integer.valueOf(String.valueOf(obj[0])));
			   cb.setCardname(String.valueOf(obj[1]));
			   cb.setDireccion(String.valueOf(obj[2]));
			   cb.setFecha_insert(String.valueOf(obj[3]));
			   
			   Lista.add(cb); 
			}
		
		return Lista;
	}

}
