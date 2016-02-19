package com.maverik.realestate.domain.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

@Entity
@Table(name = "user_roles")
@SonarClassExclusion
public class UserRoles implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false, insertable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLEID")
    private Role roleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERNAMEID")
    private User usernameId;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Role getRoleId() {
	return roleId;
    }

    public void setRoleId(Role roleId) {
	this.roleId = roleId;
    }

    public User getUsernameId() {
	return usernameId;
    }

    public void setUsernameId(User usernameId) {
	this.usernameId = usernameId;
    }

    @Override
    public int hashCode() {
	return Objects.hash(this.roleId, this.usernameId);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final UserRoles other = (UserRoles) obj;
	return Objects.equals(this.roleId, other.roleId)
		&& Objects.equals(this.usernameId, other.usernameId);
    }
}
