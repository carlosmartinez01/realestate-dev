package com.maverik.realestate.domain.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

@Entity
@Table(name = "roles")
@SonarClassExclusion
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false, insertable = false)
    private Long id;

    @Column(name = "ROLE", nullable = false, length = 200)
    private String roleName;

    @Column(name = "DESCRIPTION", nullable = false, length = 256)
    private String description;

    @OneToMany(mappedBy = "roleId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserRoles> userRoles = new HashSet<UserRoles>();

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getRoleName() {
	return roleName;
    }

    public void setRoleName(String roleName) {
	this.roleName = roleName;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public Set<UserRoles> getUserRoles() {
	return userRoles;
    }

    public void setUserRoles(Set<UserRoles> userRoles) {
	this.userRoles = userRoles;
    }

    @Override
    public int hashCode() {
	return Objects.hash(this.id, this.roleName);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final Role other = (Role) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.roleName, other.roleName);
    }
}
