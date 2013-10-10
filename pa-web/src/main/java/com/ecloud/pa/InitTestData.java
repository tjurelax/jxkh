package com.ecloud.pa;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ecloud.pa.dao.RoleDao;
import com.ecloud.pa.model.Role;
import com.ecloud.pa.model.e.RoleDataScope;

@Component
public class InitTestData {

	@Resource
	private RoleDao roleDao;

	public void execute() {
		List<Role> roleList = new ArrayList<Role>();
		for (int i = 0; i < 1000; i++) {
			Role role = new Role();
			role.setName("��ɫ" + i);
			role.setDataScope(RoleDataScope.ȫ������);
			role.setRemark("��������" + i);
			roleList.add(role);
		}
		roleDao.save(roleList);
	}
}
