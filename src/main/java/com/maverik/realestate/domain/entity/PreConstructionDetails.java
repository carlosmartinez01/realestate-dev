/**
 * 
 */
package com.maverik.realestate.domain.entity;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
@Entity
@Table(name = "project_preconstruction_details")
public class PreConstructionDetails implements Serializable {
    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1019246995496893651L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false, insertable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "preConstructionId", nullable = false)
    private ProjectPreConstruction preConstructionId;

    @Column(name = "dateReceived")
    private Date dateReceived;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fileName_id")
    private Filename filename;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public ProjectPreConstruction getPreConstructionId() {
	return preConstructionId;
    }

    public void setPreConstructionId(ProjectPreConstruction preConstructionId) {
	this.preConstructionId = preConstructionId;
    }

    public Date getDateReceived() {
	return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
	this.dateReceived = dateReceived;
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
	final PreConstructionDetails other = (PreConstructionDetails) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.preConstructionId,
			other.preConstructionId);
    }

    public Filename getFilename() {
	return filename;
    }

    public void setFilename(Filename filename) {
	this.filename = filename;
    }
}
