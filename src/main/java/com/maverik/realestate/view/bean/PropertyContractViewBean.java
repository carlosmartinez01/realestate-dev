/**
 * 
 */
package com.maverik.realestate.view.bean;

import java.io.Serializable;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

/**
 * @author jorge
 *
 */
@SonarClassExclusion
public class PropertyContractViewBean implements Serializable {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 586427795733535148L;

    public PropertyContractViewBean() {

    }

    private PropertyBean property;

    private PropertyContractBean propertyContract;

    public PropertyBean getProperty() {
	return property;
    }

    public void setProperty(PropertyBean property) {
	this.property = property;
    }

    public PropertyContractBean getPropertyContract() {
	return propertyContract;
    }

    public void setPropertyContract(PropertyContractBean propertyContract) {
	this.propertyContract = propertyContract;
    }

    /**
     * @param property
     * @param propertyContract
     */
    public PropertyContractViewBean(PropertyBean property,
	    PropertyContractBean propertyContract) {
	super();
	this.property = property;
	this.propertyContract = propertyContract;
    }

    @Override
    public String toString() {
	return "PropertyContractViewBean [property=" + property
		+ ", propertyContract=" + propertyContract + "]";
    }

}
