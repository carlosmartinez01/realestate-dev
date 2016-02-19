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
@Table(name = "property_purchase")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "property")
public class PropertyPurchase implements Serializable {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = -1854051126951024846L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROPERTYPURCHASEID", nullable = false, updatable = false, insertable = false)
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

    @Column(name = "EFFECTIVEDATE", nullable = true)
    private Date effectiveDate;

    @Column(name = "PRICE", nullable = true)
    private BigDecimal price;

    @Column(name = "DUEDILIGENCETERM", nullable = true)
    private Integer dueDiligenceTerm;

    @Column(name = "DEADLINEDATE", nullable = true)
    private Date deadLineDate;

    @Column(name = "CLOSINGTERM", nullable = true)
    private Integer closingTerm;

    @Column(name = "CLOSINGDATE", nullable = true)
    private Date closingDate;

    @OneToMany(mappedBy = "propertyPurchaseId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PurchaseExtension> extensions = new ArrayList<PurchaseExtension>();

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

    public Date getEffectiveDate() {
	return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
	this.effectiveDate = effectiveDate;
    }

    public BigDecimal getPrice() {
	return price;
    }

    public void setPrice(BigDecimal price) {
	this.price = price;
    }

    public Integer getDueDiligenceTerm() {
	return dueDiligenceTerm;
    }

    public void setDueDiligenceTerm(Integer dueDiligenceTerm) {
	this.dueDiligenceTerm = dueDiligenceTerm;
    }

    public Date getDeadLineDate() {
	return deadLineDate;
    }

    public void setDeadLineDate(Date deadLineDate) {
	this.deadLineDate = deadLineDate;
    }

    public Integer getClosingTerm() {
	return closingTerm;
    }

    public void setClosingTerm(Integer closingTerm) {
	this.closingTerm = closingTerm;
    }

    public Date getClosingDate() {
	return closingDate;
    }

    public void setClosingDate(Date closingDate) {
	this.closingDate = closingDate;
    }

    @Override
    public String toString() {
	return "PropertyPurchase [id=" + id + ", propertyId=" + propertyId
		+ ", fileId=" + fileId + ", accepted=" + accepted
		+ ", creationTime=" + creationTime + "]";
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
	final PropertyPurchase other = (PropertyPurchase) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.propertyId, other.propertyId);
    }

    public List<PurchaseExtension> getExtensions() {
	return extensions;
    }

    public void setExtensions(List<PurchaseExtension> extensions) {
	this.extensions = extensions;
    }

    public Filename getFileId() {
	return fileId;
    }

    public void setFileId(Filename fileId) {
	this.fileId = fileId;
    }
}
