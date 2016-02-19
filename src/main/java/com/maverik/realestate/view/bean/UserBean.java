package com.maverik.realestate.view.bean;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

@SonarClassExclusion
public class UserBean implements Serializable {

    private static final long serialVersionUID = -1095856469801874467L;

    private Long id;

    @NotEmpty(message = "Username must not be empty")
    private String username;

    @NotEmpty(message = "Password must not be empty")
    private String password;

    @NotEmpty(message = "First Name must not be empty")
    private String firstName;

    @NotEmpty(message = "Last Name must not be empty")
    private String lastName;

    private String cellPhone;

    private String officePhone;

    private CompanyBean company;

    private Set<String> roles;

    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Email format not valid")
    private String email;

    private Boolean active;

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username.trim();
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public Boolean getActive() {
	return active;
    }

    public void setActive(Boolean active) {
	this.active = active;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getCellPhone() {
	return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
	this.cellPhone = cellPhone;
    }

    public String getOfficePhone() {
	return officePhone;
    }

    public void setOfficePhone(String officePhone) {
	this.officePhone = officePhone;
    }

    public CompanyBean getCompany() {
	return company;
    }

    public void setCompany(CompanyBean company) {
	this.company = company;
    }

    @Override
    public int hashCode() {
	return Objects.hash(this.id, this.username);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final UserBean other = (UserBean) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.username, other.username);
    }

    public Set<String> getRoles() {
	return roles;
    }

    public void setRoles(Set<String> roles) {
	this.roles = roles;
    }

    @Override
    public String toString() {
	return "UserBean [id=" + id + ", username=" + username + ", firstName="
		+ firstName + ", lastName=" + lastName + ", email=" + email
		+ "]";
    }
}
