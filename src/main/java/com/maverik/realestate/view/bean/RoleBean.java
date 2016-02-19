package com.maverik.realestate.view.bean;

import java.io.Serializable;
import java.util.Objects;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

@SonarClassExclusion
public class RoleBean implements Serializable {

    private static final long serialVersionUID = 1848814939893465030L;

    private Long id;

    @NotEmpty(message = "Role must not be empty")
    private String roleName;

    @Length(min = 0, max = 256, message = "The description maximum length is 256 chars")
    private String description;

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
	final RoleBean other = (RoleBean) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.roleName, other.roleName);
    }

    @Override
    public String toString() {
	return "RoleBean [id=" + id + ", role=" + roleName + "]";
    }

}
