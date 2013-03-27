package kr.co.pdca.core.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.pdca.core.mapper.normal.InitializationMapper;
import kr.co.pdca.security.entity.UserRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CoreService {
	@Autowired
	InitializationMapper init;

	@Autowired
	Md5PasswordEncoder passwordEncoder;

	public void doInitializationDatabase() {
		if (!doCheckTables()) {
			init.doCreateTable_INIT();
			init.doCreateTable_AUTH();
			init.doCreateTable_AUTH_ROLE();

			// INSERT AUTH ROLES
			List<UserRole> initRole = new ArrayList<UserRole>();
			initRole.add((new UserRole("goodwilldd", "ROLE_SUPERROOKIE")));
			initRole.add((new UserRole("goodwilldd", "ROLE_USER")));
			initRole.add((new UserRole("bob", "ROLE_USER")));
			for (UserRole user : initRole) {
				init.doInsertDefaultRole(user);
			}

			List<Map<String, String>> datas = new ArrayList<Map<String, String>>();
			Map<String, String> data = new HashMap<String, String>();

			data.put("username", "bob");
			data.put("upassword", passwordEncoder.encodePassword("bob", null));
			datas.add(data);

			data = new HashMap<String, String>();
			data.put("username", "goodwilldd");
			data.put("upassword", passwordEncoder.encodePassword("goodwilldd", null));
			datas.add(data);
			for (Map<String, String> t : datas) {
				init.doDefaultDatas(t);
			}
		}
	}

	@Transactional(readOnly = true)
	public boolean doCheckTables() {
		try {
			init.doCheckTables();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
