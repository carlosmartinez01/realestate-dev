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
@Table(name = "lease_extension")
public class LeaseExtension implements Serializable {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = -8853418848433471471L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LEASEEXTENSIONID", nullable = false, updatable = false, insertable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "PROPERTYLEASEID", nullable = true)
    private PropertyLease propertyLeaseId;

    @Column(name = "EXTENSIONDAYS", nullable = false, length = 4000)
    private Integer extensionDays;

    @Column(name = "CREATIONTIME", nullable = false, updatable = false, insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
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
    public int hashCode() {
	return Objects.hash(this.id, this.propertyLeaseId);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final LeaseExtension other = (LeaseExtension) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.propertyLeaseId, other.propertyLeaseId);
    }

    public PropertyLease getPropertyLeaseId() {
	return propertyLeaseId;
    }

    public void setPropertyLeaseId(PropertyLease propertyLeaseId) {
	this.propertyLeaseId = propertyLeaseId;
    }

    @Override
    public String toString() {
	return "LeaseExtension [id=" + id + ", propertyLeaseId="
		+ propertyLeaseId + ", extensionDays=" + extensionDays
		+ ", creationTime=" + creationTime + "]";
    }

}
