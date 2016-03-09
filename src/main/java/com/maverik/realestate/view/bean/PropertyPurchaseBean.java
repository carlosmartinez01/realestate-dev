/**
 * 
 */
package com.maverik.realestate.view.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
public class PropertyPurchaseBean implements Serializable {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 4295139184117803528L;

    private Long id;

    private Long propertyId;

    private FileBean fileId;

    private Byte accepted;

    private String creationTime;

    private String effectiveDate;

    private BigDecimal price;

    private Integer dueDiligenceTerm;

    private String deadLineDate;

    private Integer closingTerm;

    private String closingDate;

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

    public String getEffectiveDate() {
	return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
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

    public String getDeadLineDate() {
	return deadLineDate;
    }

    public void setDeadLineDate(String deadLineDate) {
	this.deadLineDate = deadLineDate;
    }

    public Integer getClosingTerm() {
	return closingTerm;
    }

    public void setClosingTerm(Integer closingTerm) {
	this.closingTerm = closingTerm;
    }

    public String getClosingDate() {
	return closingDate;
    }

    public void setClosingDate(String closingDate) {
	this.closingDate = closingDate;
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
	final PropertyPurchaseBean other = (PropertyPurchaseBean) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.propertyId, other.propertyId);
    }

    @Override
    public String toString() {
	return "PropertyPurchaseBean [id=" + id + ", propertyId=" + propertyId
		+ ", fileId=" + fileId + ", accepted=" + accepted
		+ ", creationTime=" + creationTime + "]";
    }

    public FileBean getFileId() {
	return fileId;
    }

    public void setFileId(FileBean fileId) {
	this.fileId = fileId;
    }

}
