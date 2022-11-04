package it.csi.gatefire.dbhelper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import it.csi.gatefire.dbhelper.dao.GatefireDApplicazioneMapper;
import it.csi.gatefire.dbhelper.dao.GatefireDCaCollocazioneDominioMapper;
import it.csi.gatefire.dbhelper.dao.GatefireDCertificationAuthorityMapper;
import it.csi.gatefire.dbhelper.dao.GatefireDRepositoryMapper;
import it.csi.gatefire.dbhelper.dao.GatefireDStatoEventoMapper;
import it.csi.gatefire.dbhelper.dao.GatefireDTipoEventoMapper;
import it.csi.gatefire.dbhelper.dao.GatefireDXdscodeMapper;
import it.csi.gatefire.dbhelper.dao.GatefireRRepositoryAccountMapper;
import it.csi.gatefire.dbhelper.dao.GatefireRRepositoryUriMapper;
import it.csi.gatefire.dbhelper.dao.GatefireTParametroOperativoMapper;
import it.csi.gatefire.dbhelper.model.GatefireDApplicazione;
import it.csi.gatefire.dbhelper.model.GatefireDApplicazioneExample;
import it.csi.gatefire.dbhelper.model.GatefireDCaCollocazioneDominio;
import it.csi.gatefire.dbhelper.model.GatefireDCaCollocazioneDominioExample;
import it.csi.gatefire.dbhelper.model.GatefireDCertificationAuthority;
import it.csi.gatefire.dbhelper.model.GatefireDCertificationAuthorityExample;
import it.csi.gatefire.dbhelper.model.GatefireDRepository;
import it.csi.gatefire.dbhelper.model.GatefireDRepositoryExample;
import it.csi.gatefire.dbhelper.model.GatefireDStatoEvento;
import it.csi.gatefire.dbhelper.model.GatefireDStatoEventoExample;
import it.csi.gatefire.dbhelper.model.GatefireDTipoEvento;
import it.csi.gatefire.dbhelper.model.GatefireDTipoEventoExample;
import it.csi.gatefire.dbhelper.model.GatefireDXdscode;
import it.csi.gatefire.dbhelper.model.GatefireDXdscodeExample;
import it.csi.gatefire.dbhelper.model.GatefireRRepositoryAccount;
import it.csi.gatefire.dbhelper.model.GatefireRRepositoryAccountExample;
import it.csi.gatefire.dbhelper.model.GatefireRRepositoryUri;
import it.csi.gatefire.dbhelper.model.GatefireRRepositoryUriExample;
import it.csi.gatefire.dbhelper.model.GatefireTParametroOperativo;
import it.csi.gatefire.dbhelper.model.GatefireTParametroOperativoExample;

@Service("cachedListsSrvc")
public class CachedListsSrvcImpl extends BaseService implements CachedListsSrvc {

	@Autowired
	private GatefireTParametroOperativoMapper parametroOperativoMapper;

	@Autowired
	private GatefireDTipoEventoMapper tipoEventoMapper;

	@Autowired
	private GatefireDStatoEventoMapper statoEventoMapper;

	@Autowired
	private GatefireDCertificationAuthorityMapper certificationAuthorityMapper;

	@Autowired
	private GatefireDApplicazioneMapper applicazioneMapper;

	@Autowired
	private GatefireDCaCollocazioneDominioMapper dominiMapper;

	@Autowired
	private GatefireDRepositoryMapper repositoryMapper;

	@Autowired
	private GatefireDXdscodeMapper xdsMapper;

	@Autowired
	private GatefireRRepositoryUriMapper repositoryUriMapper;

	@Autowired
	private GatefireRRepositoryAccountMapper repositoryAccountMapper;

	@CacheEvict(value = { "parametriCa", "tipiEvento", "statiEvento", "caList", "appList", "parametriGenerici",
			"domini", "repository", "xdsList", "repositoryUri", "repositoryAccount" }, allEntries = true)
	public int refreshCache() {
		return 1;
	}

