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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

@Entity
@Table(name = "company")
@SonarClassExclusion
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "property")
public class Company implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1451988366656926043L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false, insertable = false)
    private Long id;

    @Column(name = "COMPANYNAME", nullable = false, length = 256)
    private String companyName;

    @Column(name = "ADDRESS", nullable = false, length = 128)
    private String address;

    @Column(name = "SUITE", nullable = false, length = 128)
    private String suite;

    @Column(name = "CITY", nullable = false, length = 128)
    private String city;

    @Column(name = "STATE", nullable = false, length = 2)
    private String state;

    @Column(name = "ZIPCODE", nullable = false, precision = 5, scale = 0)
    private Integer zipCode;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<User>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "company_property_xref", joinColumns = { @JoinColumn(name = "companyId") }, inverseJoinColumns = { @JoinColumn(name = "propertyId") })
    private Set<Property> properties = new HashSet<Property>();

    @Column(name = "ACTIVE", nullable = false)
    private Boolean active;

    @Column(name = "type")
    private String type;

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

    public Set<User> getUsers() {
	return users;
    }

    public void setUsers(Set<User> users) {
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
	final Company other = (Company) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.companyName, other.companyName);
    }

    public Set<Property> getProperties() {
	return properties;
    }

    public void setProperties(Set<Property> properties) {
	this.properties = properties;
    }

    /**
     * @return the type
     */
    public String getType() {
	return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
	this.type = type;
    }
}
