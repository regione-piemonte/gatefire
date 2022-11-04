package it.csi.gatefire.dbhelper.service;

import java.util.List;

import it.csi.gatefire.dbhelper.model.GatefireDMessaggio;

public interface MessageResourceSrvc {
	List<GatefireDMessaggio> loadAllMessages();
}
