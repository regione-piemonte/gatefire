package it.csi.gatefire.dbhelper.service;

import java.util.List;

import it.csi.gatefire.dbhelper.model.GatefireDRepository;
import it.csi.gatefire.dbhelper.model.GatefireRRepositoryAccount;
import it.csi.gatefire.dbhelper.model.GatefireRRepositoryUri;

public interface RepoSrvc {
	List<GatefireRRepositoryUri> getRepoUris(String codiceRepository);

	int deleteUri(Long id);

	boolean exist(GatefireRRepositoryUri uri);

	int insertParam(GatefireRRepositoryUri uri);

	int updateUri(GatefireRRepositoryUri uri);

	GatefireRRepositoryUri getUri(Long id);

	GatefireDRepository getRepo(String codice);

	List<GatefireDRepository> getAllRepository();

	int updateRepo(GatefireDRepository repo);

	List<GatefireRRepositoryAccount> getRepoAccounts(String codiceRepository);

	int deleteAccount(Long id);

	int updateAccount(GatefireRRepositoryAccount parametro);

	int insertAccount(GatefireRRepositoryAccount parametro);

	boolean exist(GatefireRRepositoryAccount parametro);

	GatefireRRepositoryAccount getAccount(Long id);
}
