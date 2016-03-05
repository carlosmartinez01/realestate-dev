/**
 * 
 */
package com.maverik.realestate.view.bean;

import java.io.Serializable;
import java.util.Objects;

import org.hibernate.validator.constraints.NotEmpty;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;
import com.maverik.realestate.domain.entity.PreConstructionType;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
public class PreConstructionTypeDetailsBean implements Serializable {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = -3387586276651852567L;

    private Long id;

    private PreConstructionType preConstructionTypeId;

    @NotEmpty(message = "Date is mandatory field")
    private String dateReceived;

    private FileBean filename;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public PreConstructionType getPreConstructionTypeId() {
	return preConstructionTypeId;
    }

    public void setPreConstructionTypeId(
	    PreConstructionType preConstructionTypeId) {
	this.preConstructionTypeId = preConstructionTypeId;
    }

    public String getDateReceived() {
	return dateReceived;
    }

    public void setDateReceived(String dateReceived) {
	this.dateReceived = dateReceived;
    }

    public FileBean getFilename() {
	return filename;
    }

    public void setFilename(FileBean filename) {
	this.filename = filename;
    }

    @Override
    public String toString() {
	return "PreConstructionTypeDetailsBean [id=" + id
		+ ", preConstructionTypeId=" + preConstructionTypeId
		+ ", dateReceived=" + dateReceived + ", filename=" + filename
		+ "]";
    }

    @Override
    public int hashCode() {
	return Objects.hash(this.id, this.preConstructionTypeId);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final PreConstructionTypeDetailsBean other = (PreConstructionTypeDetailsBean) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.preConstructionTypeId,
			other.preConstructionTypeId);
    }

}
