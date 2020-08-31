package com.websystique.springmvc.service.certificados;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.websystique.springmvc.dao.certificados.CertificadosDAO;
import com.websystique.springmvc.dao.certificados.LiberacioncmDAO;
import com.websystique.springmvc.dao.certificados.Remisiones_para_certificadoDAO;
import com.websystique.springmvc.dao.certificados.Tf_para_certificadoDAO;
import com.websystique.springmvc.model.ParamsGeneral;
import com.websystique.springmvc.model.certificados.Certificados;
import com.websystique.springmvc.model.certificados.Liberacioncm;
import com.websystique.springmvc.model.certificados.Remisiones_para_certificado;

@Service("certificadosAllDataService")
@Transactional
public class CertificadosAllDataServiceImpl implements CertificadosAllDataService{
	
	@Autowired CertificadosDAO CertDAO;
	@Autowired Remisiones_para_certificadoDAO RemDao;
	@Autowired LiberacioncmDAO lcmDao;
	@Autowired Tf_para_certificadoDAO tfsDao;
	
	@SuppressWarnings("rawtypes")
	@Override
	public JSONObject BuscarXIDCert(Integer id) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().setDateFormat("yyyy-MM-dd HH:mm").create();
		Certificados cer = new Certificados();
		cer = CertDAO.BuscarxId(id);
		JSONObject JsonCert = new JSONObject(gson.toJson(cer));
		
		List<ParamsGeneral> ParamsR = new ArrayList<ParamsGeneral>();
		ParamsR.add(new ParamsGeneral(1,"numatcard",cer.getRemision(),"EQ"));
		ParamsR.add(new ParamsGeneral(1,"tfi",cer.getFolio_tf(),"EQ"));
		ParamsR.add(new ParamsGeneral(1,"itemcodesi",cer.getItemcodecert(),"EQ"));
		Remisiones_para_certificado rem = new Remisiones_para_certificado();
		rem = RemDao.BuscarXParamsObj(ParamsR);
		JSONObject JsonRem = new JSONObject(gson.toJson(rem));
		
		Liberacioncm lcm = new Liberacioncm();
		lcm = lcmDao.BuscarxId(cer.getItemcodecert());
		/*JSONObject JsonLcm;
		if(lcm == null)
			 JsonLcm = new JSONObject();
		else
			JsonLcm = new JSONObject(gson.toJson(lcm));*/

		List<ParamsGeneral> ParamsT = new ArrayList<ParamsGeneral>();
		ParamsT.add(new ParamsGeneral(1,"itemcode",rem.getItemcodesi(),"EQ"));
		ParamsT.add(new ParamsGeneral(1,"tf",rem.getTfi(),"EQ"));
		JSONObject JsonTfs = new JSONObject(gson.toJson(tfsDao.BuscarxParams(ParamsT)));

		Iterator itRem = JsonRem.keys();
		//Iterator itLcm = JsonLcm.keys();
		Iterator itTfs = JsonTfs.keys();

		while(itRem.hasNext()) {
            String key = (String) itRem.next();
            JsonCert.put(key, JsonRem.get(key));
        }

		/*while(itLcm.hasNext()) {
            String key = (String) itLcm.next();
            JsonCert.put(key, JsonLcm.get(key));
        }*/
		if(lcm != null)
		{
			JsonCert.put("tf_lcm", lcm.getTf_lcm());
			JsonCert.put("itemcode_lcm", lcm.getItemcode_lcm());
			JsonCert.put("a", lcm.getA());
			JsonCert.put("b", lcm.getB());
			JsonCert.put("c", lcm.getC());
			JsonCert.put("d", lcm.getD());
			JsonCert.put("e", lcm.getE());
			JsonCert.put("f", lcm.getF());
			JsonCert.put("g", lcm.getG());
			JsonCert.put("h", lcm.getH());
			JsonCert.put("i", lcm.getI());
			JsonCert.put("j", lcm.getJ());
			JsonCert.put("k", lcm.getK());
			JsonCert.put("l", lcm.getL());
			JsonCert.put("m", lcm.getM());
			JsonCert.put("n", lcm.getN());
			JsonCert.put("observaciones_lcm", lcm.getObservaciones_lcm());
			JsonCert.put("dir_file", lcm.getDir_file());		
			JsonCert.put("activo", lcm.getActivo());
		}
		while(itTfs.hasNext()) {
            String key = (String) itTfs.next();
            JsonCert.put(key, JsonTfs.get(key));
        }
		//System.out.println(JsonCert.toString());
		return JsonCert;
	}

}
