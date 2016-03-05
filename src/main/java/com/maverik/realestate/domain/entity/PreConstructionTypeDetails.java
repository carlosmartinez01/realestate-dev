/**
 * 
 */
package com.maverik.realestate.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Objects;

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
import javax.persistence.Transient;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
@Entity
@Table(name = "project_preconstructionType_details")
public class PreConstructionTypeDetails implements Serializable {
    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1019246995496893651L;

    @Transient
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false, insertable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "preConstructionTypeId", nullable = false)
    private PreConstructionType preConstructionTypeId;

    @Column(name = "dateReceived")
    private Date dateReceived;

    @Transient
    private String dateReceivedAsString;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fileName_id")
    private Filename filename;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Date getDateReceived() {
	return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
	this.dateReceived = dateReceived;
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
	final PreConstructionTypeDetails other = (PreConstructionTypeDetails) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.preConstructionTypeId,
			other.preConstructionTypeId);
    }

    public Filename getFilename() {
	return filename;
    }

    public void setFilename(Filename filename) {
	this.filename = filename;
    }

    public PreConstructionType getPreConstructionTypeId() {
	return preConstructionTypeId;
    }

    public void setPreConstructionTypeId(
	    PreConstructionType preConstructionTypeId) {
	this.preConstructionTypeId = preConstructionTypeId;
    }

    public String getDateReceivedAsString() {
	return dateReceivedAsString;
    }

    public void setDateReceivedAsString(String dateReceivedAsString)
	    throws DateTimeParseException {
	this.dateReceivedAsString = dateReceivedAsString;
	if (dateReceivedAsString != null) {
	    LocalDate date = LocalDate.parse(dateReceivedAsString, dtf);
	    this.dateReceived = new Date(date.toEpochDay());
	}
    }

    @Override
    public String toString() {
	return "PreConstructionTypeDetails [id=" + id
		+ ", preConstructionTypeId=" + preConstructionTypeId + "]";
    }
}
