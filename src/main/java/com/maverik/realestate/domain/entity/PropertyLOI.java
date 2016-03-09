/**
 * 
 */
package com.maverik.realestate.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.OneToOne;
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
@Table(name = "property_loi")
public class PropertyLOI implements Serializable {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = -6847042623518801594L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROPERTYLOIID", nullable = false, updatable = false, insertable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "PROPERTYID", nullable = true)
    private Property propertyId;

    @Column(name = "PRICE", nullable = false, length = 256)
    private BigDecimal price;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "filename_id")
    private Filename fileId;

    @Column(name = "ACCEPTED", nullable = false, length = 256)
    private Byte accepted;

    @Column(name = "CREATIONTIME", nullable = false, updatable = false, insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;

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

    public BigDecimal getPrice() {
	return price;
    }

    public void setPrice(BigDecimal price) {
	this.price = price;
    }

    public Byte getAccepted() {
	return accepted;
    }

    public void setAccepted(Byte accepted) {
	this.accepted = accepted;
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
	final PropertyLOI other = (PropertyLOI) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.propertyId, other.propertyId);
    }

    public Date getCreationTime() {
	return creationTime;
    }

    public void setCreationTime(Date creationTime) {
	this.creationTime = creationTime;
    }

    @Override
    public String toString() {
	return "PropertyLOI [id=" + id + ", propertyId=" + propertyId
		+ ", price=" + price + ", fileId=" + fileId + ", accepted="
		+ accepted + ", creationTime=" + creationTime + "]";
    }

    public Filename getFileId() {
	return fileId;
    }

    public void setFileId(Filename fileId) {
	this.fileId = fileId;
    }

}
