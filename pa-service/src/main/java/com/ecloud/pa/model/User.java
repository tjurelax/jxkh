package com.ecloud.pa.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author ����
 * 
 * �û�
 */
@Entity
@Table(name = "SYS_USER")
public class User extends IdEntity implements UserDetails,Serializable {

	private static final long serialVersionUID = -5221942540542772834L;

	@Column(name = "USERNAME", unique = true, nullable = false, length = 100)
	private String username;
	
	@Column(name = "PASSWORD", nullable = false, length = 100)
	private String password;
	
	@Column(name = "LOCKSTATE")
	private boolean lockState = true;
	
	@Column(name = "ENABLE")
	private boolean enable = true;
	
	@Column(name = "EXPIRYDATE")
	private Date expiryDate;
	
	@Column(name = "CREDENTIALSEXPIRYDATE")
	private Date credentialsExpiryDate;
	
	private Set<Role> roles;
	
	private Set<Group> groups;
	
	/**
	 * @param userName Ҫ���õ� userName
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return lockState
	 */
	public boolean isLockState() {
		return lockState;
	}

	/**
	 * @param lockState Ҫ���õ� lockState
	 */
	public void setLockState(boolean lockState) {
		this.lockState = lockState;
	}

	/**
	 * @return enable
	 */
	public boolean isEnable() {
		return enable;
	}

	/**
	 * @param enable Ҫ���õ� enable
	 */
	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	/**
	 * @return expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate Ҫ���õ� expiryDate
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * @return credentialsExpiryDate
	 */
	public Date getCredentialsExpiryDate() {
		return credentialsExpiryDate;
	}

	/**
	 * @param credentialsExpiryDate Ҫ���õ� credentialsExpiryDate
	 */
	public void setCredentialsExpiryDate(Date credentialsExpiryDate) {
		this.credentialsExpiryDate = credentialsExpiryDate;
	}

	/**
	 * @return roles
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles Ҫ���õ� roles
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	/**
	 * @return groups
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "GROUP_USER", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "GROUP_ID") })
	public Set<Group> getGroups() {
		return groups;
	}

	/**
	 * @param groups Ҫ���õ� groups
	 */
	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}
	
	/**
	 * @param group ��ӵ� group
	 */
	public void setGroup(Group group) {
		if (groups==null) {
			groups= new HashSet<Group>();
		}
		groups.add(group);
	}

	/**
	 * @param password Ҫ���õ� password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @see org.springframework.security.userdetails.UserDetails#getUsername()
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @see org.springframework.security.userdetails.UserDetails#isAccountNonExpired()
	 */
	@Transient
	public boolean isAccountNonExpired() {
		if (expiryDate!=null && expiryDate.before(new Date())) {
			return false;
		}
		return true;
	}

	/**
	 * @see org.springframework.security.userdetails.UserDetails#isAccountNonLocked()
	 */
	@Transient
	public boolean isAccountNonLocked() {
		return lockState;
	}

	/**
	 * @see org.springframework.security.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	@Transient
	public boolean isCredentialsNonExpired() {
		if (credentialsExpiryDate!=null && credentialsExpiryDate.before(new Date())) {
			return false;
		}
		return true;
	}

	/**
	 * @see org.springframework.security.userdetails.UserDetails#isEnabled()
	 */
	@Transient
	public boolean isEnabled() {
		return enable;
	}


	/**
	 * @see org.springframework.security.userdetails.UserDetails#getPassword()
	 */
	public String getPassword() {
		return password;
	}

	@Override
	@Transient
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> grantedAuthoritySet = new HashSet<GrantedAuthority>();
		//��ȡ�û�������ɫ��Ȩ��
		if (roles!=null && !roles.isEmpty()) {
			for (Role role : roles) {
			}
		}
		return grantedAuthoritySet;
	}
}
