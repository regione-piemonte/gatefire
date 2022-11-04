package it.csi.gatefire.dbhelper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.gatefire.dbhelper.dao.GatefireTParametroOperativoMapper;
import it.csi.gatefire.dbhelper.model.GatefireTParametroOperativo;
import it.csi.gatefire.dbhelper.model.GatefireTParametroOperativoExample;

@Service("caSrvc")
public class CaSrvcImpl extends BaseService implements CaSrvc {

	@Autowired
	private GatefireTParametroOperativoMapper parametroMapper;

	public List<GatefireTParametroOperativo> getParametriCa(String codiceCa) {
		GatefireTParametroOperativoExample ex = new GatefireTParametroOperativoExample();
		ex.or().andCodiceCaEqualTo(codiceCa).andDataCancellazioneIsNull();
		ex.setOrderByClause("PARAMETRO");
		return parametroMapper.selectByExample(ex);
	}

}
