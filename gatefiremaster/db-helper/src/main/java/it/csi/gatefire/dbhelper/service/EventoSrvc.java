package it.csi.gatefire.dbhelper.service;

import java.util.List;

import it.csi.gatefire.dbhelper.model.GatefireLEvento;
import it.csi.gatefire.dbhelper.model.GatefireLEventoStep;
import it.csi.gatefire.dbhelper.model.fc.EventoFilterBean;

public interface EventoSrvc {
	GatefireLEvento insertEvento(GatefireLEvento evento, String jsonInput);

	void closeEvento(GatefireLEvento evento, String stato, String descrizioneEvtlog);

	public GatefireLEventoStep insertEventoLog(GatefireLEventoStep eventoLog);

	public GatefireLEventoStep insertEventoLog(Long idEvento, String tipoPasso, String logState, String msg,
			String retCode, String retMessage);

	public void updateEvento(GatefireLEvento evento);

	GatefireLEvento getEvento(Long id);

	GatefireLEvento getEventoFullById(Long id);

	List<GatefireLEvento> ricercaEventi(EventoFilterBean filterBean);

	public List<String> selectCodiceFiscaleLike(String term);

}
