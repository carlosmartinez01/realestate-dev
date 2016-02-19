/**
 * 
 */
package com.maverik.realestate.view.bean;

import java.io.Serializable;
import java.util.Objects;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
public class FileBean implements Serializable {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = -8224440444018475109L;

    private Long id;

    private String name;

    private String absolutePath;

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

    @Override
    public String toString() {
	return "FilenameBean [id=" + id + ", name=" + name + "]";
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
	final FileBean other = (FileBean) obj;
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
