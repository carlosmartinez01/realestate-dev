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
public class PropertyContractBean implements Serializable {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 5956361473640470213L;

    private Long id;

    private Long propertyId;

    private String contractType;

    private String emDeposited;

    private String emDepositedDate;

    private String reCommitteeSignOff;

    private String reCommitteeSODate;

    private FileBean reCommitteeSOFileName;

    private String geoTechOrdered;

    private String geoTechOrderedCompany;

    private String escrowHolder;

    private String escrowHolderName;

    private String titleCommitment;

    private FileBean titleCommitmentFileName;

    private String titlePolicy;

    private FileBean titlePolicyFileName;

    private String settlement;

    private BigDecimal settlementQty;

    private FileBean settlementFileName;

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

    public String getEmDeposited() {
	return emDeposited;
    }

    public void setEmDeposited(String emDeposited) {
	this.emDeposited = emDeposited;
    }

    public String getEmDepositedDate() {
	return emDepositedDate;
    }

    public void setEmDepositedDate(String emDepositedDate) {
	this.emDepositedDate = emDepositedDate;
    }

    public String getReCommitteeSignOff() {
	return reCommitteeSignOff;
    }

    public void setReCommitteeSignOff(String reCommitteeSignOff) {
	this.reCommitteeSignOff = reCommitteeSignOff;
    }

    public String getReCommitteeSODate() {
	return reCommitteeSODate;
    }

    public void setReCommitteeSODate(String reCommitteeSODate) {
	this.reCommitteeSODate = reCommitteeSODate;
    }

    public FileBean getReCommitteeSOFileName() {
	return reCommitteeSOFileName;
    }

    public void setReCommitteeSOFileName(FileBean reCommitteeSOFileName) {
	this.reCommitteeSOFileName = reCommitteeSOFileName;
    }

    public String getGeoTechOrdered() {
	return geoTechOrdered;
    }

    public void setGeoTechOrdered(String geoTechOrdered) {
	this.geoTechOrdered = geoTechOrdered;
    }

    public String getEscrowHolder() {
	return escrowHolder;
    }

    public void setEscrowHolder(String escrowHolder) {
	this.escrowHolder = escrowHolder;
    }

    public String getTitleCommitment() {
	return titleCommitment;
    }

    public void setTitleCommitment(String titleCommitment) {
	this.titleCommitment = titleCommitment;
    }

    public FileBean getTitleCommitmentFileName() {
	return titleCommitmentFileName;
    }

    public void setTitleCommitmentFileName(FileBean titleCommitmentFileName) {
	this.titleCommitmentFileName = titleCommitmentFileName;
    }

    public String getTitlePolicy() {
	return titlePolicy;
    }

    public void setTitlePolicy(String titlePolicy) {
	this.titlePolicy = titlePolicy;
    }

    public FileBean getTitlePolicyFileName() {
	return titlePolicyFileName;
    }

    public void setTitlePolicyFileName(FileBean titlePolicyFileName) {
	this.titlePolicyFileName = titlePolicyFileName;
    }

    public FileBean getSettlementFileName() {
	return settlementFileName;
    }

    public void setSettlementFileName(FileBean settlementFileName) {
	this.settlementFileName = settlementFileName;
    }

    @Override
    public String toString() {
	return "PropertyContractBean [id=" + id + ", propertyId=" + propertyId
		+ ", emDeposited=" + emDeposited + ", emDepositedDate="
		+ emDepositedDate + ", reCommitteeSignOff="
		+ reCommitteeSignOff + ", reCommitteeSODate="
		+ reCommitteeSODate + ", reCommitteeSOFileName="
		+ reCommitteeSOFileName + "]";
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
	final PropertyContractBean other = (PropertyContractBean) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.propertyId, other.propertyId);
    }

    public String getContractType() {
	return contractType;
    }

    public void setContractType(String contractType) {
	this.contractType = contractType;
    }

    public String getGeoTechOrderedCompany() {
	return geoTechOrderedCompany;
    }

    public void setGeoTechOrderedCompany(String geoTechOrderedCompany) {
	this.geoTechOrderedCompany = geoTechOrderedCompany;
    }

    public String getEscrowHolderName() {
	return escrowHolderName;
    }

    public void setEscrowHolderName(String escrowHolderName) {
	this.escrowHolderName = escrowHolderName;
    }

    public String getSettlement() {
	return settlement;
    }

    public void setSettlement(String settlement) {
	this.settlement = settlement;
    }

    public BigDecimal getSettlementQty() {
	return settlementQty;
    }

    public void setSettlementQty(BigDecimal settlementQty) {
	this.settlementQty = settlementQty;
    }

}
