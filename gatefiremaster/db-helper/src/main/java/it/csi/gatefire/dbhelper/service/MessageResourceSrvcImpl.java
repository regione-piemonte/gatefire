package it.csi.gatefire.dbhelper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.gatefire.dbhelper.dao.GatefireDMessaggioMapper;
import it.csi.gatefire.dbhelper.model.GatefireDMessaggio;
import it.csi.gatefire.dbhelper.model.GatefireDMessaggioExample;

@Service("messageResourceSrvc")
public class MessageResourceSrvcImpl implements MessageResourceSrvc {

	@Autowired
	private GatefireDMessaggioMapper gatefireDMessaggioMapper;

	@Override
	public List<GatefireDMessaggio> loadAllMessages() {
		GatefireDMessaggioExample ex = new GatefireDMessaggioExample();
		ex.or();

		return gatefireDMessaggioMapper.selectByExample(ex);
	}

}
