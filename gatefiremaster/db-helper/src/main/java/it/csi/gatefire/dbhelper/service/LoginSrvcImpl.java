package it.csi.gatefire.dbhelper.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.csi.gatefire.common.web.model.Role;
import it.csi.gatefire.common.web.model.User;
import it.csi.gatefire.dbhelper.dao.GatefireTAmministratoreMapper;
import it.csi.gatefire.dbhelper.dao.GatefireTRuoloMapper;
import it.csi.gatefire.dbhelper.model.GatefireTAmministratore;
import it.csi.gatefire.dbhelper.model.GatefireTAmministratoreExample;
import it.csi.gatefire.dbhelper.model.GatefireTRuolo;

@Service("loginSrvc")
public class LoginSrvcImpl implements LoginSrvc {

	@Autowired
	private GatefireTAmministratoreMapper amministratoreMapper;

	@Autowired
	private GatefireTRuoloMapper ruoloMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		GatefireTAmministratoreExample ex = new GatefireTAmministratoreExample();
		ex.or().andEmailEqualTo(username);

		List<GatefireTAmministratore> list = amministratoreMapper.selectByExample(ex);

		if (!list.isEmpty()) {

			GatefireTAmministratore appUtente = list.get(0);
			User user = new User();
			user.setUsername(username);
			user.setPassword(appUtente.getPassword());
			user.setNome(appUtente.getNome());
			user.setCognome(appUtente.getCognome());
			user.setEmail(appUtente.getEmail());
			user.setDataFineValidita(appUtente.getDataFineValidita());
			List<Role> roleList = new ArrayList<>();

			List<GatefireTRuolo> ruoloList = ruoloMapper.getRuoliAmministratore(username);
			for (GatefireTRuolo appRuolo : ruoloList) {
				Role role = new Role();

				role.setCodice(appRuolo.getCodice());

				roleList.add(role);

			}
			user.setRoleList(roleList);
			return user;
		}
		throw new UsernameNotFoundException("Utente inesistente");

	}

	@Override
	public User fillUserData(String username) {

		GatefireTAmministratoreExample ex = new GatefireTAmministratoreExample();
		ex.or().andEmailEqualTo(username);

		List<GatefireTAmministratore> list = amministratoreMapper.selectByExample(ex);

		if (!list.isEmpty()) {

			GatefireTAmministratore appUtente = list.get(0);
			User user = new User();
			user.setUsername(appUtente.getEmail());
			user.setPassword(appUtente.getPassword());
			user.setNome(appUtente.getNome());
			user.setCognome(appUtente.getCognome());
			user.setEmail(appUtente.getEmail());
			user.setDataFineValidita(appUtente.getDataFineValidita());
			List<Role> roleList = new ArrayList<>();

			List<GatefireTRuolo> ruoloList = ruoloMapper.getRuoliAmministratore(username);
			for (GatefireTRuolo appRuolo : ruoloList) {
				Role role = new Role();

				role.setCodice(appRuolo.getCodice());

				roleList.add(role);

			}
			user.setRoleList(roleList);
			return user;
		}
		throw new UsernameNotFoundException("Utente inesistente");

	}

}
