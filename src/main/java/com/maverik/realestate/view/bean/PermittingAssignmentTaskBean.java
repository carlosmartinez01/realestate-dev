/**
 * 
 */
package com.maverik.realestate.view.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author jorge
 *
 */
public class PermittingAssignmentTaskBean implements Serializable {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 8643137351090411884L;

    private Long id;

    private Long propertyId;

    private String description;

    private String dueDate;

    private String assignedBy;

    @NotEmpty(message = "Assigned To must not be empty")
    private String assignedTo;

    private Byte isComplete;

    private List<String> users;

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

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getDueDate() {
	return dueDate;
    }

    public void setDueDate(String dueDate) {
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
	final PermittingAssignmentTaskBean other = (PermittingAssignmentTaskBean) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.propertyId, other.propertyId);
    }

    @Override
    public String toString() {
	return "PermittingAssignmentTaskBean [id=" + id + ", propertyId="
		+ propertyId + ", description=" + description + "]";
    }

    public List<String> getUsers() {
	return users;
    }

    public void setUsers(List<String> users) {
	this.users = users;
    }
}
