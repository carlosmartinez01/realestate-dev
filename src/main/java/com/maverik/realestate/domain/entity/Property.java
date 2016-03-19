package com.maverik.realestate.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

@SonarClassExclusion
@Entity
@Table(name = "property")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "property")
public class Property implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false, insertable = false)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 256)
    private String name;

    @Column(name = "ADDRESS", nullable = true, length = 256)
    private String address;

    @Column(name = "CITY", nullable = true, length = 128)
    private String city;

    @Column(name = "STATE", nullable = true, length = 2)
    private String state;

    @Column(name = "ZIPCODE", nullable = true, precision = 5, scale = 0)
    private Integer zipCode;

    @Column(name = "STORENUMBER", nullable = true)
    private Integer storeNumber;

    @Column(name = "STOREPHONE", nullable = true, length = 18)
    private String storePhone;

    @Column(name = "STOREFAX", nullable = true, length = 18)
    private String storeFax;

    @Column(name = "ACRES", nullable = true)
    private Integer acres;

    @Column(name = "PARCEL", nullable = true, length = 22)
    private String parcel;

    @Column(name = "SQFT", nullable = true)
    private Integer sqft;

    @Column(name = "PROPERTYPRICE", nullable = true)
    private BigDecimal propertyPrice;

    @Column(name = "LATITUDE", nullable = true, precision = 9, scale = 6)
    private BigDecimal latitude;

    @Column(name = "LONGITUDE", nullable = true, precision = 9, scale = 6)
    private BigDecimal longitude;

    @OneToMany(targetEntity = PropertyNotes.class, mappedBy = "propertyId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PropertyNotes> propertyNotes = new HashSet<PropertyNotes>();

    @OneToMany(mappedBy = "property", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Project> projects = new HashSet<Project>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "property_user_xref", joinColumns = { @JoinColumn(name = "propertyId") }, inverseJoinColumns = { @JoinColumn(name = "userId") })
    private Set<User> users = new HashSet<User>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "company_property_xref", joinColumns = { @JoinColumn(name = "propertyId") }, inverseJoinColumns = { @JoinColumn(name = "companyId") })
    private Set<Company> companies = new HashSet<Company>();

    @Column(name = "STATUS", nullable = false)
    private Byte status;

    @Column(name = "BUDGET", nullable = true)
    private BigDecimal budget;

    @Column(name = "PROJECTEDGALLONS", nullable = true)
    private Integer projectedGallons;

    @Column(name = "PROJECTEDMARGIN", nullable = true)
    private Integer projectedMargin;

    @Column(name = "PROJECTEDSTORESALES", nullable = true)
    private BigDecimal projectedStoreSales;

    @Column(name = "IIR", nullable = true)
    private Integer iir;

    @OneToOne(mappedBy = "propertyId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PropertyContract contractType;

    @OneToOne(mappedBy = "propertyId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PropertyPermitting permitting;

    @OneToMany(mappedBy = "propertyId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PropertyLease> leases = new HashSet<PropertyLease>();

    @OneToMany(mappedBy = "propertyId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PropertyPurchase> purchases = new HashSet<PropertyPurchase>();

    @OneToMany(mappedBy = "propertyId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PermittingAssignmentTask> permittingTasks = new ArrayList<PermittingAssignmentTask>();

    @OneToMany(mappedBy = "propertyId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PermittingContact> permittingContacts = new ArrayList<PermittingContact>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "pictureFilename_id")
    private Filename pictureFileName;

    public void addCompany(Company company) {
	companies.add(company);
    }

    public void addProject(Project project) {
	projects.add(project);
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public BigDecimal getLatitude() {
	return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
	this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
	return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
	this.longitude = longitude;
    }

    @Override
    public int hashCode() {
	return Objects.hash(this.id, this.name);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final Property other = (Property) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.name, other.name);
    }

    public Set<Project> getProjects() {
	return projects;
    }

    public void setProjects(Set<Project> projects) {
	this.projects = projects;
    }

    public Byte getStatus() {
	return status;
    }

    public void setStatus(Byte status) {
	this.status = status;
    }

    public Set<User> getUsers() {
	return users;
    }

    public void setUsers(Set<User> users) {
	this.users = users;
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

    public Integer getStoreNumber() {
	return storeNumber;
    }

    public void setStoreNumber(Integer storeNumber) {
	this.storeNumber = storeNumber;
    }

    public String getStorePhone() {
	return storePhone;
    }

    public void setStorePhone(String storePhone) {
	this.storePhone = storePhone;
    }

    public String getStoreFax() {
	return storeFax;
    }

    public void setStoreFax(String storeFax) {
	this.storeFax = storeFax;
    }

    public Integer getAcres() {
	return acres;
    }

    public void setAcres(Integer acres) {
	this.acres = acres;
    }

    public String getParcel() {
	return parcel;
    }

    public void setParcel(String parcel) {
	this.parcel = parcel;
    }

    public Integer getSqft() {
	return sqft;
    }

    public void setSqft(Integer sqft) {
	this.sqft = sqft;
    }

    public BigDecimal getPropertyPrice() {
	return propertyPrice;
    }

    public void setPropertyPrice(BigDecimal propertyPrice) {
	this.propertyPrice = propertyPrice;
    }

    public Set<PropertyNotes> getPropertyNotes() {
	return propertyNotes;
    }

    public void setPropertyNotes(Set<PropertyNotes> propertyNotes) {
	this.propertyNotes = propertyNotes;
    }

    public BigDecimal getBudget() {
	return budget;
    }

    public void setBudget(BigDecimal budget) {
	this.budget = budget;
    }

    public Integer getProjectedGallons() {
	return projectedGallons;
    }

    public void setProjectedGallons(Integer projectedGallons) {
	this.projectedGallons = projectedGallons;
    }

    public Integer getProjectedMargin() {
	return projectedMargin;
    }

    public void setProjectedMargin(Integer projectedMargin) {
	this.projectedMargin = projectedMargin;
    }

    public BigDecimal getProjectedStoreSales() {
	return projectedStoreSales;
    }

    public void setProjectedStoreSales(BigDecimal projectedStoreSales) {
	this.projectedStoreSales = projectedStoreSales;
    }

    public Integer getIir() {
	return iir;
    }

    public void setIir(Integer iir) {
	this.iir = iir;
    }

    public PropertyContract getContractType() {
	return contractType;
    }

    public void setContractType(PropertyContract contractType) {
	this.contractType = contractType;
    }

    public Set<PropertyLease> getLeases() {
	return leases;
    }

    public void setLeases(Set<PropertyLease> leases) {
	this.leases = leases;
    }

    public Set<PropertyPurchase> getPurchases() {
	return purchases;
    }

    public void setPurchases(Set<PropertyPurchase> purchases) {
	this.purchases = purchases;
    }

    public Filename getPictureFileName() {
	return pictureFileName;
    }

    public void setPictureFileName(Filename pictureFileName) {
	this.pictureFileName = pictureFileName;
    }

    public PropertyPermitting getPermitting() {
	return permitting;
    }

    public void setPermitting(PropertyPermitting permitting) {
	this.permitting = permitting;
    }

    public List<PermittingAssignmentTask> getPermittingTasks() {
	return permittingTasks;
    }

    public void setPermittingTasks(
	    List<PermittingAssignmentTask> permittingTasks) {
	this.permittingTasks = permittingTasks;
    }

    public List<PermittingContact> getPermittingContacts() {
	return permittingContacts;
    }

    public void setPermittingContacts(List<PermittingContact> permittingContacts) {
	this.permittingContacts = permittingContacts;
    }

    public Set<Company> getCompanies() {
	return companies;
    }

    public void setCompanies(Set<Company> companies) {
	this.companies = companies;
    }
}
