package it.csi.gatefire.gateway.service.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import it.csi.gatefire.common.model.Result;
import it.csi.gatefire.common.util.ErrorHelper;
import it.csi.gatefire.dbhelper.service.BaseService;
import it.csi.gatefire.dbhelper.service.CachedListsSrvc;
import it.csi.gatefire.dbhelper.service.ParametriSrvc;

@Service("paramsWSSrvc")
public class ParamsWSSrvcImpl extends BaseService implements ParamsRestWSSrvc {

	@Autowired
	CachedListsSrvc cachedListsSrvc;

	@Autowired
	ParametriSrvc parametriSrvc;

	@Override
	public Result refreshCache(String mode) {

		if (!StringUtils.hasLength(mode)) {
			mode = "A";
		}
		String msg = "";
		boolean ok = true;
		try {

			if ("A".equalsIgnoreCase(mode) || "P".equalsIgnoreCase(mode)) {
				cachedListsSrvc.refreshCache();
				msg += " - Parametri ricaricati con successo";
			}

		} catch (Exception e) {
			msg += "Impossibile ricaricare i parametri: " + e.getMessage();
			ok = false;

		}
		if (ok) {
			try {

				if ("A".equalsIgnoreCase(mode) || "M".equalsIgnoreCase(mode)) {
					reloadMessages();
					msg += " - Messaggi ricaricati con successo";
				}

			} catch (Exception e) {
				msg += "Impossibile ricaricare i messaggi " + e.getMessage();
				ok = false;

			}
		}
		Result result = new Result();

		if (!ok) {
			result.setErrorCode(ErrorHelper.ERRORE_GENERICO);
		}
		result.setMessage(msg);
		return result;
	}

	@Override
	public Result checkDb() {

		Result result = new Result();
		try {
			parametriSrvc.countParams();
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			result.setErrorCode(ErrorHelper.ERRORE_CONNESSIONE_DB);
		}
		return result;
	}

}
