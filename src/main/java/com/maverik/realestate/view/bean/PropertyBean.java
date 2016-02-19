package com.maverik.realestate.view.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

@SonarClassExclusion
public class PropertyBean implements Serializable {

    private static final long serialVersionUID = 3027661179910884691L;

    private Long id;

    @NotEmpty(message = "Property name must not be empty")
    private String name;

    private String address;

    @Digits(integer = 3, fraction = 6, message = "Precision is 9 and Scale is 6 - [XXX.XXXXX]")
    private BigDecimal latitude;

    @Digits(integer = 3, fraction = 6, message = "Precision is 9 and Scale is 6 - [XXX.XXXXX]")
    private BigDecimal longitude;

    private List<NoteBean> notes;

    private Byte status;

    private UserBean userId;

    private String city;

    @Length(min = 2, max = 2, message = "Choose a state")
    private String state;

    private Integer zipCode;

    private Integer storeNumber;

    private String storePhone;

    private String storeFax;

    private Integer acres;

    private String parcel;

    private Integer sqft;

    private BigDecimal propertyPrice;

    private BigDecimal budget;

    private Integer projectedGallons;

    private Integer projectedMargin;

    private BigDecimal projectedStoreSales;

    private Integer iir;

    private PropertyContractBean contractType;

    private PropertyPermittingBean permitting;

    private FileBean pictureFileName;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name.trim();
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	if (address.length() > 0) {
	    this.address = address.trim();
	}
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
	final PropertyBean other = (PropertyBean) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
	return "PropertyBean [id=" + id + ", name=" + name + "]";
    }

    public List<NoteBean> getNotes() {
	return notes;
    }

    public void setNotes(List<NoteBean> notes) {
	this.notes = notes;
    }

    public Byte getStatus() {
	return status;
    }

    public void setStatus(Byte status) {
	this.status = status;
    }

    public UserBean getUserId() {
	return userId;
    }

    public void setUserId(UserBean userId) {
	this.userId = userId;
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

    public PropertyContractBean getContractType() {
	return contractType;
    }

    public void setContractType(PropertyContractBean contractType) {
	this.contractType = contractType;
    }

    public FileBean getPictureFileName() {
	return pictureFileName;
    }

    public void setPictureFileName(FileBean pictureFileName) {
	this.pictureFileName = pictureFileName;
    }

    public PropertyPermittingBean getPermitting() {
	return permitting;
    }

    public void setPermitting(PropertyPermittingBean permitting) {
	this.permitting = permitting;
    }
}
