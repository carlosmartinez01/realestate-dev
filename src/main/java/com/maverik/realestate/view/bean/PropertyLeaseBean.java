/**
 * 
 */
package com.maverik.realestate.view.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maverik.maverikannotations.sonar.SonarClassExclusion;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
public class PropertyLeaseBean implements Serializable {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 2949351982674083278L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("propertyId")
    private Long propertyId;

    private FileBean fileId;

    private Byte accepted;

    private String creationTime;

    @JsonProperty("landlord")
    private String landlord;

    @JsonProperty("leaseAmount")
    private BigDecimal leaseAmount;

    @JsonProperty("rentCommencementDate")
    private String rentCommencementDate;

    private Integer extension;

    private String deadLineDate;

    private Integer initialTerm;

    private String rofr;

    private String rofo;

    private Integer dueDiligenceTerm;

    private String purchaseOption;

    private String purchaseOptionAfter;

    private Integer numberOfExtensions;

    private Integer extensionLengthOfYears;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Long getPropertyId() {
	return propertyId;
    }

    public void setPropertyId(Long propertyId) {
	this.propertyId = propertyId;
    }

    public Byte getAccepted() {
	return accepted;
    }

    public void setAccepted(Byte accepted) {
	this.accepted = accepted;
    }

    public String getCreationTime() {
	return creationTime;
    }

    public void setCreationTime(String creationTime) {
	this.creationTime = creationTime;
    }

    public String getLandlord() {
	return landlord;
    }

    public void setLandlord(String landlord) {
	this.landlord = landlord;
    }

    public BigDecimal getLeaseAmount() {
	return leaseAmount;
    }

    public void setLeaseAmount(BigDecimal leaseAmount) {
	this.leaseAmount = leaseAmount;
    }

    public String getRentCommencementDate() {
	return rentCommencementDate;
    }

    public void setRentCommencementDate(String rentCommencementDate) {
	this.rentCommencementDate = rentCommencementDate;
    }

    public Integer getExtension() {
	return extension;
    }

    public void setExtension(Integer extension) {
	this.extension = extension;
    }

    public String getDeadLineDate() {
	return deadLineDate;
    }

    public void setDeadLineDate(String deadLineDate) {
	this.deadLineDate = deadLineDate;
    }

    public Integer getInitialTerm() {
	return initialTerm;
    }

    public void setInitialTerm(Integer initialTerm) {
	this.initialTerm = initialTerm;
    }

    @Override
    public int hashCode() {
	return Objects.hash(this.id, this.propertyId);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final PropertyLeaseBean other = (PropertyLeaseBean) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.propertyId, other.propertyId);
    }

    @Override
    public String toString() {
	return "PropertyLeaseBean [id=" + id + ", propertyId=" + propertyId
		+ ", fileId=" + fileId + ", accepted=" + accepted
		+ ", creationTime=" + creationTime + "]";
    }

    public Integer getDueDiligenceTerm() {
	return dueDiligenceTerm;
    }

    public void setDueDiligenceTerm(Integer dueDiligenceTerm) {
	this.dueDiligenceTerm = dueDiligenceTerm;
    }

    public FileBean getFileId() {
	return fileId;
    }

    public void setFileId(FileBean fileId) {
	this.fileId = fileId;
    }

    public String getRofr() {
	return rofr;
    }

    public void setRofr(String rofr) {
	this.rofr = rofr;
    }

    public String getRofo() {
	return rofo;
    }

    public void setRofo(String rofo) {
	this.rofo = rofo;
    }

    public String getPurchaseOption() {
	return purchaseOption;
    }

    public void setPurchaseOption(String purchaseOption) {
	this.purchaseOption = purchaseOption;
    }

    public String getPurchaseOptionAfter() {
	return purchaseOptionAfter;
    }

    public void setPurchaseOptionAfter(String purchaseOptionAfter) {
	this.purchaseOptionAfter = purchaseOptionAfter;
    }

    public Integer getNumberOfExtensions() {
	return numberOfExtensions;
    }

    public void setNumberOfExtensions(Integer numberOfExtensions) {
	this.numberOfExtensions = numberOfExtensions;
    }

    public Integer getExtensionLengthOfYears() {
	return extensionLengthOfYears;
    }

    public void setExtensionLengthOfYears(Integer extensionLengthOfYears) {
	this.extensionLengthOfYears = extensionLengthOfYears;
    }

}
