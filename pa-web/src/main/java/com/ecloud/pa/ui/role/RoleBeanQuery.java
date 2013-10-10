/**
 * 
 */
package com.ecloud.pa.ui.role;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.vaadin.addons.lazyquerycontainer.AbstractBeanQuery;
import org.vaadin.addons.lazyquerycontainer.QueryDefinition;

import ru.xpoft.vaadin.SpringApplicationContext;

import com.ecloud.pa.dao.RoleDao;
import com.ecloud.pa.model.Role;

/**
 * @author ’≈Ô«
 * 
 */
public class RoleBeanQuery extends AbstractBeanQuery<Role> {

	private RoleDao roleDao;

	public RoleBeanQuery(final QueryDefinition queryDefinition, final Map<String, Object> queryConfiguration, final Object[] sortPropertyIds, final boolean[] sortStates) {
		super(queryDefinition, queryConfiguration, sortPropertyIds, sortStates);
		roleDao = SpringApplicationContext.getApplicationContext().getBean(RoleDao.class);
	}

	public RoleBeanQuery() {
		roleDao = SpringApplicationContext.getApplicationContext().getBean(RoleDao.class);
	}

	@Override
	protected Role constructBean() {
		return new Role();
	}

	@Override
	protected List<Role> loadBeans(int startIndex, int count) {
		return roleDao.findAll(new PageRequest(startIndex / count, count)).getContent();
	}

	@Override
	protected void saveBeans(List<Role> arg0, List<Role> addedTasks, List<Role> modifiedTasks) {
	}

	@Override
	public int size() {
		return (int) roleDao.count();
	}

}
