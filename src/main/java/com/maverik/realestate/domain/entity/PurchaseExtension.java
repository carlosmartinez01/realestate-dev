/**
 * 
 */
package com.maverik.realestate.domain.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
@Entity
@Table(name = "purchase_extension")
public class PurchaseExtension implements Serializable {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 609908605234717758L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PURCHASEEXTENSIONID", nullable = false, updatable = false, insertable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "PROPERTYPURCHASEID", nullable = true)
    private PropertyPurchase propertyPurchaseId;

    @Column(name = "EXTENSIONDAYS", nullable = false, length = 4000)
    private Integer extensionDays;

    @Column(name = "CREATIONTIME", nullable = false, updatable = false, insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;

    @Override
    public int hashCode() {
	return Objects.hash(this.id, this.propertyPurchaseId);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final PurchaseExtension other = (PurchaseExtension) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.propertyPurchaseId,
			other.propertyPurchaseId);
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public PropertyPurchase getPropertyPurchaseId() {
	return propertyPurchaseId;
    }

    public void setPropertyPurchaseId(PropertyPurchase propertyPurchaseId) {
	this.propertyPurchaseId = propertyPurchaseId;
    }

    public Integer getExtensionDays() {
	return extensionDays;
    }

    public void setExtensionDays(Integer extensionDays) {
	this.extensionDays = extensionDays;
    }

    public Date getCreationTime() {
	return creationTime;
    }

    public void setCreationTime(Date creationTime) {
	this.creationTime = creationTime;
    }

    @Override
    public String toString() {
	return "PurchaseExtension [id=" + id + ", propertyPurchaseId="
		+ propertyPurchaseId + ", extensionDays=" + extensionDays
		+ ", creationTime=" + creationTime + "]";
    }

}
