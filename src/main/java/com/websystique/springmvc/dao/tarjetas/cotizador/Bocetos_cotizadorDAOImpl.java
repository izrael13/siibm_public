package com.websystique.springmvc.dao.tarjetas.cotizador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.websystique.springmvc.dao.AbstractDao;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.tarjetas.cotizador.Bocetos_cotizador;

@Repository("bocetos_cotizadorDAO")
public class Bocetos_cotizadorDAOImpl extends AbstractDao<Integer, Bocetos_cotizador> implements Bocetos_cotizadorDAO{

	@Override
	public Bocetos_cotizador BuscarXId(Integer id) {
		return getByKey(id);
	}

	@Override
	public List<Bocetos_cotizador> BuscarxIdCot(Integer idcot) {
		List<ParamsGeneral> Params = new ArrayList<ParamsGeneral>();
		Params.add(new ParamsGeneral(1,"idcotizacion",idcot,"EQ"));
		Map<String,String> mOrd =  new HashMap<String, String>();
		return criteriaGeneralList(Params,mOrd);
	}

	@Override
	public void Guardar(Bocetos_cotizador Boceto) {
		persist(Boceto);
	}

	@Override
	public void Actualizar(Bocetos_cotizador Boceto) {
		update(Boceto);
	}

	@Override
	public void Eliminar(Bocetos_cotizador Boceto) {
		delete(Boceto);
	}

	@Override
	public Integer Maximo(String atributo) {
		return (Integer) Max(atributo);
	}

	@Override
	public List<Bocetos_cotizador> BuscarxIdCot(List<ParamsGeneral> params) {
		Map<String,String> mOrd =  new HashMap<String, String>();
		return criteriaGeneralList(params, mOrd);
	}

}
