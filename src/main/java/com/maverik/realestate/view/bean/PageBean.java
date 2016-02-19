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
public class PageBean implements Serializable {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = -490935405172946923L;

    private Long id;

    private String pageName;

    private String description;

    private String shortName;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getPageName() {
	return pageName;
    }

    public void setPageName(String pageName) {
	this.pageName = pageName;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getShortName() {
	return shortName;
    }

    public void setShortName(String shortName) {
	this.shortName = shortName;
    }

    @Override
    public String toString() {
	return "PageBean [id=" + id + ", pageName=" + pageName + ", shortName="
		+ shortName + "]";
    }

    @Override
    public int hashCode() {
	return Objects.hash(this.id, this.pageName);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final PageBean other = (PageBean) obj;
	return Objects.equals(this.id, other.id)
		&& Objects.equals(this.pageName, other.pageName);
    }

}
