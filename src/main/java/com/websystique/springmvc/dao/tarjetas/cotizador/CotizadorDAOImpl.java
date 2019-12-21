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
	public void Guardar(Cotizador cot) {
		persist(cot);
	}

	@Override
	public void Actualizar(Cotizador cot) {
		// FIXME Auto-generated method stub
		update(cot);
	}

	@Override
	public List<Cotizador> ListasCotAut(List<ParamsGeneral> params) {
		List<Cotizador> Lista = new ArrayList<Cotizador>();
		Map<String,String> mOrd =  new HashMap<String, String>();
		Lista = criteriaGeneralList(params, mOrd);
		return Lista;
	}

	@Override
	public Integer Maximo(String atributo) {
		return (Integer) Max(atributo);
	}
	

}
