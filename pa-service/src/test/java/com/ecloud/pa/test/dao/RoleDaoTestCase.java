/**
 * 
 */
package com.ecloud.pa.test.dao;

import javax.annotation.Resource;

import org.hibernate.dialect.H2Dialect;
import org.junit.Test;

import com.ecloud.pa.dao.RoleDao;
import com.ecloud.pa.model.Role;
import com.ecloud.pa.test.spring.SpringContextTestCase;

/**
 * @author ’≈Ô«
 *
 */
public class RoleDaoTestCase extends SpringContextTestCase{
	
	@Resource
	private RoleDao roleDao;
	
	@Test
	public void testSave() {
		Role role = new Role();
		role.setName("a1");
		roleDao.save(role);
		role = new Role();
		role.setName("b2");
		roleDao.save(role);
		role = new Role();
		role.setName("c3");
		roleDao.save(role);
		role = new Role();
		role.setName("d4");
		roleDao.save(role);
/*		Iterator<Role> it = roleDao.findAll().iterator();
		Role r = it.next();
		System.err.println(r.getId());
		r = it.next();
		System.err.println(r.getId());
		r = it.next();
		System.err.println(r.getId());
		r = it.next();
		System.err.println(r.getId());*/
		System.err.println(roleDao.findAll());
		System.err.println(roleDao.findByName("c3"));
	}
	
public static void main(String[] args) {
	System.err.println(H2Dialect.class.getName());
}
}
