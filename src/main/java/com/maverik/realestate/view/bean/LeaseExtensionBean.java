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
public class LeaseExtensionBean implements Serializable {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 7476823087820695401L;

    private Long id;

    private Long propertyLeaseId;

    private Integer extensionDays;

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
	final LeaseExtensionBean other = (LeaseExtensionBean) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.propertyLeaseId, other.propertyLeaseId);
    }

    public Long getPropertyLeaseId() {
	return propertyLeaseId;
    }

    public void setPropertyLeaseId(Long propertyLeaseId) {
	this.propertyLeaseId = propertyLeaseId;
    }

    @Override
    public String toString() {
	return "LeaseExtensionBean [id=" + id + ", propertyLeaseId="
		+ propertyLeaseId + ", extensionDays=" + extensionDays
		+ ", creationTime=" + creationTime + "]";
    }

}
