package it.csi.gatefire.dbhelper.service;

import java.util.List;

import it.csi.gatefire.dbhelper.model.GatefireTParametroOperativo;

public interface CaSrvc {
	

	List<GatefireTParametroOperativo> getParametriCa(String codiceCa);
	
	

}