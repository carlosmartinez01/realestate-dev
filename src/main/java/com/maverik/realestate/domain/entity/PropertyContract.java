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
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
@Entity
@Table(name = "property_contract")
@SecondaryTables({
	@SecondaryTable(name = "property_task_emd", pkJoinColumns = { @PrimaryKeyJoinColumn(name = "propertyContractId") }),
	@SecondaryTable(name = "property_task_recso", pkJoinColumns = { @PrimaryKeyJoinColumn(name = "propertyContractId") }),
	@SecondaryTable(name = "property_task_geotech", pkJoinColumns = { @PrimaryKeyJoinColumn(name = "propertyContractId") }),
	@SecondaryTable(name = "property_task_escrow", pkJoinColumns = { @PrimaryKeyJoinColumn(name = "propertyContractId") }),
	@SecondaryTable(name = "property_task_commitment", pkJoinColumns = { @PrimaryKeyJoinColumn(name = "propertyContractId") }),
	@SecondaryTable(name = "property_task_policy", pkJoinColumns = { @PrimaryKeyJoinColumn(name = "propertyContractId") }),
	@SecondaryTable(name = "property_task_settlement", pkJoinColumns = { @PrimaryKeyJoinColumn(name = "propertyContractId") }) })
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "property")
public class PropertyContract implements Serializable {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 7151856749391202463L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "propertyContractId", nullable = false, updatable = false, insertable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "propertyId", nullable = true)
    private Property propertyId;

    @Column(name = "CONTRACTTYPE", nullable = true)
    private String contractType;

    @Column(name = "EMDeposited", nullable = true, length = 20, table = "property_task_emd")
    private String emDeposited;

    @Column(name = "EMDepositedDate", nullable = true, table = "property_task_emd")
    private Date emDepositedDate;

    @Column(name = "RECommitteeSignOff", nullable = true, length = 20, table = "property_task_recso")
    private String reCommitteeSignOff;

    @Column(name = "RECommitteeSODate", nullable = true, table = "property_task_recso")
    private Date reCommitteeSODate;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "RECommitteeSOFileName_id", table = "property_task_recso")
    private Filename reCommitteeSOFileName;

    @Column(name = "geoTechOrdered", nullable = true, length = 20, table = "property_task_geotech")
    private String geoTechOrdered;

    @Column(name = "geoTechOrderedCompany", nullable = true, length = 4000, table = "property_task_geotech")
    private String geoTechOrderedCompany;

    @Column(name = "escrowHolder", nullable = true, length = 20, table = "property_task_escrow")
    private String escrowHolder;

    @Column(name = "escrowHolderName", nullable = true, length = 4000, table = "property_task_escrow")
    private String escrowHolderName;

    @Column(name = "titleCommitment", nullable = true, length = 20, table = "property_task_commitment")
    private String titleCommitment;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "titleCommitmentFileName_id", table = "property_task_commitment")
    private Filename titleCommitmentFileName;

    @Column(name = "titlePolicy", nullable = true, length = 20, table = "property_task_policy")
    private String titlePolicy;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "titlePolicyFileName_id", table = "property_task_policy")
    private Filename titlePolicyFileName;

    @Column(name = "settlement", nullable = true, table = "property_task_settlement")
    private String settlement;

    @Column(name = "settlementQty", nullable = true, table = "property_task_settlement")
    private BigDecimal settlementQty;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "SettlementFileName_id", table = "property_task_settlement")
    private Filename settlementFileName;

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

    public String getEmDeposited() {
	return emDeposited;
    }

    public void setEmDeposited(String emDeposited) {
	this.emDeposited = emDeposited;
    }

    public Date getEmDepositedDate() {
	return emDepositedDate;
    }

    public void setEmDepositedDate(Date emDepositedDate) {
	this.emDepositedDate = emDepositedDate;
    }

    public String getReCommitteeSignOff() {
	return reCommitteeSignOff;
    }

    public void setReCommitteeSignOff(String reCommitteeSignOff) {
	this.reCommitteeSignOff = reCommitteeSignOff;
    }

    public Date getReCommitteeSODate() {
	return reCommitteeSODate;
    }

    public void setReCommitteeSODate(Date reCommitteeSODate) {
	this.reCommitteeSODate = reCommitteeSODate;
    }

    public Filename getReCommitteeSOFileName() {
	return reCommitteeSOFileName;
    }

    public void setReCommitteeSOFileName(Filename reCommitteeSOFileName) {
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

    public Filename getTitleCommitmentFileName() {
	return titleCommitmentFileName;
    }

    public void setTitleCommitmentFileName(Filename titleCommitmentFileName) {
	this.titleCommitmentFileName = titleCommitmentFileName;
    }

    public String getTitlePolicy() {
	return titlePolicy;
    }

    public void setTitlePolicy(String titlePolicy) {
	this.titlePolicy = titlePolicy;
    }

    public Filename getTitlePolicyFileName() {
	return titlePolicyFileName;
    }

    public void setTitlePolicyFileName(Filename titlePolicyFileName) {
	this.titlePolicyFileName = titlePolicyFileName;
    }

    public Filename getSettlementFileName() {
	return settlementFileName;
    }

    public void setSettlementFileName(Filename settlementFileName) {
	this.settlementFileName = settlementFileName;
    }

    @Override
    public String toString() {
	return "PropertyContract [id=" + id + ", propertyId=" + propertyId
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
	final PropertyContract other = (PropertyContract) obj;
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
