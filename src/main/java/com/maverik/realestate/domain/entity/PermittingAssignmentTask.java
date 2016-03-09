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
@Table(name = "permitting_assignment_task")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "property")
public class PermittingAssignmentTask implements Serializable {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 4560962230135570373L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, insertable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "propertyId", nullable = true)
    private Property propertyId;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "dueDate", nullable = false)
    private Date dueDate;

    @Column(name = "assignedBy", nullable = false)
    private String assignedBy;

    @Column(name = "assignedTo", nullable = false)
    private String assignedTo;

    @Column(name = "isComplete", nullable = false)
    private Byte isComplete;

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

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public Date getDueDate() {
	return dueDate;
    }

    public void setDueDate(Date dueDate) {
	this.dueDate = dueDate;
    }

    public String getAssignedBy() {
	return assignedBy;
    }

    public void setAssignedBy(String assignedBy) {
	this.assignedBy = assignedBy;
    }

    public String getAssignedTo() {
	return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
	this.assignedTo = assignedTo;
    }

    public Byte getIsComplete() {
	return isComplete;
    }

    public void setIsComplete(Byte isComplete) {
	this.isComplete = isComplete;
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
	final PermittingAssignmentTask other = (PermittingAssignmentTask) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.propertyId, other.propertyId);
    }

    @Override
    public String toString() {
	return "PermittingAssignmentTask [id=" + id + ", propertyId="
		+ propertyId + ", description=" + description + "]";
    }
}
