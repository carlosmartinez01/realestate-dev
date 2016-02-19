/**
 * 
 */
package com.maverik.realestate.view.bean;

import java.io.Serializable;
import java.util.Objects;

import com.maverik.realestate.domain.entity.Filename;

/**
 * @author jorge
 *
 */
public class PreConstructionDetailsBean implements Serializable {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 21135708338031571L;

    private Long id;

    private Long preConstructionId;

    private String dateReceived;

    private Filename filename;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Long getPreConstructionId() {
	return preConstructionId;
    }

    public void setPreConstructionId(Long preConstructionId) {
	this.preConstructionId = preConstructionId;
    }

    public String getDateReceived() {
	return dateReceived;
    }

    public void setDateReceived(String dateReceived) {
	this.dateReceived = dateReceived;
    }

    public Filename getFilename() {
	return filename;
    }

    public void setFilename(Filename filename) {
	this.filename = filename;
    }

    @Override
    public int hashCode() {
	return Objects.hash(this.id, this.preConstructionId);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final PreConstructionDetailsBean other = (PreConstructionDetailsBean) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.preConstructionId,
			other.preConstructionId);
    }

    @Override
    public String toString() {
	return "PreConstructionDetailsBean [id=" + id + ", preConstructionId="
		+ preConstructionId + "]";
    }

}