	@Cacheable("parametriCa")
	@Override
	public List<GatefireTParametroOperativo> getAllParametriCa() {
		GatefireTParametroOperativoExample ex = new GatefireTParametroOperativoExample();
		ex.or().andCodiceCaIsNotNull().andDataCancellazioneIsNull();
		ex.setOrderByClause("CODICE_CA,parametro");

		return parametroOperativoMapper.selectByExample(ex);
	}

	@Cacheable("parametriGenerici")
	@Override
	public List<GatefireTParametroOperativo> getParametriGenerici() {

		GatefireTParametroOperativoExample ex = new GatefireTParametroOperativoExample();
		ex.or().andCodiceCaIsNull().andDataCancellazioneIsNull();
		ex.setOrderByClause("PARAMETRO");
		return parametroOperativoMapper.selectByExample(ex);
	}

	@Cacheable("tipiEvento")
	@Override
	public List<GatefireDTipoEvento> getTipiEvento() {
		GatefireDTipoEventoExample ex = new GatefireDTipoEventoExample();

		ex.or().andDataCancellazioneIsNull();

		ex.setOrderByClause("descrizione");

		return tipoEventoMapper.selectByExample(ex);
	}

	@Cacheable("statiEvento")
	@Override
	public List<GatefireDStatoEvento> getStatiEvento() {
		GatefireDStatoEventoExample ex = new GatefireDStatoEventoExample();
		ex.or().andDataCancellazioneIsNull();

		return statoEventoMapper.selectByExample(ex);
	}

	@Cacheable("caList")
	@Override
	public List<GatefireDCertificationAuthority> getAllCa() {
		GatefireDCertificationAuthorityExample ex = new GatefireDCertificationAuthorityExample();
		ex.or().andDataCancellazioneIsNull();

		return certificationAuthorityMapper.selectByExample(ex);
	}

	@Cacheable("appList")
	@Override
	public List<GatefireDApplicazione> getAllApplicazioni() {
		GatefireDApplicazioneExample ex = new GatefireDApplicazioneExample();
		ex.or().andDataCancellazioneIsNull();
		ex.setOrderByClause("codice");

		return applicazioneMapper.selectByExample(ex);
	}

	@Cacheable("domini")
	@Override
	public List<GatefireDCaCollocazioneDominio> getAllDomini() {
		GatefireDCaCollocazioneDominioExample ex = new GatefireDCaCollocazioneDominioExample();
		ex.or().andDataCancellazioneIsNull();

		ex.setOrderByClause("CODICE_CA,DOMINIO");
		return dominiMapper.selectByExample(ex);
	}

	@Override
	@Cacheable("repository")
	public List<GatefireDRepository> getAllRepository() {
		GatefireDRepositoryExample ex = new GatefireDRepositoryExample();
		ex.or().andDataCancellazioneIsNull();

		ex.setOrderByClause("CODICE");
		return repositoryMapper.selectByExample(ex);
	}

	@Override
	@Cacheable("xdsList")
	public List<GatefireDXdscode> getAllXdsCodes() {
		GatefireDXdscodeExample ex = new GatefireDXdscodeExample();
		ex.or();

		ex.setOrderByClause("CODE_TYPE");
		return xdsMapper.selectByExample(ex);
	}

	@Override
	@Cacheable("repositoryUri")
	public List<GatefireRRepositoryUri> getAllRepositoryUri() {
		GatefireRRepositoryUriExample ex = new GatefireRRepositoryUriExample();
		ex.or();

		ex.setOrderByClause("CODICE_REPOSITORY");
		return repositoryUriMapper.selectByExample(ex);
	}

	@Override
	@Cacheable("repositoryAccount")
	public List<GatefireRRepositoryAccount> getAllRepositoryAccount() {
		GatefireRRepositoryAccountExample ex = new GatefireRRepositoryAccountExample();
		ex.or();

		ex.setOrderByClause("CODICE_REPOSITORY");
		return repositoryAccountMapper.selectByExample(ex);
	}

}
