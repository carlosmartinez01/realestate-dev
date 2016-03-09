/**
 * 
 */
package com.maverik.realestate.view.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
public class PurchaseExtensionBean implements Serializable {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = -7039888250580136584L;

    private Long id;

    private Long propertyPurchaseId;

    private Integer extensionDays;

    private Date creationTime;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Long getPropertyPurchaseId() {
	return propertyPurchaseId;
    }

    public void setPropertyPurchaseId(Long propertyPurchaseId) {
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
    public int hashCode() {
	return Objects.hash(this.id, this.propertyPurchaseId);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final PurchaseExtensionBean other = (PurchaseExtensionBean) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.propertyPurchaseId,
			other.propertyPurchaseId);
    }

    @Override
    public String toString() {
	return "PurchaseExtensionBean [id=" + id + ", propertyPurchaseId="
		+ propertyPurchaseId + ", extensionDays=" + extensionDays
		+ ", creationTime=" + creationTime + "]";
    }

}
