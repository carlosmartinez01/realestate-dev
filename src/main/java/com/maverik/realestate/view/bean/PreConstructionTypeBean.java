/**
 * 
 */
package com.maverik.realestate.view.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
public class PreConstructionTypeBean implements Serializable {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 21135708338031571L;

    private Long id;

    private ProjectPreConstructionBean preConstruction;

    private String constructionDocumentType;

    private String checked;

    private String contactName;

    @Valid
    private List<PreConstructionTypeDetailsBean> typeDetails;

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
	final PreConstructionTypeBean other = (PreConstructionTypeBean) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.preConstruction, other.preConstruction);
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public ProjectPreConstructionBean getPreConstruction() {
	return preConstruction;
    }

    public void setPreConstruction(ProjectPreConstructionBean preConstruction) {
	this.preConstruction = preConstruction;
    }

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

    @Override
    public String toString() {
	return "PreConstructionTypeBean [id=" + id + ", preConstruction="
		+ preConstruction + ", constructionDocumentType="
		+ constructionDocumentType + "]";
    }

    public List<PreConstructionTypeDetailsBean> getTypeDetails() {
	return typeDetails;
    }

    public void setTypeDetails(List<PreConstructionTypeDetailsBean> typeDetails) {
	this.typeDetails = typeDetails;
    }

}
