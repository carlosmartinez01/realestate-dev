/**
 * 
 */
package com.maverik.realestate.domain.entity;

import java.io.Serializable;
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
@Table(name = "permitting_contacts")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "property")
public class PermittingContact implements Serializable {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 4155621516856038072L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, insertable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "propertyId", nullable = true)
    private Property propertyId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "comment", nullable = false)
    private String comment;

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

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getComment() {
	return comment;
    }

    public void setComment(String comment) {
	this.comment = comment;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final PermittingContact other = (PermittingContact) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.propertyId, other.propertyId);
    }

    @Override
    public int hashCode() {
	return Objects.hash(this.id, this.propertyId);
    }

    @Override
    public String toString() {
	return "PermittingContact [id=" + id + ", propertyId=" + propertyId
		+ ", title=" + title + "]";
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

}
