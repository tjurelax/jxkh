package com.ecloud.pa.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ecloud.pa.model.Role;

public interface RoleDao extends PagingAndSortingRepository<Role, Long> {

	public Role findByName(String name);
	
}
