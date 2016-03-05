/**
 * 
 */
package com.maverik.realestate.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.Table;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
@Entity
@Table(name = "project_preconstructionType")
public class PreConstructionType implements Serializable {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 8894299694166392723L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false, insertable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "preConstructionId", nullable = false)
    private ProjectPreConstruction preConstruction;

    @Column(name = "type", nullable = false, length = 400)
    private String constructionDocumentType;

    @Column(name = "checked", nullable = true, length = 10)
    private String checked;

    @Column(name = "contactName")
    private String contactName;

    @OneToMany(mappedBy = "preConstructionTypeId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PreConstructionTypeDetails> typeDetails = new ArrayList<PreConstructionTypeDetails>();;

    public String getConstructionDocumentType() {
	return constructionDocumentType;
    }

    public void setConstructionDocumentType(String constructionDocumentType) {
	this.constructionDocumentType = constructionDocumentType;
    }

    public String getChecked() {
	return checked;
    }

    public void setChecked(String checked) {
	this.checked = checked;
    }

    public String getContactName() {
	return contactName;
    }

    public void setContactName(String contactName) {
	this.contactName = contactName;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public ProjectPreConstruction getPreConstruction() {
	return preConstruction;
    }

    public void setPreConstruction(ProjectPreConstruction preConstruction) {
	this.preConstruction = preConstruction;
    }

    public List<PreConstructionTypeDetails> getTypeDetails() {
	return typeDetails;
    }

    public void setTypeDetails(List<PreConstructionTypeDetails> typeDetails) {
	this.typeDetails = typeDetails;
    }

    @Override
    public int hashCode() {
	return Objects.hash(this.id, this.preConstruction);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final PreConstructionType other = (PreConstructionType) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.preConstruction, other.preConstruction);
    }

    @Override
    public String toString() {
	return "PreConstructionType [id=" + id + ", preConstruction="
		+ preConstruction + ", constructionDocumentType="
		+ constructionDocumentType + "]";
    }

}
