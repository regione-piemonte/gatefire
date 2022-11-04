package it.csi.gatefire.dbhelper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.gatefire.dbhelper.dao.GatefireRCaFunzioneMapper;
import it.csi.gatefire.dbhelper.model.GatefireRCaFunzioneExample;

@Service("verifySrvc")
public class VerifySrvcImpl implements VerifySrvc {
	@Autowired
	GatefireRCaFunzioneMapper gatefireRCaFunzioneMapper;

	@Override
	public boolean checkfunzioneCa(String codiceCa, String codiceFunzione) {
		GatefireRCaFunzioneExample ex = new GatefireRCaFunzioneExample();
		ex.or().andCodiceCaEqualTo(codiceCa).andCodiceFunzioneEqualTo(codiceFunzione).andDataCancellazioneIsNull();

		return (gatefireRCaFunzioneMapper.countByExample(ex) > 0);
	}

}
