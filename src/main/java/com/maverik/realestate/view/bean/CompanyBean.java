package com.maverik.realestate.view.bean;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

@SonarClassExclusion
public class CompanyBean implements Serializable {

    private static final long serialVersionUID = -3676469129470887974L;

    private Long id;

    @NotEmpty(message = "Company name must not be empty")
    private String companyName;

    @NotEmpty(message = "Address must not be empty")
    private String address;

    private String suite;

    @NotEmpty(message = "City must not be empty")
    private String city;

    @NotEmpty(message = "State must not be empty")
    @Length(min = 2, max = 2, message = "State must be 2 chars")
    private String state;

    @Range(min = 500, max = 99999, message = "Zip code is not valid")
    private Integer zipCode;

    private Set<UserBean> users;

    private Boolean active;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getCompanyName() {
	return companyName;
    }

    public void setCompanyName(String companyName) {
	this.companyName = companyName;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public String getSuite() {
	return suite;
    }

    public void setSuite(String suite) {
	this.suite = suite;
    }

    public String getCity() {
	return city;
    }

    public void setCity(String city) {
	this.city = city;
    }

    public String getState() {
	return state;
    }

    public void setState(String state) {
	this.state = state;
    }

    public Integer getZipCode() {
	return zipCode;
    }

    public void setZipCode(Integer zipCode) {
	this.zipCode = zipCode;
    }

    public Set<UserBean> getUsers() {
	return users;
    }

    public void setUsers(Set<UserBean> users) {
	this.users = users;
    }

    public Boolean getActive() {
	return active;
    }

    public void setActive(Boolean active) {
	this.active = active;
    }

    @Override
    public int hashCode() {
	return Objects.hash(this.id, this.companyName);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final CompanyBean other = (CompanyBean) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.companyName, other.companyName);
    }

    @Override
    public String toString() {
	return "CompanyBean [id=" + id + ", companyName=" + companyName + "]";
    }
}
