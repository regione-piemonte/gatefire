package it.csi.gatefire.dbhelper.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.gatefire.dbhelper.dao.GatefireDRepositoryMapper;
import it.csi.gatefire.dbhelper.dao.GatefireRRepositoryAccountMapper;
import it.csi.gatefire.dbhelper.dao.GatefireRRepositoryUriMapper;
import it.csi.gatefire.dbhelper.model.GatefireDRepository;
import it.csi.gatefire.dbhelper.model.GatefireDRepositoryExample;
import it.csi.gatefire.dbhelper.model.GatefireRRepositoryAccount;
import it.csi.gatefire.dbhelper.model.GatefireRRepositoryAccountExample;
import it.csi.gatefire.dbhelper.model.GatefireRRepositoryUri;
import it.csi.gatefire.dbhelper.model.GatefireRRepositoryUriExample;

@Service("repoSrvc")
public class RepoSrvcImpl implements RepoSrvc {

	@Autowired
	private GatefireRRepositoryUriMapper uriMapper;
	@Autowired
	private GatefireDRepositoryMapper repositoryMapper;
	@Autowired
	private GatefireRRepositoryAccountMapper accountMapper;

	@Override
	public List<GatefireRRepositoryUri> getRepoUris(String codiceRepository) {
		GatefireRRepositoryUriExample ex = new GatefireRRepositoryUriExample();
		ex.or().andCodiceRepositoryEqualTo(codiceRepository);

		ex.setOrderByClause("TRANSAZIONE");
		return uriMapper.selectByExample(ex);
	}

	public int deleteUri(Long id) {
		return uriMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateUri(GatefireRRepositoryUri parametro) {
		parametro.setDataAggiornamento(new Date());

		return uriMapper.updateByPrimaryKey(parametro);
	}

	@Override
	public int insertParam(GatefireRRepositoryUri parametro) {
		Date data = new Date();
		parametro.setDataCreazione(data);
		parametro.setDataAggiornamento(data);
		parametro.setDataInizioValidita(data);

		return uriMapper.insert(parametro);
	}

	@Override
	public boolean exist(GatefireRRepositoryUri parametro) {
		GatefireRRepositoryUriExample ex = new GatefireRRepositoryUriExample();

		ex.or().andCodiceRepositoryEqualTo(parametro.getCodiceRepository())
				.andTransazioneEqualTo(parametro.getTransazione());

		return uriMapper.countByExample(ex) > 0;
	}

	@Override
	public GatefireRRepositoryUri getUri(Long id) {
		return uriMapper.selectByPrimaryKey(id);
	}

	public List<GatefireDRepository> getAllRepository() {
		GatefireDRepositoryExample ex = new GatefireDRepositoryExample();
		ex.or().andDataCancellazioneIsNull();

		ex.setOrderByClause("CODICE");
		return repositoryMapper.selectByExample(ex);
	}

	@Override
	public GatefireDRepository getRepo(String codice) {
		return repositoryMapper.selectByPrimaryKey(codice);
	}

	@Override
	public int updateRepo(GatefireDRepository parametro) {
		parametro.setDataAggiornamento(new Date());

		return repositoryMapper.updateByPrimaryKey(parametro);
	}

	@Override
	public List<GatefireRRepositoryAccount> getRepoAccounts(String codiceRepository) {
		GatefireRRepositoryAccountExample ex = new GatefireRRepositoryAccountExample();
		ex.or().andCodiceRepositoryEqualTo(codiceRepository);

		ex.setOrderByClause("CODICE_APPLICAZIONE");
		return accountMapper.selectByExample(ex);
	}

	public int deleteAccount(Long id) {
		return accountMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateAccount(GatefireRRepositoryAccount parametro) {
		parametro.setDataAggiornamento(new Date());

		return accountMapper.updateByPrimaryKey(parametro);
	}

	@Override
	public int insertAccount(GatefireRRepositoryAccount parametro) {
		Date data = new Date();
		parametro.setDataCreazione(data);
		parametro.setDataAggiornamento(data);
		parametro.setDataInizioValidita(data);
		return accountMapper.insert(parametro);
	}

	@Override
	public boolean exist(GatefireRRepositoryAccount parametro) {
		GatefireRRepositoryAccountExample ex = new GatefireRRepositoryAccountExample();

		ex.or().andCodiceRepositoryEqualTo(parametro.getCodiceRepository())
				.andCodiceApplicazioneEqualTo(parametro.getCodiceApplicazione());

		return accountMapper.countByExample(ex) > 0;
	}

	@Override
	public GatefireRRepositoryAccount getAccount(Long id) {
		return accountMapper.selectByPrimaryKey(id);
	}
}
