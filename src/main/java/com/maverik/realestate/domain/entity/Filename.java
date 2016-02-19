/**
 * 
 */
package com.maverik.realestate.domain.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
@Entity
@Table(name = "files")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "property")
public class Filename implements Serializable {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = -4228596940573006953L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "files_id", nullable = false, updatable = false, insertable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 4000)
    private String name;

    @Column(name = "absolutePath", nullable = false, length = 4000)
    private String absolutePath;

    @Column(name = "CREATIONTIME", nullable = false, updatable = false, insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Date getCreationTime() {
	return creationTime;
    }

    public void setCreationTime(Date creationTime) {
	this.creationTime = creationTime;
    }

    @Override
    public String toString() {
	return "Filename [id=" + id + ", name=" + name + "]";
    }

    @Override
    public int hashCode() {
	return Objects.hash(this.id, this.name);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final Filename other = (Filename) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.name, other.name);
    }

    public String getAbsolutePath() {
	return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
	this.absolutePath = absolutePath;
    }
}
