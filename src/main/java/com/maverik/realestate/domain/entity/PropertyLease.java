/**
 * 
 */
package com.maverik.realestate.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
@Entity
@Table(name = "property_lease")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "property")
public class PropertyLease implements Serializable {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = -4747264763694474005L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROPERTYLEASEID", nullable = false, updatable = false, insertable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "PROPERTYID", nullable = true)
    private Property propertyId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "filename_id")
    private Filename fileId;

    @Column(name = "ACCEPTED", nullable = false)
    private Byte accepted;

    @Column(name = "CREATIONTIME", nullable = false, updatable = false, insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;

    @Column(name = "LANDLORD", nullable = true)
    private String landlord;

    @Column(name = "LEASEAMOUNT", nullable = true)
    private BigDecimal leaseAmount;

    @Column(name = "RENTCOMMENCEMENTDATE", nullable = true)
    private Date rentCommencementDate;

    @Column(name = "EXTENSION", nullable = true)
    private Integer extension;

    @Column(name = "DEADLINEDATE", nullable = true)
    private Date deadLineDate;

    @Column(name = "INITIALTERM", nullable = true)
    private Integer initialTerm;

    @Column(name = "ROFR", nullable = true)
    private String rofr;

    @Column(name = "ROFO", nullable = true)
    private String rofo;

    @Column(name = "DUEDILIGENCETERM", nullable = true)
    private Integer dueDiligenceTerm;

    @Column(name = "purchaseOption", nullable = true)
    private String purchaseOption;

    @Column(name = "purchaseOptionAfter", nullable = true)
    private String purchaseOptionAfter;

    @Column(name = "numberOfExtensions", nullable = true)
    private Integer numberOfExtensions;

    @Column(name = "extensionLengthOfYears", nullable = true)
    private Integer extensionLengthOfYears;

    @OneToMany(mappedBy = "propertyLeaseId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<LeaseExtension> extensions = new ArrayList<LeaseExtension>();

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Property getPropertyId() {
	return propertyId;
    }

    public void setPropertyId(Property propertyId) {
	this.propertyId = propertyId;
    }

    public Byte getAccepted() {
	return accepted;
    }

    public void setAccepted(Byte accepted) {
	this.accepted = accepted;
    }

    public Date getCreationTime() {
	return creationTime;
    }

    public void setCreationTime(Date creationTime) {
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

    public Date getRentCommencementDate() {
	return rentCommencementDate;
    }

    public void setRentCommencementDate(Date rentCommencementDate) {
	this.rentCommencementDate = rentCommencementDate;
    }

    public Integer getExtension() {
	return extension;
    }

    public void setExtension(Integer extension) {
	this.extension = extension;
    }

    public Date getDeadLineDate() {
	return deadLineDate;
    }

    public void setDeadLineDate(Date deadLineDate) {
	this.deadLineDate = deadLineDate;
    }

    public Integer getInitialTerm() {
	return initialTerm;
    }

    public void setInitialTerm(Integer initialTerm) {
	this.initialTerm = initialTerm;
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
	final PropertyLease other = (PropertyLease) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.propertyId, other.propertyId);
    }

    @Override
    public String toString() {
	return "PropertyLease [id=" + id + ", propertyId=" + propertyId
		+ ", fileId=" + fileId + ", accepted=" + accepted
		+ ", creationTime=" + creationTime + "]";
    }

    public List<LeaseExtension> getExtensions() {
	return extensions;
    }

    public void setExtensions(List<LeaseExtension> extensions) {
	this.extensions = extensions;
    }

    public Integer getDueDiligenceTerm() {
	return dueDiligenceTerm;
    }

    public void setDueDiligenceTerm(Integer dueDiligenceTerm) {
	this.dueDiligenceTerm = dueDiligenceTerm;
    }

    public String getPurchaseOption() {
	return purchaseOption;
    }

    public void setPurchaseOption(String purchaseOption) {
	this.purchaseOption = purchaseOption;
    }

    public Filename getFileId() {
	return fileId;
    }

    public void setFileId(Filename fileId) {
	this.fileId = fileId;
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
