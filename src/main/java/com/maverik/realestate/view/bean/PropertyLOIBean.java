/**
 * 
 */
package com.maverik.realestate.view.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
public class PropertyLOIBean implements Serializable {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = -8738121350460335189L;

    private Long id;

    private Long propertyId;

    private BigDecimal price;

    private FileBean fileId;

    private Byte accepted;

    private Date creationTime;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
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
	final PropertyLOIBean other = (PropertyLOIBean) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.propertyId, other.propertyId);
    }

    public Date getCreationTime() {
	return creationTime;
    }

    public void setCreationTime(Date creationTime) {
	this.creationTime = creationTime;
    }

    public Long getPropertyId() {
	return propertyId;
    }

    public void setPropertyId(Long propertyId) {
	this.propertyId = propertyId;
    }

    @Override
    public String toString() {
	return "PropertyLOIBean [id=" + id + ", propertyId=" + propertyId
		+ ", price=" + price + ", fileId=" + fileId + ", accepted="
		+ accepted + ", creationTime=" + creationTime + "]";
    }

    public FileBean getFileId() {
	return fileId;
    }

    public void setFileId(FileBean fileId) {
	this.fileId = fileId;
    }
}
